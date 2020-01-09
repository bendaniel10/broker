package com.bendaniel10.broker.web.ui.model

data class ViewBrokerProjectResponseRuleModel(
    val urlTrigger: String,
    val body: String,
    val headers: String,
    val httpResponseCode: Int
)