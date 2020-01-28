package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.util.flattenEntries

class EditBrokerProjectResponseRulePage(
    private val editBrokerProjectRuleUseCase: EditBrokerProjectRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Get, editBrokerProjectRuleUseCase.viewPathUrl()) {

    override suspend fun intercept(call: ApplicationCall) {
        call.respond(editBrokerProjectRuleUseCase.view(call.request.queryParameters.flattenEntries().toMap()))
    }
}