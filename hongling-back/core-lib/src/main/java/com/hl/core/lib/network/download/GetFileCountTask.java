package com.hl.core.lib.network.download;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @Describe: 访问网络是否通畅以及查询文件大小的任务，用于判断是否是断点续传
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */
public class GetFileCountTask implements Runnable {
    private Call<ResponseBody> mResponseCall;

    private GetFileCountListener mGetFileCountListener;

    GetFileCountTask(Call<ResponseBody> responseCall, GetFileCountListener getFileCountListener) {
        mResponseCall = responseCall;
        mGetFileCountListener = getFileCountListener;
    }

    @Override
    public void run() {
        Response response = null;
        try {
            response = mResponseCall.execute();
            if (response.isSuccessful()) {

                if (mGetFileCountListener != null) {
                    Long total=0L;
                    if(response.headers().get("Content-Range")!=null){
                        total=Long.parseLong(response.headers().get("Content-Range").split("/")[1]);
                    }else if(!TextUtils.isEmpty(response.headers().get("Content-Length"))){
                        total=Long.parseLong(response.headers().get("Content-Length"));
                    }
                    mGetFileCountListener.success((!TextUtils.isEmpty(response.headers().get("Content-Range")) && !TextUtils.isEmpty(response.headers().get("Content-Length"))), response.code() != 206, response.headers().get("Last-Modified"), total);
                }
            } else {
                if (mGetFileCountListener != null) {
                    mGetFileCountListener.failed(response.message());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            mGetFileCountListener.failed(e.getMessage());
        } catch (Exception e) {
            if (mGetFileCountListener != null) {
                mGetFileCountListener.failed(e.getMessage());
            }
        } finally {
            if (response.body() != null) {
                ((ResponseBody) response.body()).close();
            }
        }
    }
}
