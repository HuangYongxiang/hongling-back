package com.hl.core.lib.network.upload;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Describe:上传文件的任务
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */

public class UploadTask implements Runnable {
    private UpLoadProgressListener upLoadProgressListener;
    private List<File> files;
    private Map<String,String> data;
    private String url;
    private String fileType;
    private String tag;
    Call<com.hl.core.lib.bean.Response> call;

    public UploadTask(UpLoadProgressListener upLoadProgressListener, List<File> files, String tag) {
        this.upLoadProgressListener = upLoadProgressListener;
        this.files = files;
        this.tag = tag;
    }

    public UploadTask(String fileType,Map<String,String>  data,UpLoadProgressListener upLoadProgressListener, List<File> files, String tag) {
        this.upLoadProgressListener = upLoadProgressListener;
        this.files = files;
        this.tag = tag;
        this.data=data;
        this.fileType=fileType;
    }
    public UploadTask(String fileType,String url,Map<String,String>  data,UpLoadProgressListener upLoadProgressListener, List<File> files, String tag) {
        this.upLoadProgressListener = upLoadProgressListener;
        this.files = files;
        this.tag = tag;
        this.data=data;
        this.fileType=fileType;
        this.url=url;
    }

    public UploadTask(String fileType,String url,Map<String,String>  data, List<File> files, String tag) {
        this.files = files;
        this.tag = tag;
        this.data=data;
        this.fileType=fileType;
        this.url=url;
    }

    public UploadTask(Map<String,String>  data,UpLoadProgressListener upLoadProgressListener, List<File> files, String tag) {
        this.upLoadProgressListener = upLoadProgressListener;
        this.files = files;
        this.tag = tag;
        this.data=data;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Map<String, RequestBody> fileRequest = UploadRequest.instance().createFileRequest(fileType,data,files, upLoadProgressListener, tag);
        //Map<String, RequestBody> fileRequest = UploadRequest.instance().createFileRequest(files, upLoadProgressListener, tag);
        call = UploadRequest.instance().getUploadFileService().uploadFiles(url,fileRequest);

        try {
            call.enqueue(new Callback<com.hl.core.lib.bean.Response>() {
                @Override
                public void onResponse(Call<com.hl.core.lib.bean.Response> call, Response<com.hl.core.lib.bean.Response> response) {
                    if(response.isSuccessful()){
                        if(upLoadProgressListener!=null)
                            upLoadProgressListener.onCompleted(response.body());
                    }else{
                        if(upLoadProgressListener!=null)
                            upLoadProgressListener.onError(new Throwable(response.message()));
                    }
                }

                @Override
                public void onFailure(Call<com.hl.core.lib.bean.Response> call, Throwable t) {
                    if(upLoadProgressListener!=null)
                        upLoadProgressListener.onError(t);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if(upLoadProgressListener!=null)
                upLoadProgressListener.onError(new Throwable(e.getMessage()));
            onCancel();
        }
    }

    public void onCancel() {
        if(call!=null){
            call.cancel();
            call = null;
            if(upLoadProgressListener!=null)
                upLoadProgressListener.onCancel();
        }

    }


}
