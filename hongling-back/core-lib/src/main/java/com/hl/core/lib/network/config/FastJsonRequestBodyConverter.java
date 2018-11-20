package com.hl.core.lib.network.config;


import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;


/**
 * @Describe:json转换Converter
 * @Package: com.hl.core.lib.network.config
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:28
 * @Copyright: hl
 */


public class FastJsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/octet-stream");

    @Override
    public RequestBody convert(T value) throws IOException {
        return RequestBody.create(MEDIA_TYPE, JSON.toJSONBytes(value));
    }
}
