package com.cyh.framework.app

import android.app.Application

/**
 * Description:BaseApplication
 * @Author: chenyihui
 * Date: 2022/3/9
 */
class BaseApplication : Application() {
    companion object {
        var instance: Application? = null
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}