package com.qxy.tiktlin.data.netData

import java.io.Serializable

data class IesVideoData(val item_list: List<Item>) : Serializable {
    data class Item(val author: Author, val video: Video)
    data class Author(val nickname: String) : Serializable
    data class Video(val play_addr: PlayAddr) : Serializable
    data class PlayAddr(val url_list: List<String>) : Serializable
}
