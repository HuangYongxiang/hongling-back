package com.hl.core.lib.network.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hl.core.lib.constant.URLSurvey;
import com.hl.core.lib.network.retrofit.MobileRequest;
import com.hl.core.lib.network.util.MD5Util;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

import static com.alibaba.fastjson.util.IOUtils.UTF8;

/**
 * @Package: com.hl.core.lib.network.interceptor
 * @Author: liyu
 * @Date: 2018/4/28/ 15:23
 * @Copyright: hl
 */


public class SaltInterceptor<T> implements Interceptor {

    private String TAG = getClass().getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        HttpUrl url = request.url();
        if (url.pathSegments().size()<0){
            return chain.proceed(request);
        }
        // 查勘的POST请求时需要这个添加验证
        boolean isPostRequest = request.method().equals("POST");
        if (isPostRequest && url.pathSegments().get(0).equals(URLSurvey.MOBILE_SURVEY_NAME) ){
            // 获取请求Request 对象
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = request.body().contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            String requestJsonStr = buffer.readString(charset); // request String 对象
            MobileRequest mobileRequest = JSON.parseObject(requestJsonStr, MobileRequest.class);
            mobileRequest.setOperatingTime(System.currentTimeMillis()); // 添加时间戳
            String jsonString = JSON.toJSONString(mobileRequest);
            String md5par="JY"+ jsonString;
            md5par=md5par.replace("\"", "");
            //  UtilManager.Log.i(TAG,"加密前："+md5par);

            try {
                String token = MD5Util.GetMD5Code(md5par); // 获取加密token
                mobileRequest.setToken(token);
                Request newRequest = new Request.Builder() // 构建新的request对象
                        .url(url)
                        .headers(request.headers())
                        .post(RequestBody.create(contentType, JSONObject.toJSONString(mobileRequest))).build();
                return chain.proceed(newRequest);
            } catch (Exception e) {
                e.printStackTrace();
                return chain.proceed(request);
            }
        }else{
            return chain.proceed(request);
        }
//        if (body instanceof FormBody){
//            // 表单类型数据
//            FormBody formBody = (FormBody)body;
//
//        }else if (body instanceof MultipartBody){
//
//            MultipartBody multipartBody = (MultipartBody)body;
//        }
    }
}
