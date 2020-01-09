package com.bendaniel10.broker.storage.repository

import com.bendaniel10.broker.storage.model.BrokerProjectRuleResponse
import org.dizitart.no2.objects.Cursor

interface BrokerProjectRuleResponseRepository : BrokerRepository<BrokerProjectRuleResponse> {
    fun getBrokerProjectRuleResponseByBrokerProjectRuleId(
        brokerProjectRuleId: Long,
        start: Int,
        size: Int
    ): Cursor<BrokerProjectRuleResponse>
}