package com.bendaniel10.broker.web.ui

import com.bendaniel10.broker.storage.di.StorageModule
import com.bendaniel10.broker.web.ui.di.WebUiModule
import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectModel
import com.bendaniel10.broker.web.ui.model.CreateBrokerProjectResponseRuleModel
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectResponseRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.CreateBrokerProjectUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectResponseRuleUseCase
import com.bendaniel10.broker.web.ui.usecase.ViewBrokerProjectUseCase
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.freemarker.FreeMarker
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.get

object WebUIServer : KoinComponent {

    private lateinit var viewBrokerProjectUseCase: ViewBrokerProjectUseCase
    private lateinit var createBrokerProjectUseCase: CreateBrokerProjectUseCase
    private lateinit var createBrokerProjectResponseRuleUseCase: CreateBrokerProjectResponseRuleUseCase
    private lateinit var viewBrokerProjectResponseRuleUseCase: ViewBrokerProjectResponseRuleUseCase

    @JvmStatic
    fun main(args: Array<String>) {
        start()
    }

    fun start() {
        startKoin { modules(listOf(StorageModule.instance(), WebUiModule.instance())) }
        viewBrokerProjectUseCase = get()
        createBrokerProjectUseCase = get()
        createBrokerProjectResponseRuleUseCase = get()
        viewBrokerProjectResponseRuleUseCase = get()

        embeddedServer(Netty, 8080) {
            install(FreeMarker) {
                templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
            }
            routing {
                get("/") {
                    call.respond(viewBrokerProjectUseCase.view())
                }
                get("/create_broker_project") {
                    call.respond(createBrokerProjectUseCase.view())
                }
                get("/create_broker_project_response_rule") {
                    val brokerProjectToken = requireNotNull(call.request.queryParameters["brokerProjectToken"])
                    call.respond(createBrokerProjectResponseRuleUseCase.view(brokerProjectToken))
                }
                get("/view_broker_project_response") {
                    val brokerProjectToken = requireNotNull(call.request.queryParameters["brokerProjectToken"])
                    call.respond(viewBrokerProjectResponseRuleUseCase.view(brokerProjectToken))
                }

                post("/create_broker_project") {
                    val params = call.receiveParameters()

                    createBrokerProjectUseCase.createBrokerProject(
                        CreateBrokerProjectModel(
                            requireNotNull(params["name"]),
                            requireNotNull(params["description"]),
                            requireNotNull(params["originalUrl"])
                        )
                    )

                    call.respond(viewBrokerProjectUseCase.view())
                }

                post("/create_broker_project_response_rule") {
                    val params = call.receiveParameters()

                    createBrokerProjectResponseRuleUseCase.createBrokerProjectResponseRule(
                        CreateBrokerProjectResponseRuleModel(
                            requireNotNull(params["brokerProjectToken"]),
                            requireNotNull(params["urlTrigger"]),
                            requireNotNull(params["responseBody"]),
                            requireNotNull(params["headers"]),
                            requireNotNull(params["httpResponseCode"]).toInt()
                        )
                    )

                    call.respond(viewBrokerProjectResponseRuleUseCase.view(requireNotNull(params["brokerProjectToken"])))
                }
            }
        }.start(wait = true)
    }
}