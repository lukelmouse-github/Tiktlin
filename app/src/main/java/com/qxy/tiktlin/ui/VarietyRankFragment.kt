package com.qxy.tiktlin.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.drake.logcat.LogCat
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.adapter.VarietyRankAdapter
import com.qxy.tiktlin.databinding.FragmentVarietyRankBinding
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.ui.vm.VarietyRankViewModel
import com.qxy.tiktlin.widget.BaseFragment
import kotlinx.coroutines.launch

class VarietyRankFragment : BaseFragment<FragmentVarietyRankBinding>(R.layout.fragment_variety_rank) {

    private var vaRankAdapter = VarietyRankAdapter()

    private val viewModel by viewModels<VarietyRankViewModel>()

     override fun initVaRank() {
        binding.viewModel = viewModel
        LogCat.d("Va: ${viewModel.va}")
        lifecycleScope.launch {
            val vaList = Repository.getRankList(type = 3).data.list
            vaRankAdapter.setData(vaList)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.initVaRank()
    }
}
