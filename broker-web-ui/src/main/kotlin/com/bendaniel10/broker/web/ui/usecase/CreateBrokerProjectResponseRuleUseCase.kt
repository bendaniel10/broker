package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectResponseRuleModel
import io.ktor.freemarker.FreeMarkerContent

interface CreateBrokerProjectResponseRuleUseCase {
    fun view(brokerProjectToken: String): FreeMarkerContent
    fun createBrokerProjectResponseRule(model: CreateBrokerProjectResponseRuleModel)
}