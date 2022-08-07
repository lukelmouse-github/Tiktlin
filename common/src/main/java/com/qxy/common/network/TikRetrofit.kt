package com.qxy.common.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.qxy.common.network.config.DefaultInterceptor
import com.qxy.common.network.config.LocalCookieJar
import com.qxy.common.network.config.LogInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object TikRetrofit {
    private val mOkClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .cookieJar(LocalCookieJar())
        .addNetworkInterceptor(DefaultInterceptor())
        .addNetworkInterceptor(LogInterceptor {
            logLevel(LogInterceptor.LogLevel.BODY)
        })
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(mOkClient)

    private var retrofit: Retrofit? = null

    fun initConfig(baseUrl: String, okClient: OkHttpClient = mOkClient): TikRetrofit {
        retrofit = retrofitBuilder.baseUrl(baseUrl).client(okClient).build()
        return this
    }

    fun <T> getService(serviceClazz: Class<T>): T {
        if (retrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit 未初始化，需要配置 baseUrl")
        } else {
            return retrofit!!.create(serviceClazz)
        }
    }
}