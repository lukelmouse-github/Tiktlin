package com.qxy.tiktlin.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.netData.VideoList
import com.qxy.tiktlin.data.netData.VideoPlay
import com.qxy.tiktlin.databinding.FragmentVideoSimpleBinding
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.util.navController
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.widget.VideoListAdapter
import kotlinx.coroutines.launch

class VideoSimpleFragment(): BaseFragment<FragmentVideoSimpleBinding>(R.layout.fragment_video_simple), VideoListAdapter.OnVideoClickListener{
    private var tagName: String = "作品"

    private lateinit var videoListAdapter : VideoListAdapter

    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("tagName")?.let {
            tagName = it
        }
    }
    override fun initView() {
        super.initView()
        videoListAdapter = VideoListAdapter()
        videoListAdapter.setVideoClickListener(this)
        gridLayoutManager = GridLayoutManager(context,3)
        binding.videoListRecycler.layoutManager = gridLayoutManager
        binding.videoListRecycler.adapter = videoListAdapter
        lifecycleScope.launch {
            val list = Repository.getVideoList(count = 20).list
            videoListAdapter.setData(list)
        }

    }

    override fun initData() {
        super.initData()
    }

    companion object {
        @JvmStatic
        fun newInstance(tagName: String) = VideoSimpleFragment().apply {
            arguments = Bundle().apply {
                putString("tagName", tagName)
            }
        }
    }

    override fun onItemClick(video: VideoList.Video) {
        lifecycleScope.launch {
            val lists = listOf<VideoPlay?>(Repository.getVideoPlay(video))
            val videos = lists.filterNotNull().toTypedArray()
            val args = VideoDetailFragmentArgs(videos)
            navController.navigate(R.id.nav_video_detail, args.toBundle())
        }
    }

}