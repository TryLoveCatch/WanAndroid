package com.tlc.wanandroid.ui.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.SingleRequest
import com.bumptech.glide.request.target.ImageViewTarget
import com.tlc.wanandroid.core.utils.loge
import java.util.regex.Pattern

/**
 * 检测是否有异常数据
 */
fun detect(imageViewTarget: ImageViewTarget<*>?, resource: Any?) {
    if (imageViewTarget == null || resource == null) {
        return
    }
    val imageView: ImageView = imageViewTarget.view ?: return
    //View大小 与 加载的 资源大小
    val viewWidth = imageView.width
    val viewHeight = imageView.height
    var resWidth = 0
    var resHeight = 0
    if (resource is Bitmap) {
        resWidth = resource.width
        resHeight = resource.height
    } else if (resource is Drawable) {
        resWidth = resource.intrinsicWidth
        resHeight = resource.intrinsicHeight
    }

    //获取加载的url
    val url = getUrlFromRequest(imageViewTarget.request)
    "url: $url, isLegalUrl(url): ${isLegalUrl(url)}, bitmapSize(width x height): $resWidth x $resHeight ".loge()
    if (resHeight > viewWidth && resWidth > viewWidth) {
        "image to large".loge()
    }
}

/**
 * 获取请求的Url
 * @param request
 * @return
 */
fun getUrlFromRequest(request: Request?): String? {
    if (request != null && request is SingleRequest<*>) {
        //反射获取加载的url
        val requestClass: Class<*> = request.javaClass
        try {
            val modelFiled = requestClass.getDeclaredField("model")
            modelFiled.isAccessible = true
            val model = modelFiled[request]
            var url: String? = null
            if (model is String) {
                url = model
            } else if (model is Uri) {
                url = model.toString()
            }
            return url
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }
    return null
}

/**
 * 是否是合法的Url
 * @param url
 * @return
 */
private fun isLegalUrl(url: String?): Boolean {
    if (TextUtils.isEmpty(url)) {
        return false
    }
    val pattern = Pattern.compile("http(s)?://\\w{3,}(\\.\\w+)+(\\.\\w{2,3})+")
    val matcher = pattern.matcher(url)
    return matcher.find()
}