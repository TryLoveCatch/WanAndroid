package com.tlc.wanandroid.core.utils

import android.util.Log
import com.tlc.wanandroid.BuildConfig
import java.util.*

private const val defaultLogTag: String = BuildConfig.APPLICATION_ID
private const val versionName: String = BuildConfig.VERSION_NAME

private enum class LogLevel {
    Verbose, Debug, Info, Warn, Error
}

/**
 * 给Any增加扩展函数
 * 所有对象都有了这个方法，可以直接调用
 */
fun Any.logv(tag: String = defaultLogTag) {
    intervalLog(
        LogLevel.Verbose,
        tag,
        this
    )
}


fun Any.logd(tag: String = defaultLogTag) {
    intervalLog(
        LogLevel.Debug,
        tag,
        this
    )
}

fun Any.logi(tag: String = defaultLogTag) {
    intervalLog(
        LogLevel.Info,
        tag,
        this
    )
}

fun Any.logw(tag: String = defaultLogTag) {
    intervalLog(
        LogLevel.Warn,
        tag,
        this
    )
}

fun Any.loge(tag: String = defaultLogTag) {
    intervalLog(
        LogLevel.Error,
        tag,
        this
    )
}

fun Throwable.loge(tag: String = defaultLogTag) {
    intervalLog(
        LogLevel.Error,
        tag,
        this.message!!
    )
}

private fun intervalLog(level: LogLevel, tag: String, msg: Any) {
    val tmp = when (msg) {
        msg is Byte, msg is Int, msg is Long, msg is Float, msg is Double, msg is Boolean -> msg.toString()
        else -> msg.toJson()
    }

    val trace = Throwable().fillInStackTrace().stackTrace

    var caller = "<unknown>"
    for (i in 2 until trace.size) {
        var callingClass = trace[i].className
        if (!callingClass.contains("LogExt")) {
            callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1)
            caller = callingClass + "." + trace[i].methodName
            break
        }
    }
    val message = String.format(
            Locale.US, "[%s][%s]%s() -> %s",
        versionName, Thread.currentThread().name, caller, tmp
        )

    if (BuildConfig.DEBUG) {
        when (level) {
            LogLevel.Verbose -> Log.v(tag, message)
            LogLevel.Debug -> Log.d(tag, message)
            LogLevel.Info -> Log.i(tag, message)
            LogLevel.Warn -> Log.w(tag, message)
            LogLevel.Error -> Log.e(tag, message)
        }
    }
}