package com.qxy.tiktlin.util

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qxy.tiktlin.R

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("loadAvatar")
fun loadAvatar(img: ImageView, url: String?) {
    if (url != null) {
        Glide.with(img.context).load(url)
            .centerCrop()
            .transform(CircleBorderTransformation(5f, Color.WHITE))
            .into(img)
    }

}



//用法示例：
//在imageview中：app:imageUrl="@{announcement.imageUrl}"
@BindingAdapter(value = ["imageUri", "placeholder"], requireAll = false)
fun imageUri(imageView: ImageView, imageUri: Uri?, placeholder: Drawable?) {
    when (imageUri) {
        null -> {
            Glide.with(imageView)
                .load(placeholder)
                .into(imageView)
        }
        else -> {
            Glide.with(imageView)
                .load(imageUri)
                .apply(RequestOptions().placeholder(placeholder))
                .into(imageView)
        }
    }
}


@BindingAdapter(value = ["userGender"])
fun ImageView.userGender(userGender: Int) {
    setImageResource(when (userGender) {
        0 -> R.drawable.ic_man
        1 -> R.drawable.ic_woman
        else -> R.drawable.ic_woman
    })
}



@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun imageUrl(imageView: ImageView, imageUrl: String?, placeholder: Drawable?) {
    imageUri(imageView, imageUrl?.toUri(), placeholder)
}


