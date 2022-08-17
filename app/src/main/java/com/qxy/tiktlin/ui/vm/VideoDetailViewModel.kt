package com.qxy.tiktlin.ui.vm

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.VideoData
import com.qxy.tiktlin.util.DateUtils
import com.qxy.tiktlin.widget.BaseViewModel
import com.qxy.tiktlin.widget.appInstance

class VideoDetailViewModel(val video: VideoData.Video) : BaseViewModel() {

    class Factory(private val video: VideoData.Video) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideoDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoDetailViewModel(video) as T
            } else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    class ViewData(
        val title: String,
        val createTime: String,
        val likes: String,
        val comments: String,
        val downloads: String,
        val forwards: String,
        val plays: String,
        val shares: String
    )

    val viewData = ViewData(
        title = video.title,
        createTime = "·  " + DateUtils.transToString(video.create_time),
        likes = parseNumber(video.statistics.digg_count, R.string.like),
        comments = parseNumber(video.statistics.comment_count, R.string.first_comment),
        downloads = parseNumber(video.statistics.download_count),
        forwards = parseNumber(video.statistics.forward_count),
        plays = parseNumber(video.statistics.play_count),
        shares = parseNumber(video.statistics.share_count)
    )

    private fun parseNumber(number: Int, @StringRes zero: Int = 0) = when (number) {
        0 -> if (zero == 0) "0" else appInstance.getString(zero)
        else -> number.toString()
    }
}
