package com.tlc.wanandroid.data.article

import com.tlc.wanandroid.core.net.Response
import com.tlc.wanandroid.core.net.ResponsePage
import com.tlc.wanandroid.data.article.datasource.IArticleDataSource
import com.tlc.wanandroid.data.article.model.Article
import com.tlc.wanandroid.data.article.model.ArticleApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ArticleRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val articleDataSource: IArticleDataSource
) {
    /**
     * 根据页数得到文章列表
     * @param page Int
     * @return List<Article>
     */
    suspend fun fetchArticleList(page: Int): Response<ResponsePage<Article>> =
        withContext(ioDispatcher) {
            val response = articleDataSource.fetchArticleList(page)
            if (response.errorCode == 0 && response.data != null) {
                Response(
                    data = ResponsePage(
                        datas = response.data.datas.map { convert(it) },
                        curPage = response.data.curPage,
                        offset = response.data.offset,
                        over = response.data.over,
                        pageCount = response.data.pageCount,
                        size = response.data.size,
                        total = response.data.total,
                    ),
                )

            } else {
                Response(
                    data = null,
                    errorCode = response.errorCode,
                    errorMessage = response.errorMessage,
                )

            }


        }

    /**
     * ArticleApiModel转换为上层需要的Article
     *
     * @param articleApiModel ArticleApiModel
     * @return Article
     */
    private fun convert(articleApiModel: ArticleApiModel): Article {
        return Article(
            author = articleApiModel.author,
            chapterName = articleApiModel.chapterName,
            link = articleApiModel.link,
            niceDate = articleApiModel.niceDate,
            niceShareDate = articleApiModel.niceShareDate,
            publishTime = articleApiModel.publishTime,
            shareDate = articleApiModel.shareDate,
            shareUser = articleApiModel.chapterName,
            superChapterName = articleApiModel.superChapterName,
            title = articleApiModel.title,
        )
    }
}