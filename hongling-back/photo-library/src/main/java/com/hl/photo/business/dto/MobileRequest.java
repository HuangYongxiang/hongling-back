package com.hl.photo.business.dto;

import android.text.TextUtils;

import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.support.util.PreferenceManager;


/**
 * Created by hl on 2017/8/29.
 */

public class MobileRequest<T> {
    private String operatingTime;

    private String token;
    private String requestSourceCode;
    private String requestSourceName;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getRequestSourceCode() {
        return TextUtils.isEmpty(requestSourceCode)? PreferenceManager.getString(PhotoClaimUtil.REQUEST_SOURCE_CODE,""):requestSourceCode;
    }

    public void setRequestSourceCode(String requestSourceCode) {
        this.requestSourceCode = requestSourceCode;
    }

    public String getRequestSourceName() {
        return requestSourceName;
    }

    public void setRequestSourceName(String requestSourceName) {
        this.requestSourceName = requestSourceName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
