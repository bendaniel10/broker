package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.HowToBrokerUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.util.flattenEntries

class HowToBrokerPage(
    private val howToBrokerUseCase: HowToBrokerUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Get, howToBrokerUseCase.viewPathUrl()) {
    override suspend fun intercept(call: ApplicationCall) {
        call.respond(howToBrokerUseCase.view(call.request.queryParameters.flattenEntries().toMap()))
    }
}