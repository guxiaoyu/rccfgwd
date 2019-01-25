package com.zrt.rccfgwd.base

/**
 * Created by Administrator on 2019/1/18.
 */
interface BaseView {
    fun showLoading(title:String)
    fun stopLoading()
    fun showError(msg:String)
}