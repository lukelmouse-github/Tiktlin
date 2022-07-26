package com.qxy.tiktlin.ui

import androidx.fragment.app.viewModels
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentMessageBinding
import com.qxy.tiktlin.ui.vm.MessageViewModel

class MessageFragment : BaseFragment<FragmentMessageBinding>(R.layout.fragment_message) {

    private val viewModel by viewModels<MessageViewModel>()

    override fun initConfig() {

    }

    override fun initView() {
        binding.viewModel = viewModel
    }
}
