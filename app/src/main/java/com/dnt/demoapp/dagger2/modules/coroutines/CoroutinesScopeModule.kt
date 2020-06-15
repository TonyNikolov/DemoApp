package com.dnt.demoapp.dagger2.modules.coroutines

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@Module
object CoroutineScopeModule {

    @Provides
    fun provideIOCoroutineScope(): CoroutineScope = IOCoroutineScope()
}

interface IOCoroutineScope : CoroutineScope

public fun IOCoroutineScope(
    job: Job = SupervisorJob()
): IOCoroutineScope = object : IOCoroutineScope {
    override val coroutineContext = job + Dispatchers.IO
}

public fun IOCoroutineScope(
    context: CoroutineContext
): IOCoroutineScope = object : IOCoroutineScope {
    override val coroutineContext = context + Dispatchers.IO
}