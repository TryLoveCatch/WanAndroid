package com.tlc.wanandroid.data.article.datasource

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.core.net.ResponsePage
import com.tlc.wanandroid.data.article.model.ArticleApiModel
import com.tlc.wanandroid.data.get

class ArticleNetworkDataSource: IArticleDataSource {

    override suspend fun fetchArticleList(page: Int): Response<ResponsePage<ArticleApiModel>> {
        return get().fetchArticleList(page)
    }
}