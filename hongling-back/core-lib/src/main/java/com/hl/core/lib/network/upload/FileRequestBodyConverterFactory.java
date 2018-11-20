package com.hl.core.lib.network.upload;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Describe:文件处理工厂类
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:54
 * @Copyright: hl
 */
public  class FileRequestBodyConverterFactory extends Converter.Factory {
    @Override
    public Converter<File, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FileRequestBodyConverter();
    }
}

