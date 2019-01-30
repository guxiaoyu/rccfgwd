package com.zrt.rccfgwd.utils

import android.content.Context
import android.content.pm.PackageManager

/**
 * Created by Administrator on 2019/1/30.
 */
object RccfUtils {

    /**
     * 获取版本信息
     */
    fun getVersionCode(context: Context):String{
        val pm = context.packageManager
        try {
            val info = pm.getPackageInfo(context.packageName, 0)
            return "" + info.versionCode
        }catch (e:PackageManager.NameNotFoundException){
            return ""
        }
    }

    /**
     * 获取版本信息
     */
    fun getVersionName(context: Context):String{
        val pm = context.packageManager
        try {
            val info = pm.getPackageInfo(context.packageName, 0)
            return info.versionName
        }catch (e:PackageManager.NameNotFoundException){
            return ""
        }

    }
    /**
     * 获取当前手机系统版本号
     */
    fun getSystemVersion():String{
        return android.os.Build.VERSION.RELEASE
    }

    /**
     * 获取当前手机系统版本
     */
    fun getSystemModel():String{
        return android.os.Build.MODEL
    }
}