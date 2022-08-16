package com.qxy.tiktlin.common.rsp

import java.io.Serializable

// https://open.douyin.com/platform/doc?doc=docs/openapi/data-open-service/douyin-tv-tops-data/get-douyin-tv-tops

data class RankList(
    val data: Data,
    val extra: Extra
): Serializable {
    data class Data(
        val active_time: String = "-1",
        val description: String = "-1",
        val error_code: Long = -1,
        val list: List<RankItem> = listOf()
    ): Serializable {
        data class RankItem(
            val actors: List<String> = listOf(),
            val name: String = "",
            val areas: List<String> = listOf(),
            val directors: List<String> = listOf(),
            val id: String = "",
            val discussion_hot: Long = 0,
            val search_hot: Long = 0,
            val release_data: String = "",
            val type: Int = 0,
            val hot: Long = 0,
            val poster: String = "",
            val tags: List<String> = listOf()
        )
    }
}