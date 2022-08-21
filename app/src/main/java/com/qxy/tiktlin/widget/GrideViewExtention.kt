package com.qxy.tiktlin.widget

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qxy.tiktlin.R

/*
使用glide进行图片添加示例
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.lifecycle.repeatOnLifecycle(STARTED) {
            viewModel.currentUserImageUri.collect {
                setProfileAvatar(context, imageView, it)
            }
        }
    }
 */

fun setUserPicture(
    context: Context,
    imageView: ImageView,
    imageUri: Uri?,
    placeholder: Int = R.drawable.default_user_avatar
) {
    // Inflate the drawable for proper tinting
    val placeholderDrawable = AppCompatResources.getDrawable(context, placeholder)
    when (imageUri) {
        null -> {
            Glide.with(context)
                .load(placeholderDrawable)
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)
        }
        else -> {
            Glide.with(context)
                .load(imageUri)
                .apply(RequestOptions.placeholderOf(placeholderDrawable).circleCrop())
                .into(imageView)
        }
    }
}

