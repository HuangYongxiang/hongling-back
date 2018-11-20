package com.hl.core.lib.network.config;

import android.text.TextUtils;

import com.hl.core.lib.constant.UrlConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Describe: 协议头拦截器：增加token
 * @Package: com.hl.core.lib.network.config
 * @Author: liyu
 * @Date: 2018/1/31 16:58
 * @Copyright: hl
 */
public class HeadInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        //token
        if(!TextUtils.isEmpty(UrlConstants.TOKEN_APP)){
            builder.addHeader("access_token", UrlConstants.TOKEN_APP);
        }
        builder.addHeader("accept", "application/json");
        return chain.proceed(builder.build());
    }

}
