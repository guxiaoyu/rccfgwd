package com.zrt.rccfgwd.base

import org.json.JSONException
import rx.Observer
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException

/**
 * Created by Administrator on 2019/1/24.
 */
abstract class BaseResultCallBack<T> :Observer<T> {

    fun BaseResultCallBack(){}
    open fun onStart(){}
    open fun onFinish(){}
    abstract fun onSuccess(t:T)
    abstract fun onFailure(code:Int,msg:String)
    override fun onNext(t: T) {
        this.onSuccess(t)
    }

    override fun onCompleted() {
        this.onFinish()
    }

    override fun onError(e: Throwable?) {
        if (e is SocketTimeoutException) {
            this.onFailure(-1, "连接超时，请重试")
        } else if (e is SSLException) {
            this.onFailure(-2, "证书错误")
        } else if (e is JSONException) {
            this.onFailure(-3, "解析异常")
        } else {
            this.onFailure(-4, "连接失败，请重试")
        }
    }
}