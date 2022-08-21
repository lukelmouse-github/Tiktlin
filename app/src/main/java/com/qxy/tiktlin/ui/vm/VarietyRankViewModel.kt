package com.qxy.tiktlin.ui.vm


import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.qxy.tiktlin.data.netData.RankList
import com.qxy.tiktlin.util.DateUtils
import com.qxy.tiktlin.widget.BaseViewModel
import com.qxy.tiktlin.widget.appInstance

class VarietyRankViewModel(val va: RankList.Data.RankItem) : BaseViewModel() {

    class Factory(private val va: RankList.Data.RankItem) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(VarietyRankViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return VarietyRankViewModel(va) as T
            } else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    class rankList(
        val VaPoster: String,
        val VaName: String,
        val VaActor: String,
        val VaTime: String,
        val VaFlane: String
    )

    val rankLists = rankList(
        VaPoster = va.poster,
        VaName = va.name,
        VaActor = va.actors[0],
        VaTime = va.release_data,
        VaFlane = ""+DateUtils.transToString(va.hot)
    )

    private fun parseNumber(number: Int, @StringRes zero: Int = 0) = when (number) {
        0 -> if (zero == 0) "0" else appInstance.getString(zero)
        else -> number.toString()
    }
}
