package com.dnt.demoapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner


class TestDemoAppRunner : AndroidJUnitRunner() {
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(
        cl: ClassLoader,
        className: String,
        context: Context
    ): Application {
        return super.newApplication(cl, TestDemoApp::class.java.name, context)
    }
}
