package com.bendaniel10.broker.rewrite

import io.ktor.application.ApplicationCall
import io.ktor.util.pipeline.PipelineContext

interface BrokerRewriter {
    suspend fun rewrite(pipelineContext: PipelineContext<Unit, ApplicationCall>)
}
