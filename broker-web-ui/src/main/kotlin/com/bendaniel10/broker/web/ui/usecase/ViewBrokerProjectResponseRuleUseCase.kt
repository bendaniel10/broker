package com.bendaniel10.broker.web.ui.usecase

import io.ktor.freemarker.FreeMarkerContent

interface ViewBrokerProjectResponseRuleUseCase {
    fun view(brokerProjectToken: String): FreeMarkerContent
}