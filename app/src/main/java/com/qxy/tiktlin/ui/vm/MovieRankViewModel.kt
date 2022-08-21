package com.qxy.tiktlin.ui.vm


import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qxy.tiktlin.data.netData.RankList
import com.qxy.tiktlin.util.DateUtils
import com.qxy.tiktlin.widget.BaseViewModel
import com.qxy.tiktlin.widget.appInstance

class MovieRankViewModel(val movie: RankList.Data.RankItem) : BaseViewModel() {

    class Factory(private val movie: RankList.Data.RankItem) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieRankViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieRankViewModel(movie) as T
            } else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    class rankList(
        val MoviePoster: String,
        val MovieName: String,
        val MovieTag: String,
        val MovieTime: String,
        val MovieFlane: String
    )

    val rankLists = rankList(
        MoviePoster = movie.poster,
        MovieName = movie.name,
        MovieTag = movie.tags[0],
        MovieTime = movie.release_data,
        MovieFlane = ""+DateUtils.transToString(movie.hot)
    )

    private fun parseNumber(number: Int, @StringRes zero: Int = 0) = when (number) {
        0 -> if (zero == 0) "0" else appInstance.getString(zero)
        else -> number.toString()
    }
}
