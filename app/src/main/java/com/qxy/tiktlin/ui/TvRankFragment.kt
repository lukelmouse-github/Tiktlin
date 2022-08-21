package com.qxy.tiktlin.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.drake.logcat.LogCat
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.adapter.TvRankAdapter
import com.qxy.tiktlin.databinding.FragmentTvRankBinding
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.ui.vm.TvRankViewModel
import com.qxy.tiktlin.widget.BaseFragment
import kotlinx.coroutines.launch

class TvRankFragment : BaseFragment<FragmentTvRankBinding>(R.layout.fragment_tv_rank) {

    private var tvRankAdapter = TvRankAdapter()

    private val viewModel by viewModels<TvRankViewModel>()

     override fun initTvRank() {
        binding.viewModel = viewModel
        LogCat.d("Tv: ${viewModel.tv}")
        lifecycleScope.launch {
            val tvList = Repository.getRankList(type = 2).data.list
            tvRankAdapter.setData(tvList)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.initTvRank()
    }
}
