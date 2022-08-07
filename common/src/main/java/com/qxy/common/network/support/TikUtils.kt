package com.qxy.common.network.support

import java.util.regex.Pattern

object TikUtils {

    // unicode 转 中文
    fun unicodeDecode(string: String): String {
        var string = string
        val pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))")
        val matcher = pattern.matcher(string)
        var ch: Char
        while (matcher.find()) {
            ch = matcher.group(2)!!.toInt(16).toChar()
            //            Integer.valueOf("", 16);
            string = string.replace(matcher.group(1)!!, ch.toString() + "")
        }
        return string
    }
}