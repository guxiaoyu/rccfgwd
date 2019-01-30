package com.zrt.rccfgwd.http

import com.zrt.rccfgwd.base.BaseResultCallBack
import com.zrt.rccfgwd.entity.VersionRequestBody

/**
 * Created by Administrator on 2019/1/29.
 */
object RccfRequest {
    fun getVersion( versionRequestBody: VersionRequestBody,baseResultCallBack: BaseResultCallBack<ResultBean<String>>) {
        val version = ApiFactory.getApiService(RccfService::class.java).getAppVersion(versionRequestBody)
        HttpManager.get().execute(version, baseResultCallBack)
    }
}