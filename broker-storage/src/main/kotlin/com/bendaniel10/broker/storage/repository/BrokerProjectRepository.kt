package com.bendaniel10.broker.storage.repository

import com.bendaniel10.broker.storage.model.BrokerProject
import org.dizitart.no2.objects.Cursor

interface BrokerProjectRepository : BrokerRepository<BrokerProject> {
    fun getByToken(token: String): Cursor<BrokerProject>
}