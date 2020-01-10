package com.bendaniel10.broker.server.feature.impl

import com.bendaniel10.broker.server.feature.BrokerServerFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import java.util.*

class BrokerStatusPageServerFeature : BrokerServerFeature {
    override fun install(application: Application) {
        application.install(StatusPages) {
            exception<Throwable> { cause ->
                cause.toString()
                call.respond(HttpStatusCode.InternalServerError, Arrays.toString(cause.stackTrace))
            }
        }
    }
}