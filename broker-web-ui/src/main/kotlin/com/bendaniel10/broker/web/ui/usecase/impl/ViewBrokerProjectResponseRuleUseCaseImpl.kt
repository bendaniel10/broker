package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import com.bendaniel10.broker.web.ui.model.ViewBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import io.ktor.freemarker.FreeMarkerContent

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
                        brokerProjectRule.urlTrigger,
                        body,
                        headers,
                        httpResponseCode
                    )
                }
            }
    }

    override fun view(brokerProjectToken: String): FreeMarkerContent {
        val brokerProject = brokerProjectRepository.getByToken(brokerProjectToken)
        val viewBrokerProjectResponseRuleModel = getPaginatedBrokerProjectResponseRule(
            requireNotNull(brokerProject.id).idValue,
            0,
            Int.MAX_VALUE
        )
        return FreeMarkerContent(
            "view_broker_project_response.ftl",
            mapOf(
                "rules" to viewBrokerProjectResponseRuleModel,
                "brokerProjectName" to brokerProject.name,
                "brokerProjectToken" to brokerProject.token
            )
        )
    }
}