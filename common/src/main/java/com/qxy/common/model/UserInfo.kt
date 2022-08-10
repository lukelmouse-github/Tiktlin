package com.qxy.common.model

import java.io.Serializable

/*
{
  "data": {
    "avatar": "https://example.com/x.jpeg",
    "city": "上海",
    "country": "中国",
    "description": "",
    "e_account_role": "<nil>",
    "error_code": "0",
    "gender": "<nil>",
    "nickname": "张伟",
    "open_id": "0da22181-d833-447f-995f-1beefea5bef3",
    "province": "上海",
    "union_id": "1ad4e099-4a0c-47d1-a410-bffb4f2f64a4"
  }
}

 */
// https://open.douyin.com/platform/doc?doc=docs/openapi/account-management/get-account-open-info
data class UserInfo(
    val data: Data? = null
): Serializable {
    data class Data (
        val avatar: String? = null,
        val country: String? = null,
        val gender: Long? = null,
        val nickname: String? = null,
        val open_id: String? = null,
        val province: String? = null,
        val city: String? = null,
        val description: String? = null,
        val e_account_role: String? = null,
        val error_code: Long? = null,
        val union_id: String? = null
    ): Serializable
}