package com.zrt.rccfgwd

import android.app.Application
import com.zrt.rccfgwd.http.HttpManager
import com.zrt.rccfgwd.http.OkHttpClientHelper
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by Administrator on 2019/1/18.
 */
class MyApplication :Application(){
    companion object{
        val APP_ID = "wxd04e84116973343d"//正式appid
        lateinit var instance:MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val client = OkHttpClientHelper.getOkHttpClient(this.applicationContext, HttpLoggingInterceptor.Level.BODY, Interceptor())
        HttpManager.get().init(client)

    }
}