package com.qxy.tiktlin.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.drake.logcat.LogCat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentVideoDetailBinding
import com.qxy.tiktlin.ui.vm.VideoDetailViewModel
import com.qxy.tiktlin.util.navController
import com.qxy.tiktlin.util.setupToolbar
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

        lifecycleScope.launch {
            val url = viewModel.getVideoUrl()
            LogCat.d("Video url: $url")
            val player = ExoPlayer.Builder(requireContext()).build()
            binding.video.player = player
            binding.video.controllerAutoShow = false
            player.setMediaItem(MediaItem.fromUri(url))
            player.repeatMode = ExoPlayer.REPEAT_MODE_ALL
            player.prepare()
            player.play()
        }
    }
}
