package com.qxy.common.network

import androidx.lifecycle.LiveData
import com.qxy.common.model.AccessToken
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

// Todo 接口信息
// https://open.douyin.com/platform/resource/docs/openapi/account-permission/get-access-token
interface Api {

    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_secret") client_secret: String,
        @Field("code") code: String,
        @Field("grant_type") grant_type: String,
        @Field("client_key") client_key: String
    ): AccessToken


}