package com.bendaniel10.broker.web.ui.feature

import com.bendaniel10.broker.server.feature.BrokerServerFeature
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker

class BrokerFreeMarkerServerFeature : BrokerServerFeature {
    override fun install(application: Application) {
        application.install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }
    }
}