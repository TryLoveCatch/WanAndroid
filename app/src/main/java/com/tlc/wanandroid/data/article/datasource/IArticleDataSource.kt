package com.tlc.wanandroid.data.article.datasource

import com.tlc.wanandroid.data.article.model.ArticleApiModel

interface IArticleDataSource {
    suspend fun fetchArticleList(page: Int): List<ArticleApiModel>
}