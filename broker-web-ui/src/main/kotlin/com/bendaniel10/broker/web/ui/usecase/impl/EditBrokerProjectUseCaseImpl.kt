package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.model.BrokerProject
import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.web.ui.error.ErrorPage
import com.bendaniel10.broker.web.ui.model.EditBrokerProjectModel
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectUseCase
import io.ktor.freemarker.FreeMarkerContent

class EditBrokerProjectUseCaseImpl(
    private val brokerProjectRepository: BrokerProjectRepository
) : EditBrokerProjectUseCase {

    override fun editBrokerProject(editBrokerProjectModel: EditBrokerProjectModel) {
        val brokerProject = brokerProjectRepository.getByToken(editBrokerProjectModel.token).firstOrDefault()
        brokerProjectRepository.update(editBrokerProjectModel.toBrokerProject(requireNotNull(brokerProject.id)))
    }

    override fun view(parameters: Map<String, String>): FreeMarkerContent {
        val brokerProjectToken = requireNotNull(parameters["brokerProjectToken"])
        val brokerProject: BrokerProject = brokerProjectRepository.getByToken(brokerProjectToken).firstOrDefault()
            ?: return ErrorPage.instance("Invalid project token.")
        return FreeMarkerContent(
            templateFileName(),
            mapOf(
                "brokerProjectName" to brokerProject.name,
                "brokerDescription" to brokerProject.description,
                "brokerOriginalUrl" to brokerProject.originalUrl,
                "brokerProjectToken" to brokerProject.token,
                "brokerProjectEnabled" to brokerProject.enabled
            )
        )
    }

    override fun viewPathUrl(parameters: Map<String, String>) =
        buildPathUrlWithParameters("/edit_broker_project", parameters)

    override fun templateFileName() = "/edit_broker_project.ftl"
}