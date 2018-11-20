package com.hl.core.lib.network.download;


import android.util.Log;


import com.hl.core.lib.network.download.db.DownLoadEntity;
import com.hl.core.lib.network.retrofit.NetWorkRequest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.RandomAccessFile;
import java.net.SocketTimeoutException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @Describe: 下载任务
 * @Package: com.hl.core.lib.network.download
 * @Author: liyu
 * @Date: 2018/1/26 0026 下午 17:07
 * @Copyright: hl
 */
public class DownLoadTask implements Runnable {

    private final String TAG = DownLoadTask.class.getSimpleName();
    private final DownLoadService downLoadService;

    private String mSaveFileName;

    private DownLoadTaskListener mDownLoadTaskListener;

    private Call<ResponseBody> mResponseCall;

    private long mFileSizeDownloaded;

    private DownLoadEntity mDownLoadEntity;

    private long mNeedDownSize;

    private final long CALL_BACK_LENGTH = 10 * 1024;
    private boolean isCancelFlag=false;

    public void setIsCancelFlag(boolean flag){
        this.isCancelFlag=flag;
    }
    DownLoadTask(DownLoadEntity downLoadEntity, DownLoadTaskListener downLoadTaskListener) {
        this.mDownLoadEntity = downLoadEntity;
        this.mDownLoadTaskListener = downLoadTaskListener;
        this.mSaveFileName = downLoadEntity.saveName;
        this.mNeedDownSize = downLoadEntity.end - (downLoadEntity.start + downLoadEntity.downed);
        downLoadService = NetWorkRequest.getInstance().create(DownLoadService.class);
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        if (mDownLoadEntity.downed != 0) {
            mResponseCall = downLoadService.downloadFile(mDownLoadEntity.url,
                    "bytes=" + (mDownLoadEntity.downed + mDownLoadEntity.start) + "-" + mDownLoadEntity.end);
        } else {
            mResponseCall = downLoadService.downloadFile(mDownLoadEntity.url,
                    "bytes=" + mDownLoadEntity.start + "-" + mDownLoadEntity.end);
        }
        ResponseBody result = null;
        try {
            Response response = mResponseCall.execute();
            //onStart();
            result = (ResponseBody) response.body();
            if (response.isSuccessful()) {
                Log.e("xxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                if (writeToFile(result, mDownLoadEntity.start, mDownLoadEntity.downed)) {
                    onCompleted();
                }
            } else {
                onError(new Throwable(response.message()));
            }
        } catch (IOException e) {
            onError(new Throwable(e.getMessage()));
        } finally {
            if (result != null) {
                result.close();
            }
        }
    }

    private boolean writeToFile(ResponseBody body, long startSet, long mDownedSet) {
        try {
            File futureStudioIconFile = new File(mSaveFileName);

            if (!futureStudioIconFile.exists()) {
                futureStudioIconFile.createNewFile();
            }

            RandomAccessFile oSavedFile = new RandomAccessFile(futureStudioIconFile, "rw");

            FileChannel channelOut = oSavedFile.getChannel();

            MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                    startSet + mDownedSet, body.contentLength());

            InputStream inputStream = null;

            try {
                byte[] fileReader = new byte[1024 * 8];

                inputStream = body.byteStream();

                while (mFileSizeDownloaded < mNeedDownSize) {
                    if(isCancelFlag){
                        throw new InterruptedIOException();
                    }
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }
                    mappedBuffer.put(fileReader, 0, read);

                    mFileSizeDownloaded += read;

                    if (mFileSizeDownloaded >= CALL_BACK_LENGTH) {
                        onDownLoading(mFileSizeDownloaded);
                        mNeedDownSize -= mFileSizeDownloaded;
                        mFileSizeDownloaded = 0;
                    } else {
                        if (mNeedDownSize < CALL_BACK_LENGTH) {
                            if (mFileSizeDownloaded - 1 == mNeedDownSize) {
                                onDownLoading(mFileSizeDownloaded);
                                break;
                            }
                        }
                    }
                }
                return true;
            } finally {
                channelOut.close();

                oSavedFile.close();

                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (IOException e) {
            if (e instanceof InterruptedIOException && !(e instanceof SocketTimeoutException)) {
                onCancel();
            } else {
                onError(e);
            }
            return false;
        }
    }

    private void onStart() {
        mDownLoadTaskListener.onStart();
    }

    private void onCancel() {
        mResponseCall.cancel();
        mResponseCall = null;
        mDownLoadTaskListener.onCancel(mDownLoadEntity);
    }

    private void onCompleted() {
        mResponseCall = null;
        mDownLoadTaskListener.onCompleted(mDownLoadEntity);
    }

    private void onDownLoading(long downSize) {
        mDownLoadTaskListener.onDownLoading(downSize);
        mDownLoadEntity.downed += downSize;
    }

    private void onError(Throwable throwable) {
        mDownLoadTaskListener.onError(mDownLoadEntity, throwable);
    }

    public static final class Builder {
        private DownLoadEntity mDownModel;

        private DownLoadTaskListener mDownLoadTaskListener;

        public Builder downLoadModel(DownLoadEntity downLoadEntity) {
            mDownModel = downLoadEntity;
            return this;
        }

        public Builder downLoadTaskListener(DownLoadTaskListener downLoadTaskListener) {
            mDownLoadTaskListener = downLoadTaskListener;
            return this;
        }

        public DownLoadTask build() {
            if (mDownModel.url.isEmpty()) {
                throw new IllegalStateException("DownLoad URL required.");
            }

            if (mDownLoadTaskListener == null) {
                throw new IllegalStateException("DownLoadTaskListener required.");
            }

            if (mDownModel.end == 0) {
                throw new IllegalStateException("End required.");
            }

            return new DownLoadTask(mDownModel, mDownLoadTaskListener);
        }
    }
}
