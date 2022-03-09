package com.cyh.network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Description:Okhttp返回数据日志拦截器
 * @Author: chenyihui
 * Date: 2022/3/8
 */
class LoggingInterceptor : Interceptor {
    private val byteCount = 1024 * 1024

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //chain里面包含了request和response，按需获取
        val request = chain.request()
        val response = chain.proceed(request)
        Log.d("intercept", String.format("发送请求  %s", request.url))
        val responseBody = response.peekBody(byteCount.toLong())
        Log.d("intercept", "responseBody: " + responseBody.string())
        return response
    }
}