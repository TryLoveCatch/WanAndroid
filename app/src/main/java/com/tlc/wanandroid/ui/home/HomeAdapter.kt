package com.tlc.wanandroid.ui.home

import android.content.Context
import com.tlc.wanandroid.core.adapter.FooterAdapter

class HomeAdapter(context: Context): FooterAdapter(context) {
    companion object {
        const val TYPE_BANNER = 0
        const val TYPE_ARTICLE = 1
    }
    override fun getItemViewType(position: Int): Int {
        var type = super.getItemViewType(position)
        if (type == 0 && position == 0) {
            type = TYPE_BANNER
        } else if (type == 0) {
            type = TYPE_ARTICLE
        }
        return type
    }
}