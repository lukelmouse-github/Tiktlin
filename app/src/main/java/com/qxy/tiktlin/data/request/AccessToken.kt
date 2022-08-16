package com.qxy.tiktlin.common.rsp

import java.io.Serializable


/*
{
  "data": {
    "access_token": "access_token",
    "description": "",
    "error_code": "0",
    "expires_in": "86400",
    "open_id": "aaa-bbb-ccc",
    "refresh_expires_in": "86400",
    "refresh_token": "refresh_token",
    "scope": "user_info"
  },
  "message": "<nil>"
}
 */
// https://open.douyin.com/platform/doc?doc=docs/openapi/account-permission/get-access-token

data class AccessToken (
    val data: Data,
    val message: String = ""
): Serializable {
    data class Data (
        val error_code: Long = -1,
        val expires_in: Long = -1,
        val open_id: String = "",
        val refresh_expires_in: Long = -1,
        val refresh_token: String = "",
        val scope: String = "",
        val access_token: String = "",
        val description: String = ""
    ): Serializable
}

