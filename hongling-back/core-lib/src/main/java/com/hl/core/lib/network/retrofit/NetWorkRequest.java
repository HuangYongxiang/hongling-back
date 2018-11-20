package com.hl.core.lib.network.retrofit;

import android.app.ProgressDialog;
import android.content.Context;

import com.hl.core.lib.event.EventBus;
import com.hl.core.lib.event.TokenEvent;
import com.hl.core.lib.network.CacheRepository;
import com.hl.core.lib.network.config.AuthenticatorManager;
import com.hl.core.lib.network.config.CommonInterceptor;
import com.hl.core.lib.network.config.CookieManager;
import com.hl.core.lib.network.config.FastJsonConverterFactory;
import com.hl.core.lib.network.config.HeadInterceptor;
import com.hl.core.lib.network.config.LoggingInterceptor;
import com.hl.core.lib.network.config.SaltInterceptor;
import com.hl.core.lib.network.retrofit.ssl.HttpsUtils;
import com.hl.core.lib.network.retrofit.ssl.Tls12SocketFactory;
import com.hl.core.lib.network.util.NetErrStringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/* @Describe:网络请求封装的底层类
 * @Package: com.hl.core.lib.network.retrofit
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:37
 * @Copyright: hl
 */
public class NetWorkRequest{

    public static final String TAG = NetWorkRequest.class.getSimpleName();
    private ProgressDialog progressBar;

     protected NetWorkRequest() {

    }

    public void addProgressDialog(ProgressDialog progressBar){
        this.progressBar=progressBar;
    }

    private static NetWorkRequest sInstance = new NetWorkRequest();

    public static NetWorkRequest getInstance() {
        return sInstance;
    }

    private Map<String, Map<Integer, Call>> mRequestMap = new ConcurrentHashMap<>();

    public Context mContext;

    private Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;

    //private DownLoadService mDownLoadService;
    OkHttpClient.Builder builder ;

