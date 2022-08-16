package com.qxy.tiktlin.widget

import com.qxy.tiktlin.model.Api
import com.qxy.tiktlin.model.TikRetrofit
import com.qxy.tiktlin.data.config.AppConfig
import com.qxy.tiktlin.db.TikDatabase
import com.qxy.tiktlin.db.entity.User

object Repository {
    private val db = TikDatabase.appDatabase
    private val retrofit = TikRetrofit.initConfig(AppConfig.BASE_URL)
    val Api = retrofit.create<Api>()
    suspend fun getAccessToken(authCode: String) = Api.getAccessToken(AppConfig.CLIENT_SECRET, authCode, AppConfig.AUTHORIZATION_CODE, AppConfig.CLIENT_KEY)
    suspend fun getUserInfo(open_id: String = AppConfig.OPEN_ID, accessToken: String = AppConfig.ACCESS_TOKEN) = Api.getUserInfo(accessToken, open_id)
    suspend fun getUser(open_id: String = AppConfig.OPEN_ID): User {
        return TikDatabase.appDatabase.userDao().getUser(open_id)
    }

    suspend fun getClientToken() = Api.getClientToken(AppConfig.CLIENT_KEY, AppConfig.CLIENT_SECRET, "client_credential")
    suspend fun getRankList(type: Int) = Api.getRank(AppConfig.CLIENT_TOKEN ,type)

    suspend fun getTotalFans() = Api.getUserFans(AppConfig.ACCESS_TOKEN, AppConfig.OPEN_ID, 0, 1).data.total
    suspend fun getFans(cursor: Long, count: Int) = Api.getUserFans(AppConfig.ACCESS_TOKEN, AppConfig.OPEN_ID, cursor, count)

    suspend fun getFollows(cursor: Long, count: Int) = Api.getUserFollow(AppConfig.ACCESS_TOKEN, AppConfig.OPEN_ID, cursor, count).data.total

}