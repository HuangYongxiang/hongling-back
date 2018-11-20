package com.hl.core.lib.network.upload;

import com.hl.core.lib.bean.Response;

/**
 * @Describe:上传文件的监听
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */

public interface UpLoadProgressListener {
    //获得长度时可以先加载圆形进度条
    void onLoad();
    //取消圆形进度条
    void onDisLoad();
    //进度开始加载
    void onStart();
    //要是单文件上传，就不必再根据字节去计算了，直接在requestbody中计算好进度直接返回
    void onProgress(int progress, long written, long total, String tag);
    //完成
    void onCompleted(Response<Response> response);
    //取消
    void onCancel();
    //c错误
    void onError(Throwable throwable);

}
