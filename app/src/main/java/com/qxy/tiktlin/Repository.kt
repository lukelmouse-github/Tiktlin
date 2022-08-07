package com.qxy.tiktlin

import com.qxy.common.network.Api
import com.qxy.common.network.TikRetrofit
import com.qxy.common.network.config.AppConfig

object Repository {
    private val retrofit = TikRetrofit.initConfig(AppConfig.BASE_URL)
    val Api = retrofit.create<Api>()
    suspend fun getAccessToken(authCode: String) = Api.getAccessToken(AppConfig.CLIENT_SECRET, authCode, "authorization_code", AppConfig.CLIENT_KEY)

}