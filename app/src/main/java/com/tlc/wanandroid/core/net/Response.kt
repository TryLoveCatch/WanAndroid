package com.tlc.wanandroid.core.net

data class Response<T>(
    val data: T,
    val errorCode: Int,
    val errorMessage: String,
)
