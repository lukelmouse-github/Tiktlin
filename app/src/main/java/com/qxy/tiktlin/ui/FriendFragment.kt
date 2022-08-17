package com.qxy.tiktlin.ui

import androidx.fragment.app.viewModels
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentFriendBinding
import com.qxy.tiktlin.ui.vm.FriendViewModel

class FriendFragment : BaseFragment<FragmentFriendBinding>(R.layout.fragment_friend) {

    private val viewModel by viewModels<FriendViewModel>()

    override fun initConfig() {

    }

    override fun initView() {
        binding.viewModel = viewModel
    }
}
