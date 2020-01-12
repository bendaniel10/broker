package com.bendaniel10.broker.web.ui.model

data class ViewBrokerProjectResponseRuleModel(
    val brokerProjectRuleId: String,
    val urlTrigger: String,
    val body: String,
    val headers: String,
    val httpResponseCode: Int
)