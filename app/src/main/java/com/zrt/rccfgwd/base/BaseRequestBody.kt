package com.zrt.rccfgwd.base

/**
 * Created by Administrator on 2019/1/29.
 */
open class BaseRequestBody constructor(
    var token:String,
    var timestamp:String,
    var nonce:String,
    var signature:String,
    var source:String,
    var versioninfo:String,
    var deviceToken:String){}
