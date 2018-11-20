package com.hl.core.lib.network.upload;

import com.hl.core.lib.bean.Response;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * @Describe:上传文件的service
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */
public interface UploadFileService {
    /***
     * 获得文件已经上传的长度的接口
     * @param url
     * @param data
     * @return
     */
    //@POST("/StanClaimProd-mobile-ifc/picture/multipleFilesLen")
    @POST
    Call<Response<List<UploadData>>> getAlreadyUploadLength(@Url String url, @Body UploadRequestDTO data);
    /***
     * 获得文件已经上传的长度的接口，重载
     * @param data
     * @return
     */
    @POST("/StanClaimProd-mobile-ifc/picture/multipleFilesLen")
    Call<Response<List<UploadData>>> getAlreadyUploadLength( @Body UploadRequestDTO data);


    /***
     * 单文件或多文件上传MultipartBody.Part方式
     * @param description
     * @param file
     * @return
     */
    @Multipart
    @POST("/UploadService/UploadServlet")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part... file);
    /***
     * 单文件或多文件上传
     * @param file
     * @return
     */
    @Multipart
    @Headers({
                "Connection:keep-alive",
    })
    //@POST("/UploadService/UploadServlet")
    @POST("/StanClaimProd-mobile-ifc/survey/taskSubmit")
    Call<ResponseBody> upload(@Part MultipartBody.Part... file);

    /***
     * 单文件或多文件上传
     * @param description
     * @param file
     * @return
     */
    @Multipart
    @POST("/UploadService/UploadServlet")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);

    /***
     * 单文件上传
     * @param file
     * @return
     */
    @Multipart
    @POST("/UploadService/UploadServlet")
    Call<ResponseBody> upload(@Part MultipartBody.Part file);

    /***
     * 多文件上传
     * @param params
     * @return
     */
    @Multipart
    @POST("/UploadService/UploadServlet")
    Call<ResponseBody> uploadFiles(@Part("description") RequestBody description, @PartMap Map<String, RequestBody> params);
    /***
     * 多文件上传
     * @param params
     * @return
     */
    @Multipart
    @Headers({
                "Connection:keep-alive",
    })
    //@POST("/UploadService/UploadServlet")
    @POST("/StanClaimProd-mobile-ifc/survey/taskSubmit")
    Call<Response> uploadFiles(@PartMap Map<String, RequestBody> params);


    /***
     *
     * @param url
     * @param params
     * @return
     */
    @Multipart
    //@POST("/StanClaimProd-mobile-ifc/survey/taskSubmit")
    @POST
    Call<Response> uploadFiles(@Url String url, @PartMap Map<String, RequestBody> params);


}

