package com.zrt.rccfgwd.entity;

/**
 * Created by Administrator on 2019/2/1.
 */

public class VersionEntity {

    /**
     * id : 5966ffbdf1278ea7a6eab160
     * version : 1.6.0
     * versionCode : 11
     * versionType : 1
     * versionKind : 2
     * versionDesc : 嵘创财富安卓顾问APP Android版发版啦！
     * downloadUrl : https://files.shrccf.com/client/test/rccfgwd_test.apk
     * isMandatory : 0
     * isDel : 0
     * ifCurrentVer : 1
     * buildNo : 11
     * createUser : 000000000000000000000000
     * createTime : 2019-01-08 09:52:49
     * updateUser :
     * updateTime : null
     */

    private String id;
    private String version;
    private int versionCode;
    private String versionType;
    private String versionKind;
    private String versionDesc;
    private String downloadUrl;
    private String isMandatory;
    private String isDel;
    private String ifCurrentVer;
    private int buildNo;
    private String createUser;
    private String createTime;
    private String updateUser;
    private Object updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }

    public String getVersionKind() {
        return versionKind;
    }

    public void setVersionKind(String versionKind) {
        this.versionKind = versionKind;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(String isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getIfCurrentVer() {
        return ifCurrentVer;
    }

    public void setIfCurrentVer(String ifCurrentVer) {
        this.ifCurrentVer = ifCurrentVer;
    }

    public int getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(int buildNo) {
        this.buildNo = buildNo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }
}
