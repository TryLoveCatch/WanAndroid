package com.tlc.wanandroid.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tlc.wanandroid.data.banner.model.Banner


class BannerPagerAdapter(context: Context): BasePagerAdapter<Banner>(context) {
    override fun bindingView(data: Banner, position: Int): View {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        Glide.with(context).load(data.imagePath).into(imageView)
        return imageView
    }
}