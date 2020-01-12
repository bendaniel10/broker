package com.bendaniel10.broker.web.ui.usecase

import com.bendaniel10.broker.web.ui.model.EditBrokerProjectModel

interface EditBrokerProjectUseCase : BrokerWebUIUseCase {
    fun editBrokerProject(editBrokerProjectModel: EditBrokerProjectModel)
}