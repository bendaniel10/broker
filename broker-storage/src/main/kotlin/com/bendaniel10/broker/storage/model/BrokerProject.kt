package com.bendaniel10.broker.storage.model

import org.dizitart.no2.IndexType
import org.dizitart.no2.NitriteId
import org.dizitart.no2.objects.Id
import org.dizitart.no2.objects.Index
import org.dizitart.no2.objects.Indices
import java.time.OffsetDateTime

@Indices(
    Index("token", type = IndexType.Unique)
)
data class BrokerProject(
    @field:Id var id: NitriteId? = null,
    val name: String,
    val description: String = "",
    val originalUrl: String,
    val token: String,
    val createdDate: OffsetDateTime = OffsetDateTime.now(),
    val enabled: Boolean = true
)