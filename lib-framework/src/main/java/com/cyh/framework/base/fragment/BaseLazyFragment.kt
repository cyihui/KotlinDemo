package com.cyh.framework.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Description:BaseLazyFragment
 * @Author: chenyihui
 * Date: 2022/3/9
 */
abstract class BaseLazyFragment : Fragment() {

    protected var mRootView: View? = null
    private var canVisible = false

    /** 是否是第一次加载布局  */
    private var isInitView = false

    lateinit var mActivity: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initLoadMoreViews()
        isInitView = true
        isCanLoadData

        isVisible
        return getContentView(layoutInflater, container)
//        if (mRootView == null) {
//            mRootView = inflater.inflate(getLayoutRes, container, false)
//        }
//        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //isVisibleToUser值表示:该Fragment的UI用户是否可见，获取该标志记录下来
        if (isVisibleToUser) {
            canVisible = true
            isCanLoadData
        } else {
            canVisible = false
        }
    }

    //所以能加载数据条件是view初始化完成并且对用户可见
    private val isCanLoadData: Unit
        private get() {
            //所以能加载数据条件是view初始化完成并且对用户可见
            if (canVisible && isInitView) {
                loadData()
                canVisible = false
                isInitView = false
            }
        }

    protected abstract fun initView()

    protected fun initData() {}

    protected fun initLoadMoreViews() {}

    /**
     * 懒加载方法
     */
    protected fun loadData() {}

    abstract fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View

//    abstract val getLayoutRes: Int
}