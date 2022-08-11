package com.qxy.tiktlin.fragment

import androidx.fragment.app.viewModels
import com.qxy.common.base.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentMessageBinding
import com.qxy.tiktlin.vm.MessageViewModel

class MessageFragment : BaseFragment<FragmentMessageBinding>(R.layout.fragment_message) {

    private val viewModel by viewModels<MessageViewModel>()

    override fun initConfig() {

    }

    override fun initView() {
        binding.viewModel = viewModel
    }
}
