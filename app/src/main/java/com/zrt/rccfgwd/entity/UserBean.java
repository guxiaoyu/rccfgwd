package com.zrt.rccfgwd.entity;

import java.io.Serializable;

/**
 * Created by acer on 2018/8/23.
 */

public class UserBean implements Serializable {


    /**
     * id : string,用户主键ID
     * loginTime : string,最后登录时间
     * idtype : string,证件类型key
     * status : string,用户状态1-手机认证，2-实名认证，3-银行卡绑定
     * token : string,用户TOKEN
     * realname : string,用户姓名
     * idtypeName : string,证件类型枚举值
     * idno : string,证件号码
     * mobile : string,用户手机号码
     */

    private String id="";
    private String loginTime="";
    private String idtype="";
    private String status="";
    private String token="";
    private String realname="";
    private String idtypeName="";
    private String idno="";
    private String mobile="";
    private String invitecode="";
    private String loginIp="";
    private String createSource="";
    private String ifRealname="";
    private String notes;


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getIfRealname() {
        return ifRealname;
    }

    public void setIfRealname(String ifRealname) {
        this.ifRealname = ifRealname;
    }

    public String getInvitecode() {
        return invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getCreateSource() {
        return createSource;
    }

    public void setCreateSource(String createSource) {
        this.createSource = createSource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdtypeName() {
        return idtypeName;
    }

    public void setIdtypeName(String idtypeName) {
        this.idtypeName = idtypeName;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
