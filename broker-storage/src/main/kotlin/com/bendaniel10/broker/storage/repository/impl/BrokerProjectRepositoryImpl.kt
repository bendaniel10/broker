package com.bendaniel10.broker.storage.repository.impl

import com.bendaniel10.broker.storage.model.BrokerProject
import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import org.dizitart.no2.Nitrite
import org.dizitart.no2.objects.Cursor
import org.dizitart.no2.objects.filters.ObjectFilters

internal class BrokerProjectRepositoryImpl(
    database: Nitrite
) : BrokerProjectRepository,
    BrokerRepositoryDefaultImpl<BrokerProject>(database.getRepository(BrokerProject::class.java)) {

    override fun getByToken(token: String): Cursor<BrokerProject> {
        return objectRepository.find(ObjectFilters.eq("token", token))
    }
}