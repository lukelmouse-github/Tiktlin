package com.qxy.tiktlin.ui.vm

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlayer
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.VideoPlay
import com.qxy.tiktlin.util.DateUtils
import com.qxy.tiktlin.widget.BaseViewModel
import com.qxy.tiktlin.widget.appInstance

class VideoDetailViewModel(val videos: Array<VideoPlay>) : BaseViewModel() {

    class Factory(private val videos: Array<VideoPlay>) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideoDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoDetailViewModel(videos) as T
            } else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    data class ViewData(
        val title: String,
        val account: String,
        val createTime: String,
        val likes: String,
        val comments: String,
        val downloads: String,
        val forwards: String,
        val plays: String,
        val shares: String
    )

    val viewDatum = Array(videos.size) {
        val videoPlay = videos[it]
        ViewData(
            title = videoPlay.video.title,
            account = videoPlay.author,
            createTime = "·  " + DateUtils.transToString(videoPlay.video.create_time),
            likes = parseNumber(videoPlay.video.statistics.digg_count, R.string.like),
            comments = parseNumber(videoPlay.video.statistics.comment_count, R.string.first_comment),
            downloads = parseNumber(videoPlay.video.statistics.download_count),
            forwards = parseNumber(videoPlay.video.statistics.forward_count),
            plays = parseNumber(videoPlay.video.statistics.play_count),
            shares = parseNumber(videoPlay.video.statistics.share_count)
        )
    }

    val players = Array<ExoPlayer?>(videos.size) { null }

    private fun parseNumber(number: Int, @StringRes zero: Int = 0) = when (number) {
        0 -> if (zero == 0) "0" else appInstance.getString(zero)
        else -> number.toString()
    }
}
