package com.zrt.rccfgwd.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.IBinder
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView

/**
 * Created by Administrator on 2019/1/18.
 */

class HideKey

private constructor(activity: Activity,content:ViewGroup?){
    init{
        var mContext = content
        if (mContext == null) {
            mContext = activity.findViewById<View>(android.R.id.content) as ViewGroup
        }
        getScrollView(mContext,activity)
        mContext.setOnTouchListener { _, motionEvent ->
            dispatchTouchEvent(activity,motionEvent)
            false
        }
    }

    private fun getScrollView(viewGroup: ViewGroup?,activity: Activity){
        if (null == viewGroup){
            return
        }
        val count = viewGroup.childCount
        for (i in 0 until count){
            val view = viewGroup.getChildAt(i)
            when(view){
                is ScrollView -> view.setOnTouchListener{_,motionEvent ->
                    dispatchTouchEvent(activity,motionEvent)
                    false
                }
                is AbsListView -> view.setOnTouchListener { _, motionEvent ->
                    dispatchTouchEvent(activity,motionEvent)
                    false
                }
                is RecyclerView -> view.setOnTouchListener { _, motionEvent ->
                    dispatchTouchEvent(activity,motionEvent)
                    false
                }
                is ViewGroup -> this.getScrollView(view,activity)
            }

            if (view.isClickable && view is TextView && view !is EditText){
                view.setOnTouchListener { _, motionEvent ->
                    dispatchTouchEvent(activity,motionEvent)
                    false
                }
            }
        }
    }

    private fun dispatchTouchEvent(mActivity:Activity,ev:MotionEvent):Boolean {
        if(ev.action == MotionEvent.ACTION_DOWN){
            val v = mActivity.currentFocus
            if (null !=v && isShouldHideInput(v,ev)){
                hideSoftInput(mActivity,v.windowToken)
            }
        }

        return false
    }

    private fun isShouldHideInput(v:View,event: MotionEvent):Boolean{
        if (v is EditText){
            val rect = Rect()
            v.getHitRect(rect)
            if (rect.contains(event.x.toInt(),event.y.toInt())){
                return false
            }
        }

        return true
    }

    private fun hideSoftInput(mActivity: Activity,token:IBinder?){
        if (token != null){
            val im = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token,InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    companion object{

        fun init(activity: Activity){
            HideKey(activity,null)
        }

        fun init(activity: Activity,content: ViewGroup){
            HideKey(activity, content)
        }

        fun hideSoftKeyBoard(activity: Activity){
            val view = activity.currentFocus
            if (null != view){
                val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        fun hideSoftKeyboard(activity: Activity,view :View) {
            val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}

