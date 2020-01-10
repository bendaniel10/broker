package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.web.ui.model.ViewBrokerProjectModel
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectUseCase
import io.ktor.freemarker.FreeMarkerContent

class ViewBrokerProjectUseCaseImpl(
    private val brokerProjectRepository: BrokerProjectRepository
) : ViewBrokerProjectUseCase {

    override fun getPaginatedBrokerProjects(start: Int, size: Int) =
        brokerProjectRepository.getItems(start, size)
            .map { ViewBrokerProjectModel.fromBrokerProject(it) }

    override fun view(parameters: Map<String, String>): FreeMarkerContent {
        return FreeMarkerContent(
            templateFileName(),
            mapOf("brokerProjects" to getPaginatedBrokerProjects(0, Int.MAX_VALUE))
        )
    }

    override fun viewPathUrl(parameters: Map<String, String>) = "/"

    override fun templateFileName() = "view_broker_project.ftl"
}