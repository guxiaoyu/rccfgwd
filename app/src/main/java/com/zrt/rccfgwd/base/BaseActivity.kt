package com.zrt.rccfgwd.base

import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.zrt.rccfgwd.utils.ActivityManager
import com.zrt.rccfgwd.utils.HideKey
import pub.devrel.easypermissions.AppSettingsDialog

/**
 * Created by Administrator on 2019/1/18.
 */
abstract class BaseActivity : AppCompatActivity(),BaseView{
    private var isAllowScreenRoate = true
    private var prDialog:ProgressDialog?=null
    private var settingDialog:AppSettingsDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            try {
                val decorViewCalzz = Class.forName("com.android.internal.policy.DecorView")
                val field = decorViewCalzz.getDeclaredField("mSemiTransparentStatusBarColor")
                field.isAccessible=true
                field.setInt(window.decorView,Color.TRANSPARENT)
            }catch (e:Exception){

            }
        }

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        HideKey.init(this)//键盘点击空白消失
        initPresenter()
        val layout = getLayout()
        if (layout!=0)
            setContentView(layout)
        initView()
        setListener()
        initData()
        ActivityManager.get().addActivity(this)
    }

    protected abstract fun getLayout():Int
    protected abstract fun setListener()
    protected abstract fun initView()
    protected abstract fun initData()
    abstract fun initPresenter()
    override fun showLoading(title: String) {
        showProgressDialog(title)
    }

    override fun stopLoading() {
        dismissProgressDialog()
    }

    override fun showError(msg: String) {

    }

    /**
     * 是否允许屏幕旋转
     * @param isAllowScreenRoate
     */
    fun setScreenRoate(isAllowScreenRoate:Boolean){
        this.isAllowScreenRoate = isAllowScreenRoate
    }

    protected fun isCustomerBar():Boolean{
        return false;
    }

    /**
     * 自定义状态栏
     */
    protected fun customerBar(){

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    fun showProgressDialog(msg: String){
        if(prDialog == null)
            prDialog = ProgressDialog(this)
        if (!TextUtils.isEmpty(msg)) {
            prDialog?.setTitle(msg)
        }

        prDialog?.show()
    }

    fun dismissProgressDialog(){
        if (prDialog?.isShowing!!)
            prDialog?.dismiss()
    }

    override fun onStop() {
        super.onStop()
        HideKey.hideSoftKeyBoard(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.get().finishActivity(this)
    }
}