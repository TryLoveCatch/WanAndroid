package com.tlc.wanandroid.core.net

import com.tlc.wanandroid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * object
 * 对象声明，可以直接用来实现单例模式
 * 在实际的业务场景是有一定限制的：对于需要携带参数的单例类，object 就有点力不从心了。
 */
object Retrofit {
    private const val TIME_OUT = 5
    private val mMapService by lazy {
        HashMap<Class<*>, Any>()
    }

    private val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .addInterceptor(getHttpLoggingInterceptor())
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        builder.build()
    }

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.BASIC
        }
        return logging
    }

    private fun getRetrofit(baseUrl: String = ""): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    /**
     * 获取retrofit的service
     * @param serviceClass Class<T>
     * @param baseUrl String
     * @return T
     */
    open fun <T> getService(serviceClass: Class<T>, baseUrl: String): T {
        val cache = mMapService[serviceClass];
        return if (cache == null) {
            val service = getRetrofit(baseUrl).create(serviceClass);
            mMapService[serviceClass] = service!!;
            service
        } else {
            cache as T
        }
    }
}