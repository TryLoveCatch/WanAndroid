package com.tlc.wanandroid.data.banner.model


/**
 * xxx
 *  desc: "一起来做个App吧",
    id: 10,
    imagePath: "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
    isVisible: 1,
    order: 1,
    title: "一起来做个App吧",
    type: 1,
    url: "https://www.wanandroid.com/blog/show/2"
 * @constructor
 */
data class BannerApiModel(
    val id: Int,
    val type: Int,
    val title: String,
    val desc: String,
    val imagePath: String,
    val order: Int,
    val isVisible: Int,
    val url: String
)
