package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.DeleteBrokerProjectResponseRuleModel

interface DeleteBrokerProjectRuleUseCase {
    fun deleteBrokerProjectResponseRule(model: DeleteBrokerProjectResponseRuleModel)
}