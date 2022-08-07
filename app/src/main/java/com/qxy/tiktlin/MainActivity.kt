package com.qxy.tiktlin

import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi
import com.drake.serialize.serialize.deserialize
import com.qxy.common.base.BaseActivity
import com.qxy.common.network.config.AppConfig
import com.qxy.tiktlin.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initConfig() {
        super.initConfig()
        douyinApi()
        // authCode 读取
        val authCode: String = deserialize("authCode")


    }

    private fun douyinApi() {
        DouYinOpenApiFactory.init(DouYinOpenConfig(AppConfig.CLIENT_KEY))
        val douyinOpenApi: DouYinOpenApi = DouYinOpenApiFactory.create(this)
        val request: Authorization.Request = Authorization.Request()
        request.scope = "user_info" // 用户授权时必选权限
        douyinOpenApi.authorize(request)
    }
}