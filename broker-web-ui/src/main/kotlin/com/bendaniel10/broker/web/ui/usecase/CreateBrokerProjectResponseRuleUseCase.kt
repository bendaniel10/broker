package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectResponseRuleModel

interface CreateBrokerProjectResponseRuleUseCase : BrokerWebUIUseCase {
    fun createBrokerProjectResponseRule(model: CreateBrokerProjectResponseRuleModel)
}