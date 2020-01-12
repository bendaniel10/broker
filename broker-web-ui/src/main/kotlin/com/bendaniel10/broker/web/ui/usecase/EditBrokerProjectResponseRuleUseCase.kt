package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.EditBrokerProjectResponseRuleModel

interface EditBrokerProjectResponseRuleUseCase : BrokerWebUIUseCase {
    fun editBrokerProjectResponseRule(model: EditBrokerProjectResponseRuleModel)
}