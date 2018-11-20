package com.hl.photo.business.upload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hl.core.lib.constant.URLSurvey;
import com.hl.photo.business.dto.MobileRequest;
import com.hl.photo.business.dto.PhotoUploadDTO;
import com.hl.photo.business.dto.Response;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

/**
 * Created by wxl on 2017/9/12.
 *
 *     上传照片网络请求类
 */
public class HttpUploadPicture {


    /**
     *
     * @param photoInfo   for test  待上传的dto
     * @param lisenter   上传进度的监听
     * @return
     */
    public static Response<String> uploadPicture(PhotoUploadDTO photoInfo, ContinueUploadInputStreamEntity.ProgressListener lisenter) {

         Response<String> response = getResponse(photoInfo,"0",lisenter);

         return response;
    }


    /**
      * @param dto  上传dto
     * @param requestCode  请求码
     * @param lisenter   进度监听器
     * @return
     */
    private static Response<String> getResponse(Object dto, String requestCode, ContinueUploadInputStreamEntity.ProgressListener lisenter) {
        Response<String> response = null;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

//        String qtData = gson.toJson(dto);
//
//        Request busRequest = new Request();
//        busRequest.setRequestCode(requestCode);
//        busRequest.setData(qtData);

        MobileRequest mobileRequest = new MobileRequest();
        mobileRequest.setRequestSourceCode(mobileRequest.getRequestSourceCode());
        mobileRequest.setData(dto);

        String responseData = null;
        try {
//            responseData = request(gson.toJson(busRequest), lisenter);
            responseData = request(gson.toJson(mobileRequest), lisenter);
            response = gson.fromJson(responseData, new TypeToken<Response<String>>() {}.getType());
        } catch (SocketTimeoutException ste) {

            response = new Response();
            response.setCode("-1");
            response.setMessage("操作失败:网络不稳定，请稍后再试");
        } catch (Exception e) {

            response = new Response();
            response.setCode("-1");
            response.setMessage("操作失败：获取服务端数据失败。" + e.getMessage());
        } finally {

            if (response == null) {
                response = new Response();
                response.setCode("-1");
                response.setMessage("系统未知错误，请与技术支持人员联系。");
            }
        }

        return response;
    }


    /**
     *   网络访问代码
     *
     * @param json   发送数据
     * @param lisenter   上传的进度监听
     * @return
     * @throws Exception
     */
    private static String request(String json,ContinueUploadInputStreamEntity.ProgressListener lisenter) throws Exception{
        //发送任务数据包

        HttpContext localContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost(URLSurvey.URL_IMAGE_UPLOAD_ONE_BY_ONE);  //上传网络地址
        byte[] dataBuffer = json.getBytes("UTF-8");  //编码
        ByteArrayInputStream bis = new ByteArrayInputStream(dataBuffer) ;
        ContinueUploadInputStreamEntity entity = new ContinueUploadInputStreamEntity( bis, dataBuffer.length, lisenter);
        httpPost.setEntity(entity);
        HttpResponse response = getHttpClient().execute(httpPost, localContext);
        //接收响应结果
        BufferedReader reader = null ;
        String data = "";
        try{
            int stateCode = response.getStatusLine().getStatusCode() ;
            if(stateCode == 200){
                reader = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent(), "UTF-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    data += line;
                }
            }else if(stateCode >=300 && stateCode < 400 ){
                throw new Exception("服务错误，错误码"+stateCode);
            }else if(stateCode >=400 && stateCode < 500 ){
                throw new Exception("找不到移动查勘服务，错误码"+stateCode);
            }else if(stateCode >=500  ){
                throw new Exception("服务器发生了错误，错误码"+stateCode);
            }else{
                throw new Exception("发生了未知的网络错误，错误码"+stateCode);
            }
        }finally{
            if(reader!=null){
                reader.close();
            }
        }

        return data ;
    }


    public static DefaultHttpClient getHttpClient(){
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 60000);
        HttpConnectionParams.setSoTimeout(httpParameters, 60000);
        return new DefaultHttpClient(httpParameters);
    }




}
