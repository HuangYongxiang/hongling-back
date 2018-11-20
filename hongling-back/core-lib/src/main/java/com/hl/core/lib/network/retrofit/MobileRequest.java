package com.hl.core.lib.network.retrofit;


import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by hl on 2017/8/29.
 */

public class MobileRequest<T> {

    @JSONField(ordinal = 1)
    private T data;
    @JSONField(ordinal = 2)
    private long operatingTime;
    @JSONField(ordinal = 3)
    private String requestSourceCode;
    @JSONField(ordinal = 4)
    private String requestSourceName;
    @JSONField(ordinal = 5)
    private String token;

    public long getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(long operatingTime) {
        this.operatingTime = operatingTime;
    }

    public String getRequestSourceCode() {
        return requestSourceCode;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
