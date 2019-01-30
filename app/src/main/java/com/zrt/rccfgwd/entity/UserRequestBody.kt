package com.zrt.rccfgwd.entity

import com.zrt.rccfgwd.base.BaseRequestBody

/**
 * Created by Administrator on 2019/1/29.
 */
class UserRequestBody(token: String, timestamp: String, nonce: String, signature: String, source: String, versioninfo: String, deviceToken: String,mobile:String,password:String)
    : BaseRequestBody(token, timestamp, nonce, signature, source, versioninfo, deviceToken) {

}