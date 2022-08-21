package com.qxy.tiktlin.data.netData

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoPlay(
    val video: VideoList.Video,
    val author: String,
    val url: String?
) : Parcelable
