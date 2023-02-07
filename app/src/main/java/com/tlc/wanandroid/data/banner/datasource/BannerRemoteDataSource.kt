package com.tlc.wanandroid.data.banner.datasource

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.data.banner.model.BannerApiModel
import com.tlc.wanandroid.data.get

class BannerRemoteDataSource: IBannerDataSource {

    override suspend fun fetchBanner(): Response<List<BannerApiModel>> {
        return get().fetchBanner()
    }
}