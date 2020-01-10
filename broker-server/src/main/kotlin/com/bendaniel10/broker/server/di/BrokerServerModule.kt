package com.bendaniel10.broker.server.di

import com.bendaniel10.broker.server.feature.impl.BrokerRoutingServerFeature
import com.bendaniel10.broker.server.feature.impl.BrokerStatusPageServerFeature
import com.bendaniel10.broker.server.interceptor.HttpMethodRoutingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.definition.Kind
import org.koin.core.scope.Scope
import org.koin.dsl.module
import kotlin.reflect.full.isSubclassOf

object BrokerServerModule {

    fun instance() = module {
        single { BrokerRoutingServerFeature(getAllOfType<HttpMethodRoutingInterceptor>().toList()) }
        single { BrokerStatusPageServerFeature() }
    }

    inline fun <reified T : Any> KoinComponent.getAllOfType(): Collection<T> =
        getKoin().let { koin ->
            koin.rootScope.beanRegistry.getAllDefinitions()
                .flatMap {
                    koin.scopeRegistry
                        .getScopeSets()
                        .map { it.definitions }
                        .flatten()
                        .run {
                            listOf(it, *this.toTypedArray())
                        }
                }
                .filter {
                    it.kind == Kind.Single && it.primaryType.isSubclassOf(T::class)
                }
                .map { koin.get<T>(clazz = it.primaryType, qualifier = null, parameters = null) }

        }

    inline fun <reified T : Any> Scope.getAllOfType(): Collection<T> =
        beanRegistry.getAllDefinitions()
            .filter {
                it.kind == Kind.Single && it.primaryType.isSubclassOf(T::class)
            }
            .map { getKoin().get<T>(clazz = it.primaryType, qualifier = null, parameters = null) }
}