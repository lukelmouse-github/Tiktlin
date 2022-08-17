package com.qxy.tiktlin.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}