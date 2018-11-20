package com.hl.photo.business.dto;

/**
 * Created by wangliang on 2017/1/10.
 */

public class Response<T>  {
    private String code;
    private String message;
    private T  result;

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

}
