package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.util.flattenEntries

class ViewBrokerProjectResponseRulePage(
    private val viewBrokerProjectResponseRuleUseCase: ViewBrokerProjectResponseRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Get, viewBrokerProjectResponseRuleUseCase.viewPathUrl()) {

    override suspend fun intercept(call: ApplicationCall) {
        call.respond(viewBrokerProjectResponseRuleUseCase.view(call.request.queryParameters.flattenEntries().toMap()))
    }
}