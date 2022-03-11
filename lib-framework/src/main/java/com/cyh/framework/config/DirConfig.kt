package com.cyh.framework.config

import android.os.Environment

/**
 * Description:DirConfig
 * @Author: chenyihui
 * Date: 2022/3/9
 */
object DirConfig {
    /**
     * 请求数据缓存
     */
    val HTTP_CACHE = Environment.getExternalStorageDirectory().absolutePath + "/httpCache"
}