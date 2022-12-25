package com.tlc.wanandroid.core.net

/**
 * 包含分页的一些数据
 * @param T
 * @property data T
 * @property errorCode Int
 * @property errorMessage String
 * @constructor
 */
data class ResponsePage<T>(
    val datas: List<T>,
    val curPage: Int,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int,
)
