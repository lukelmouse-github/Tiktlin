package com.qxy.tiktlin.douyinapi

import android.app.Activity
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.qxy.tiktlin.common.network.config.AppConfig
import com.qxy.tiktlin.douyinapi.AuthorizationAdapter.AuthCallback
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object AuthorizationAdapter {

    private fun interface AuthCallback {
        fun onResponse(result: Result<String>)
    }

    private lateinit var mCallback: AuthCallback

    fun emitAuthCode(response: Authorization.Response) {
        if (response.isSuccess) {
            Timber.d("Success     authCode:${response.authCode}, errorCode:${response.errorCode}, grantedPermissions:${response.grantedPermissions}")
            mCallback.onResponse(Result.success(response.authCode))
        } else {
            Timber.d("Error     authCode:${response.authCode}, errorCode:${response.errorCode}, grantedPermissions:${response.grantedPermissions}")
            val err = RuntimeException("错误码 ${response.errorCode} 错误信息 ${response.errorMsg}")
            mCallback.onResponse(Result.failure(err))
        }
    }

    suspend fun fetchAuthCode(activity: Activity): Result<String> {
        DouYinOpenApiFactory.init(DouYinOpenConfig(AppConfig.CLIENT_KEY))
        val douyinOpenApi: DouYinOpenApi = DouYinOpenApiFactory.create(activity)
        val request: Authorization.Request = Authorization.Request()

        // 用户授权权限Scope，用逗号隔开
        request.scope = "user_info, trial.whitelist, following.list, fans.list, fans.check, " +
            "item.comment, data.external.item, discovery.ent, aweme.share, " +
            "im.share"
        return suspendCoroutine { cont ->
            mCallback = AuthCallback { result -> cont.resume(result) }
            douyinOpenApi.authorize(request)
        }
    }
}
