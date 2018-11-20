package com.hl.core.lib.network.upload;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;


/**
 * @Describe:文件处理工厂类
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:54
 * @Copyright: hl
 */
public class FileRequestBodyConverter implements Converter<File, RequestBody> {
    @Override
    public RequestBody convert(File file) throws IOException {
        return RequestBody.create(MediaType.parse(UploadRequest.FILE_MEDIA_TYPE.getTypeName()), file);
    }
}
