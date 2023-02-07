package com.tlc.wanandroid.data

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.core.net.ResponsePage
import com.tlc.wanandroid.core.net.Retrofit
import com.tlc.wanandroid.data.article.model.ArticleApiModel
import com.tlc.wanandroid.data.banner.model.BannerApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    companion object {
        const val BASE_URL = "https://wanandroid.com/"
    }

    /**
     * Retrofit 自 2.6 版本开始，原生支持了协程，我们只需要在方法前添加 suspend 修饰符，即可直接返回实体对象
     * https://juejin.cn/post/6959115482511343647
     *
     * 分页获取文章列表
     *
     * @param page Int 页数
     * @return Response<ResponsePage<ArticleApiModel>>
     */
    @GET("article/list/{page}/json")
    suspend fun fetchArticleList(@Path("page") page: Int): Response<ResponsePage<ArticleApiModel>>

    @GET("banner/json")
    suspend fun fetchBanner(): Response<List<BannerApiModel>>

}

fun get(): ApiService{
    return Retrofit.getService(ApiService::class.java, ApiService.BASE_URL);
}