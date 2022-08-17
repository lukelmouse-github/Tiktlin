package com.qxy.tiktlin.model.datasource.network

import com.qxy.tiktlin.data.config.AppConfig


/**
调用示例
viewModelScope.launch {
try {
    val FansResult = NetReasouse.getFans()
    _status.value = FansResult
    } catch (e: Exception) {
    _status.value = "Failure: ${e.message}"
    }
 }
 */

object NetDataSource {
    private val retrofit = ApiRetrofit.initConfig(AppConfig.BASE_URL)
    val ApiService = ApiRetrofit.create<ApiService>()
    suspend fun getAccessToken(authCode: String) = ApiService.getAccessToken(AppConfig.CLIENT_SECRET, authCode, AppConfig.AUTHORIZATION_CODE, AppConfig.CLIENT_KEY)
    suspend fun getUserInfo(open_id: String = AppConfig.OPEN_ID, accessToken: String = AppConfig.ACCESS_TOKEN) = ApiService.getUserInfo(accessToken, open_id)
    suspend fun getClientToken() = ApiService.getClientToken(AppConfig.CLIENT_KEY, AppConfig.CLIENT_SECRET, "client_credential")
    suspend fun getRankList(type: Int) = ApiService.getRank(AppConfig.CLIENT_TOKEN ,type)
    suspend fun getTotalFans() = ApiService.getUserFans(AppConfig.ACCESS_TOKEN, AppConfig.OPEN_ID, 0, 1).data.total
    suspend fun getFans(cursor: Long, count: Int) = ApiService.getUserFans(AppConfig.ACCESS_TOKEN, AppConfig.OPEN_ID, cursor, count)
    suspend fun getFollows(cursor: Long, count: Int) = ApiService.getUserFollow(AppConfig.ACCESS_TOKEN, AppConfig.OPEN_ID, cursor, count).data.total

}