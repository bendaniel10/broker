package com.bendaniel10.broker.web.ui.model

import com.bendaniel10.broker.storage.model.BrokerProject
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

data class ViewBrokerProjectModel(
    val name: String,
    val description: String,
    val originalUrl: String,
    val token: String,
    val createdDateFormatted: String,
    val enabled: String
) {
    companion object {
        fun fromBrokerProject(brokerProject: BrokerProject) = ViewBrokerProjectModel(
            brokerProject.name,
            brokerProject.description,
            brokerProject.originalUrl,
            brokerProject.token,
            DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(brokerProject.createdDate),
            if (brokerProject.enabled) "Yes" else "No"
        )
    }
}