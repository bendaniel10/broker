package com.bendaniel10.broker.storage.repository.impl

import com.bendaniel10.broker.storage.model.BrokerProjectRule
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import org.dizitart.no2.FindOptions.limit
import org.dizitart.no2.Nitrite
import org.dizitart.no2.NitriteId
import org.dizitart.no2.objects.Cursor
import org.dizitart.no2.objects.filters.ObjectFilters.and
import org.dizitart.no2.objects.filters.ObjectFilters.eq

internal class BrokerProjectRuleRepositoryImpl(
    database: Nitrite
) : BrokerProjectRuleRepository,
    BrokerRepositoryDefaultImpl<BrokerProjectRule>(database.getRepository(BrokerProjectRule::class.java)) {

    override fun getBrokerProjectRuleByBrokerProjectId(
        brokerProjectId: Long,
        start: Int,
        size: Int
    ): Cursor<BrokerProjectRule> {
        return objectRepository.find(
            eq("projectId", NitriteId.createId(brokerProjectId)),
            limit(start, size)
        )
    }

    override fun getBrokerProjectRuleByUrlAndProjectId(
        brokerProjectId: Long,
        url: String
    ): Cursor<BrokerProjectRule> {
        return objectRepository.find(
            and(eq("projectId", NitriteId.createId(brokerProjectId)), eq("urlTrigger", url))
        )
    }
}