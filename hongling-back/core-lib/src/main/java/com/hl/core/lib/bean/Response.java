package com.hl.core.lib.bean;


import com.hl.core.lib.network.retrofit.BaseResponseEntity;

/**
 * @Describe:
 * @Package: com.hl.core.lib.bean
 * @Author: liyu
 * @Date: 2018/1/8 13:21
 * @Copyright: hl
 */
public class Response<T>  extends BaseResponseEntity<T> {
    private String code;
    private String message;
    private T  result;
    private String messasge;
    private T  data;

    @Override
    public String getBusinessCode() {
        return code;
    }

    @Override
    public T getResponseResult() {
        return result;
    }

    @Override
    public String getResponseMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessasge() {
        return message;
    }

    public void setMessasge(String messasge) {
        this.messasge = messasge;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

