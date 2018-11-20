package com.hl.core.lib.network.upload;



import com.hl.core.lib.bean.Response;
import com.hl.core.lib.network.AppConfig;
import com.hl.core.lib.network.retrofit.NetWorkRequest;
import com.hl.core.lib.network.util.FileMD5;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @Describe:上传文件的入口，单例调用
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */
public class UploadManage {
    private ExecutorService mExecutorService = Executors.newCachedThreadPool();
    private static UploadManage intance;
    UploadFileService uploadFileService;
    UploadRequest uploadRequest;
    private Map<String,  Future> mTaskFutureMap = new ConcurrentHashMap<>();
    private Map<String,  UploadTask> mTaskMap = new ConcurrentHashMap<>();
    public static UploadManage init(){
        synchronized (UploadManage.class){
            if(intance==null){
                intance=new UploadManage();
            }
        }
        return intance;
    }

    private UploadManage() {
        uploadFileService = NetWorkRequest.getInstance().create(UploadFileService.class);
        uploadRequest = UploadRequest.instance();
    }

   /** public void upLoadMultipart(final String fileType,final List<File> files,final UpLoadProgressListener upLoadProgressListener,final String tag) {
        List<UploadData> datas=new ArrayList<>();
        UploadRequestDTO uploadRequestDTO =null;
        if(files!=null&&files.size()>0){
            uploadRequestDTO = new UploadRequestDTO();
            for (File file:files){
                UploadData uploadData = new UploadData();
                String fileMD5 = FileMD5.getFileMD5(file);
                uploadData.setMd5FileName(fileMD5);
                uploadData.setFileTotalSize(String.valueOf(file.length()));
                datas.add(uploadData);
            }

            uploadRequestDTO.setFileDataList(datas);
        }
        Call<Response<List<UploadData>>> call = uploadFileService.getAlreadyUploadLength(uploadRequestDTO);
        call.enqueue(new Callback<Response<List<UploadData>>>() {
            @Override
            public void onResponse(Call<Response<List<UploadData>>> call, retrofit2.Response<Response<List<UploadData>>> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    if("1".equals(response.body().getCode())) {
                        List<UploadData> datas = response.body().getResult();
                        Map<String,String> map=new HashMap<>();
                        for(UploadData data:datas){
                            map.put(data.getMd5FileName(),data.getAlreadyUpladeLength());
                        }
                        if(upLoadProgressListener!=null)
                            upLoadProgressListener.onStart();
                        //请求上传文件
                        if (uploadFileService != null) {
                            UploadTask uploadTask = new UploadTask(fileType,map,upLoadProgressListener, files, tag);
                            mTaskMap.put(tag,uploadTask);
                            mTaskFutureMap.put(tag,mExecutorService.submit(uploadTask));
                        }

                    }else{
                        if(upLoadProgressListener!=null)
                            upLoadProgressListener.onError(new Throwable("服务器异常:"+response.body().getMessage()));
                    }

                }
            }

            @Override
            public void onFailure(Call<Response<List<UploadData>>> call, Throwable t) {
                if(upLoadProgressListener!=null)
                    upLoadProgressListener.onError(new Throwable("网络跑丢了！！！"));
            }
        });

    }*/

    //取消当前tag所有任务
    public void cancel(String tag) {
        if (!mTaskFutureMap.isEmpty()) {
            if (mTaskFutureMap.containsKey(tag)) {
                //取消任务 执行
                Future future = mTaskFutureMap.get(tag);
                future.cancel(true);
                mTaskMap.get(tag).onCancel();
                mTaskMap.remove(tag);
                mTaskFutureMap.remove(tag);
            }
        }
    }

