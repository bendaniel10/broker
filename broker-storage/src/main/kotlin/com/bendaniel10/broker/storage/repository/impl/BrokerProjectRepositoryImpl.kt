package com.bendaniel10.broker.storage.repository.impl

import com.bendaniel10.broker.storage.model.BrokerProject
import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import org.dizitart.no2.Nitrite

internal class BrokerProjectRepositoryImpl(
    database: Nitrite
) : BrokerProjectRepository,
    BrokerRepositoryDefaultImpl<BrokerProject>(database.getRepository(BrokerProject::class.java))