package com.zrt.rccfgwd.utils

import java.security.MessageDigest
import kotlin.experimental.and

/**
 * Created by Administrator on 2019/1/30.
 */
object MD5Util {
//    fun encrypt(plaintext:String):String{
//        val hexDigits = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
//        try {
//            val btInput = plaintext.toByteArray()
//            val digest = MessageDigest.getInstance("MD5")//获取MD5摘要算法的MessageDigest对象
//            digest.update(btInput)//使用指定字节更新摘要
//            val md = digest.digest()// 获得密文
//            val size = md?.size//密文转换成十六进制的字符串形式
//            val chars = size?.times(2)?.let { CharArray(it) }
//            var k = 0
//            for (i in 0..size!!.minus(1)) {
//                val byte0 = md[i]
//                chars?.set(k++, hexDigits[byte0.ushr(4) and 0xf])
//                chars?.set(k++, hexDigits[(byte0 and 0xf).toInt()])
//            }
//            return String(chars!!)
//        }catch (e:Exception){
//            return ""
//        }
//    }
}