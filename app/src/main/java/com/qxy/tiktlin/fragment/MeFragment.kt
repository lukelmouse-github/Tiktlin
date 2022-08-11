package com.qxy.tiktlin.fragment

import androidx.fragment.app.viewModels
import com.qxy.tiktlin.common.base.BaseFragment
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentMeBinding
import com.qxy.tiktlin.vm.MeViewModel

class MeFragment : BaseFragment<FragmentMeBinding>(R.layout.fragment_me) {
    private val viewModel by viewModels<MeViewModel>()
    override fun initConfig() {

    }

    override fun initView() {

    }
}
