package com.tlc.wanandroid.data.article.datasource

import com.tlc.wanandroid.data.article.model.ArticleApiModel

class ArticleTestDataSource: IArticleDataSource {
    override suspend fun fetchArticleList(page: Int): List<ArticleApiModel> {
        val result = mutableListOf<ArticleApiModel>();
        /**
         * 模拟数据
         */
        Thread.sleep(2000)
        return result;
    }
}