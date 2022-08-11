package com.qxy.tiktlin.common.model

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
    val data: Data? = null,
    val message: String? = null
): Serializable {
    data class Data (
        val error_code: Long? = null,
        val expires_in: Long? = null,
        val open_id: String? = null,
        val refresh_expires_in: Long? = null,
        val refresh_token: String? = null,
        val scope: String? = null,
        val access_token: String? = null,
        val description: String? = null
    ): Serializable
}

