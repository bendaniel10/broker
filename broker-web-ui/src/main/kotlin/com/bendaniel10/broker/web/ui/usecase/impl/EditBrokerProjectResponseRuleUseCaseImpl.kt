package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import com.bendaniel10.broker.web.ui.error.ErrorPage
import com.bendaniel10.broker.web.ui.model.EditBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.EditBrokerProjectResponseRuleUseCase
import io.ktor.freemarker.FreeMarkerContent

internal class EditBrokerProjectResponseRuleUseCaseImpl(
    private val brokerProjectRepository: BrokerProjectRepository,
    private val brokerProjectRuleRepository: BrokerProjectRuleRepository,
    private val brokerProjectRuleResponseRepository: BrokerProjectRuleResponseRepository
) : EditBrokerProjectResponseRuleUseCase {

    override fun editBrokerProjectResponseRule(model: EditBrokerProjectResponseRuleModel) {
        val brokerProjectRule = brokerProjectRuleRepository.getById(model.brokerProjectRuleId.toLong())
        val brokerProjectResponse =
            brokerProjectRuleResponseRepository.getBrokerProjectRuleResponseByBrokerProjectRuleId(
                requireNotNull(brokerProjectRule.id).idValue,
                0,
                1
            ).firstOrDefault()

        brokerProjectRuleRepository.update(brokerProjectRule.copy(urlTrigger = model.urlTrigger))

        brokerProjectRuleResponseRepository.update(
            brokerProjectResponse.copy(
                body = model.body,
                headers = model.headers,
                httpResponseCode = model.httpResponseCode,
                enabled = model.enabled
            )
        )
    }

    override fun view(parameters: Map<String, String>): FreeMarkerContent {
        val brokerProjectRuleId =
            requireNotNull(parameters["brokerProjectRuleId"]).toLongOrNull() ?: return ErrorPage.instance("Invalid project rule.")
        val brokerProjectResponse =
            brokerProjectRuleResponseRepository.getBrokerProjectRuleResponseByBrokerProjectRuleId(
                brokerProjectRuleId,
                0,
                1
            ).firstOrDefault() ?: return ErrorPage.instance("Invalid project rule.")
        val brokerProjectRule = brokerProjectRuleRepository.getById(brokerProjectResponse.brokerProjectRuleId.idValue)
        val brokerProject = brokerProjectRepository.getById(brokerProjectRule.projectId.idValue)

        return FreeMarkerContent(
            templateFileName(),
            mapOf(
                "originalUrl" to brokerProject.originalUrl,
                "brokerProjectName" to brokerProject.name,
                "urlTrigger" to brokerProjectRule.urlTrigger,
                "responseBody" to brokerProjectResponse.body,
                "headers" to brokerProjectResponse.headers,
                "httpResponseCode" to brokerProjectResponse.httpResponseCode,
                "brokerProjectResponseRuleEnabled" to brokerProjectResponse.enabled,
                "brokerProjectRuleId" to brokerProjectRuleId.toString(),
                "brokerProjectToken" to brokerProject.token
            )
        )
    }

    override fun viewPathUrl(parameters: Map<String, String>) =
        buildPathUrlWithParameters("/edit_broker_project_response_rule", parameters)

    override fun templateFileName() = "edit_broker_project_response_rule.ftl"
}