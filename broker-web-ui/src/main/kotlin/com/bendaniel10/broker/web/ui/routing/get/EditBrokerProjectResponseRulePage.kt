package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectResponseRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.util.flattenEntries

class EditBrokerProjectResponseRulePage(
    private val editBrokerProjectResponseRuleUseCase: EditBrokerProjectResponseRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Get, editBrokerProjectResponseRuleUseCase.viewPathUrl()) {

    override suspend fun intercept(call: ApplicationCall) {
        call.respond(editBrokerProjectResponseRuleUseCase.view(call.request.queryParameters.flattenEntries().toMap()))
    }
}