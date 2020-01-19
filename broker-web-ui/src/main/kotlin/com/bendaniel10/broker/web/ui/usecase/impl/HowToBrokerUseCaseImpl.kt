package com.bendaniel10.broker.web.ui.usecase.impl

import com.bendaniel10.broker.web.ui.usecase.HowToBrokerUseCase
import io.ktor.freemarker.FreeMarkerContent

internal class HowToBrokerUseCaseImpl : HowToBrokerUseCase {
    override fun view(parameters: Map<String, String>): FreeMarkerContent {
        return FreeMarkerContent(
            templateFileName(),
            parameters
        )
    }

    override fun viewPathUrl(parameters: Map<String, String>) =
        buildPathUrlWithParameters("/how_to_broker", parameters)

    override fun templateFileName() = "how_to_broker.ftl"
}