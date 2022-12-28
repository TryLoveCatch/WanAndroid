package com.tlc.wanandroid.data.article.datasource

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.core.net.ResponsePage
import com.tlc.wanandroid.data.article.model.ArticleApiModel

class ArticleTestDataSource: IArticleDataSource {
    override suspend fun fetchArticleList(page: Int): Response<ResponsePage<ArticleApiModel>> {

        /**
         * 模拟异常
         */



        /**
         * 模拟数据
         */
        val result = mutableListOf<ArticleApiModel>()
        var responsePage = ResponsePage(
            curPage = 1,
            offset = 0,
            over = false,
            pageCount = 2,
            size = 20,
            total = 38,
            datas = result,
        )
        var response = Response(
            errorCode = 0,
            errorMessage = "",
            data = responsePage,
        )

        Thread.sleep(2000)
        return response
    }
}