package com.bendaniel10.broker.storage.repository.impl

import com.bendaniel10.broker.storage.model.BrokerProjectRuleResponse
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import org.dizitart.no2.Nitrite

internal class BrokerProjectRuleResponseRepositoryImpl(
    database: Nitrite
) : BrokerProjectRuleResponseRepository,
    BrokerRepositoryDefaultImpl<BrokerProjectRuleResponse>(database.getRepository(BrokerProjectRuleResponse::class.java))