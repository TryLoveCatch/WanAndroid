package com.tlc.wanandroid.core.vm

import com.google.gson.JsonParseException
import com.tlc.wanandroid.core.utils.loge
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.net.ssl.SSLHandshakeException
import javax.security.cert.CertificateException
import kotlin.coroutines.CoroutineContext

/**
 * 未捕获异常处理
 * 参考：https://blog.fintopia.tech/6119e0152078082a378ec5f4/
 *
 * 把异常处理都统一到这个地方来处理
 * 例如，ApiErrorException，这个是我们自定义的一些异常，当然也可以没有
 * 比如说，code为没有登录，就可以抛一个异常，然后统一跳转到登录界面
 *
 *
 * 如果某个api需要单独处理的，自己来单独使用try catch进行处理，这个过程最好是再ViewModel里面
 *
 *
 * 这个类的使用，需要在ViewModel里面，对viewModelScope进行拦截异常，参见[BaseViewModel]
 *
 *
 *
 *
 * @property key Key<*>
 */
class CoroutineExceptionHandlerImpl: CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        exception.loge()
        when (exception) {
//            is ApiErrorException -> {
//                //处理业务错误
//            }
            is JsonParseException -> {
                //数据解析异常
            }
            is CertificateException, is SSLHandshakeException -> {
                //证书异常
            }
            else -> {
            }
        }
    }
}