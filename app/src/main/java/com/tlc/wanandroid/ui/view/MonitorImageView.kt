package com.tlc.wanandroid.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Looper
import android.os.MessageQueue
import android.util.AttributeSet
import android.widget.ImageView
import com.tlc.wanandroid.core.utils.loge


open class MonitorImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr), MessageQueue.IdleHandler {

    companion object {

        private const val MAX_ALARM_IMAGE_SIZE = 1024

    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        monitor()
    }

    override fun setImageBitmap(bm: Bitmap?) {
        super.setImageBitmap(bm)
        monitor()
    }

    override fun setImageResource(resId: Int) {
        super.setImageResource(resId)
        monitor()
    }


    private fun monitor() {
        Looper.myQueue().removeIdleHandler(this)
        Looper.myQueue().addIdleHandler(this)
    }

    override fun queueIdle(): Boolean {
        checkDrawable()
        return false
    }

    private fun checkDrawable() {
        val mDrawable = drawable ?: return
        val drawableWidth = mDrawable.intrinsicWidth
        val drawableHeight = mDrawable.intrinsicHeight
        val viewWidth = measuredWidth
        val viewHeight = measuredHeight
        val imageSize = calculateImageSize(mDrawable)
        "图片大小$imageSize".loge()
        if (imageSize > MAX_ALARM_IMAGE_SIZE) {
            log(log = "图片大小超标 -> $imageSize")
        }
        if (drawableWidth > viewWidth || drawableHeight > viewHeight) {
            log(log = "图片尺寸超标 -> drawable：$drawableWidth x $drawableHeight  view：$viewWidth x $viewHeight")
        }
    }

    /**
     * 计算 drawable 的大小
     */
    private fun calculateImageSize(drawable: Drawable): Int {
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            return bitmap.byteCount
        }
        val pixelSize = if (drawable.opacity != PixelFormat.OPAQUE) 4 else 2
        return pixelSize * drawable.intrinsicWidth * drawable.intrinsicHeight
    }

    private fun log(log: String) {
        log.loge()
    }

}