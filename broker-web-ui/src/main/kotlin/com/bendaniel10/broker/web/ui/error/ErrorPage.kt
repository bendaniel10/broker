package com.bendaniel10.broker.web.ui.error

import io.ktor.freemarker.FreeMarkerContent
import java.lang.Exception

object ErrorPage {
    @JvmStatic
    fun instance(errorMessage: String) =
        FreeMarkerContent("broker_error_page.ftl", mapOf("errorMessage" to errorMessage))

    @JvmStatic
    fun execption(message: String) = Exception(message)
}