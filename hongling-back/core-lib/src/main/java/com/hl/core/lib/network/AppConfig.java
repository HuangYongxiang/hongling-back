package com.hl.core.lib.network;

/**
 * @Describe:基地址配置类
 * @Package: com.hl.core.lib.network
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:51
 * @Copyright: hl
 */

public class AppConfig {
    //public static String APP_ROOT_URL="http://192.168.120.129:8059/";
   //public static String APP_ROOT_URL="https://estimatepartapph5ft.cpic.com.cn";
    //public static String APP_ROOT_URL="http://182.150.61.50:31001";
    public enum UPLOADTYPE{
        FILELENGTH,UPLOAD;
    }
//    public static String APP_ROOT_URL="http://192.168.80.122:7003";
//  public static String APP_ROOT_URL="http://mocktest.honglingtech.com.cn";//测试环境
    public static String APP_ROOT_URL="http://system.honglingtech.com.cn";//正式环境
//    public static String APP_ROOT_URL="http://192.168.120.60:8080";
//    public static String APP_ROOT_URL="http://192.168.120.85:8080";
    public static String UPLOAD_ROOT_URL="http://192.168.120.129:8083";

    public static String GET_FILE_LENGTH_URL="/StanClaimProd-mobile-ifc/picture/multipleFilesLen";
    public static String UPLOAD_URL="/StanClaimProd-mobile-ifc/survey/taskSubmit";
    public static String getUploadUrl(UPLOADTYPE uploadType){
        if(uploadType==UPLOADTYPE.FILELENGTH){
            return UPLOAD_ROOT_URL+GET_FILE_LENGTH_URL;
        }else if(uploadType==UPLOADTYPE.UPLOAD){
            return UPLOAD_ROOT_URL+UPLOAD_URL;
        }
        return "";
    }

    public static String setBaseUrl(String appBaseUrl){
        synchronized (appBaseUrl){
            APP_ROOT_URL=appBaseUrl;
        }
        return APP_ROOT_URL;
    }
    public static String setUpLoadUrl(String appUploadUrl){
        synchronized (appUploadUrl){
            UPLOAD_ROOT_URL=appUploadUrl;
        }
        return UPLOAD_ROOT_URL;
    }
}
