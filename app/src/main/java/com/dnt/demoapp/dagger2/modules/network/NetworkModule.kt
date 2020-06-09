package com.dnt.demoapp.dagger2.modules.network

import android.app.Application
import com.dnt.demoapp.BuildConfig
import com.dnt.demoapp.api.DemoAppApi
import com.dnt.demoapp.dagger2.NetworkConnectionInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [InternalNetworkModule::class])
class NetworkModule : INetworkModule {
    companion object {
        private const val CACHE_SIZE = 10L * 1024 * 1024   //10MB
        private const val TIMEOUT = 30L    // Seconds
    }

    @Provides
    @Singleton
    override fun provideOkHttpClient(
        application: Application,
        interceptor: Interceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.cache(Cache(application.cacheDir, CACHE_SIZE))
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS).connectTimeout(TIMEOUT, TimeUnit.SECONDS)

        builder.interceptors().add(interceptor)
        builder.interceptors().add(networkConnectionInterceptor)

        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.networkInterceptors().add(httpLoggingInterceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    override fun provideDemoAppApi(
        gson: Gson, okHttpClient: OkHttpClient
    ): DemoAppApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.MARVEL_API_ENDPOINT)
            .client(okHttpClient)
            .build()
            .create(DemoAppApi::class.java)
    }
}