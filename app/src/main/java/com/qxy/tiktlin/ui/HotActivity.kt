package com.qxy.tiktlin.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.ActivityHotBinding
import com.qxy.tiktlin.widget.BaseActivity

class HotActivity : BaseActivity<ActivityHotBinding>(R.layout.activity_hot) {
    override fun initData() {
        super.initData()
    }

    override fun initView() {
        super.initView()


        binding.pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> MovieFragment.newInstance(1)
                    1 -> MovieFragment.newInstance(2)
                    else -> MovieFragment.newInstance(3)
                }
            }

        }
        TabLayoutMediator(binding.tabLayout, binding.pager, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                when (position) {
                    0 -> tab.text = "电影"
                    1 -> tab.text = "电视剧"
                    2 -> tab.text = "综艺"
                }
            }
        }).attach()
    }
}