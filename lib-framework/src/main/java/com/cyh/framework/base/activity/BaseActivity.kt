package com.cyh.framework.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Description:BaseActivity
 * @Author: chenyihui
 * Date: 2022/3/9
 */
abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
    }

    protected abstract fun setLayoutId(): Int
}