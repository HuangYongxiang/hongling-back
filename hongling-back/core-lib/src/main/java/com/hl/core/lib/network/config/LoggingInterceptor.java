package com.hl.core.lib.network.config;


import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import static com.alibaba.fastjson.util.IOUtils.UTF8;


/**
 * @Describe:日志拦截打印日志
 * @Package: com.hl.core.lib.network.config
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:28
 * @Copyright: hl
 */

public class LoggingInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间


        if(!request.url().toString().toLowerCase().contains("download")){
            Log.i("请求：",String.format("发送请求 %s on %s%n%s%n",
                        request.url(), chain.connection(), request.headers()
//                    ,request.body().toString()
            ));

            if(!"GET".equals(request.method())){
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                Log.i("请求体头", request.headers().toString());
                Charset charset = UTF8;
                MediaType contentType = request.body().contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                String requestJson = buffer.readString(charset);
                Log.i("请求体内容",requestJson);
            }
        }

        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        //这里不能直接使用response.body().string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
        //个新的response给应用层处理

        if(!request.url().toString().toLowerCase().contains("download")){
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            String responseJson = responseBody.string();
            Log.i("响应",String.format("接收响应:%.1fms%n%s  [%s] %n返回json:【%s】   ",
                        (t2 - t1) / 1e6d,
                        response.request().url(),
                        responseJson,
                        response.headers()));
        }



        if (request != null && request.url() != null) {
        }

        return response;
    }



}
