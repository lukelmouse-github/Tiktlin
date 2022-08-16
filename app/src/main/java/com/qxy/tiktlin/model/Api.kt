package com.qxy.tiktlin.model

import com.qxy.tiktlin.common.rsp.AccessToken
import com.qxy.tiktlin.common.rsp.ClientToken
import com.qxy.tiktlin.common.rsp.RankList
import com.qxy.tiktlin.common.rsp.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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

    @FormUrlEncoded
    @POST("/oauth/client_token/")
    suspend fun getClientToken(
        @Field("client_key") client_key: String,
        @Field("client_secret") client_secret: String,
        @Field("grant_type") grant_type: String,
    ): ClientToken

    @GET("/discovery/ent/rank/item/")
    suspend fun getRank(
        @Header("access-token") access_token: String,
        @Query("type") type: Int
    ): RankList
}