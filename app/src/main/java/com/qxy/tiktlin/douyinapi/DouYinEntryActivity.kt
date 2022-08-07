package com.qxy.tiktlin.douyinapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.bytedance.sdk.open.aweme.CommonConstants
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.aweme.common.handler.IApiEventHandler
import com.bytedance.sdk.open.aweme.common.model.BaseReq
import com.bytedance.sdk.open.aweme.common.model.BaseResp
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.drake.serialize.serialize.serialize

class DouYinEntryActivity : Activity(), IApiEventHandler {
    private lateinit var douYinOpenApi: DouYinOpenApi
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        douYinOpenApi = DouYinOpenApiFactory.create(this)
        douYinOpenApi.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {}
    override fun onResp(resp: BaseResp) {
        // 授权成功可以获得authCode
        if (resp.type == CommonConstants.ModeType.SEND_AUTH_RESPONSE) {
            val response = resp as Authorization.Response
            if (resp.isSuccess()) {
                Toast.makeText(
                    this, "授权成功，获得权限：" + response.grantedPermissions,
                    Toast.LENGTH_LONG
                ).show()
                // authCode 存储
                serialize("authCode" to resp.authCode)
            } else {
                Toast.makeText(
                    this, "授权失败" + response.grantedPermissions,
                    Toast.LENGTH_LONG
                ).show()
            }
            finish()
        }
    }

    override fun onErrorIntent(intent: Intent?) {
        // 错误数据
        Toast.makeText(this, "intent出错啦", Toast.LENGTH_LONG).show()
        finish()
    }
}