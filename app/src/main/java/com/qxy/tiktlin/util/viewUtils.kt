package com.qxy.tiktlin.util

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide




fun setContentMaxWidth(view: View) {
    val parent = view.parent as? ConstraintLayout ?: return
    val layoutParams = view.layoutParams as ConstraintLayout.LayoutParams
    val screenDensity = view.resources.displayMetrics.density
    val widthDp = parent.width / screenDensity
    val widthPercent = getContextMaxWidthPercent(widthDp.toInt())
    layoutParams.matchConstraintPercentWidth = widthPercent
    view.requestLayout()
}


private fun getContextMaxWidthPercent(maxWidthDp: Int): Float {
    // These match @dimen/content_max_width_percent.
    return when {
        maxWidthDp >= 1024 -> 0.6f
        maxWidthDp >= 840 -> 0.7f
        maxWidthDp >= 600 -> 0.8f
        else -> 1f
    }
}