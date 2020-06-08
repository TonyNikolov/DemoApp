package com.dnt.demoapp.dagger2.modules.network

import android.content.Context
import android.security.KeyChain.getPrivateKey
import com.dnt.demoapp.api.API_PRIVATE_KEY
import com.dnt.demoapp.api.API_PUBLIC_KEY
import com.dnt.demoapp.dagger2.NetworkConnectionInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Singleton


@Module
class InternalNetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
        gsonBuilder.setLenient()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor {
            val request: Request = it.request()
            val timeStamp = System.currentTimeMillis().toString()
            val hashInput: String = timeStamp + API_PRIVATE_KEY + API_PUBLIC_KEY
            val url: HttpUrl = request.url().newBuilder()
                .addQueryParameter("apikey", API_PUBLIC_KEY)
                .addQueryParameter("ts", timeStamp)
                .addQueryParameter("hash", hashInput.md5())
                .build()
            val builder = request.newBuilder()
                .url(url)


            it.proceed(builder.build())
        }
    }

    @Provides
    @Singleton
    fun providesNetworkConnectionInterceptor(context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}
