package com.bendaniel10.broker.web.ui.routing.post

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.model.EditBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect

class EditBrokerProjectResponseRulePageAction(
    private val editBrokerProjectRuleUseCase: EditBrokerProjectRuleUseCase,
    private val viewBrokerProjectResponseRuleUseCase: ViewBrokerProjectResponseRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Post, editBrokerProjectRuleUseCase.viewPathUrl()) {

    override suspend fun intercept(call: ApplicationCall) {
        val params = call.receiveParameters()

        editBrokerProjectRuleUseCase.editBrokerProjectResponseRule(
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