package com.zrt.rccfgwd.http

import com.zrt.rccfgwd.entity.UserBean
import com.zrt.rccfgwd.entity.UserRequestBody
import com.zrt.rccfgwd.entity.VersionRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Administrator on 2019/1/28.
 */
interface RccfService{

    @POST("/app/advisor/public/advisorLogin.do")
    fun login(@Body body:UserRequestBody): Call<ResultBean<UserBean>>

    @POST("/app/advisor/public/getAdvisorAppVersion.do")
    fun getAppVersion(@Body body:VersionRequestBody) :Call<ResultBean<String>>
}
