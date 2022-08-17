package com.qxy.tiktlin.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun transToString(time: Long): String {
        val dateFormat = SimpleDateFormat("MM 月 DD 日 hh:mm:ss", Locale.getDefault())
        return dateFormat.format(time)
    }
}
