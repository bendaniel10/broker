package com.bendaniel10.broker.storage.repository.impl

import com.bendaniel10.broker.storage.model.BrokerProjectRule
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import org.dizitart.no2.Nitrite

internal class BrokerProjectRuleRepositoryImpl(
    database: Nitrite
) : BrokerProjectRuleRepository,
    BrokerRepositoryDefaultImpl<BrokerProjectRule>(database.getRepository(BrokerProjectRule::class.java))