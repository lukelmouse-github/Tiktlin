package com.qxy.tiktlin.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.drake.logcat.LogCat
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentVideoDetailBinding
import com.qxy.tiktlin.util.navController
import com.qxy.tiktlin.util.setupToolbar
import com.qxy.tiktlin.ui.vm.VideoDetailViewModel
import com.qxy.tiktlin.widget.BaseFragment
import kotlinx.coroutines.launch

class VideoDetailFragment : BaseFragment<FragmentVideoDetailBinding>(R.layout.fragment_video_detail) {

    private val viewModel by viewModels<VideoDetailViewModel>() {
        val args by navArgs<VideoDetailFragmentArgs>()
        VideoDetailViewModel.Factory(args.videoData)
    }

    override fun initView() {
        binding.viewModel = viewModel
        setupToolbar(
            toolbar = binding.toolbar,
            title = null,
            navigationIcon = R.drawable.baseline_arrow_back_ios_24,
            navigationOnClick = { navController.navigateUp() }
        )

        LogCat.d("Video: ${viewModel.video}")
        lifecycleScope.launch {
            with(binding.video) {
                start()
            }
        }
    }
}
