package com.bendaniel10.broker.web.ui.routing.post

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.model.EditBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectResponseRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect

class EditBrokerProjectResponseRulePageAction(
    private val editBrokerProjectResponseRuleUseCase: EditBrokerProjectResponseRuleUseCase,
    private val viewBrokerProjectResponseRuleUseCase: ViewBrokerProjectResponseRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Post, "/edit_broker_project_response_rule") {

    override suspend fun intercept(call: ApplicationCall) {
        val params = call.receiveParameters()

        editBrokerProjectResponseRuleUseCase.editBrokerProjectResponseRule(
            EditBrokerProjectResponseRuleModel(
                requireNotNull(params["brokerProjectRuleId"]),
                requireNotNull(params["urlTrigger"]),
                requireNotNull(params["responseBody"]),
                requireNotNull(params["headers"]),
                requireNotNull(params["httpResponseCode"]).toInt(),
                requireNotNull(params["brokerProjectResponseRuleEnabled"]).toBoolean()
            )
        )

        call.respondRedirect(
            viewBrokerProjectResponseRuleUseCase.viewPathUrl(
                mapOf("brokerProjectToken" to requireNotNull(params["brokerProjectToken"]))
            )
        )
    }
}