package com.tlc.wanandroid.data.article.datasource

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.core.net.ResponsePage
import com.tlc.wanandroid.data.article.model.ArticleApiModel

interface IArticleDataSource {
    suspend fun fetchArticleList(page: Int): Response<ResponsePage<ArticleApiModel>>
}