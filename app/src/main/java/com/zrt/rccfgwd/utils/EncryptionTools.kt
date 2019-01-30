package com.zrt.rccfgwd.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Administrator on 2019/1/29.
 */
object EncryptionTools {
    var TIMESTAMP =""
    var NONCE=""
    var SIGNATURE=""

    fun initEncrypMD5(Token:String){
        TIMESTAMP = getDateToString("yyyyMMddHHmmss")
        NONCE = Random().nextInt(10000).toString()
        var stitching = Token + TIMESTAMP + NONCE

    }

    fun getDateToString(pattern:String):String{
        val date = Date()
        val format = SimpleDateFormat(pattern)
        return format.format(date)
    }

//    fun sort(str:String):String{
//        str.to
//    }
}