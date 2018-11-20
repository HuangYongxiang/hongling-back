package com.hl.core.lib.network.retrofit;


/* @Describe:网络请求响应的回调接口
 * @Package: com.hl.core.lib.network.retrofit
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:37
 * @Copyright: hl
 */

public interface NetworkResponse<T extends BaseResponseEntity> {
    /**
     * 服务器返回成功回调
     *
     * @param response 网络请求返信息
     */
    void onDataReady(T response);

    /**
     * 调用失败回调
     */
    void onDataError(int requestCode, int responseCode, String message);
}
