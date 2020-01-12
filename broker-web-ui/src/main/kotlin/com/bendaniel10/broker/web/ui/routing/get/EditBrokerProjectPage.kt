package com.bendaniel10.broker.web.ui.routing.get

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.response.respond
import io.ktor.util.flattenEntries

class EditBrokerProjectPage(
    private val editBrokerProjectUseCase: EditBrokerProjectUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Get, editBrokerProjectUseCase.viewPathUrl()) {

    override suspend fun intercept(call: ApplicationCall) {
        call.respond(editBrokerProjectUseCase.view(call.request.queryParameters.flattenEntries().toMap()))
    }
}