package com.tlc.wanandroid.data.article

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
    suspend fun fetchArticleList(page: Int): List<Article> =
        withContext(ioDispatcher) {
            val origin = articleDataSource.fetchArticleList(page);
            /**
             * 转换数据
             */
            origin.map { convert(it) }
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