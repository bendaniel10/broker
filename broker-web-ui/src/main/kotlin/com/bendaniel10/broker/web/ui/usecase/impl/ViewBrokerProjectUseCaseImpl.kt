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

    override fun view(): FreeMarkerContent {
        return FreeMarkerContent(
            "view_broker_project.ftl",
            mapOf("brokerProjects" to getPaginatedBrokerProjects(0, Int.MAX_VALUE))
        )
    }
}