package com.bendaniel10.broker.storage.model

import org.dizitart.no2.NitriteId
import org.dizitart.no2.objects.Id
import java.time.OffsetDateTime

data class BrokerProjectRule(
    @field:Id var id: NitriteId? = null,
    val projectId: NitriteId,
    val urlTrigger: String,
    val createdDate: OffsetDateTime = OffsetDateTime.now(),
    val enabled: Boolean = true
)