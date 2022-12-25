package com.tlc.wanandroid.data.article.datasource

import com.tlc.wanandroid.data.article.model.ArticleApiModel
import com.tlc.wanandroid.data.get

class ArticleNetworkDataSource: IArticleDataSource {
    /**
     * retrofit的错误怎么统一处理呢？
     */
    override suspend fun fetchArticleList(page: Int): List<ArticleApiModel> {
        val result =  get().fetchArticleList(page);
        return result.data.datas;
    }
}