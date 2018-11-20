package com.hl.core.lib.network.config;


/**
 * @Describe:请求DTO
 * @Package: com.hl.core.lib.network.config
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:28
 * @Copyright: hl
 */

public class RequestDTO<T> {
    private T requestData;

    public T getRequestData() {
        return requestData;
    }

    public void setRequestData(T requestData) {
        this.requestData = requestData;
    }
}
