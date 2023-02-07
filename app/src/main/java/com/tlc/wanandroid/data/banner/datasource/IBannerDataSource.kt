package com.tlc.wanandroid.data.banner.datasource

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.data.banner.model.BannerApiModel

interface IBannerDataSource {
    suspend fun fetchBanner(): Response<List<BannerApiModel>>
}