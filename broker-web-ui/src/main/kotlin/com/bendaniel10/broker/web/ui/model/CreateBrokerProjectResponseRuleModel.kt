package com.bendaniel10.broker.web.ui.model

data class CreateBrokerProjectResponseRuleModel(
    val brokerProjectToken: String,
    val urlTrigger: String,
    val body: String,
    val headers: String,
    val httpResponseCode: Int
)