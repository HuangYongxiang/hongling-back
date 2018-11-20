package com.hl.core.lib.network.download;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Describe: 下载用的service
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */
public interface DownLoadService {
    /**
     * Download file.
     *
     * @param fileUrl
     * @param range
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl, @Header("Range") String range);

    @Streaming
    @GET
    Call<ResponseBody> getHttpHeader(@Url String fileUrl, @Header("Range") String range);

    @Streaming
    @GET
    Call<ResponseBody> getHttpHeaderWithIfRange(@Url String fileUrl, @Header("If-Range") String lastModify, @Header("Range") String range);
}
