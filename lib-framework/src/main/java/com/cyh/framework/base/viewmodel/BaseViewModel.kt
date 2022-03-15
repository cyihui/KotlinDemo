package com.cyh.framework.base.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cyh.framework.base.livedata.BaseLiveData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * Description:
 * @Author: chenyihui
 * Date: 2022/3/9
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    var baseLiveData = BaseLiveData()
    val mStateLiveData = MutableLiveData<State>()

    fun <T> liveDataEx(block: suspend () -> T) = liveData {
        kotlin.runCatching {
            mStateLiveData.value = LoadState
            block()
        }.onSuccess {
            emit(it)
            mStateLiveData.value = SuccessState
        }.onFailure { e ->
            mStateLiveData.value = ErrorState(e.message)
        }
    }

    //使用Flow流式编程类似RxJava
    fun <T> flowEx(block: suspend () -> T) = flow {
        emit(block())
    }.onStart {
        mStateLiveData.value = LoadState
    }.onCompletion {
        mStateLiveData.value = SuccessState
    }.catch { cause ->
        mStateLiveData.value = ErrorState(cause.message)
    }.asLiveData()
}

sealed class State

object LoadState : State()

object SuccessState : State()

class ErrorState(val errorMsg: String?) : State()