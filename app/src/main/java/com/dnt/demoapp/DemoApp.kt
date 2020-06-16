package com.dnt.demoapp

import android.app.Application
import android.text.SpannableStringBuilder
import android.util.Log
import com.dnt.demoapp.dagger2.AppComponent
import com.dnt.demoapp.dagger2.DaggerAppComponent
import com.dnt.demoapp.dagger2.modules.ContextModule
import com.dnt.demoapp.dagger2.modules.network.NetworkModule
import timber.log.Timber
import java.io.PrintWriter
import java.io.StringWriter

open class DemoApp : Application() {
    companion object {
        class ProductionTree : Timber.Tree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                if (priority == Log.ERROR) reportMessage(message)
            }
        }

        // TODO (Add third party message reporting)
        @JvmStatic
        fun reportMessage(message: String, priority: String = "error") {
        }

        // TODO (Add third party crash reporting)
        @JvmStatic
        fun reportException(throwable: Throwable, description: String, level: String = "critical") {
        }
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Thread.setDefaultUncaughtExceptionHandler { _, e ->
                reportException(
                    e,
                    formatUncaughtException(e)
                )
            }
            Timber.plant(ProductionTree())
        }

        initAppComponent()
    }

    open fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()

    }

    private fun formatUncaughtException(e: Throwable): String {
        val sw = StringWriter()
        e.printStackTrace(PrintWriter(sw))

        return SpannableStringBuilder().append("Uncaught Exception: ").append(e.localizedMessage)
            .append("\nStack Trace:\n").append(sw.toString()).toString()
    }
}