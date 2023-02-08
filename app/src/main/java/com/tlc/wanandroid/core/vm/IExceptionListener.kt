package com.tlc.wanandroid.core.vm

import kotlin.coroutines.CoroutineContext

interface IExceptionListener {
    fun onException(context: CoroutineContext, exception: Throwable)
}