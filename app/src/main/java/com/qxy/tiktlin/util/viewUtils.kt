package com.qxy.tiktlin.util

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("loadAvatar")
fun loadAvatar(img: ImageView, url: String?) {
    if (url != null) {
        Glide.with(img.context).load(url)
            .centerCrop()
            .transform(CircleBorderTransformation(5f, Color.WHITE))
            .into(img)
    }

}