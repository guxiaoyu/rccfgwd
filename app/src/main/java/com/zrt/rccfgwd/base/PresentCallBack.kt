package com.zrt.rccfgwd.base

/**
 * Created by Administrator on 2019/1/18.
 */
interface PresentCallBack<T> {
    fun onBack(t:T)
    fun onError(error:String)
    fun onFinish()
}