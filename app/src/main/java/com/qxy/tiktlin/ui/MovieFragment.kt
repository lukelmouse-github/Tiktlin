package com.qxy.tiktlin.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.qxy.tiktlin.R
import com.qxy.tiktlin.databinding.FragmentMovieBinding
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.widget.BaseFragment
import com.qxy.tiktlin.widget.appInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie) {

    override fun initData() {
        super.initData()
        val type = requireArguments().getInt("type")
        lifecycleScope.launch(Dispatchers.IO) {
            val data = Repository.getRankList(type).data.list
            withContext(Dispatchers.Main) {
                binding.rv.layoutManager = LinearLayoutManager(appInstance)
                binding.rv.adapter = MovieAdapter(data)
                binding.rv.addItemDecoration(DividerItemDecoration(appInstance, DividerItemDecoration.VERTICAL))
            }
        }
    }

    override fun initView() {
    }

    companion object {
        // 榜单类型： * 1 - 电影 * 2 - 电视剧 * 3 - 综艺
        @JvmStatic
        fun newInstance(type: Int) = MovieFragment().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }
}
