package com.qxy.tiktlin.data.netData

import java.io.Serializable

data class VideoList(
    val data: Data,
    val extra: Extra
) : Serializable {
    data class Data(
        val cursor: Long,
        val has_more: Boolean,
        val list: List<Video>
    ) : Serializable

    data class Video(
        val video_status: Int,
        val title: String,
        val cover: String,
        val is_top: Boolean,
        val create_time: Long,
        val item_id: String,
        val is_reviewed: Boolean,
        val video_id: Long,
        val share_url: String,
        val media_type: Int,
        val statistics: Statistics
    ) : Serializable {
        data class Statistics(
            val share_count: Int,
            val forward_count: Int,
            val comment_count: Int,
            val digg_count: Int,
            val download_count: Int,
            val play_count: Int
        ) : Serializable
    }
}
