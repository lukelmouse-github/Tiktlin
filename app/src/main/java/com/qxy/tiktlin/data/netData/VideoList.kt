package com.qxy.tiktlin.data.netData

import java.io.Serializable

data class VideoList(
    val data: Data,
    val extra: Extra
) : Serializable {
    data class Data(
        val cursor: Long,
        val has_more: Boolean,
        val list: List<VideoData.Video>
    ) : Serializable
}
