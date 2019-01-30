package com.zrt.rccfgwd.main

import com.zrt.rccfgwd.base.BaseResultCallBack
import com.zrt.rccfgwd.base.PresentCallBack
import com.zrt.rccfgwd.entity.VersionRequestBody
import com.zrt.rccfgwd.http.HttpManager
import com.zrt.rccfgwd.http.RccfRequest
import com.zrt.rccfgwd.http.ResultBean

/**
 * Created by Administrator on 2019/1/23.
 */
class MainModel {
    fun getAppVersion(versionRequestBody: VersionRequestBody,presentCallBack: PresentCallBack<String>){
        RccfRequest.getVersion(versionRequestBody,object : BaseResultCallBack<ResultBean<String>>() {
            override fun onSuccess(t: ResultBean<String>) {
                t.result?.let { presentCallBack.onBack(it) }
            }

            override fun onFailure(code: Int, msg: String) {
                presentCallBack.onError(msg)
            }

        })
    }
}