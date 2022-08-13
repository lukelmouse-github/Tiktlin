package com.qxy.tiktlin.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadAvatar")
fun loadAvatar(img: ImageView, url: String?) {
    if (url != null) {
        Glide.with(img.context).load(url).apply(RequestOptions.bitmapTransform(CircleCrop())).into(img)
    }

}