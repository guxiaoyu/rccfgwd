package com.zrt.rccfgwd.http

import com.zrt.rccfgwd.BuildConfig
import com.zrt.rccfgwd.utils.Config
import retrofit2.Retrofit

/**
 * Created by Administrator on 2019/1/28.
 */
object ApiFactory {
    fun <T> getApiService(calzz:Class<T>):T{
        val retrofit = HttpManager.get().getRetrofit(Config.BASE_URL)
        val t = retrofit?.create(calzz)
        return t!!
    }
}