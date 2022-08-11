package com.qxy.tiktlin.common.network.config

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class DefaultInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()

        // Todo 公共参数


        // Todo Token


        val newBuilder = originRequest.newBuilder()
            .cacheControl(CacheControl.FORCE_NETWORK) // 强制使用网络

        return chain.proceed(newBuilder.build())
    }
}