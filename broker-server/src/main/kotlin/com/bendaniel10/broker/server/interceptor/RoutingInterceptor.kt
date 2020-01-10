package com.bendaniel10.broker.server.interceptor

import io.ktor.application.ApplicationCall

interface RoutingInterceptor {
    suspend fun intercept(call: ApplicationCall)
}