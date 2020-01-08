package com.bendaniel10.broker.web.ui.di

import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectUseCase
import com.bendaniel10.broker.web.ui.usecase.impl.CreateBrokerProjectUseCaseImpl
import com.bendaniel10.broker.web.ui.usecase.impl.ViewBrokerProjectUseCaseImpl
import org.koin.dsl.module

object WebUiModule {

    fun instance() = module {
        single<ViewBrokerProjectUseCase> { ViewBrokerProjectUseCaseImpl(get()) }
        single<CreateBrokerProjectUseCase> { CreateBrokerProjectUseCaseImpl(get()) }
    }
}