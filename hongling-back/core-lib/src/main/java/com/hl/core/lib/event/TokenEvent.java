package com.hl.core.lib.event;

import com.hl.core.lib.network.retrofit.BaseResponseEntity;
import com.hl.core.lib.network.retrofit.NetworkResponse;

import retrofit2.Call;

/**
 * @Describe: token事件：当token失效时触发
 * @Package: com.hl.core.lib.event
 * @Author: liyu
 * @Date: 2018/2/5 13:19
 * @Copyright: hl
 */
public class TokenEvent<T extends BaseResponseEntity> extends BaseEvent {

    private boolean isInvalid;//是否失效：true失效 | false有效
    public final static String code = "203";//信息标识
    public String businessCode;//业务码：0,201,203
    private String message;//提示信息

    /**重新请求时使用*/
    String Tag;//获得token成功后即将执行的Tag标记
    int requestCode;//获得token成功后即将执行的请求码
    Call<T> requestCall;
    NetworkResponse<T> responseListener;

    public TokenEvent(boolean isInvalid) {
        this.isInvalid = isInvalid;
    }

    public TokenEvent message(String message){
        this.message = message;
        return this;
    }

    public boolean isInvalid() {
        return isInvalid;
    }

    public String getMessage() {
        return message;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public Call<T> getRequestCall() {
        return requestCall;
    }

    public void setRequestCall(Call<T> requestCall) {
        this.requestCall = requestCall;
    }

    public NetworkResponse<T> getResponseListener() {
        return responseListener;
    }

    public void setResponseListener(NetworkResponse<T> responseListener) {
        this.responseListener = responseListener;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
