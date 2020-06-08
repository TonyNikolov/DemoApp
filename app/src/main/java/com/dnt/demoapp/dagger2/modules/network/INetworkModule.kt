package com.dnt.demoapp.dagger2.modules.network

import android.app.Application
import com.dnt.demoapp.api.DemoAppApi
import com.dnt.demoapp.dagger2.NetworkConnectionInterceptor
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.Executor

interface INetworkModule {
    fun provideOkHttpClient(
        application: Application,
        interceptor: Interceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient

    fun provideDemoAppApi(gson: Gson, okHttpClient: OkHttpClient): DemoAppApi
}
