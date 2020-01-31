package com.bendaniel10.broker.web.ui.routing.post

import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectModel
import com.bendaniel10.broker.web.ui.model.EditBrokerProjectModel
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectUseCase
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpMethod
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect

class EditBrokerProjectPageAction(
    private val editBrokerProjectUseCase: EditBrokerProjectUseCase,
    private val viewBrokerProjectUseCase: ViewBrokerProjectUseCase
) : HttpMethodRoutingInterceptor(HttpMethod.Post, "/edit_broker_project") {

    override suspend fun intercept(call: ApplicationCall) {
        val params = call.receiveParameters()

        editBrokerProjectUseCase.editBrokerProject(
            EditBrokerProjectModel(
                requireNotNull(params["brokerProjectToken"]),
                requireNotNull(params["name"]),
                requireNotNull(params["description"]),
                requireNotNull(params["originalUrl"]),
                params["brokerProjectEnabled"]?.toBoolean() ?: false
            )
        )

        call.respondRedirect(viewBrokerProjectUseCase.viewPathUrl())
    }
}