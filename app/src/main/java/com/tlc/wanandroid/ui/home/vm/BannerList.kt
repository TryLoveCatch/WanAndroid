package com.tlc.wanandroid.ui.home.vm

import com.tlc.wanandroid.core.BaseModel
import com.tlc.wanandroid.data.banner.model.Banner

data class BannerList (
    val data: List<Banner>? = listOf(),
    ): BaseModel()
