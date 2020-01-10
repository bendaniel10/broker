package com.bendaniel10.broker.server.interceptor

import io.ktor.http.HttpMethod

abstract class HttpMethodRoutingInterceptor(val httpMethod: HttpMethod, val path: String) : RoutingInterceptor