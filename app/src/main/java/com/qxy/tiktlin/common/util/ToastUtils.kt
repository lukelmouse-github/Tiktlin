package com.qxy.tiktlin.common.util

import android.widget.Toast
import androidx.annotation.StringRes
import com.qxy.tiktlin.appInstance

fun makeToast(
    message: String,
    length: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(appInstance, message, length).show()
}

fun makeToast(
    @StringRes message: Int,
    length: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(appInstance, message, length).show()
}
