package com.bendaniel10.broker.web.ui.model

import com.bendaniel10.broker.storage.model.BrokerProject
import org.dizitart.no2.NitriteId

data class EditBrokerProjectModel(
    val token: String,
    val name: String,
    val description: String = "",
    val originalUrl: String,
    val enabled: Boolean
) {
    fun toBrokerProject(id: NitriteId): BrokerProject {
        return BrokerProject(
            id,
            name,
            description,
            originalUrl,
            token,
            enabled = enabled
        )
    }
}