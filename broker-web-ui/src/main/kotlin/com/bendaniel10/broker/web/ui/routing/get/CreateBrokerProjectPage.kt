package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond

class CreateBrokerProjectPage(
    private val createBrokerProjectUseCase: CreateBrokerProjectUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Get, createBrokerProjectUseCase.viewPathUrl()) {
    override suspend fun intercept(call: ApplicationCall) {
        call.respond(createBrokerProjectUseCase.view())
    }
}