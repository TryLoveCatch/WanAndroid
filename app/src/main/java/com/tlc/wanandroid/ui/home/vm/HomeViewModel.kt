package com.tlc.wanandroid.ui.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tlc.wanandroid.core.vm.BaseViewModel
import com.tlc.wanandroid.data.article.ArticleRepository
import com.tlc.wanandroid.data.article.datasource.ArticleRemoteDataSource
import com.tlc.wanandroid.data.banner.BannerRepository
import com.tlc.wanandroid.data.banner.datasource.BannerRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {
    private val articleRepository by lazy {
//        ArticleRepository(Dispatchers.IO, ArticleMockDataSource())
        ArticleRepository(Dispatchers.IO, ArticleRemoteDataSource())
    }

    private val bannerRepository by lazy {
        BannerRepository(Dispatchers.IO, BannerRemoteDataSource())
    }

    private val _homeListLiveData = MutableLiveData<HomeUiState>()
    val homeListLiveData: LiveData<HomeUiState> = _homeListLiveData

    fun fetchHome(page: Int) {
        viewModelScope2.launch {
            /**
             * 目前是串行，需要改为并行
             */
            var response = articleRepository.fetchArticleList(page)
            var errorMessage: String = response.errorMessage
            if (errorMessage.isNullOrEmpty()) {
                if (response.data == null || response.data?.datas.isNullOrEmpty()) {
                    errorMessage = "暂无数据"
                }
            }

            var homeUiState = HomeUiState(
                errorMsg = errorMessage,
                isHasMore = if (response.data == null) false else !response.data!!.over,
            )

            if (!errorMessage.isNullOrEmpty()) {
                _homeListLiveData.postValue(homeUiState)
                return@launch
            }
            response.data?.datas?.let {
                homeUiState.items.addAll(it)
            }

            if (page != 0) {
                _homeListLiveData.postValue(homeUiState)
                return@launch
            }
            var bannerResponse = bannerRepository.fetchBanner()
            var bannerErrorMessage: String = bannerResponse.errorMessage
            if (bannerErrorMessage.isNullOrEmpty()) {
                var bannerList = BannerList(
                    data = bannerResponse.data
                )
                bannerList.let {
                    homeUiState.items.add(0, it)
                }
            }

            _homeListLiveData.postValue(homeUiState)

        }
    }
}