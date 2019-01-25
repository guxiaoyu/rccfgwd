package com.zrt.rccfgwd.utils

import android.app.Activity
import java.util.*

/**
 * Created by Administrator on 2019/1/21.
 */
class ActivityManager private constructor(){

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity){
        if (activityStack == null){
            activityStack = Stack()
        }

        activityStack?.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入栈的）
     */
    fun currentActivity(): Activity? {
        return activityStack?.lastElement()
    }

    fun finishActivity(){
        val lastElement = activityStack?.lastElement()
        finishActivity(lastElement)
    }

    /**
     * 结束指定Activity
     */
    fun finishActivity(activity: Activity?) {
        if (activity!=null){
            activityStack?.remove(activity)
            activity.finish()
        }
    }

    /**
     * 结束指定类名的activity
     */
    fun finishActivity(cls:Class<*>){
        for (activity in activityStack!!){
            if (activity.javaClass == cls)
                finishActivity(activity)
        }
    }

    fun finishAllActivity(){
        for (i in activityStack!!.indices){
            (activityStack as Stack<Activity>)[i].finish()
        }
        activityStack?.clear()
    }

    companion object{
        private var activityStack:Stack<Activity>?=null
        private var instance:ActivityManager?=null
        get(){
            if (field == null){
                field = ActivityManager()
            }
            return field
        }

        fun get():ActivityManager{
            return instance!!
        }

    }
}