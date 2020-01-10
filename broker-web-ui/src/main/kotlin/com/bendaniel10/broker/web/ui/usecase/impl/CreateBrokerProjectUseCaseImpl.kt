package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectModel
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectUseCase
import io.ktor.freemarker.FreeMarkerContent

class CreateBrokerProjectUseCaseImpl(
    private val brokerProjectRepository: BrokerProjectRepository
) : CreateBrokerProjectUseCase {

    override fun createBrokerProject(model: CreateBrokerProjectModel) {
        brokerProjectRepository.save(model.toBrokerProject())
    }

    override fun view(parameters: Map<String, String>): FreeMarkerContent {
        return FreeMarkerContent(
            templateFileName(),
            emptyMap<String, String>()
        )
    }

    override fun viewPathUrl(parameters: Map<String, String>) =
        buildPathUrlWithParameters("/create_broker_project", parameters)

    override fun templateFileName() = "create_broker_project.ftl"
}