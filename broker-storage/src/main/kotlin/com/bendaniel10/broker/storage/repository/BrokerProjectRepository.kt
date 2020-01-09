package com.bendaniel10.broker.storage.repository

import com.bendaniel10.broker.storage.model.BrokerProject

interface BrokerProjectRepository : BrokerRepository<BrokerProject> {
    fun getByToken(token: String): BrokerProject
}