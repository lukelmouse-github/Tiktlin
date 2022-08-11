package com.qxy.tiktlin.common.network

import com.qxy.tiktlin.common.network.config.DefaultInterceptor
import com.qxy.tiktlin.common.network.config.LocalCookieJar
import com.qxy.tiktlin.common.network.config.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object TikRetrofit {
    private val mOkClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .cookieJar(LocalCookieJar())
        .addNetworkInterceptor(DefaultInterceptor())
        .addNetworkInterceptor(LogInterceptor {
            logLevel(LogInterceptor.LogLevel.BODY)
        })
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .client(mOkClient)

    private var retrofit: Retrofit? = null

    fun initConfig(baseUrl: String, okClient: OkHttpClient = mOkClient): TikRetrofit {
        retrofit = retrofitBuilder.baseUrl(baseUrl).client(okClient).build()
        return this
    }


    fun <T> create(serviceClass: Class<T>): T = retrofit!!.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}