package com.hl.core.lib.network.retrofit;

/* @Describe:基类响应实体
 * @Package: com.hl.core.lib.network.retrofit
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:37
 * @Copyright: hl
 */
public abstract class BaseResponseEntity<T> {
    /**
     * 接口请求requestCode,用于区分多个请求同时发起的情况
     */
    public int requestCode;

    /**
     * 接口请求返回的状态码
     */
    public int responseCode;

    /**
     * 接口请求返回的提示信息
     */
    public String serverTip;

    public abstract T getResponseResult();

    public abstract String getResponseMessage();

    public abstract String getBusinessCode();

}
