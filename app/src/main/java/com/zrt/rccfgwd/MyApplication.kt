package com.zrt.rccfgwd

import android.app.Application

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

    }
}