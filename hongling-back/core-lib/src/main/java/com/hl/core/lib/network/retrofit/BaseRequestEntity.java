package com.hl.core.lib.network.retrofit;


/**
 * @Describe:封装的请求实体
 * @Package: com.hl.core.lib.network.retrofit
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:37
 * @Copyright: hl
 */

public class BaseRequestEntity {
    private String tag;
    private int requestCode;
    private NetworkResponse mKKNetworkResponse;

    public BaseRequestEntity(String tag, int requestCode, NetworkResponse kKNetworkResponse) {
        this.tag = tag;
        this.requestCode = requestCode;
        this.mKKNetworkResponse = kKNetworkResponse;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public NetworkResponse getKKNetworkResponse() {
        return mKKNetworkResponse;
    }

}
