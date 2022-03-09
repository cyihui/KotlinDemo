package com.cyh.network.service

import android.app.Application
import android.content.Context
import android.os.Environment
import com.cyh.base.config.DirConfig
import com.cyh.network.interceptor.HeaderInterceptor
import com.cyh.network.interceptor.LoggingInterceptor
import com.cyh.network.interceptor.NetCacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Description:
 * @Author: chenyihui
 * Date: 2022/3/8
 */
object ServiceCreator {
    private const val TIMEOUT = 10

    private val mOkHttpClient: OkHttpClient by lazy { createClient() }

    private fun createClient(): OkHttpClient = OkHttpClient.Builder().apply {
        //连接超时设置10s
        connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        //写入缓存超时10s
        readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        //读取缓存超时10s
        writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        //失败重连
        retryOnConnectionFailure(false)
        addInterceptor(this)
        addLoggingInterceptor(this)
        setCacheFile(this)
    }.build()

    fun <T> create(baseUrl: String, serviceClass: Class<T>): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(mOkHttpClient)
            .build().create(serviceClass)

    inline fun <reified T> create(baseUrl: String): T = create(baseUrl, T::class.java)


    /**
     * 设置缓存文件路径
     */
    private fun setCacheFile(builder: OkHttpClient.Builder) {
        //设置缓存文件
        val cacheFile = File(DirConfig.HTTP_CACHE)
        //缓存大小为10M
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(cacheFile, cacheSize.toLong())
        builder.cache(cache)
    }

    /**
     * 调试模式下加入日志拦截器
     * @param builder
     */
    private fun addLoggingInterceptor(builder: OkHttpClient.Builder) {
        //debug模式开启
            builder.addInterceptor(LoggingInterceptor())
    }

    private fun addInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(HeaderInterceptor())
        builder.addInterceptor(NetCacheInterceptor())
    }
}