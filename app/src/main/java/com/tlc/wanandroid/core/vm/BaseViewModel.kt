package com.tlc.wanandroid.core.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.plus

/**
 * 增加了异常统一处理
 *
 * ui层以下的统一处理
 *
 * @property viewModelScope2 CoroutineScope
 */
public abstract class BaseViewModel: ViewModel(), IExceptionListener {

    public val viewModelScope2 by lazy {
        viewModelScope + CoroutineExceptionHandlerImpl(this)
    }

}