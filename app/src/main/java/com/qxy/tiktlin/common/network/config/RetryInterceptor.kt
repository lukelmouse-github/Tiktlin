package com.qxy.tiktlin.common.network.config

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RetryInterceptor(private val maxRetry: Int = 0) : Interceptor {
    private var retriedNum: Int = 0
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        Log.d("RetryInterceptor", "当前 retriedNum=$retriedNum")
        var response = chain.proceed(request)
        while (!response.isSuccessful && retriedNum < maxRetry) {
            retriedNum ++
            Log.d("RetryInterceptor", "当前 retriedNum=$retriedNum")
            response = chain.proceed(request)
        }
        return response
    }
}