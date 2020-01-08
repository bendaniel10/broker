package com.bendaniel10.broker.web.ui.model

import com.bendaniel10.broker.storage.model.BrokerProject
import java.util.*

data class CreateBrokerProjectModel(
    val name: String,
    val description: String = "",
    val originalUrl: String
) {
    fun toBrokerProject(): BrokerProject {
        return BrokerProject(
            null,
            name,
            description,
            originalUrl,
            UUID.randomUUID().toString()
        )
    }
}