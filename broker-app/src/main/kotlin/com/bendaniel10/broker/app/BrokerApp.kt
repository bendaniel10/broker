package com.bendaniel10.broker.app

import com.bendaniel10.broker.server.BrokerServer
import com.bendaniel10.broker.server.di.BrokerServerModule
import com.bendaniel10.broker.storage.di.StorageModule
import com.bendaniel10.broker.web.ui.di.WebUIModule
import org.koin.core.context.startKoin

object BrokerApp {

    @JvmStatic
    fun main(args: Array<String>) {
        startKoin {
            modules(
                listOf(
                    StorageModule.instance(),
                    WebUIModule.instance(),
                    BrokerServerModule.instance()
                )
            )
        }
        BrokerServer.startServer(serverPort = 8080)
    }
}