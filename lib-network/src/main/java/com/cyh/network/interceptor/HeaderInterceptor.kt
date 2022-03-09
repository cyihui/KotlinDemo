package com.cyh.network.interceptor


import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Description:统一添加header的拦截器
 * @Author: chenyihui
 * Date: 2022/3/9
 */
class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token")
        return chain.proceed(builder.build())
    }
}