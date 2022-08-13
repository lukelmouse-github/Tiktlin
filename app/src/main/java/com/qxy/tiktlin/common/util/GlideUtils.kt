package com.qxy.tiktlin.common.util

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

// 圆形边框
class CircleBorderTransformation(private val borderWidth: Float, private val borderColor: Int) : BitmapTransformation() {
    override fun updateDiskCacheKey(messageDigest: MessageDigest) {

    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return circleCrop(pool, toTransform)
    }

    private fun circleCrop(pool: BitmapPool, source: Bitmap): Bitmap {
        val size = Math.min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val square = Bitmap.createBitmap(source, x, y, size, size)
        val result: Bitmap = pool.get(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(square, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val radius = size / 2f
        canvas.drawCircle(radius, radius, radius, paint)

        val borderRadius = radius - (borderWidth / 2)
        val boardPaint = Paint()
        boardPaint.color = borderColor
        boardPaint.style = Paint.Style.STROKE
        boardPaint.strokeWidth = borderWidth
        boardPaint.isDither = true
        canvas.drawCircle(radius, radius, borderRadius, boardPaint)
        return result
    }

}