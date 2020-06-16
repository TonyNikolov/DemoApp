package com.dnt.demoapp

import com.dnt.demoapp.dagger.DaggerTestAppComponent
import com.dnt.demoapp.dagger.TestAppComponent
import com.dnt.demoapp.dagger2.modules.ContextModule

class TestDemoApp : DemoApp() {

    lateinit var testAppComponent: TestAppComponent

    override fun initAppComponent() {
        testAppComponent = DaggerTestAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()

        appComponent = testAppComponent
    }
}