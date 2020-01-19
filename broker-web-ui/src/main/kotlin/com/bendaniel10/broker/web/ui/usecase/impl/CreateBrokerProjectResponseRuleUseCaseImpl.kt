package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.model.BrokerProject
import com.bendaniel10.broker.storage.model.BrokerProjectRule
import com.bendaniel10.broker.storage.model.BrokerProjectRuleResponse
import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import com.bendaniel10.broker.web.ui.error.ErrorPage
import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectResponseRuleUseCase
import io.ktor.freemarker.FreeMarkerContent

internal class CreateBrokerProjectResponseRuleUseCaseImpl(
    private val brokerProjectRepository: BrokerProjectRepository,
    private val brokerProjectRuleRepository: BrokerProjectRuleRepository,
    private val brokerProjectRuleResponseRepository: BrokerProjectRuleResponseRepository
) : CreateBrokerProjectResponseRuleUseCase {

    override fun view(parameters: Map<String, String>): FreeMarkerContent {
        val brokerProjectToken = requireNotNull(parameters["brokerProjectToken"])
        val brokerProject: BrokerProject = brokerProjectRepository.getByToken(brokerProjectToken)
            .firstOrDefault() ?: return ErrorPage.instance("Invalid project")

        return FreeMarkerContent(
            templateFileName(),
            mapOf(
                "originalUrl" to brokerProject.originalUrl,
                "brokerProjectName" to brokerProject.name,
                "brokerProjectToken" to brokerProjectToken
            )
        )
    }

    override fun createBrokerProjectResponseRule(model: CreateBrokerProjectResponseRuleModel) {
        val brokerProject = brokerProjectRepository.getByToken(model.brokerProjectToken).firstOrDefault()

        val brokerProjectRule = BrokerProjectRule(
            null,
            requireNotNull(brokerProject.id),
            model.urlTrigger
        )

        brokerProjectRuleRepository.save(brokerProjectRule)

        val brokerProjectRuleResponse = BrokerProjectRuleResponse(
            null,
            requireNotNull(brokerProjectRule.id),
            model.body,
            model.headers,
            model.httpResponseCode
        )

        brokerProjectRuleResponseRepository.save(brokerProjectRuleResponse)
    }

    override fun templateFileName() = "create_broker_project_response_rule.ftl"

    override fun viewPathUrl(parameters: Map<String, String>) =
        buildPathUrlWithParameters("/create_broker_project_response_rule", parameters)
}