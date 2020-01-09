package com.bendaniel10.broker.storage.repository.impl

import com.bendaniel10.broker.storage.model.BrokerProjectRuleResponse
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import org.dizitart.no2.FindOptions
import org.dizitart.no2.Nitrite
import org.dizitart.no2.NitriteId
import org.dizitart.no2.objects.Cursor
import org.dizitart.no2.objects.filters.ObjectFilters

internal class BrokerProjectRuleResponseRepositoryImpl(
    database: Nitrite
) : BrokerProjectRuleResponseRepository,
    BrokerRepositoryDefaultImpl<BrokerProjectRuleResponse>(database.getRepository(BrokerProjectRuleResponse::class.java)) {

    override fun getBrokerProjectRuleResponseByBrokerProjectRuleId(
        brokerProjectRuleId: Long,
        start: Int,
        size: Int
    ): Cursor<BrokerProjectRuleResponse> {
        return objectRepository.find(
            ObjectFilters.eq("brokerProjectRuleId", NitriteId.createId(brokerProjectRuleId)),
            FindOptions.limit(start, size)
        )
    }
}