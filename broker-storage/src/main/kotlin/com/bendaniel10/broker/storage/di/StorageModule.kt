package com.bendaniel10.broker.storage.di

import com.bendaniel10.broker.storage.repository.BrokerProjectRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleRepository
import com.bendaniel10.broker.storage.repository.BrokerProjectRuleResponseRepository
import com.bendaniel10.broker.storage.repository.impl.BrokerProjectRepositoryImpl
import com.bendaniel10.broker.storage.repository.impl.BrokerProjectRuleRepositoryImpl
import com.bendaniel10.broker.storage.repository.impl.BrokerProjectRuleResponseRepositoryImpl
import org.dizitart.no2.Nitrite
import org.koin.dsl.module
import java.io.File

object StorageModule {
    fun instance() = module {
        single { nitriteDb() }
        single<BrokerProjectRepository> { BrokerProjectRepositoryImpl(get()) }
        single<BrokerProjectRuleRepository> { BrokerProjectRuleRepositoryImpl(get()) }
        single<BrokerProjectRuleResponseRepository> { BrokerProjectRuleResponseRepositoryImpl(get()) }
    }

    private fun nitriteDb(): Nitrite {
        with(File("data")) {
            if (!exists() || !isDirectory) {
                mkdirs()
            }
        }
        return Nitrite.builder()
            .compressed()
            .filePath("data/broker_database.db")
            .openOrCreate()
    }
}