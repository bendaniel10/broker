package com.bendaniel10.broker.rewrite.feature

import com.bendaniel10.broker.rewrite.response.BrokerResponseRewriter
import com.bendaniel10.broker.server.feature.BrokerServerFeature
import io.ktor.application.Application
import io.ktor.application.ApplicationCallPipeline

class BrokerResponseRewriteFeature(
    private val responseRewriter: BrokerResponseRewriter
) : BrokerServerFeature {

    override fun install(application: Application) {
        application.intercept(ApplicationCallPipeline.Call) {
            responseRewriter.rewrite(this)
        }
    }
}