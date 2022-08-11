package com.qxy.tiktlin

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.qxy.tiktlin.common.base.BaseActivity
import com.qxy.tiktlin.common.network.config.AppConfig
import com.qxy.tiktlin.common.util.makeToast
import com.qxy.tiktlin.databinding.ActivityMainBinding
import com.qxy.tiktlin.douyinapi.AuthorizationAdapter
import com.qxy.tiktlin.fragment.AddVideoFragment
import com.qxy.tiktlin.fragment.FriendFragment
import com.qxy.tiktlin.fragment.HomeFragment
import com.qxy.tiktlin.fragment.MeFragment
import com.qxy.tiktlin.fragment.MessageFragment
import com.qxy.tiktlin.vm.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun initConfig() {
        lifecycleScope.launch {
            val authResult = AuthorizationAdapter.fetchAuthCode(this@MainActivity)
            authResult.onSuccess {
                Repository.getAccessToken(it).data.let { data ->
                    AppConfig.ACCESS_TOKEN = data.access_token
                    AppConfig.OPEN_ID = data.open_id
                }
            }.onFailure {
                makeToast("授权失败\n${it.message}")
            }
            withContext(Dispatchers.IO) {
                val user = Repository.getUser(AppConfig.OPEN_ID)
                withContext(Dispatchers.Main) {
                    Timber.d("授权成功\n${AppConfig.ACCESS_TOKEN}")
                    Timber.d("userInfo: $user")
                }
            }

        }
    }

    override fun initData() {
        super.initData()
//        GlobalScope.launch {
//            val x = Repository.getUser("123")
//        }


    }

    override fun initView() {
        binding.viewModel = viewModel
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        // 禁止滑动
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 5

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment()
                    1 -> FriendFragment()
                    2 -> AddVideoFragment()
                    3 -> MessageFragment()
                    else -> MeFragment()
                }
            }
        }

        TabLayoutMediator(binding.bottomNav, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_home)
                1 -> tab.text = getString(R.string.title_friend)
                3 -> tab.text = getString(R.string.title_message)
                else -> tab.text = getString(R.string.title_me)
            }
        }.attach()


        // 底部导航栏自定义样式，听说radio比tablelayout简单
        binding.bottomNav.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("ResourceType")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = tab?.let { vg.getChildAt(it.position) } as ViewGroup
                for (i in 0..vgTab.childCount) {
                    val tabViewChild = vgTab.getChildAt(i)
                    if (tabViewChild is TextView) {
                        tabViewChild.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17F)
                        tabViewChild.setTypeface(null, Typeface.BOLD)
                    }
                }
            }

            @SuppressLint("ResourceType")
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = tab?.let { vg.getChildAt(it.position) } as ViewGroup
                for (i in 0..vgTab.childCount) {
                    val tabViewChild = vgTab.getChildAt(i)
                    if (tabViewChild is TextView) {
                        tabViewChild.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15F)
                        tabViewChild.setTypeface(null, Typeface.BOLD)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
