package com.cyh.framework.databinding

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Description:
 * @Author: chenyihui
 * Date: 2022/3/11
 */
object BindingAdapterUtil {

    @JvmStatic
    @BindingAdapter(value = ["imgUrl"], requireAll = true)
    fun setImgUrl(imageView:ImageView, url:String?) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}