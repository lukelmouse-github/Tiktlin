package com.qxy.tiktlin.fragment

import androidx.fragment.app.viewModels
import com.qxy.common.base.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentAddVideoBinding
import com.qxy.tiktlin.vm.AddVideoViewModel

class AddVideoFragment : BaseFragment<FragmentAddVideoBinding>(R.layout.fragment_add_video) {

    private val viewModel by viewModels<AddVideoViewModel>()

    override fun initConfig() {

    }

    override fun initView() {
        binding.viewModel = viewModel
    }
}
