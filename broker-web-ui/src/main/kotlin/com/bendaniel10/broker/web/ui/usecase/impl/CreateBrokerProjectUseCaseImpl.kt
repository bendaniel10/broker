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

    override fun view(): FreeMarkerContent {
        return FreeMarkerContent(
            "create_broker_project.ftl",
            emptyMap<String, String>()
        )
    }
}