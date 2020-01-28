package com.bendaniel10.broker.web.ui.routing.post

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.model.DeleteBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.DeleteBrokerProjectRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect

class DeleteBrokerProjectResponseRuleAction(
    private val deleteBrokerProjectRuleUseCase: DeleteBrokerProjectRuleUseCase,
    private val viewBrokerProjectResponseRuleUseCase: ViewBrokerProjectResponseRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Post, "delete_broker_project_response_rule") {

    override suspend fun intercept(call: ApplicationCall) {
        val params = call.receiveParameters()

        deleteBrokerProjectRuleUseCase.deleteBrokerProjectResponseRule(
            DeleteBrokerProjectResponseRuleModel(
                requireNotNull(params["brokerProjectRuleId"])
            )
        )

        call.respondRedirect(
            viewBrokerProjectResponseRuleUseCase.viewPathUrl(
                mapOf("brokerProjectToken" to requireNotNull(params["brokerProjectToken"]))
            )
        )
    }
}