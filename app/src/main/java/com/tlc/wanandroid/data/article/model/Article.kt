package com.tlc.wanandroid.data.article.model

import com.tlc.wanandroid.core.BaseModel

/**
 * 精简的数据，只包含界面层需要的数据
 * @property author String
 * @property chapterName String
 * @property link String
 * @property niceDate String
 * @property niceShareDate String
 * @property publishTime Long
 * @property shareDate Long
 * @property shareUser String
 * @property superChapterName String
 * @property title String
 * @constructor
 */
data class Article(
    val author: String,
    val chapterName: String,
    val link: String,
    val niceDate: String,
    val niceShareDate: String,
    val publishTime: Long,
    val shareDate: Long,
    val shareUser: String,
    val superChapterName: String,
//    val tags: [],
    val title: String,
): BaseModel()
