package com.bendaniel10.broker.storage.repository

import com.bendaniel10.broker.storage.model.BrokerProjectRule
import org.dizitart.no2.objects.Cursor

interface BrokerProjectRuleRepository : BrokerRepository<BrokerProjectRule> {
    fun getBrokerProjectRuleByBrokerProjectId(
        brokerProjectId: Long,
        start: Int,
        size: Int
    ): Cursor<BrokerProjectRule>

    fun getBrokerProjectRuleByUrlAndProjectId(brokerProjectId: Long, url: String): Cursor<BrokerProjectRule>
}