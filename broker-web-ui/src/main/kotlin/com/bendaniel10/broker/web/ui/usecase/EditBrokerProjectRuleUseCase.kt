package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.EditBrokerProjectResponseRuleModel

interface EditBrokerProjectRuleUseCase : BrokerWebUIUseCase {
    fun editBrokerProjectResponseRule(model: EditBrokerProjectResponseRuleModel)
}