package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectModel

interface CreateBrokerProjectUseCase : BrokerWebUIUseCase {
    fun createBrokerProject(model: CreateBrokerProjectModel)
}