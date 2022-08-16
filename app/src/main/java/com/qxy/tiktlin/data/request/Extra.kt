package com.qxy.tiktlin.common.rsp

import java.io.Serializable

data class Extra(
    val error_code: Int = 0,
    val description: String = "",
    val sub_error_code: Int = 0,
    val sub_description: String = "",
    val logid: String = "",
    val now: Long = 0
): Serializable