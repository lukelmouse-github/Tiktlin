package com.qxy.tiktlin.model.datasource.network

import com.qxy.tiktlin.data.netData.VideoData
import com.qxy.tiktlin.data.netData.VideoList
import com.qxy.tiktlin.data.netData.*
import retrofit2.http.*

// https://open.douyin.com/platform/resource/docs/openapi/account-permission/get-access-token
interface ApiService {

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

    @GET("/fans/list/")
    suspend fun getUserFans(
        @Header("access-token") access_token: String,
        @Query("open_id") open_id: String,
        @Query("cursor") cursor: Long,
        @Query("count") count: Int
    ): Fans

    @GET("/following/list/")
    suspend fun getUserFollow(
        @Header("access-token") access_token: String,
        @Query("open_id") open_id: String,
        @Query("cursor") cursor: Long,
        @Query("count") count: Int
    ): Follows

    @GET("/video/list/")
    suspend fun getVideoList(
        @Header("access-token") access_token: String,
        @Query("open_id") open_id: String,
        @Query("cursor") cursor: Long?,
        @Query("count") count: Int
    ): VideoList

    @POST("/video/data/")
    suspend fun getVideoData(
        @Header("access-token") access_token: String,
        @Query("open_id") open_id: String,
        @Body item_ids: VideoData.QueryBody
    ): VideoData
}
