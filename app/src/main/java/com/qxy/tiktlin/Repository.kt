package com.qxy.tiktlin

import com.qxy.tiktlin.common.network.Api
import com.qxy.tiktlin.common.network.TikRetrofit
import com.qxy.tiktlin.common.network.config.AppConfig
import com.qxy.tiktlin.db.TikDatabase
import com.qxy.tiktlin.db.entity.User

object Repository {
    private val db = TikDatabase.appDatabase
    private val retrofit = TikRetrofit.initConfig(AppConfig.BASE_URL)
    val Api = retrofit.create<Api>()
    suspend fun getAccessToken(authCode: String) = Api.getAccessToken(AppConfig.CLIENT_SECRET, authCode, AppConfig.AUTHORIZATION_CODE, AppConfig.CLIENT_KEY)
    suspend fun getUserInfo(open_id: String, accessToken: String) = Api.getUserInfo(accessToken, open_id)
    suspend fun getUser(open_id: String): User {
        val user = db.userDao().getUser(open_id)
        if (user != null) {
            return user
        }
        val userInfo = getUserInfo(open_id, AppConfig.ACCESS_TOKEN)
        val new_user = User(
            name = userInfo.data.nickname,
            avatar = userInfo.data.avatar,
            openId = userInfo.data.open_id,
            gender = userInfo.data.gender,
            country = userInfo.data.country,
            province = userInfo.data.province,
            city = userInfo.data.city
        )
        db.userDao().insertUser(new_user)
        return new_user
    }
}