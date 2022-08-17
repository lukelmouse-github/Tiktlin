package com.qxy.tiktlin.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
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
import com.qxy.tiktlin.R
import com.qxy.tiktlin.widget.BaseActivity
import com.qxy.tiktlin.data.config.AppConfig
import com.qxy.tiktlin.databinding.ActivityMainBinding
import com.qxy.tiktlin.model.datasource.database.TikDatabase
import com.qxy.tiktlin.model.datasource.database.User

import com.qxy.tiktlin.ui.vm.MainViewModel
import com.qxy.tiktlin.model.datasource.network.NetDataSource
import com.qxy.tiktlin.widget.immediateStatusBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by viewModels<MainViewModel>()

    override fun initConfig() {
        lifecycleScope.launch {
            val authResult = AuthorizationAdapter.fetchAuthCode(this@MainActivity)
            authResult.onSuccess {
                NetDataSource.getAccessToken(it).data.let { data ->
                    AppConfig.ACCESS_TOKEN = data.access_token
                    AppConfig.OPEN_ID = data.open_id
                    LogCat.d(AppConfig.ACCESS_TOKEN)
                    LogCat.d(AppConfig.OPEN_ID)
                    val user = NetDataSource.getUserInfo()
                    val totalFans = NetDataSource.getTotalFans()
                    val newUser = User(
                        name = user.data.nickname,
                        city = user.data.city,
                        openId = user.data.open_id,
                        avatar = user.data.avatar,
                        gender = user.data.gender,
                        country = user.data.country,
                        province = user.data.province,
                        totalFans = totalFans,
                    )
                    withContext(Dispatchers.IO) {
                        TikDatabase.appDatabase.userDao().insertUser(newUser)
                    }
                }
            }.onFailure {
                toast("授权失败\n${it.message}")
            }
        }
//         测试打开
//        AppConfig.ACCESS_TOKEN = "act.6565e48cc3c93ccbdf8f79ee9bd02b6e6XuRbU6Trs1MvfrsGJIej6gqdGyk"
//        AppConfig.OPEN_ID = "_000CojbsHqIehmLb4PXfnnDj0mIfBs3d7L3"
    }

    override fun initData() {
        super.initData()
        binding.viewPager.currentItem = 0
        binding.bottomNav.getTabAt(0)?.select()
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
        setTabLayout()
    }

    // TabLayout 自定义view
    private fun setTabLayout() {

        for (i in 0 until binding.bottomNav.tabCount) {
            val tab: TabLayout.Tab? = binding.bottomNav.getTabAt(i)
            if (tab != null) {
                val tabTextView = TextView(this)
                tab.customView = tabTextView
                tabTextView.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.text = tab.text
            }
        }

        binding.bottomNav.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                //  设置背景
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
                // 设置Text 颜色 Style
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab!!.position) as ViewGroup
                val tabTextView = vgTab.getChildAt(2) as TextView
                if (tab.position < 3) {
                    tabTextView.setTextColor(Color.WHITE)
                    tabTextView.setTypeface(null, Typeface.BOLD)
                    tabTextView.textSize = 16f
                } else {
                    tabTextView.setTextColor(Color.BLACK)
                    tabTextView.setTypeface(null, Typeface.BOLD)
                    tabTextView.textSize = 16f
                }
            }

            @SuppressLint("ResourceAsColor")
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab!!.position) as ViewGroup
                val tabTextView = vgTab.getChildAt(2) as TextView
                tabTextView.setTextColor(Color.GRAY)
                tabTextView.setTypeface(null, Typeface.NORMAL)
                tabTextView.textSize = 15f
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
