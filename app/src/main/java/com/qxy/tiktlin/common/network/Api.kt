package com.qxy.tiktlin.common.network

import com.qxy.tiktlin.common.model.AccessToken
import com.qxy.tiktlin.common.model.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

// Todo 接口信息
// https://open.douyin.com/platform/resource/docs/openapi/account-permission/get-access-token
interface Api {

    @FormUrlEncoded
    @POST("oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_secret") client_secret: String,
        @Field("code") code: String,
        @Field("grant_type") grant_type: String,
        @Field("client_key") client_key: String
    ): AccessToken

    @FormUrlEncoded
    @POST("/oauth/userinfo/")
    suspend fun getUserInfo(
        @Field("access_token") access_token: String,
        @Field("open_id") open_id: String
    ): UserInfo
}