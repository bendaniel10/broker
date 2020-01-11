package com.bendaniel10.broker.rewrite.response.impl

import com.bendaniel10.broker.rewrite.response.BrokerResponseRewriter
import com.bendaniel10.broker.storage.model.BrokerProject
import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import io.ktor.application.ApplicationCall
import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.http.*
import io.ktor.http.content.OutgoingContent
import io.ktor.request.header
import io.ktor.request.httpMethod
import io.ktor.request.port
import io.ktor.request.uri
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.util.filter
import io.ktor.util.pipeline.PipelineContext
import kotlinx.coroutines.io.ByteWriteChannel
import kotlinx.coroutines.io.copyAndClose
import mu.KotlinLogging
import java.net.URI

internal class BrokerResponseRewriterImpl(
    private val projectRepository: BrokerProjectRepository,
    private val projectRuleRepository: BrokerProjectRuleRepository,
    private val projectRuleResponseRepository: BrokerProjectRuleResponseRepository
) : BrokerResponseRewriter {

    private val logger = KotlinLogging.logger {}

    override suspend fun rewrite(pipelineContext: PipelineContext<Unit, ApplicationCall>) {
        val context = pipelineContext.context
        val brokerProjectToken = context.request.header("broker_project_token")
        if (brokerProjectToken == null) {
            context.respond(HttpStatusCode.BadRequest)
        } else {
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
            context.respond(HttpStatusCode.BadRequest)
            return
        }
        val requestUri = URI(
            "${context.request.local.scheme}://${context.request.local.host}:${context.request.port()}${context.request.uri}"
        )
        val requestUriPath = requestUri.path.replaceFirst("/", "")
        val brokerProjectRuleCursor = projectRuleRepository.getBrokerProjectRuleByUrlAndProjectId(
            requireNotNull(brokerProject.id).idValue,
            requestUriPath
        )
        if (brokerProjectRuleCursor.size() > 0) {
            val brokerProjectRule = brokerProjectRuleCursor.first()
            val brokerProjectRuleResponse =
                projectRuleResponseRepository.getBrokerProjectRuleResponseByBrokerProjectRuleId(
                    requireNotNull(brokerProjectRule.id).idValue,
                    0,
                    Int.MAX_VALUE
                ).first()
            println( "Rewriting response for: $requestUriPath. Response: ${brokerProjectRuleResponse.body}" )
            context.respondText(brokerProjectRuleResponse.body, ContentType.Application.Json, HttpStatusCode.OK)
        } else {
            passThroughRequestAndReturnResponse(context, brokerProject, requestUri)
        }
    }

    private suspend fun passThroughRequestAndReturnResponse(
        context: ApplicationCall,
        brokerProject: BrokerProject,
        requestUri: URI
    ) {
        println( "Pass through for $requestUri" )
        val client = HttpClient()
        val recreatedOriginalRequest = buildOriginalRequest(context, brokerProject, requestUri)
        print("foo ${recreatedOriginalRequest.url.host}")
        val originalResponse = client.call(recreatedOriginalRequest)
        context.respond(object : OutgoingContent.WriteChannelContent() {
            val originalHeaders = originalResponse.response.headers
            override val contentLength: Long? = originalHeaders[HttpHeaders.ContentLength]?.toLong()
            override val contentType: ContentType? =
                originalHeaders[HttpHeaders.ContentType]?.let { ContentType.parse(it) }
            override val headers: Headers = Headers.build {
                appendAll(originalHeaders.filter { key, _ ->
                    !key.equals(
                        HttpHeaders.ContentType,
                        ignoreCase = true
                    ) && !key.equals(HttpHeaders.ContentLength, ignoreCase = true)
                })
            }
            override val status: HttpStatusCode? = originalResponse.response.status
            override suspend fun writeTo(channel: ByteWriteChannel) {
                originalResponse.response.content.copyAndClose(channel)
            }
        })
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
                host = brokerProject.originalUrl
            }
            headers {
                this.appendAll(context.request.headers)
            }
            body = context.request.receiveChannel()
        }.also {
            print("recreated url: ${it.url}")
        }
}