package com.qxy.tiktlin.common.rsp

import java.io.Serializable

//{
//    "data": {
//    "access_token": "access_token",
//    "description": "",
//    "error_code": "0",
//    "expires_in": "7200"
//},
//    "message": "<nil>"
//}
data class ClientToken(
    val data: Data,
    val message: String = "0"
): Serializable {
    data class Data (
        val access_token: String = "",
        val description: String = "",
        val error_code: Long = 0,
        val expires_in: Long = 0
    )
}