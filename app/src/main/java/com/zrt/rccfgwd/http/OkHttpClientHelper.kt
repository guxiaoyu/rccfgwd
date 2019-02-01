package com.zrt.rccfgwd.http

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Created by Administrator on 2019/1/30.
 */
object OkHttpClientHelper {
    fun getOkHttpClient(context: Context, level: HttpLoggingInterceptor.Level, vararg interceptors: Interceptor): OkHttpClient{
        val builder = Builder()
        val cacheDir = File(context.cacheDir, "netCache")
        var cacheDirStatus = true
        if (!cacheDir.exists()){
            cacheDirStatus = cacheDir.mkdirs()
        }

        builder.connectTimeout(15L,TimeUnit.SECONDS)
        builder.writeTimeout(15L,TimeUnit.SECONDS)
        builder.readTimeout(15L,TimeUnit.SECONDS)
        if ( interceptors.isNotEmpty()){
            val interceptors1 = interceptors
            val size = interceptors1.size
            (0..size.minus(1))
                    .map { interceptors1[it] }
                    .forEach { builder.addInterceptor(it) }
        }
        builder.addNetworkInterceptor(HttpLoggingInterceptor(LogInterceptor()).setLevel(level))
        return builder.build()
    }
}