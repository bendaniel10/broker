package com.bendaniel10.broker.storage.di

import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import com.bendaniel10.broker.storage.repository.impl.BrokerProjectRepositoryImpl
import com.bendaniel10.broker.storage.repository.impl.BrokerProjectRuleRepositoryImpl
import com.bendaniel10.broker.storage.repository.impl.BrokerProjectRuleResponseRepositoryImpl
import org.dizitart.no2.Nitrite
import org.koin.dsl.module

object StorageModule {
    fun instance() = module {
        single {
            Nitrite.builder()
                .compressed()
                .filePath("data/broker_database.db")
                .openOrCreate()
        }
        single<BrokerProjectRepository> { BrokerProjectRepositoryImpl(get()) }
        single<BrokerProjectRuleRepository> { BrokerProjectRuleRepositoryImpl(get()) }
        single<BrokerProjectRuleResponseRepository> { BrokerProjectRuleResponseRepositoryImpl(get()) }
    }
}