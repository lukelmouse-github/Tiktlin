package com.qxy.tiktlin.ui.vm

import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.VideoList
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.util.DateUtils
import com.qxy.tiktlin.widget.BaseViewModel
import com.qxy.tiktlin.widget.appInstance

class VideoDetailViewModel(private val video: VideoList.Video) : BaseViewModel() {

    class Factory(private val video: VideoList.Video) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VideoDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VideoDetailViewModel(video) as T
            } else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    class ViewData(
        val title: String,
        val account: ObservableField<String?>,
        val createTime: ObservableField<String?>,
        val likes: String,
        val comments: String,
        val downloads: String,
        val forwards: String,
        val plays: String,
        val shares: String
    )

    val viewData = ViewData(
        title = video.title,
        account = ObservableField(),
        createTime = ObservableField(),
        likes = parseNumber(video.statistics.digg_count, R.string.like),
        comments = parseNumber(video.statistics.comment_count, R.string.first_comment),
        downloads = parseNumber(video.statistics.download_count),
        forwards = parseNumber(video.statistics.forward_count),
        plays = parseNumber(video.statistics.play_count),
        shares = parseNumber(video.statistics.share_count)
    )

    suspend fun getVideoUrl(): String {
        val iesVideoData = Repository.getIesVideoData(video.video_id)
        viewData.account.set(iesVideoData.author.nickname)
        viewData.createTime.set("·  " + DateUtils.transToString(video.create_time))
        return iesVideoData.video.play_addr.url_list[0]
    }

    private fun parseNumber(number: Int, @StringRes zero: Int = 0) = when (number) {
        0 -> if (zero == 0) "0" else appInstance.getString(zero)
        else -> number.toString()
    }
}
