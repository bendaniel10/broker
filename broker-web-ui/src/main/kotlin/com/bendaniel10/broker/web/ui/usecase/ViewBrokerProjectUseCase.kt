package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.ViewBrokerProjectModel
import io.ktor.freemarker.FreeMarkerContent

interface ViewBrokerProjectUseCase {
    fun getPaginatedBrokerProjects(start: Int, size: Int): Iterable<ViewBrokerProjectModel>
    fun view(): FreeMarkerContent
}