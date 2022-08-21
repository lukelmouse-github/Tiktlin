package com.qxy.tiktlin.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun transToString(time: Long): String {
        val dateFormat = SimpleDateFormat("MM 月 DD 日 hh:mm:ss", Locale.getDefault())
        return dateFormat.format(time)
    }

    fun transHotNum(num: Long): String {
        if (num / 10000 > 0) {
            return (num / 10000).toString() + "." + (num / 1000).toString() + " 万"
        } else if (num / 1000 > 0){
            return (num / 1000).toString() + "." + (num / 100).toString() + " 千"
        } else {
            return num.toString()
        }
    }
}
