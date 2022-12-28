package com.tlc.wanandroid.ui.article.vm

import com.tlc.wanandroid.data.article.model.Article

data class ArticleUiState (
    val articleItems: List<Article>? = listOf(),
    val errorMsg: String = "",
    val isHasMore: Boolean = false,

)
