package com.zrt.rccfgwd.entity

import com.zrt.rccfgwd.base.BaseRequestBody

/**
 * Created by Administrator on 2019/1/29.
 */
class VersionRequestBody(token: String, timestamp: String, nonce: String, signature: String, source: String, versioninfo: String, deviceToken: String,versionType:String)
    : BaseRequestBody(token, timestamp, nonce, signature, source, versioninfo, deviceToken) {

}