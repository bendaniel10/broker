package com.bendaniel10.broker.server.feature

import io.ktor.application.Application

interface BrokerServerFeature {
    fun install(application: Application)
}