package com.bendaniel10.broker.storage.model

import org.dizitart.no2.NitriteId
import org.dizitart.no2.objects.Id
import java.time.OffsetDateTime

data class BrokerProjectRuleResponse(
    @field:Id val id: NitriteId? = null,
    val brokerProjectRuleId: NitriteId,
    val body: String,
    val headers: String, // serialized map [String, String]
    val httpResponseCode: Int,
    val createdDate: OffsetDateTime = OffsetDateTime.now(),
    val enabled: Boolean = true
)