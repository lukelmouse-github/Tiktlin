package com.qxy.tiktlin

import com.qxy.tiktlin.common.network.Api
import com.qxy.tiktlin.common.network.TikRetrofit
import com.qxy.tiktlin.common.network.config.AppConfig

object Repository {
    private val retrofit = TikRetrofit.initConfig(AppConfig.BASE_URL)
    val Api = retrofit.create<Api>()
    suspend fun getAccessToken(authCode: String) = Api.getAccessToken(AppConfig.CLIENT_SECRET, authCode, AppConfig.AUTHORIZATION_CODE, AppConfig.CLIENT_KEY)
    suspend fun getUserInfo(open_id: String, accessToken: String) = Api.getUserInfo(accessToken, open_id)

}