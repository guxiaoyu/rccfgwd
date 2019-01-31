package com.zrt.rccfgwd.http

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Administrator on 2019/1/25.
 */
object RetrofitHelper {
//    companion object{
        fun getRetrofit(client:OkHttpClient,base_url:String):Retrofit{
            val gson = GsonBuilder().create()
            val builder = Retrofit.Builder().baseUrl(base_url).client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            val retrofit = builder.build()
            return retrofit
        }
//    }

}