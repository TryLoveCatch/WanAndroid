package com.tlc.wanandroid.ui.home.vm

import com.tlc.wanandroid.core.BaseModel

data class HomeUiState (
    val items: MutableList<BaseModel> = mutableListOf(),
    val errorMsg: String = "",
    val isHasMore: Boolean = false,
    )
