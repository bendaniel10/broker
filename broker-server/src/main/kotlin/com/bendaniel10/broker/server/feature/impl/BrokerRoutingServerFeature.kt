package com.bendaniel10.broker.server.feature.impl

import com.bendaniel10.broker.server.feature.BrokerServerFeature
import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.http.HttpMethod
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

class BrokerRoutingServerFeature(
    private val httpMethodRoutingInterceptors: List<HttpMethodRoutingInterceptor>
) : BrokerServerFeature {

    override fun install(application: Application) {
        application.install(Routing) {
            httpMethodRoutingInterceptors.filter { it.httpMethod == HttpMethod.Get }
                .forEach { getMethodRoutingInterceptor ->
                    this.get(getMethodRoutingInterceptor.path) {
                        getMethodRoutingInterceptor.intercept(this.call)
                    }
                }
            httpMethodRoutingInterceptors.filter { it.httpMethod == HttpMethod.Post }
                .forEach { postMethodRoutingInterceptor ->
                    this.post(postMethodRoutingInterceptor.path) {
                        postMethodRoutingInterceptor.intercept(this.call)
                    }
                }
        }
    }
}