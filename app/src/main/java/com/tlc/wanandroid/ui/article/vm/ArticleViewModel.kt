package com.tlc.wanandroid.ui.article.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tlc.wanandroid.core.vm.BaseViewModel
import com.tlc.wanandroid.data.article.ArticleRepository
import com.tlc.wanandroid.data.article.datasource.ArticleNetworkDataSource
import com.tlc.wanandroid.data.article.datasource.ArticleTestDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel : BaseViewModel() {
    private val articleRepository by lazy {
//        ArticleRepository(Dispatchers.IO, ArticleTestDataSource())
        ArticleRepository(Dispatchers.IO, ArticleNetworkDataSource())
    }

    private val _articleListLiveData = MutableLiveData<ArticleUiState>()
    val articleListLiveData: LiveData<ArticleUiState> = _articleListLiveData

    fun fetchArticleList(page: Int) {
        viewModelScope2.launch {
            var response = articleRepository.fetchArticleList(page)
            var errorMessage: String = response.errorMessage
            if (errorMessage.isNullOrEmpty()) {
                if (response.data == null || response.data?.datas.isNullOrEmpty()) {
                    errorMessage = "暂无数据"
                }
            }

            var articleUiState = ArticleUiState(
                errorMsg = errorMessage,
                articleItems = response.data?.datas,
                isHasMore = if (response.data == null) false else !response.data!!.over,
                )
            _articleListLiveData.postValue(articleUiState)
        }
    }
}