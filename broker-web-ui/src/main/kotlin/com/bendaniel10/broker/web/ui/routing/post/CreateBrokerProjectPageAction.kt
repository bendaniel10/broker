package com.bendaniel10.broker.web.ui.routing.post

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectModel
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect

class CreateBrokerProjectPageAction(
    private val createBrokerProjectUseCase: CreateBrokerProjectUseCase,
    private val viewBrokerProjectUseCase: ViewBrokerProjectUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Post, "/create_broker_project") {

    override suspend fun intercept(call: ApplicationCall) {
        val params = call.receiveParameters()

        createBrokerProjectUseCase.createBrokerProject(
            CreateBrokerProjectModel(
                requireNotNull(params["name"]),
                requireNotNull(params["description"]),
                requireNotNull(params["originalUrl"])
            )
        )

        call.respondRedirect(viewBrokerProjectUseCase.viewPathUrl())
    }

}