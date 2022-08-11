package com.qxy.tiktlin.fragment

import androidx.fragment.app.viewModels
import com.qxy.tiktlin.common.base.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentFriendBinding
import com.qxy.tiktlin.vm.FriendViewModel

class FriendFragment : BaseFragment<FragmentFriendBinding>(R.layout.fragment_friend) {

    private val viewModel by viewModels<FriendViewModel>()

    override fun initConfig() {

    }

    override fun initView() {
        binding.viewModel = viewModel
    }
}
