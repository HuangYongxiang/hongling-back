package com.hl.core.lib.network.upload;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * @Describe:用于获得监听进度对RequestBody的二次封装
 * @Package: com.hl.core.lib.network.upload
 * @Author: liyu
 * @Date: 2018/1/12 0012 下午 17:58
 * @Copyright: hl
 */
public class UploadFileRequestBody extends RequestBody {

    private RequestBody mRequestBody;
    private UpLoadProgressListener mProgressListener;

    private BufferedSink bufferedSink;

    //每个RequestBody对应一个tag，存放在map中，保证计算的时候不会出现重复
    private String tag;

    int alreadyLength;

    public UploadFileRequestBody(File file, UpLoadProgressListener progressListener, String tag) {
        this.mRequestBody = RequestBody.create(MediaType.parse(UploadRequest.MEDIATYPE.APPLICAITON.getTypeName()), file);
        this.mProgressListener = progressListener;
        this.tag = tag;
    }

    //其实只是添加一个回调和tag标识，实际起作用的还是requestBody
    public UploadFileRequestBody(int alreadyLength,RequestBody requestBody, UpLoadProgressListener progressListener, String tag) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
        this.tag = tag;
        this.alreadyLength=alreadyLength;
    }
    //其实只是添加一个回调和tag标识，实际起作用的还是requestBody
    public UploadFileRequestBody(RequestBody requestBody, UpLoadProgressListener progressListener, String tag) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
        this.tag = tag;
    }

    //返回了requestBody的类型，想什么form-data/MP3/MP4/png等等等格式
    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    //返回了本RequestBody的长度，也就是上传的totalLength
    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            //包装
            bufferedSink = Okio.buffer(sink(sink));
        }
        //写入
        mRequestBody.writeTo(bufferedSink);
        //必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            //当前写入字节数
            long bytesWritten = 0L;
            //总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    //获得contentLength的值，后续不再调用
                    contentLength = contentLength();
                }
                //增加当前写入的字节数
                bytesWritten += byteCount;
                //回调上传接口
                double percent=(alreadyLength/((double)(contentLength+alreadyLength))+bytesWritten /((double)(alreadyLength+contentLength)))*100;
                if(mProgressListener!=null)
                    mProgressListener.onProgress(new Double(percent).intValue(),bytesWritten+alreadyLength, contentLength+alreadyLength, tag);
            }
        };
    }
}

