package com.qxy.tiktlin.ui

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.drake.logcat.LogCat
import com.qxy.tiktlin.R
import com.qxy.tiktlin.data.adapter.MovieRankAdapter
import com.qxy.tiktlin.databinding.FragmentMovieRankBinding
import com.qxy.tiktlin.databinding.FragmentVideoDetailBinding
import com.qxy.tiktlin.databinding.ItemMovieRankBinding
import com.qxy.tiktlin.model.repository.Repository
import com.qxy.tiktlin.ui.vm.HomeViewModel
import com.qxy.tiktlin.ui.vm.MovieRankViewModel
import com.qxy.tiktlin.util.navController
import com.qxy.tiktlin.util.setupToolbar
import com.qxy.tiktlin.ui.vm.VideoDetailViewModel
import com.qxy.tiktlin.widget.BaseFragment
import kotlinx.coroutines.launch

class MovieRankFragment : BaseFragment<FragmentMovieRankBinding>(R.layout.fragment_movie_rank) {

    private var movieRankAdapter = MovieRankAdapter()



    private val viewModel by viewModels<MovieRankViewModel>()

      override fun initMovieRank() {
        binding.viewModel = viewModel
        LogCat.d("Movie: ${viewModel.movie}")
        lifecycleScope.launch {

            val movieList = Repository.getRankList(type = 1).data.list
            movieRankAdapter.setData(movieList)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.initMovieRank()
    }



}
