package com.tlc.wanandroid.ui.article.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonParseException
import com.tlc.wanandroid.core.vm.BaseViewModel
import com.tlc.wanandroid.data.article.ArticleRepository
import com.tlc.wanandroid.data.article.datasource.ArticleNetworkDataSource
import com.tlc.wanandroid.data.article.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel : BaseViewModel() {
    private val articleRepository by lazy {
//        ArticleRepository(Dispatchers.IO, ArticleTestDataSource())
        ArticleRepository(Dispatchers.IO, ArticleNetworkDataSource())
    }

    private val _articleListLiveData = MutableLiveData<List<Article>>()
    val articleListLiveData: LiveData<List<Article>> = _articleListLiveData

    fun fetchArticleList(page: Int) {
        viewModelScope2.launch {
            _articleListLiveData.postValue(articleRepository.fetchArticleList(page))
        }
    }
}