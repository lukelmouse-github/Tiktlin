package com.qxy.tiktlin.ui.vm


import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qxy.tiktlin.data.netData.RankList
import com.qxy.tiktlin.util.DateUtils
import com.qxy.tiktlin.widget.BaseViewModel
import com.qxy.tiktlin.widget.appInstance

class TvRankViewModel(val tv: RankList.Data.RankItem) : BaseViewModel() {

    class Factory(private val tv: RankList.Data.RankItem) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TvRankViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TvRankViewModel(tv) as T
            } else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    class rankList(
        val TvPoster: String,
        val TvName: String,
        val TvActor: String,
        val TvTime: String,
        val TvFlane: String
    )

    val rankLists = rankList(
        TvPoster = tv.poster,
        TvName = tv.name,
        TvActor = tv.actors[0],
        TvTime = tv.release_data,
        TvFlane = ""+DateUtils.transToString(tv.hot)
    )

    private fun parseNumber(number: Int, @StringRes zero: Int = 0) = when (number) {
        0 -> if (zero == 0) "0" else appInstance.getString(zero)
        else -> number.toString()
    }
}
