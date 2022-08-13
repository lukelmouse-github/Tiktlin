package com.qxy.tiktlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.drake.logcat.LogCat
import com.drake.tooltip.toast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.qxy.tiktlin.common.base.BaseActivity
import com.qxy.tiktlin.common.ktx.immediateStatusBar
import com.qxy.tiktlin.common.network.Api
import com.qxy.tiktlin.common.network.config.AppConfig
import com.qxy.tiktlin.databinding.ActivityMainBinding
import com.qxy.tiktlin.douyinapi.AuthorizationAdapter
import com.qxy.tiktlin.fragment.AddVideoFragment
import com.qxy.tiktlin.fragment.FriendFragment
import com.qxy.tiktlin.fragment.HomeFragment
import com.qxy.tiktlin.fragment.MeFragment
import com.qxy.tiktlin.fragment.MessageFragment
import com.qxy.tiktlin.vm.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun initConfig() {
//        lifecycleScope.launch {
//            val authResult = AuthorizationAdapter.fetchAuthCode(this@MainActivity)
//            authResult.onSuccess {
//                Repository.getAccessToken(it).data.let { data ->
//                    AppConfig.ACCESS_TOKEN = data.access_token
//                    AppConfig.OPEN_ID = data.open_id
//                    LogCat.d(AppConfig.ACCESS_TOKEN)
//                    LogCat.d(AppConfig.OPEN_ID)
//                }
//            }.onFailure {
//                toast("授权失败\n${it.message}")
//            }
//        }
//         测试打开
        AppConfig.ACCESS_TOKEN = "act.6565e48cc3c93ccbdf8f79ee9bd02b6e6XuRbU6Trs1MvfrsGJIej6gqdGyk"
        AppConfig.OPEN_ID = "_000CojbsHqIehmLb4PXfnnDj0mIfBs3d7L3"
    }

    override fun initData() {
        super.initData()
    }

    override fun initView() {
        immediateStatusBar()
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
                4 -> tab.text = getString(R.string.title_me)
            }
        }.attach()

        // TabLayout 自定义view
        for (i in 0 until binding.bottomNav.getTabCount()) {
            val tab: TabLayout.Tab? = binding.bottomNav.getTabAt(i)
            if (tab != null) {
                val tabTextView = TextView(this)
                tab.customView = tabTextView
                tabTextView.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.text = tab.text
                if (i == 0) {
                    tabTextView.textSize = 15f
                }
            }
        }

        binding.bottomNav.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when (tab.position) {
                        0,1,2 -> {
                            binding.bottomNav.setBackgroundColor(Color.BLACK)
                            binding.navAddVideo.setBackgroundResource(R.drawable.nav_add_video)
                        }
                        3,4 -> {
                            binding.bottomNav.setBackgroundColor(Color.WHITE)
                            binding.navAddVideo.setBackgroundResource(R.drawable.nav_add_video_black)
                        }
                    }
                }
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab!!.position) as ViewGroup
                val tabChildsCount = vgTab.childCount
                for (i in 0 until tabChildsCount) {
                    val tabViewChild: View = vgTab.getChildAt(i)
                    if (tabViewChild is TextView) {
                        if (tab.position < 3) {
                            tabViewChild.setTextColor(Color.WHITE)
                            tabViewChild.setTypeface(null, Typeface.BOLD)
                            (tabViewChild as TextView).textSize = 16f
                        } else {
                            tabViewChild.setTextColor(Color.BLACK)
                            tabViewChild.setTypeface(null, Typeface.BOLD)
                            (tabViewChild as TextView).textSize = 16f
                        }
                    }
                }
            }

            @SuppressLint("ResourceAsColor")
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab!!.position) as ViewGroup
                val tabChildsCount = vgTab.childCount
                for (i in 0 until tabChildsCount) {
                    val tabViewChild = vgTab.getChildAt(i)
                    if (tabViewChild is TextView) {
                        tabViewChild.setTextColor(Color.GRAY)
                        tabViewChild.setTypeface(null, Typeface.NORMAL)
                        (tabViewChild as TextView).textSize = 15f
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
