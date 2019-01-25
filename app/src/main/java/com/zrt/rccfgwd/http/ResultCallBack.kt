package com.zrt.rccfgwd.http

import android.content.Context
import com.zrt.rccfgwd.base.BaseResultCallBack

/**
 * Created by Administrator on 2019/1/24.
 */
abstract class ResultCallBack<T> : BaseResultCallBack<T>() {

    var context:Context? = null
    var view:ILoadingView? = null

    fun ResultCallBack(context: Context){
        this.context = context
    }

    fun ResultCallBack(context: Context,view: ILoadingView){
        this.context = context
        this.view = view
    }

    override fun onStart() {
        view?.show()
    }

    override fun onFinish() {
        view?.dismiss()
    }

    override fun onFailure(code: Int, msg: String) {

        view?.dismiss()
    }
}