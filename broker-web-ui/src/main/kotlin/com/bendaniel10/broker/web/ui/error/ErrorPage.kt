package com.bendaniel10.broker.web.ui.error

import io.ktor.freemarker.FreeMarkerContent

object ErrorPage {
    fun instance(errorMessage: String) =
        FreeMarkerContent("broker_error_page.ftl", mapOf("errorMessage" to errorMessage))
}