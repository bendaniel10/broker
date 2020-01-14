package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.model.BrokerProject
import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import com.bendaniel10.broker.web.ui.error.ErrorPage
import com.bendaniel10.broker.web.ui.model.ViewBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import io.ktor.freemarker.FreeMarkerContent
import kotlin.math.min

class ViewBrokerProjectResponseRuleUseCaseImpl(
    private val brokerProjectRepository: BrokerProjectRepository,
    private val brokerProjectRuleRepository: BrokerProjectRuleRepository,
    private val brokerProjectRuleResponseRepository: BrokerProjectRuleResponseRepository
) : ViewBrokerProjectResponseRuleUseCase {

    private fun getPaginatedBrokerProjectResponseRule(
        brokerProjectId: Long,
        start: Int,
        size: Int
    ): List<ViewBrokerProjectResponseRuleModel> {
        return brokerProjectRuleRepository.getBrokerProjectRuleByBrokerProjectId(brokerProjectId, start, size)
            .map { brokerProjectRule ->
                brokerProjectRuleResponseRepository.getBrokerProjectRuleResponseByBrokerProjectRuleId(
                    requireNotNull(brokerProjectRule.id).idValue,
                    start,
                    size
                ).first().run {
                    ViewBrokerProjectResponseRuleModel(
                        requireNotNull(brokerProjectRule.id).idValue.toString(),
                        brokerProjectRule.urlTrigger,
                        body,
                        headers.substring(0..min(20, headers.length - 1)),
                        httpResponseCode
                    )
                }
            }
    }

    override fun view(parameters: Map<String, String>): FreeMarkerContent {
        val brokerProjectToken = requireNotNull(parameters["brokerProjectToken"])
        val brokerProject: BrokerProject = brokerProjectRepository.getByToken(brokerProjectToken).firstOrDefault()
            ?: return ErrorPage.instance("Invalid project token.")
        val viewBrokerProjectResponseRuleModel = getPaginatedBrokerProjectResponseRule(
            requireNotNull(brokerProject.id).idValue,
            0,
            Int.MAX_VALUE
        )
        return FreeMarkerContent(
            templateFileName(),
            mapOf(
                "rules" to viewBrokerProjectResponseRuleModel,
                "brokerProjectName" to brokerProject.name,
                "brokerProjectToken" to brokerProject.token
            )
        )
    }

    override fun viewPathUrl(parameters: Map<String, String>): String {
        return buildPathUrlWithParameters("/view_broker_project_response", parameters)
    }

    override fun templateFileName() = "view_broker_project_response.ftl"
}