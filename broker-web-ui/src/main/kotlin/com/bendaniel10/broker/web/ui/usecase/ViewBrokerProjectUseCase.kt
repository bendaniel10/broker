package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.ViewBrokerProjectModel

interface ViewBrokerProjectUseCase : BrokerWebUIUseCase {
    fun getPaginatedBrokerProjects(start: Int, size: Int): Iterable<ViewBrokerProjectModel>
}