package com.bendaniel10.broker.rewrite.response.impl

import com.bendaniel10.broker.rewrite.response.BrokerResponseRewriter
import com.bendaniel10.broker.storage.model.BrokerProject
import com.bendaniel10.broker.storage.model.BrokerProjectRuleResponse
import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import io.ktor.application.ApplicationCall
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.http.content.OutgoingContent
import io.ktor.request.header
import io.ktor.request.httpMethod
import io.ktor.request.port
import io.ktor.request.uri
import io.ktor.response.respond
import io.ktor.util.filter
import io.ktor.util.pipeline.PipelineContext
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.copyAndClose
import kotlinx.coroutines.withContext
import java.net.URI
import java.net.URL
import kotlin.coroutines.coroutineContext

internal class BrokerResponseRewriterImpl(
    private val projectRepository: BrokerProjectRepository,
    private val projectRuleRepository: BrokerProjectRuleRepository,
    private val projectRuleResponseRepository: BrokerProjectRuleResponseRepository
) : BrokerResponseRewriter {

    override suspend fun rewrite(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        val context = pipelineContext.context
        val brokerProjectToken = context.request.header("broker_project_token")
        if (brokerProjectToken != null) {
            interceptRequestAndMockResponse(
                context,
                brokerProjectToken
            )
        }
    }

    private suspend fun interceptRequestAndMockResponse(
        context: ApplicationCall,
        brokerProjectToken: String
    ) {
        val brokerProject: BrokerProject? = projectRepository.getByToken(brokerProjectToken)
            .firstOrDefault()

        if (brokerProject == null) {
            context.respond(HttpStatusCode.NotFound)
            return
        }

        val requestUri = URI(
            "${context.request.local.scheme}://${context.request.local.host}:${context.request.port()}${context.request.uri}"
        )

        if (!brokerProject.enabled) {
            passThroughRequestAndReturnResponse(context, brokerProject, requestUri)
            return
        }

        val requestUriPath = requestUri.path.replaceFirst("/", "")
        val brokerProjectRuleCursor = projectRuleRepository.getBrokerProjectRuleByUrlAndProjectId(
            requireNotNull(brokerProject.id).idValue,
            requestUriPath
        )
        if (brokerProjectRuleCursor.size() > 0) {
            val brokerProjectRule = brokerProjectRuleCursor.last()
            val brokerProjectRuleResponse =
                projectRuleResponseRepository.getBrokerProjectRuleResponseByBrokerProjectRuleId(
                    requireNotNull(brokerProjectRule.id).idValue,
                    0,
                    Int.MAX_VALUE
                ).firstOrDefault()
            if (brokerProjectRuleResponse == null || !brokerProjectRuleResponse.enabled) {
                passThroughRequestAndReturnResponse(context, brokerProject, requestUri)
            } else {
                println("Rewriting response for: $requestUriPath.")
                mockResponse(brokerProjectRuleResponse, context)
            }
        } else {
            passThroughRequestAndReturnResponse(context, brokerProject, requestUri)
        }
    }

    private suspend fun mockResponse(
        brokerProjectRuleResponse: BrokerProjectRuleResponse,
        context: ApplicationCall
    ) {
        val headersBuilder = HeadersBuilder()
        val responseHeaders = brokerProjectRuleResponse.headers.split("\\r?\\n")
            .forEach {
                val headerParts = it.split(":")
                if (headerParts.size == 2) {
                    headersBuilder.append(headerParts[0], headerParts[1])
                }
            }.run { headersBuilder.build() }

        val responseContentType = responseHeaders["Content-Type"]
            ?.run { ContentType.parse(this) } ?: ContentType.Application.Json

        val responseStatusCode = HttpStatusCode.fromValue(brokerProjectRuleResponse.httpResponseCode)
        val responseBody = brokerProjectRuleResponse.body

        context.respond(object : OutgoingContent.ByteArrayContent() {
            val bytes by lazy(LazyThreadSafetyMode.NONE) {
                responseBody.toByteArray(
                    responseContentType.charset() ?: Charsets.UTF_8
                )
            }
            override val status = responseStatusCode
            override val contentLength = bytes.size.toLong()
            override val contentType = responseContentType
            override fun bytes(): ByteArray = bytes
            override val headers: Headers = Headers.build {
                appendAll(responseHeaders.filter { key, _ ->
                    !key.equals(
                        HttpHeaders.ContentType,
                        ignoreCase = true
                    ) && !key.equals(HttpHeaders.ContentLength, ignoreCase = true)
                })
            }
        })
    }

    private suspend fun passThroughRequestAndReturnResponse(
        context: ApplicationCall,
        brokerProject: BrokerProject,
        requestUri: URI
    ) {
        println("Skipping rewrite for $requestUri")
        val client = HttpClient()
        val recreatedOriginalRequest = buildOriginalRequest(context, brokerProject, requestUri)
        withContext(coroutineContext) {
            val originalResponse = client.request<HttpResponse>(recreatedOriginalRequest)
            context.respond(object : OutgoingContent.WriteChannelContent() {
                val originalHeaders = originalResponse.headers
                override val contentLength: Long? = originalHeaders[HttpHeaders.ContentLength]?.toLong()
                override val contentType: ContentType? =
                    originalHeaders[HttpHeaders.ContentType]?.let { ContentType.parse(it) }
                override val headers: Headers = Headers.build {
                    appendAll(originalHeaders.filter { key, _ ->
                        !arrayOf(
                            HttpHeaders.ContentType,
                            HttpHeaders.ContentLength,
                            HttpHeaders.TransferEncoding
                        ).contains(key)
                    })
                }
                override val status: HttpStatusCode? = originalResponse.status

                override suspend fun writeTo(channel: ByteWriteChannel) {
                    originalResponse.content.copyAndClose(channel)
                }
            })
        }
    }

    private fun buildOriginalRequest(
        context: ApplicationCall,
        brokerProject: BrokerProject,
        requestUri: URI
    ) =
        HttpRequestBuilder().apply {
            method = context.request.httpMethod
            url {
                takeFrom(requestUri)
                val originalUri = URL(brokerProject.originalUrl)
                host = originalUri.host
                protocol = URLProtocol.createOrDefault(originalUri.protocol)
                port = originalUri.port.takeIf { it > 0 } ?: DEFAULT_PORT
            }
            headers {
                this.appendAll(context.request.headers)
            }
            body = object : OutgoingContent.ReadChannelContent() {
                override fun readFrom(): ByteReadChannel {
                    return context.request.receiveChannel()
                }
            }
        }
}