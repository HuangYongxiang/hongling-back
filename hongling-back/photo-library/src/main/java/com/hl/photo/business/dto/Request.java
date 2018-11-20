package com.hl.photo.business.dto;

/**
 * Created by wxl on 2017/9/12.
 *
 *      图片上传数据封装类
  */
public class Request {

    private String requestCode;
    private String sessionId;
    private String data;

    public Request() {
    }

    public String getRequestCode() {
        return this.requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
