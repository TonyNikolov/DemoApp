package com.dnt.demoapp

import com.dnt.demoapp.dagger2.modules.coroutines.IOCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@ExperimentalCoroutinesApi
interface TestPolymorphicCoroutineScope : TestCoroutineScope,
    IOCoroutineScope

@ExperimentalCoroutinesApi
class TestPolymorphicCoroutineScopeImpl(
    context: CoroutineContext = EmptyCoroutineContext
) : TestPolymorphicCoroutineScope,
    // delegates all functionality to TestCoroutineScope
    // uses the TestCoroutineScope factory function
    // to inject the TestCoroutineDispatcher and TestCoroutineExceptionHandler into the context
    TestCoroutineScope by TestCoroutineScope(context)

@ExperimentalCoroutinesApi
fun TestPolymorphicCoroutineScope(
    context: CoroutineContext = EmptyCoroutineContext
): TestPolymorphicCoroutineScope = TestPolymorphicCoroutineScopeImpl(
    context = context + Dispatchers.Main
)