package com.zrt.rccfgwd.main

import com.zrt.rccfgwd.base.BaseResultCallBack
import com.zrt.rccfgwd.base.PresentCallBack
import com.zrt.rccfgwd.entity.VersionEntity
import com.zrt.rccfgwd.entity.VersionRequestBody
import com.zrt.rccfgwd.http.HttpManager
import com.zrt.rccfgwd.http.RccfRequest
import com.zrt.rccfgwd.http.ResultBean
import com.zrt.rccfgwd.http.ResultCallBack

/**
 * Created by Administrator on 2019/1/23.
 */
class MainModel {
    fun getAppVersion(versionRequestBody: VersionRequestBody,presentCallBack: PresentCallBack<VersionEntity>){
        RccfRequest.getVersion(versionRequestBody,object : ResultCallBack<ResultBean<VersionEntity>>() {
            override fun onSuccess(t: ResultBean<VersionEntity>) {
                t.result?.let { presentCallBack.onBack(it) }
            }

            override fun onFailure(code: Int, msg: String) {
                presentCallBack.onError(msg)
            }

        })
    }
}