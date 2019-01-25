package com.zrt.rccfgwd.http

import android.os.Build
import android.text.TextUtils
import android.util.ArrayMap
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit

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

    var retrofit:Retrofit?=null
    var client:OkHttpClient?=null
    var requests:Map<Object,List<Call>>?=null

    init {
        if (Build.VERSION.SDK_INT >= 19){
            this.requests = ArrayMap()
        }else{
            this.requests = HashMap()
        }
    }

    fun getRetrofit(base_url:String): Retrofit? {
        if (retrofit == null) {
            this.retrofit = client?.let { RetrofitHelper.getRetrofit(it, base_url) }
        }else{
            var url = retrofit?.baseUrl().toString()
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



}