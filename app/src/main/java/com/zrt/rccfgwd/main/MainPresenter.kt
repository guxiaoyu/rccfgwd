package com.zrt.rccfgwd.main

import com.zrt.rccfgwd.base.PresentCallBack
import com.zrt.rccfgwd.entity.VersionEntity
import com.zrt.rccfgwd.entity.VersionRequestBody
import com.zrt.rccfgwd.utils.MyLogUtils

/**
 * Created by Administrator on 2019/1/23.
 */
class MainPresenter {
    private var mModel:MainModel?=null
    private var mView:IMainView?=null

    fun pullData(versionRequestBody: VersionRequestBody){
        mView?.showLoading("")
        mModel?.getAppVersion(versionRequestBody,object :PresentCallBack<VersionEntity>{
            override fun onBack(t: VersionEntity) {
                MyLogUtils.get().error("response:onBack"+t)
                mView?.showDialog()
                mView?.stopLoading()
            }

            override fun onError(error: String) {
                MyLogUtils.get().error("response:onError"+error)
//                mView?.showError(error)
                mView?.stopLoading()

            }

            override fun onFinish() {
                MyLogUtils.get().error("response:onFinish")
                mView?.stopLoading()
            }

        })
    }

    fun setVM(mView: IMainView,mainModel: MainModel) {
        this.mView = mView
        this.mModel = mainModel
    }

    fun onDestory(){
        this.mView = null
        this.mModel = null
    }
}