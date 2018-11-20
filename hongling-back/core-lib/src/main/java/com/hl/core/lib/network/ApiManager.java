package com.hl.core.lib.network;

import android.content.Context;


import com.hl.core.lib.constant.UrlConstants;
import com.hl.core.lib.network.retrofit.NetWorkRequest;
import com.hl.core.lib.network.retrofit.NetworkResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import com.hl.core.lib.bean.Response;
import com.hl.core.lib.network.retrofit.NetworkResponseJsonString;


/**
 * @Describe:网络请求入口，单列模式请求
 * @Package: com.hl.core.lib.network
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:51
 * @Copyright: hl
 */

public class ApiManager<E> {
    private NETTYPE networkToolType=NETTYPE.RETROFIT;
    public enum NETTYPE {
        RETROFIT,OKHTTP;
    }
    //工具方法
    private ApiService mApiService;
    private static ApiManager api;
    public static ApiManager getInstance(){
            if(api==null){
                api=new ApiManager();
            }
        return api;
    }

    private ApiManager(){

    }

    /***
     * 初始化
     * @param context
     */
    public void init(Context context) {
        if(networkToolType== NETTYPE.RETROFIT){
            String baseUrl = UrlConstants.BaseUrl;
            NetWorkRequest.getInstance().init(context, AppConfig.APP_ROOT_URL);
            mApiService = NetWorkRequest.getInstance().create(ApiService.class);
        }
    }

    /**
     * 创建service
     * @param classType
     * @return
     */
    public <E> E getApiService(Class<E> classType){
        return NetWorkRequest.getInstance().create(classType);
    }

    /**
     * 设置读超时时常
     * @param readTimeLength
     */
    public void setReadTimeOut(long readTimeLength){
        NetWorkRequest.getInstance().setReadTimeOut(readTimeLength);
    }
    /**
     * 设置写超时时常
     * @param writeTimeLength
     */
    public void setWriteTimeout(long writeTimeLength){
        NetWorkRequest.getInstance().setWriteTimeout(writeTimeLength);
    }

    /**
     * 设置读写超时时常
     * @param readwriteTimeLength
     */
    public void setReadWriteTimeout(long readwriteTimeLength){
        NetWorkRequest.getInstance().setReadTimeOut(readwriteTimeLength);
        NetWorkRequest.getInstance().setWriteTimeout(readwriteTimeLength);
    }


    public ApiService getApiService() {
        return mApiService;
    }

    /***
     * 异步网络请求
     * @param TAG   标记，可自定义，用来表示请求唯一的
     * @param requestCode 请求码，call的唯一表示，可自定义
     * @param call service 返回值
     * @param responseListener 监听
     */
    public  void asyncNetWork(String TAG, int requestCode, Call<Response> call, NetworkResponse responseListener){
        if(networkToolType== NETTYPE.RETROFIT) {
            NetWorkRequest.getInstance().asyncNetWork(TAG, requestCode, call, responseListener);
        }else if(networkToolType== NETTYPE.OKHTTP){

        }
    }

    /***
     * 异步网络请求
     * @param TAG   标记，可自定义，用来表示请求唯一的
     * @param requestCode 请求码，call的唯一表示，可自定义
     * @param call service 返回值
     * @param responseListener 监听
     */
    public void  asyncNetWorkJson(String TAG, int requestCode, Call<ResponseBody> call, NetworkResponseJsonString responseListener){
        if(networkToolType== NETTYPE.RETROFIT) {
            NetWorkRequest.getInstance().asyncNetWorkBackJson(TAG, requestCode, call, responseListener);
        }else if(networkToolType== NETTYPE.OKHTTP){

        }
    }



}
