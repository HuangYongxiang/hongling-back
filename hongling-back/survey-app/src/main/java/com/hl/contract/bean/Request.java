package com.hl.contract.bean;

/**
 * Created by liyu on 2017/8/9.
 */

public class Request<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
