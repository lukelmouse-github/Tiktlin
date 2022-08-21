package com.qxy.tiktlin.ui.theme

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.drake.logcat.LogCat
import com.drake.tooltip.toast
import com.google.android.material.tabs.TabLayout
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.adapter.MovieRankAdapter
import com.qxy.tiktlin.util.navigateMain
import com.qxy.tiktlin.data.config.AppConfig
import com.qxy.tiktlin.data.netData.RankList
import com.qxy.tiktlin.databinding.ActivityMainBinding
import com.qxy.tiktlin.douyinapi.AuthorizationAdapter
import com.qxy.tiktlin.model.datasource.database.User
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.ui.vm.MainViewModel
import com.qxy.tiktlin.widget.BaseActivity
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
                Repository.getAccessToken(it).data.let { data ->
                    AppConfig.ACCESS_TOKEN = data.access_token
                    AppConfig.OPEN_ID = data.open_id
                    LogCat.d(AppConfig.ACCESS_TOKEN)
                    LogCat.d(AppConfig.OPEN_ID)
                    val user = Repository.getUserInfo()
                    val totalFans = Repository.getTotalFans()
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
                        Repository.insertUser(newUser)
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

    override fun initView() {
        immediateStatusBar()
        binding.viewModel = viewModel
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setTabLayout(navController)
    }

    override fun initData() {
        super.initData()
        binding.bottomNav.getTabAt(0)?.select()
    }

    // TabLayout 自定义view
    private fun setTabLayout(navController: NavController) {
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
            override fun onTabSelected(tab: TabLayout.Tab) {
                //  设置背景
                when (tab.position) {
                    0, 1, 2 -> {
                        binding.bottomNav.setBackgroundColor(Color.BLACK)
                        binding.tabAddVideo.setBackgroundResource(R.drawable.add_video)
                    }
                    3, 4 -> {
                        binding.bottomNav.setBackgroundColor(Color.WHITE)
                        binding.tabAddVideo.setBackgroundResource(R.drawable.add_video_black)
                    }
                }
                // 设置Text 颜色 Style
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab.position) as ViewGroup
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

                when (tab.position) {
                    0 -> navController.navigateMain(R.id.nav_home)
                    1 -> navController.navigateMain(R.id.nav_friend)
                    2 -> navController.navigateMain(R.id.nav_add_video)
                    3 -> navController.navigateMain(R.id.nav_message)
                    4 -> navController.navigateMain(R.id.nav_me)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val vg = binding.bottomNav.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab.position) as ViewGroup
                val tabTextView = vgTab.getChildAt(2) as TextView
                tabTextView.setTextColor(Color.GRAY)
                tabTextView.setTypeface(null, Typeface.NORMAL)
                tabTextView.textSize = 15f
            }

            override fun onTabReselected(tab: TabLayout.Tab) = onTabSelected(tab)
        })
    }



}
