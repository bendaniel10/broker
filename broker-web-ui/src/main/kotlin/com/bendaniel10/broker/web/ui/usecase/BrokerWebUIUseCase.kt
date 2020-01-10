package com.bendaniel10.broker.web.ui.usecase

import io.ktor.freemarker.FreeMarkerContent

interface BrokerWebUIUseCase {
    fun view(parameters: Map<String, String> = emptyMap()): FreeMarkerContent
    fun viewPathUrl(parameters: Map<String, String> = emptyMap()): String
    fun templateFileName(): String
    fun buildPathUrlWithParameters(pathUrl: String, parameters: Map<String, String> = emptyMap()): String {
        return if (parameters.isEmpty()) {
            pathUrl
        } else {
            "$pathUrl?".plus(
                parameters.map { "${it.key}=${it.value}" }
                    .toList()
                    .joinToString("&")
            )
        }

    }
}