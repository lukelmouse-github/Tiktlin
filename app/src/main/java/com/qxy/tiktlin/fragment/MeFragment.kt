package com.qxy.tiktlin.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.qxy.tiktlin.R
import com.qxy.tiktlin.Repository
import com.qxy.tiktlin.common.base.BaseFragment
import com.qxy.tiktlin.common.network.config.AppConfig
import com.qxy.tiktlin.databinding.FragmentMeBinding
import com.qxy.tiktlin.vm.MeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MeFragment : BaseFragment<FragmentMeBinding>(R.layout.fragment_me) {
    private val viewModel by viewModels<MeViewModel>()
    override fun initConfig() {

    }

    override fun initView() {
        super.initView()

    }

    override fun initData() {
        super.initData()
        lifecycleScope.launch(Dispatchers.IO) {
            binding.user = Repository.getUser(AppConfig.OPEN_ID)
        }
    }
}
