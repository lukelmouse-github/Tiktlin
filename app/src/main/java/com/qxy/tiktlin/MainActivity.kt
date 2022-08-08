package com.qxy.tiktlin

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.qxy.common.base.BaseActivity
import com.qxy.tiktlin.databinding.ActivityMainBinding
import com.qxy.tiktlin.douyinapi.AuthorizationAdapter
import com.qxy.tiktlin.model.MainViewModel
import com.qxy.tiktlin.util.makeToast
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun initConfig() {
        lifecycleScope.launch {
            val authResult = AuthorizationAdapter.fetchAuthCode(this@MainActivity)
            authResult.onSuccess {
                Timber.d("授权成功，authCode = $it")
                makeToast("授权成功")
            }.onFailure {
                makeToast("授权失败\n${it.message}")
            }
        }
    }

    override fun initView() {
        binding.viewModel = viewModel
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(binding.bottomNav, navController)
    }
}
