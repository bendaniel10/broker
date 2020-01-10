package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectResponseRuleUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.util.flattenEntries

class CreateBrokerProjectResponseRulePage(
    private val createBrokerProjectResponseRuleUseCase: CreateBrokerProjectResponseRuleUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Get, createBrokerProjectResponseRuleUseCase.viewPathUrl()) {

    override suspend fun intercept(call: ApplicationCall) {
        call.respond(createBrokerProjectResponseRuleUseCase.view(call.request.queryParameters.flattenEntries().toMap()))
    }

}