package com.tlc.wanandroid.core.utils

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ParameterizedTypeImpl(private val clz: Class<*>) : ParameterizedType {
    override fun getRawType(): Type = List::class.java

    override fun getOwnerType(): Type? = null

    override fun getActualTypeArguments(): Array<Type> = arrayOf(clz)
}

/**
 * String 转 ListBean
 */
inline fun <reified T> String.toBeanList(): List<T> = Gson().fromJson<List<T>>(this, ParameterizedTypeImpl(T::class.java))

/**
 * String 转 Bean
 *
 * inline内联函数的本质是：把函数体复制粘贴到函数调用处
 *
 * reified: 使抽象的东西更加具体或真实
 * https://juejin.cn/post/6844903833596854279
 *
 */
inline fun <reified T> String.toBean(): T = Gson().fromJson("", T::class.java)

/**
 * Bean 转 Json
 */
fun Any.toJson(): String = Gson().toJson(this)