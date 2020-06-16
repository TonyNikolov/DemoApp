package com.dnt.demoapp.dagger

import com.dnt.demoapp.TestDemoApp
import com.dnt.demoapp.dagger2.AppComponent
import com.dnt.demoapp.dagger2.modules.ContextModule
import com.dnt.demoapp.dagger2.modules.authentication.AuthenticationModule
import com.dnt.demoapp.dagger2.modules.coroutines.CoroutineScopeModule
import com.dnt.demoapp.dagger2.modules.network.NetworkModule
import com.dnt.demoapp.dagger2.modules.persistence.PersistenceModule
import com.dnt.demoapp.dagger2.modules.viewmodel.ViewModelModule
import com.dnt.demoapp.features.MainActivityTests
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, PersistenceModule::class, ViewModelModule::class, AuthenticationModule::class, CoroutineScopeModule::class])
interface TestAppComponent : AppComponent {
    fun inject(demoApp: TestDemoApp)
    fun inject(mainActivityTests: MainActivityTests)
}