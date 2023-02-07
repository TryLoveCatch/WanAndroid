package com.tlc.wanandroid.core.utils

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.tlc.wanandroid.MyApplication

/**
 * by 属性委托
 *
 * by lazy by viewModels by activityViewModes本质上都是属性委托。
 * lazy是函数，viewModels是函数，activityViewModels也是函数，
 * 他们返回了Delegate 对象，且这个对象必须要有 operator fun getValue operator fun setValue ，
 * 用val声明不需要 setValue.
 *
 * Kotlin中属性在声明的同时也要求要被初始化，否则会报错
 * 有的时候，是不能直接赋值的，可能是动态创建的
 * Kotlin中有两种延迟初始化的方式。一种是lateinit var，一种是by lazy。
 *
 * private lateinit var viewBinding2: ViewBinding;
 * 只能用来修饰类属性，不能用来修饰局部变量
 * 只能用来修饰对象，不能用来修饰基本类型(因为基本类型的属性在类加载后的准备阶段都会被初始化为默认值)。
 *
 * //用于属性延迟初始化
 * val name: Int by lazy { 1 }
 *
 * by lazy要求属性声明为val，即不可变变量
 */
val mainHandler by lazy {
    Handler(Looper.getMainLooper())
}

/**
 * toast(String)和String.toast()
 * 都打开，会报方法签名冲突
 * 因为String.toast()反编译之后，也是生成了一个toast(String)的方法，所以冲突了
 */
fun Any.toast() {
    val message = when (this) {
        this is Byte, this is Int, this is Long, this is Float, this is Double, this is Boolean -> this.toString()
        else -> this.toJson()
    }
    runOnUi {
        Toast.makeText(MyApplication.instance, message, Toast.LENGTH_SHORT).show()
    }
}


//fun toast(m: String) {
//    runOnUi {
//        Toast.makeText(MyApplication.instance, m, Toast.LENGTH_SHORT).show()
//    }
//}


private fun runOnUi(block: () -> Unit) {
    if (Looper.getMainLooper() == Looper.myLooper()) {
        block()
    } else {
        mainHandler.post(block)
    }
}