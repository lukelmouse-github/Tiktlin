package com.qxy.tiktlin.model.datasource.network

import com.qxy.tiktlin.data.config.DefaultInterceptor
import com.qxy.tiktlin.data.config.LocalCookieJar
import com.qxy.tiktlin.data.config.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

// Todo
// 1. 网络请求如何取消？
//    在页面关闭对时候触发，最好捕获作用域内的错误
// 2. 网络请求缓存 https://cloud.tencent.com/developer/article/1735697
//    https://www.jianshu.com/p/e0dd6791653d
object ApiRetrofit {
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

    fun initConfig(baseUrl: String, okClient: OkHttpClient = mOkClient): ApiRetrofit {
        retrofit = retrofitBuilder.baseUrl(baseUrl).client(okClient).build()
        return this
    }


    fun <T> create(serviceClass: Class<T>): T = retrofit!!.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}