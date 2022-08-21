package com.qxy.tiktlin.ui.theme


import android.R
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drake.logcat.LogCat
import com.google.android.material.tabs.TabLayoutMediator
import com.qxy.tiktlin.data.adapter.MovieRankAdapter
import com.qxy.tiktlin.data.adapter.TvRankAdapter
import com.qxy.tiktlin.data.adapter.VarietyRankAdapter
import com.qxy.tiktlin.databinding.ActivityRankBinding
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.ui.ViewPagerAdapter
import com.qxy.tiktlin.ui.vm.MovieRankViewModel
import kotlinx.coroutines.launch


class RankActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRankBinding
    private var Movie = MovieRankAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRankBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabLayout()




    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position -> tab.text = "" + (position + 1) }.attach()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this, 3)
        binding.viewPager.adapter = adapter
    }

    override fun onBackPressed() {
        val viewPager = binding.viewPager
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }




}