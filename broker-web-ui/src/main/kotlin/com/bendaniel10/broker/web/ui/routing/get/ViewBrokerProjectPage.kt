package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond

class ViewBrokerProjectPage(private val viewBrokerProjectUseCase: ViewBrokerProjectUseCase) :
    HttpMethodRoutingInterceptor(HttpMethod.Get, viewBrokerProjectUseCase.viewPathUrl()) {

    override suspend fun intercept(call: ApplicationCall) {
        call.respond(viewBrokerProjectUseCase.view())
    }
}