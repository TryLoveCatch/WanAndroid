package com.tlc.wanandroid.data.banner

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.data.banner.datasource.IBannerDataSource
import com.tlc.wanandroid.data.banner.model.Banner
import com.tlc.wanandroid.data.banner.model.BannerApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class BannerRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val bannerDataSource: IBannerDataSource
) {
//    suspend fun fetchBanner(): Response<List<Banner>> {
//        withContext(ioDispatcher) {
//            val response = bannerDataSource.fetchBanner()
//            if (response.errorCode == 0 && response.data != null) {
//                return@withContext Response(
//                    data = response.data.map { convert(it) },
//                )
//
//            } else {
//                return@withContext Response(
//                    data = null,
//                    errorCode = response.errorCode,
//                    errorMessage = response.errorMessage,
//                )
//
//            }
//        }
//        return Response(
//            data = null,
//            errorCode = -1,
//            errorMessage = "未知",
//        )
//    }

    suspend fun fetchBanner(): Response<List<Banner>> =
        withContext(ioDispatcher) {
            val response = bannerDataSource.fetchBanner()
            if (response.errorCode == 0 && response.data != null) {
                return@withContext Response(
                    data = response.data.map { convert(it) },
                )

            } else {
                return@withContext Response(
                    data = null,
                    errorCode = response.errorCode,
                    errorMessage = response.errorMessage,
                )

            }
        }

    /**
     * BannerApiModel转换为上层需要的Banner
     *
     * @param bannerApiModel BannerApiModel
     * @return Banner
     */
    private fun convert(bannerApiModel: BannerApiModel): Banner {
        return Banner(
            id = bannerApiModel.id,
            type = bannerApiModel.type,
            title = bannerApiModel.title,
            imagePath = bannerApiModel.imagePath,
            url = bannerApiModel.url,
        )
    }
}