    public void setReadTimeOut(long readTimeLength){
        if(builder!=null)
            builder.readTimeout(readTimeLength, TimeUnit.SECONDS);
    }
    public void setWriteTimeout(long writeTimeLengTh){
        if(builder!=null)
            builder.writeTimeout(writeTimeLengTh, TimeUnit.SECONDS);
    }
    /**
     * 初始化Retrofit
     *
     * @param context
     */
    public NetWorkRequest init(Context context, String baseURL) {
        this.mContext = context;
        synchronized (NetWorkRequest.this) {

            builder = new OkHttpClient.Builder();
                       // builder.cache(new Cache(new File(context.getExternalCacheDir(), "http_cache"), 1024 * 1024 * 100))
            builder.readTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(new SaltInterceptor())
                        .addInterceptor(new HeadInterceptor())
                        .addInterceptor(new CommonInterceptor())
                        .addInterceptor(new LoggingInterceptor())
                        .cookieJar(new CookieManager())
                        .authenticator(new AuthenticatorManager());

            if(AppParams.isBypassAuthen){
                InputStream in =null;
                try {
                      //如果有证书就放开注释，获得证书
                     //in = context.getResources().openRawResource(R.raw.cpic);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory( null, null,in);
                builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
            }else {
                SSLContext sslContext = null;
                try {
                    sslContext = SSLContext.getInstance("TLS");
                    try {
                        sslContext.init(null, null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                SSLSocketFactory socketFactory = new Tls12SocketFactory(sslContext.getSocketFactory());
                builder.sslSocketFactory(socketFactory, new HttpsUtils.UnSafeTrustManager());
            }

                mOkHttpClient=builder.build();
            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(FastJsonConverterFactory.create()).addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl(baseURL)//主机地址
                    .client(mOkHttpClient)
                    .build();
//            mDownLoadService = mRetrofit.create(DownLoadService.class);
        }
        return this;
    }


//    public DownLoadService getDownLoadService() {
//        return mDownLoadService;
//    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    public void clearCookie() {
        ((CookieManager) mOkHttpClient.cookieJar()).clearCookie();
    }

    /**
     * 封装的异步请求
     * @param TAG 每一个请求的标记
     * @param requestCode 每一个Tag下的请求码
     * @param requestCall 请求service返回的call
     * @param responseListener 响应坚挺回调
     * @param <T>
     */
    public <T extends BaseResponseEntity> void asyncNetWork(final String TAG, final int requestCode, final Call<T> requestCall, final NetworkResponse<T> responseListener) {
        if (responseListener == null) {
            return;
        }

        Call<T> call;

        if (requestCall.isExecuted()) {
            call = requestCall.clone();
        } else {
            call = requestCall;
        }
        //读取缓存
        T res = CacheRepository.get().read(requestCode);
        if(res  != null){
            responseListener.onDataReady(res);
            return ;
        }
        addCall(TAG, requestCode, call);
        if(progressBar!=null){
            progressBar.show();
        }
        call.enqueue(new Callback<T>() {

            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if(progressBar!=null&&progressBar.isShowing()){
                    progressBar.hide();
                }
//                cancelCall(TAG, requestCode);
                if (response.isSuccessful()) {
                    T result = response.body();
                   // Log.e("xxxxxxxxxxxxxxxxxxx",result.toString());
                    if (result == null) {
                        responseListener.onDataError(requestCode, response.code(), "未获得数据");
                        return;
                    }

                    if(result instanceof BaseResponseEntity){
                        //token失效 @add by liyu
                        try {
                            String businessCode = ((BaseResponseEntity) result).getBusinessCode();
                            if(TokenEvent.code.equals(businessCode)){
                                String message = "Token失效：";
                                message += ((BaseResponseEntity) result).getResponseMessage();
                                TokenEvent tokenEvent = new TokenEvent(true).message(message);
                                tokenEvent.setBusinessCode(businessCode);
                                tokenEvent.setRequestCall(requestCall);
                                tokenEvent.setTag(TAG);
                                tokenEvent.setRequestCode(requestCode);
                                tokenEvent.setResponseListener(responseListener);
                                EventBus.post(tokenEvent);
                                responseListener.onDataError(requestCode, response.code(), message);
                                return ;
                            }
//                            BaseResponseEntity baseResponseEntity = (BaseResponseEntity) result;
//                            if(baseResponseEntity.getResponseResult() == null){
//                                responseListener.onDataError(requestCode, response.code(), "解析失败："+baseResponseEntity.getResponseMessage());
//                                return;
//                            }
//                            else if(baseResponseEntity.getResponseResult() instanceof List&&((List) baseResponseEntity.getResponseResult()).size()==0){
//                                responseListener.onDataError(requestCode, response.code(), "未获得数据");
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    result.requestCode = requestCode;
                    result.serverTip = response.message();
                    result.responseCode = response.code();
                    responseListener.onDataReady(result);
                    //保存缓存 @add by liyu
                    CacheRepository.get().write(requestCode,result);

                } else {
                    responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(mContext, response.code()));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if(progressBar!=null&&progressBar.isShowing()){
                    progressBar.hide();
                }
                cancelCall(TAG, requestCode);
                responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(mContext, t));
            }
        });
    }
    /**
     * 封装的异步请求
     * @param TAG 每一个请求的标记
     * @param requestCode 每一个Tag下的请求码
     * @param requestCall 请求service返回的call
     * @param responseListener 响应坚挺回调
     */
    public void asyncNetWorkBackJson(final String TAG, final int requestCode, final Call<ResponseBody> requestCall, final NetworkResponseJsonString responseListener) {
        if (responseListener == null) {
            return;
        }

        Call<ResponseBody> call;

        if (requestCall.isExecuted()) {
            call = requestCall.clone();
        } else {
            call = requestCall;
        }
        addCall(TAG, requestCode, call);
        if(progressBar!=null){
            progressBar.show();
        }
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(progressBar!=null&&progressBar.isShowing()){
                    progressBar.hide();
                }
//                cancelCall(TAG, requestCode);
                if (response.isSuccessful()) {
                    ResponseBody result = response.body();
                   // Log.e("xxxxxxxxxxxxxxxxxxx",result.toString());
                    if (result == null) {
                        responseListener.onDataError(requestCode, response.code(), "未获得数据");
                        return;
                    }

                    responseListener.onDataReady(result);

                } else {
                    responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(mContext, response.code()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(progressBar!=null&&progressBar.isShowing()){
                    progressBar.hide();
                }
                cancelCall(TAG, requestCode);
                responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(mContext, t));
            }
        });
    }


    /***
     * 封装的同步请求 参数与异步请求相同
     * @param TAG
     * @param requestCode
     * @param requestCall
     * @param responseListener
     * @param <T>
     */
    public <T extends BaseResponseEntity> void syncNetWork(final String TAG, final int requestCode, final Call<T> requestCall, final NetworkResponse<T> responseListener) {
        if (responseListener == null) {
            return;
        }
        Call<T> call;
        try {
            if (requestCall.isExecuted()) {
                call = requestCall.clone();
            } else {
                call = requestCall;
            }
            if(progressBar!=null){
                progressBar.show();
            }
            Response<T> response = call.execute();
            addCall(TAG, requestCode, call);
            if(progressBar!=null&&progressBar.isShowing()){
                progressBar.hide();
            }
            if (response.isSuccessful()) {
                T result = response.body();
                if (result == null) {
                    responseListener.onDataError(requestCode, response.code(), "未获得到数据");
                    return;
                }
                result.requestCode = requestCode;
                result.serverTip = response.message();
                result.responseCode = response.code();
                responseListener.onDataReady(result);
            } else {
                responseListener.onDataError(requestCode, response.code(), NetErrStringUtils.getErrString(mContext, response.code()));
            }
        } catch (IOException e) {
            responseListener.onDataError(requestCode, 0, NetErrStringUtils.getErrString(mContext, e));
        } finally {
            cancelCall(TAG, requestCode);
        }
    }

    /**
     * 添加call到Map
     *
     * @param TAG 标记
     * @param call service的返回值
     */
    private void addCall(String TAG, Integer code, Call call) {
        if (TAG == null) {
            return;
        }
        if (mRequestMap.get(TAG) == null) {
            Map<Integer, Call> map = new ConcurrentHashMap<>();
            map.put(code, call);
            mRequestMap.put(TAG, map);
        } else {
            mRequestMap.get(TAG).put(code, call);
        }
    }

    /**
     * 取消某个call
     *
     * @param TAG
     * @param code
     */
    public boolean cancelCall(String TAG, Integer code) {
        if (TAG == null) {
            return false;
        }
        Map<Integer, Call> map = mRequestMap.get(TAG);
        if (map == null) {
            return false;
        }
        if (code == null) {
            //取消整个context请求
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = (Integer) iterator.next();

                Call call = map.get(key);
                if (call == null) {
                    continue;
                }
                call.cancel();
            }
            mRequestMap.remove(TAG);
            return false;
        } else {
            //取消一个请求
            if (map.containsKey(code)) {
                Call call = map.get(code);
                if (call != null) {
                    call.cancel();
                }
                map.remove(code);
            }
            if (map.size() == 0) {
                mRequestMap.remove(TAG);
                return false;
            }
        }
        return true;
    }

    /**
     * 取消整个tag请求，关闭页面时调用
     *
     * @param TAG
     */
    public void cancelTagCall(String TAG) {
        if(progressBar!=null&&progressBar.isShowing()){
            progressBar.hide();
        }
        cancelCall(TAG, null);
    }
}
