package com.hl.photo.business.dto;

/**
 * Create data： 2017/1/16.
 * Author: ChenXuefei
 * Function:
 */
public class UpgradeDTO {
    private String patchVersionCode;
    private String responseCode;
    private String versionUrl;
    private String apkFileName;
    private String versionCode;
    private String patchFileName;

    public String getPatchVersionCode() {
        return patchVersionCode;
    }

    public void setPatchVersionCode(String patchVersionCode) {
        this.patchVersionCode = patchVersionCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public String getApkFileName() {
        return apkFileName;
    }

    public void setApkFileName(String apkFileName) {
        this.apkFileName = apkFileName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPatchFileName() {
        return patchFileName;
    }

    public void setPatchFileName(String patchFileName) {
        this.patchFileName = patchFileName;
    }
}
