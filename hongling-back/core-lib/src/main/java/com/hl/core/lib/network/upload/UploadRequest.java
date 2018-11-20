package com.hl.core.lib.network.upload;


import com.hl.core.lib.network.retrofit.NetWorkRequest;
import com.hl.core.lib.network.util.FileMD5;
import com.hl.core.lib.util.common.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Describe:对okhttp对文件上传的封装，以及表单上传还是流上传的处理提前
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */

public class UploadRequest {

    public static MEDIATYPE FILE_MEDIA_TYPE= MEDIATYPE.MULTIPART;//表单上传头

    /**
     * 枚举封装头信息的
     */
    public  enum  MEDIATYPE{
        MULTIPART("multipart/form-data"),
        APPLICAITON("applicaiton/otcet-stream");
        private String typeName;
          private MEDIATYPE(String typeName) {
            this.typeName = typeName;
        }
        public String getTypeName(){
              return this.typeName;
        }
    }

    UploadFileService mApiService;
    private static UploadRequest uploadRequest;
    public static UploadRequest instance(){
        synchronized (UploadRequest.class){
            if(uploadRequest==null){
                uploadRequest=new UploadRequest();
            }
        }
        return uploadRequest;

    }

    private UploadRequest() {
        mApiService = NetWorkRequest.getInstance().create(UploadFileService.class);
    }

    public UploadFileService getUploadFileService(){
        return mApiService;
    }

    /**
     * 创建文件的RequestBody
     * @param files
     * @return
     */
    public Map<String, RequestBody> createFileRequest(String fileType,List<File> files,UpLoadProgressListener progressListener,String tag){
        Map<String, RequestBody> params = new HashMap<>();
        if(files!=null&&files.size()>0){
            for(File file:files){
                RequestBody requestBody =  RequestBody.create(MediaType.parse(FILE_MEDIA_TYPE.getTypeName()),file);
                UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(requestBody,progressListener,tag);
                params.put("file\"; filename=\""+fileType+":"+ FileMD5.getFileMD5(file)+":"+file.getName(), uploadFileRequestBody);
            }
        }
        return params;

    }
    /**
     * 创建文件的RequestBody
     * @param files
     * @return
     */
    public Map<String, RequestBody> createFileRequest(String fileType,Map<String,String> map,List<File> files,UpLoadProgressListener progressListener,String tag){
        Map<String, RequestBody> params = new HashMap<>();
        try {
            if(files!=null&&files.size()>0){
                for(File file:files){
                    String fileMD5 = FileMD5.getFileMD5(file);
                    String alreadyLength = map.get(fileMD5);
//                    RequestBody requestBody =  RequestBody.create(MediaType.parse(FILE_MEDIA_TYPE.getTypeName()),FileUtil.File2byte(file.getPath()),Integer.valueOf(alreadyLength),new Long(file.length()-Integer.valueOf(alreadyLength)).intValue());

                    File tempFile=file;
                    if(Integer.valueOf(alreadyLength)>0){
                        tempFile= FileUtil.splitFile(file,Integer.valueOf(alreadyLength));
                    }
                    RequestBody requestBody =  RequestBody.create(MediaType.parse(FILE_MEDIA_TYPE.getTypeName()),tempFile);
                    UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(Integer.valueOf(alreadyLength),requestBody,progressListener,tag);
                    params.put("file\"; filename=\""+fileType+":"+fileMD5+":"+file.getName(), uploadFileRequestBody);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;

    }



    /**
     * 创建文件的RequestBody
     * @param file
     * @return
     */
    public MultipartBody.Part createFileRequest( File file,UpLoadProgressListener progressListener,String tag){
        RequestBody requestBody =
                    RequestBody.create(MediaType.parse(FILE_MEDIA_TYPE.getTypeName()), file);
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(requestBody,progressListener,tag);
        MultipartBody.Part body = MultipartBody.Part.createFormData(file.getName(), file.getName(), uploadFileRequestBody);
        return body;
    }

    /***
     * 描述body
     * @param description
     * @return
     */
    public RequestBody  createDescriptionBody(String description){
        return RequestBody.create(MediaType.parse(MEDIATYPE.MULTIPART.getTypeName()), description);
    }

    /**
     * 单多文件创建MultipartBody.Part 数组
     * @param files
     * @return
     */
    public MultipartBody.Part[] createMultipartBodyRequest(List<File> files,UpLoadProgressListener progressListener,String tag){
        MultipartBody.Part[] params = new MultipartBody.Part[files!=null?files.size():0];
        if(files!=null&&files.size()>0){
            for(int i=0;i<files.size();i++){
                File file=files.get(i);
                RequestBody requestBody =
                            RequestBody.create(MediaType.parse(FILE_MEDIA_TYPE.getTypeName()), file);
                UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(requestBody,progressListener,tag);
                MultipartBody.Part body = MultipartBody.Part.createFormData(file.getName(), file.getName(), uploadFileRequestBody);
                params[i]=body;
            }
        }
        return params;

    }
}
