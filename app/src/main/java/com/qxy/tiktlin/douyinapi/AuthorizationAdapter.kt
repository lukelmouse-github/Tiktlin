package com.qxy.tiktlin.douyinapi

import android.app.Activity
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.drake.serialize.serialize.deserialize
import com.qxy.common.network.config.AppConfig
import com.qxy.tiktlin.douyinapi.AuthorizationAdapter.AuthCallback
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object AuthorizationAdapter {

    private fun interface AuthCallback {
        fun onResponse(result: Result<String>)
    }

    private lateinit var mCallback: AuthCallback

    fun emitAuthCode(response: Authorization.Response) {
        if (response.isSuccess) {
            mCallback.onResponse(Result.success(response.authCode))
        } else {
            val err = RuntimeException("错误码 ${response.errorCode} 错误信息 ${response.errorMsg}")
            mCallback.onResponse(Result.failure(err))
        }
    }

    suspend fun fetchAuthCode(activity: Activity): Result<String> {
        val stored = deserialize<String?>("authCode")
        if (stored != null) return Result.success(stored)
        DouYinOpenApiFactory.init(DouYinOpenConfig(AppConfig.CLIENT_KEY))
        val douyinOpenApi: DouYinOpenApi = DouYinOpenApiFactory.create(activity)
        val request: Authorization.Request = Authorization.Request()
        request.scope = "user_info" // 用户授权时必选权限
        return suspendCoroutine { cont ->
            mCallback = AuthCallback { result -> cont.resume(result) }
            douyinOpenApi.authorize(request)
        }
    }
}
