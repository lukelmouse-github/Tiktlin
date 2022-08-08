package com.qxy.tiktlin

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.qxy.common.base.BaseActivity
import com.qxy.tiktlin.databinding.ActivityMainBinding
import com.qxy.tiktlin.douyinapi.AuthorizationAdapter
import com.qxy.tiktlin.model.MainViewModel
import com.qxy.tiktlin.util.makeToast
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun initConfig() {
        lifecycleScope.launch {
            val authResult = AuthorizationAdapter.fetchAuthCode(this@MainActivity)
            authResult.onSuccess {
                makeToast("授权成功")
            }.onFailure {
                makeToast("授权失败\n${it.message}")
            }
        }
    }
}
