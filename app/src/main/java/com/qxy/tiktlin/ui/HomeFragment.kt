package com.qxy.tiktlin.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentHomeBinding
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.ui.vm.HomeViewModel
import com.qxy.tiktlin.util.navController
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun initConfig() {

    }

    override fun initView() {
        binding.viewModel = viewModel
        binding.testVideo.setOnClickListener {
            lifecycleScope.launch {
                val list = Repository.getVideoList(count = 10).list
                val args = VideoDetailFragmentArgs(list[0])
                navController.navigate(R.id.nav_video_detail, args.toBundle())
            }
        }
    }
}
