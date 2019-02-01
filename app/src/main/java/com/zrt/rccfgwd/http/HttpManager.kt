package com.zrt.rccfgwd.http

import android.os.Build
import android.text.TextUtils
import android.util.ArrayMap
import com.google.gson.JsonParseException
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.zrt.rccfgwd.base.BaseResultCallBack
import retrofit2.Call
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import javax.net.ssl.SSLException

/**
 * Created by Administrator on 2019/1/25.
 */
open class HttpManager{
    companion object{
        var mInstance:HttpManager?=null
            get(){
                if (field==null){
                    field = HttpManager()
                }
                return field
            }
        @Synchronized
        fun get():HttpManager{
            return mInstance!!
        }
    }

    var retrofit:Retrofit ?= null
    lateinit var client:OkHttpClient
    var requests:MutableMap<Any,MutableList<Call<Any>>>

    init {
        if (Build.VERSION.SDK_INT >= 19){
            this.requests = ArrayMap()
        }else{
            this.requests = HashMap()
        }
    }

    fun getRetrofit(base_url:String): Retrofit? {
        if (retrofit == null) {
            this.retrofit = client.let { RetrofitHelper.getRetrofit(it, base_url) }
        }else{
            val url = retrofit!!.baseUrl().toString()
            if (!TextUtils.equals(base_url,url)){
                this.retrofit!!.newBuilder().baseUrl(base_url).build()
            }
        }

        return this.retrofit
    }

    fun init(client: OkHttpClient) {
        Logger.addLogAdapter(AndroidLogAdapter())
        this.client = client
    }


    fun <T> execute(call:Call<T>,callBack:BaseResultCallBack<T>){
        this.execute(call,"null",callBack)
    }

    fun <T> execute(call:Call<T>,requestId:Any,callBack: BaseResultCallBack<T>){
//        if(call!=null){
            if (this.requests.containsKey(requestId)){
                val calls = this.requests[requestId] as MutableList<Call<T>>
                calls.add(call)
            }else{
                val calls : MutableList<Call<T>> = arrayListOf()
                calls.add(call)
                this.requests[requestId] = calls  as MutableList<Call<Any>>
            }
//        }

        callBack.onStart()

        call.enqueue(object :Callback<T>{
            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                if (response!!.isSuccessful){
                    callBack.onSuccess(response.body()!!)
                }else {
                    callBack.onFailure(response.code(), "连接失败，请重试" + response.code())
                }

                callBack.onFinish()

                removeCall(requestId, call as Call<T>)

            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                callBack.onFinish()

                if (!call!!.isCanceled){
                    if (t is SocketTimeoutException) {
                        callBack.onFailure(-1, "连接超时，请重试")
                    }else if (t is SSLException){
                        callBack.onFailure(-2,"证书错误")
                    }else if (t is JsonParseException){
                        callBack.onFailure(-3,"解析异常")
                    }else{
                        callBack.onFailure(-4,"连接失败，请重试")
                    }

                    removeCall(requestId, call)
                }
            }

        })



    }

    fun <T> removeCall(requestId: Any,call: Call<T>){
        val calls = this.requests[requestId] as MutableList<Call<T>>
        calls.remove(call)
        if(calls.isEmpty())
            this.requests.remove(requestId)
    }

    fun cancel(requestId: Any){
        if (requests!=null && !requests.isEmpty() && requests.containsKey(requestId)){
            val valus = requests[requestId]
            val iterator = valus?.iterator()
            if (iterator != null) {
                while (iterator.hasNext()){
                    val value = iterator.next()
                    if(value!=null && !value.isCanceled){
                        value.cancel()
                    }
                }
            }
            requests.remove(requestId)
        }
    }
}