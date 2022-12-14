package com.tlc.wanandroid

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import android.text.TextUtils


/**
 * 继承
 * 注意Application()后面的小括号
 * 如果不这样写，也可以
 * class MyApplication : Application {
 *  constructor() : super()
 * }
 *
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (!TextUtils.equals(getCurrentProcessName(this), packageName)) {
            return
        }
        instance = this
    }

    /**
     * 伴生对象
     *
     * 静态方法和静态属性
     */
    companion object {
        /**
         * JvmField，不会生成get和set方法，而是直接将属性设置为public
         *
         * lateinit var 延迟赋值，这样使用的地方，就可以不用做空的判断
         *
         * JvmStatic会在MyApplication里面增加一个static的get方法，但是感觉没什么用啊，不加也能使用
         */
//        @JvmField
        lateinit var instance: MyApplication
//            @JvmStatic get
            private set
    }

    private fun getCurrentProcessName(context: Context): String? {
        val pid = Process.myPid()
        val mActivityManager = context
            .getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (appProcess in mActivityManager.runningAppProcesses) {
            if (appProcess.pid == pid) {
                return appProcess.processName
            }
        }
        return null
    }
}