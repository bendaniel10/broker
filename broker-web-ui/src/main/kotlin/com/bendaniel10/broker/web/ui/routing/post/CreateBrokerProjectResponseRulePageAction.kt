package com.bendaniel10.broker.web.ui.routing.post

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectResponseRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect

class CreateBrokerProjectResponseRulePageAction(
    private val createBrokerProjectResponseRuleUseCase: CreateBrokerProjectResponseRuleUseCase,
    private val viewBrokerProjectResponseRuleUseCase: ViewBrokerProjectResponseRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Post, "/create_broker_project_rule") {

    override suspend fun intercept(call: ApplicationCall) {
        val params = call.receiveParameters()

        createBrokerProjectResponseRuleUseCase.createBrokerProjectResponseRule(
            CreateBrokerProjectResponseRuleModel(
                requireNotNull(params["brokerProjectToken"]),
                requireNotNull(params["urlTrigger"]),
                requireNotNull(params["responseBody"]),
                requireNotNull(params["headers"]),
                requireNotNull(params["httpResponseCode"]).toInt()
            )
        )

        call.respondRedirect(
            viewBrokerProjectResponseRuleUseCase.viewPathUrl(
                mapOf("brokerProjectToken" to requireNotNull(params["brokerProjectToken"]))
            )
        )
    }
}