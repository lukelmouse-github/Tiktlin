package com.qxy.tiktlin.data.netData

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
    val data: Data
): Serializable {
    data class Data (
        val avatar: String = "",
        val country: String = "中国",
        // 性别: * `0` - 未知 * `1` - 男性 * `2` - 女性
        val gender: Long = 0,
        val nickname: String = "未命名",
        val open_id: String = "",
        val province: String = "北京",
        val city: String = "北京",
        // 错误码描述
        val description: String = "",
        val e_account_role: String = "",
        val error_code: Long = -1,
        val union_id: String = ""
    ): Serializable
}