package com.qxy.tiktlin.data.netData

import java.io.Serializable

data class Follows(
    val data: Data,
    val extra: Extra
): Serializable {
    data class Data(
        val list: List<User>,
        val has_more: Boolean = false,
        val cursor: Long = 0
    ): Serializable {
        data class User(
            val nickname: String = "",
            val openId: String = "",
            val avatar: String = "",
            val gender: Int = 0,
            val country: String = "",
            val province: String = "",
            val city: String = "",
        )
    }
}