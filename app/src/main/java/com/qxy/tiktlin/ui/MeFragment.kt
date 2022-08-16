package com.qxy.tiktlin.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.drake.logcat.LogCat
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.qxy.tiktlin.R
import com.qxy.tiktlin.widget.Repository
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.data.config.AppConfig
import com.qxy.tiktlin.databinding.FragmentMeBinding
import com.qxy.tiktlin.ui.vm.MeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MeFragment : BaseFragment<FragmentMeBinding>(R.layout.fragment_me) {
    private val viewModel by viewModels<MeViewModel>()
    override fun initConfig() {

    }

    override fun initView() {
        super.initView()
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 4
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> FriendFragment()
                    1 -> FriendFragment()
                    2 -> FriendFragment()
                    else -> HomeFragment()
                }
            }
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "作品"
                1 -> tab.text = "收藏"
                2 -> tab.text = "私密"
                3 -> tab.text = "喜欢"
            }
        }.attach()
        setTabLayout()


    }

    override fun initData() {
        super.initData()
        lifecycleScope.launch(Dispatchers.IO) {
            binding.user = Repository.getUser(AppConfig.OPEN_ID)
        }
    }

    // Todo TabLayout 滑动无动画
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setTabLayout() {
        binding.tabLayout.animation
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            if (tab != null) {
                val tabTextView = TextView(context)
                tab.customView = tabTextView
                tabTextView.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.compoundDrawablePadding = 20
                tabTextView.text = tab.text
                if (tab.position != 0) {
                    val img = context?.getDrawable(R.drawable.lock)
                    img?.setBounds(0, 0, 45, 45)
                    tabTextView.setCompoundDrawables(null , null, img, null)
                }
            }
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val vg = binding.tabLayout.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab!!.position) as ViewGroup
                for (i in 0 until vgTab.childCount) {
                    LogCat.e("$i : ${vgTab.getChildAt(i).javaClass.name}")
                }
                val tabTextView = vgTab.getChildAt(2) as TextView
                tabTextView.setTextColor(Color.BLACK)
                tabTextView.setTypeface(null, Typeface.BOLD)

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val vg = binding.tabLayout.getChildAt(0) as ViewGroup
                val vgTab = vg.getChildAt(tab!!.position) as ViewGroup
                val tabTextView = vgTab.getChildAt(2) as TextView
                tabTextView.setTextColor(Color.GRAY)
                tabTextView.setTypeface(null, Typeface.NORMAL)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }
}
