package com.zrt.rccfgwd

import com.zrt.rccfgwd.base.BaseActivity
import com.zrt.rccfgwd.entity.VersionRequestBody
import com.zrt.rccfgwd.main.IMainView
import com.zrt.rccfgwd.main.MainModel
import com.zrt.rccfgwd.main.MainPresenter

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
//        var versionRequestBody = VersionRequestBody(
//
//        )

    }

    override fun initPresenter() {
        mPresenter = MainPresenter()
        mModel = MainModel()
        mPresenter?.setVM(this, mModel!!)
    }

}
