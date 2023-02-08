package com.tlc.wanandroid.ui.home

import android.content.Context
import com.tlc.wanandroid.core.adapter.FooterAdapter
import com.tlc.wanandroid.data.article.model.Article
import com.tlc.wanandroid.ui.home.vm.BannerList

class HomeAdapter(context: Context): FooterAdapter(context) {
    companion object {
        const val TYPE_BANNER = 0
        const val TYPE_ARTICLE = 1
    }
    override fun getItemViewType(position: Int): Int {
        var type = super.getItemViewType(position)
        if (type != 0) {
            return type
        }
        val item = getItem(position)
        if (item is BannerList) {
            type = TYPE_BANNER
        } else if (item is Article) {
            type = TYPE_ARTICLE
        }
        return type
    }
}