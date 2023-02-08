package com.tlc.wanandroid.data.banner.datasource

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.data.banner.model.BannerApiModel
import com.tlc.wanandroid.data.get

class BannerMockErrorSource: IBannerDataSource {

    override suspend fun fetchBanner(): Response<List<BannerApiModel>> {
        throw RuntimeException("mock error")
    }
}