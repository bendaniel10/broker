package com.bendaniel10.broker.web.ui.di

import com.bendaniel10.broker.web.ui.feature.BrokerFreeMarkerServerFeature
import com.bendaniel10.broker.web.ui.routing.get.*
import com.bendaniel10.broker.web.ui.routing.post.*
import com.bendaniel10.broker.web.ui.usecase.*
import com.bendaniel10.broker.web.ui.usecase.impl.*
import org.koin.dsl.module

object WebUIModule {

    fun instance() = module {
        // Use case.
        single<ViewBrokerProjectUseCase> { ViewBrokerProjectUseCaseImpl(get()) }
        single<CreateBrokerProjectUseCase> { CreateBrokerProjectUseCaseImpl(get()) }
        single<CreateBrokerProjectResponseRuleUseCase> {
            CreateBrokerProjectResponseRuleUseCaseImpl(
                get(),
                get(),
                get()
            )
        }
        single<ViewBrokerProjectResponseRuleUseCase> { ViewBrokerProjectResponseRuleUseCaseImpl(get(), get(), get()) }
        single<HowToBrokerUseCase> { HowToBrokerUseCaseImpl() }
        single<EditBrokerProjectUseCase> { EditBrokerProjectUseCaseImpl(get()) }
        single<EditBrokerProjectRuleUseCase> { EditBrokerProjectRuleUseCaseImpl(get(), get(), get()) }
        single<DeleteBrokerProjectRuleUseCase> { DeleteBrokerProjectRuleUseCaseImpl(get(), get()) }

        // Routing - GET.
        single { ViewBrokerProjectPage(get()) }
        single { CreateBrokerProjectPage(get()) }
        single { CreateBrokerProjectResponseRulePage(get()) }
        single { ViewBrokerProjectResponseRulePage(get()) }
        single { HowToBrokerPage(get()) }
        single { EditBrokerProjectPage(get()) }
        single { EditBrokerProjectResponseRulePage(get()) }

        // Routing - POST.
        single { CreateBrokerProjectPageAction(get(), get()) }
        single { CreateBrokerProjectResponseRulePageAction(get(), get()) }
        single { EditBrokerProjectPageAction(get(), get()) }
        single { EditBrokerProjectResponseRulePageAction(get(), get()) }
        single { DeleteBrokerProjectResponseRuleAction(get(), get()) }

        // Server feature.
        single { BrokerFreeMarkerServerFeature() }
    }
}