    /**
     * 同步调用，用于非主线程发起的上传文件,特殊情况下使用，例如线程是单独情况，不是主线程发起，使用该方法，使用时需要配合异步任务是用
     * @param files
     * @param upLoadProgressListener
     * @param tag
     */
    public void upLoadSyncMultipart(final String fileType,List<File> files,final UpLoadProgressListener upLoadProgressListener,String tag){
        List<UploadData> datas=new ArrayList<>();
        UploadRequestDTO uploadRequestDTO =null;
        if(files!=null&&files.size()>0){
            uploadRequestDTO = new UploadRequestDTO();
            for (File file:files){
                UploadData uploadData = new UploadData();
                String fileMD5 = FileMD5.getFileMD5(file);
                uploadData.setMd5FileName(fileMD5);
                uploadData.setFileTotalSize(String.valueOf(file.length()));
                datas.add(uploadData);
            }
            uploadRequestDTO.setFileDataList(datas);
        }
        try {

            Call<Response<List<UploadData>>> call = uploadFileService.getAlreadyUploadLength(AppConfig.getUploadUrl(AppConfig.UPLOADTYPE.FILELENGTH),uploadRequestDTO);
            retrofit2.Response<Response<List<UploadData>>> response = call.execute();
            if(response!=null&&response.isSuccessful()&&response.body()!=null){
                if("1".equals(response.body().getCode())) {
                    List<UploadData> respdatas = response.body().getResult();
                    Map<String,String> map=new HashMap<>();
                    for(UploadData data:respdatas){
                        map.put(data.getMd5FileName(),data.getAlreadyUpladeLength());
                    }
                    if(upLoadProgressListener!=null)
                        upLoadProgressListener.onStart();

                    Map<String, RequestBody> fileRequest = UploadRequest.instance().createFileRequest(fileType,map,files, upLoadProgressListener, tag);
                    Call<Response> responseCall = UploadRequest.instance().getUploadFileService().uploadFiles(AppConfig.getUploadUrl(AppConfig.UPLOADTYPE.UPLOAD), fileRequest);

                    try {
                        retrofit2.Response<Response> fileResponse = responseCall.execute();

                        if(fileResponse!=null&&fileResponse.isSuccessful()){
                            if(upLoadProgressListener!=null)
                                upLoadProgressListener.onCompleted(fileResponse.body());
                        }else{
                            if(upLoadProgressListener!=null)
                                upLoadProgressListener.onError(new Throwable(response.message()));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        if(upLoadProgressListener!=null)
                            upLoadProgressListener.onError(new Throwable(e.getMessage()));
                        responseCall.cancel();
                        responseCall=null;
                    }


                }else{
                    if(upLoadProgressListener!=null)
                        upLoadProgressListener.onError(new Throwable("服务器异常:"+response.body().getMessage()));
                }
            }else{
                if(upLoadProgressListener!=null)
                    upLoadProgressListener.onError(new Throwable("网络跑丢了！！！"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 异步调用，监听进度，用于在主线程中使用，推荐改方法
     * @param files
     * @param upLoadProgressListener
     * @param tag
     */
    public void upLoadAsynMultipart(final String fileType,List<File> files,final UpLoadProgressListener upLoadProgressListener,String tag){
        List<UploadData> datas=new ArrayList<>();
        UploadRequestDTO uploadRequestDTO =null;
        if(files!=null&&files.size()>0){
            uploadRequestDTO = new UploadRequestDTO();
            for (File file:files){
                UploadData uploadData = new UploadData();
                String fileMD5 = FileMD5.getFileMD5(file);
                uploadData.setMd5FileName(fileMD5);
                uploadData.setFileTotalSize(String.valueOf(file.length()));
                datas.add(uploadData);
            }

            uploadRequestDTO.setFileDataList(datas);
        }
        try {

            Call<Response<List<UploadData>>> call = uploadFileService.getAlreadyUploadLength(AppConfig.getUploadUrl(AppConfig.UPLOADTYPE.FILELENGTH),uploadRequestDTO);

            call.enqueue(new Callback<Response<List<UploadData>>>() {
                @Override
                public void onResponse(Call<Response<List<UploadData>>> call, retrofit2.Response<Response<List<UploadData>>> response) {
                    if(response.isSuccessful()&&response.body()!=null){
                        if("1".equals(response.body().getCode())) {
                            List<UploadData> datas = response.body().getResult();
                            Map<String,String> map=new HashMap<>();
                            for(UploadData data:datas){
                                map.put(data.getMd5FileName(),data.getAlreadyUpladeLength());
                            }
                            if(upLoadProgressListener!=null)
                                upLoadProgressListener.onStart();
                            //请求上传文件
                            if (uploadFileService != null) {
                                UploadTask uploadTask = new UploadTask(fileType,AppConfig.getUploadUrl(AppConfig.UPLOADTYPE.UPLOAD),map,upLoadProgressListener, files, tag);
                                mTaskMap.put(tag,uploadTask);
                                mTaskFutureMap.put(tag,mExecutorService.submit(uploadTask));
                            }

                        }else{
                            if(upLoadProgressListener!=null)
                                upLoadProgressListener.onError(new Throwable("服务器异常:"+response.body().getMessage()));
                        }

                    }
                }

                @Override
                public void onFailure(Call<Response<List<UploadData>>> call, Throwable t) {
                    if(upLoadProgressListener!=null)
                        upLoadProgressListener.onError(new Throwable("网络跑丢了！！！"));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***
     * 扩展时使用
     * @param description
     * @param files
     * @param upLoadProgressListener
     * @param tag
     */
    public void upLoadMultiBodyPart(String description,List<File> files,final UpLoadProgressListener upLoadProgressListener,String tag){
        if(uploadFileService!=null&&uploadRequest!=null){
            RequestBody descriptionBody = uploadRequest.createDescriptionBody(description);
            MultipartBody.Part[] multipartBodyRequest = uploadRequest.createMultipartBodyRequest(files,upLoadProgressListener,tag);
            Call<ResponseBody> call = uploadFileService.upload(descriptionBody,multipartBodyRequest);
//            call.enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                }
//
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                }
//            });

        }

    }


}
