package com.qxy.tiktlin

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.qxy.common.base.BaseActivity
import com.qxy.common.model.AccessToken
import com.qxy.common.network.config.AppConfig
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
//                val x = viewModel.getAccessToken(it) as AccessToken
                val accessTokenData = Repository.getAccessToken(it).data
                val userInfo = accessTokenData?.open_id?.let { it1 -> accessTokenData.access_token?.let { it2 ->
                    Repository.getUserInfo(it1,
                        it2
                    )
                } }

                Timber.d("授权成功\n$accessTokenData")
                Timber.d("userInfo: $userInfo")

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
