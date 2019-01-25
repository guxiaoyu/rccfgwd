package com.zrt.rccfgwd.main

import com.zrt.rccfgwd.base.PresentCallBack

/**
 * Created by Administrator on 2019/1/23.
 */
class MainPresenter {
    private var mModel:MainModel?=null
    private var mView:IMainView?=null

    fun pullData(msg:String){
        mView?.showLoading("")
        mModel?.getAppVersion(object :PresentCallBack<String>{
            override fun onBack(t: String) {
                mView?.showDialog()
            }

            override fun onError(error: String) {
                mView?.showError(error)
            }

            override fun onFinish() {
                mView?.stopLoading()
            }

        })
    }

    fun setVM(mView: IMainView,mainModel: MainModel) {
        this.mView = mView
        this.mModel = mainModel
    }

    fun onDeatory(){
        this.mView = null
        this.mModel = null
    }
}