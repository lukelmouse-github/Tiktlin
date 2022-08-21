package com.qxy.tiktlin.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.drake.logcat.LogCat
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentVideoDetailBinding
import com.qxy.tiktlin.ui.vm.VideoDetailViewModel
import com.qxy.tiktlin.util.navController
import com.qxy.tiktlin.util.setupToolbar
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.widget.VideoPagerAdapter

class VideoDetailFragment : BaseFragment<FragmentVideoDetailBinding>(R.layout.fragment_video_detail) {

    private val viewModel by viewModels<VideoDetailViewModel>() {
        val args by navArgs<VideoDetailFragmentArgs>()
        VideoDetailViewModel.Factory(args.videos)
    }

    private var currentPage = 0

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            LogCat.d("onPageSelected: $position")
            currentPage = position
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            LogCat.d("onPageScrollStateChanged: $state, currentPage = $currentPage")
            when (state) {
                ViewPager2.SCROLL_STATE_IDLE -> viewModel.players[currentPage]!!.play()
                ViewPager2.SCROLL_STATE_DRAGGING -> viewModel.players[currentPage]!!.pause()
            }
        }
    }

    override fun initView() {
        setupToolbar(
            toolbar = binding.toolbar,
            title = null,
            navigationIcon = R.drawable.baseline_arrow_back_ios_24,
            navigationOnClick = { navController.navigateUp() }
        )

        val adapter = VideoPagerAdapter(viewModel)
        binding.viewPager.adapter = adapter
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallback)
    }
}
