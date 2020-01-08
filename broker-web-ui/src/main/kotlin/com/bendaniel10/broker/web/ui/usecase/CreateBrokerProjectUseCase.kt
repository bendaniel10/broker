package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectModel
import io.ktor.freemarker.FreeMarkerContent

interface CreateBrokerProjectUseCase {
    fun createBrokerProject(model: CreateBrokerProjectModel)
    fun view(): FreeMarkerContent
}