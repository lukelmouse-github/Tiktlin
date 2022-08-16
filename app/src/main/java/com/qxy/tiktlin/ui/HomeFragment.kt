package com.qxy.tiktlin.fragment

import androidx.fragment.app.viewModels
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentHomeBinding
import com.qxy.tiktlin.ui.vm.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun initConfig() {

    }

    override fun initView() {
        binding.viewModel = viewModel
    }
}