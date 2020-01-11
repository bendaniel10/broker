package com.bendaniel10.broker.web.ui.di

import com.bendaniel10.broker.web.ui.feature.BrokerFreeMarkerServerFeature
import com.bendaniel10.broker.web.ui.routing.get.CreateBrokerProjectPage
import com.bendaniel10.broker.web.ui.routing.get.CreateBrokerProjectResponseRulePage
import com.bendaniel10.broker.web.ui.routing.get.ViewBrokerProjectPage
import com.bendaniel10.broker.web.ui.routing.get.ViewBrokerProjectResponseRulePage
import com.bendaniel10.broker.web.ui.routing.post.CreateBrokerProjectPageAction
import com.bendaniel10.broker.web.ui.routing.post.CreateBrokerProjectResponseRulePageAction
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectResponseRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectUseCase
import com.bendaniel10.broker.web.ui.usecase.impl.CreateBrokerProjectResponseRuleUseCaseImpl
import com.bendaniel10.broker.web.ui.usecase.impl.CreateBrokerProjectUseCaseImpl
import com.bendaniel10.broker.web.ui.usecase.impl.ViewBrokerProjectResponseRuleUseCaseImpl
import com.bendaniel10.broker.web.ui.usecase.impl.ViewBrokerProjectUseCaseImpl
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

        // Routing - GET.
        single { ViewBrokerProjectPage(get()) }
        single { CreateBrokerProjectPage(get()) }
        single { CreateBrokerProjectResponseRulePage(get()) }
        single { ViewBrokerProjectResponseRulePage(get()) }

        // Routing - POST.
        single { CreateBrokerProjectPageAction(get(), get()) }
        single { CreateBrokerProjectResponseRulePageAction(get(), get()) }

        // Server feature.
        single { BrokerFreeMarkerServerFeature() }
    }
}