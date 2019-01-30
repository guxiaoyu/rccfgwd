package com.zrt.rccfgwd.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Administrator on 2019/1/30.
 */
object SpUtils {
    private val RCCF_SP = "rccf_sp"
    private val TOKEN = "token"
    private fun getSp(context: Context):SharedPreferences{
        return context.getSharedPreferences(RCCF_SP,Context.MODE_PRIVATE)
    }

    fun setToken(context: Context,token:String){
        getSp(context).edit().putString(TOKEN,token).apply()
    }

    fun getToken(context: Context):String{
        return getSp(context).getString(TOKEN,"")
    }
}