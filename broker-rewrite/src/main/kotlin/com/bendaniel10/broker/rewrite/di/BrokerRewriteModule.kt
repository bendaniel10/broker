package com.bendaniel10.broker.rewrite.di

import com.bendaniel10.broker.rewrite.feature.BrokerResponseRewriteFeature
import com.bendaniel10.broker.rewrite.response.BrokerResponseRewriter
import com.bendaniel10.broker.rewrite.response.impl.BrokerResponseRewriterImpl
import org.koin.dsl.module

object BrokerRewriteModule {
    fun instance() = module {
        single<BrokerResponseRewriter> { BrokerResponseRewriterImpl(get(), get(), get()) }
        single { BrokerResponseRewriteFeature(get()) }
    }
}