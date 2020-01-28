package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import com.bendaniel10.broker.web.ui.model.DeleteBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.DeleteBrokerProjectRuleUseCase

internal class DeleteBrokerProjectRuleUseCaseImpl(
    private val brokerProjectRuleRepository: BrokerProjectRuleRepository,
    private val brokerProjectRuleResponseRepository: BrokerProjectRuleResponseRepository
) : DeleteBrokerProjectRuleUseCase {

    override fun deleteBrokerProjectResponseRule(model: DeleteBrokerProjectResponseRuleModel) {
        val brokerProjectRule = brokerProjectRuleRepository.getById(model.id.toLong())
        val brokerProjectResponse =
            brokerProjectRuleResponseRepository.getBrokerProjectRuleResponseByBrokerProjectRuleId(
                requireNotNull(brokerProjectRule.id).idValue,
                0,
                1
            ).firstOrNull()
        brokerProjectResponse?.let { brokerProjectRuleResponseRepository.delete(it) }
        brokerProjectRuleRepository.delete(brokerProjectRule)
    }
}