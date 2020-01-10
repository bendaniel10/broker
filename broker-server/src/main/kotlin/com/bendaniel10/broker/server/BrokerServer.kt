package com.bendaniel10.broker.server

import com.bendaniel10.broker.server.di.BrokerServerModule.getAllOfType
import com.bendaniel10.broker.server.feature.BrokerServerFeature
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.core.KoinComponent

object BrokerServer : KoinComponent {

    fun startServer(serverPort: Int = 8080) {

        embeddedServer(Netty, serverPort) {
            getAllOfType<BrokerServerFeature>()
                .forEach {
                    it.install(this)
                }

        }.start(wait = true)
    }
}