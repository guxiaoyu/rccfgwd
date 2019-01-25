package com.zrt.rccfgwd.utils

import android.util.Log

/**
 * Created by Administrator on 2019/1/23.
 */
class MyLogUtils private constructor(){
    companion object{
        private var TAG ="MyLogUtils"
        private var instance:MyLogUtils?=null
        get() {
            if (field == null)
                field = MyLogUtils()
            return field
        }
        fun get():MyLogUtils{
            return instance!!
        }
        private var IS_SHOW_DEBUG = true
    }

    fun debug(msg:String){
        if (IS_SHOW_DEBUG)
            Log.d(TAG,msg)
    }

    fun info(msg:String){
        if (IS_SHOW_DEBUG)
            Log.i(TAG,msg)
    }

    fun error(msg:String){
        if (IS_SHOW_DEBUG)
            Log.e(TAG,msg)
    }
}