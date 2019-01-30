package com.zrt.rccfgwd

import com.zrt.rccfgwd.base.BaseActivity
import com.zrt.rccfgwd.entity.VersionRequestBody
import com.zrt.rccfgwd.main.IMainView
import com.zrt.rccfgwd.main.MainModel
import com.zrt.rccfgwd.main.MainPresenter
import com.zrt.rccfgwd.utils.EncryptionTools
import com.zrt.rccfgwd.utils.RccfUtils
import com.zrt.rccfgwd.utils.SpUtils

class MainActivity : BaseActivity(), IMainView {
    override fun showDialog() {

    }

    private var mPresenter:MainPresenter?=null
    private var mModel:MainModel?=null
    private var msg:String?=null
    override fun getLayout(): Int {
        return  R.layout.activity_main

    }

    override fun setListener() {
    }

    override fun initView() {
    }

    override fun initData() {
        EncryptionTools.initEncrypMD5(SpUtils.getToken(MyApplication.instance))
        var versionRequestBody = VersionRequestBody(
                SpUtils.getToken(MyApplication.instance),
                EncryptionTools.TIMESTAMP,
                EncryptionTools.NONCE,
                EncryptionTools.SIGNATURE,
                "3",
                "OperationSyste:"+"Android"+RccfUtils.getSystemVersion()+"ApplicationVersion:"
        +RccfUtils.getVersionName(MyApplication.instance)+"("+RccfUtils.getVersionCode(MyApplication.instance)
        +")"+"PhoneModels:"+RccfUtils.getSystemModel(),
                "",
                "1"
        )
        mPresenter?.pullData(versionRequestBody)

    }

    override fun initPresenter() {
        mPresenter = MainPresenter()
        mModel = MainModel()
        mPresenter?.setVM(this, mModel!!)
    }

}
