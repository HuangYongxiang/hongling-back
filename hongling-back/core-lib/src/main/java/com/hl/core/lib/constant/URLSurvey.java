package com.hl.core.lib.constant;

/**
 * @Describe:
 * @Package: com.hl.contract.util
 * @Author: Administrator
 * @Date: 2018/3/16 13:10
 * @Copyright: hl
 */
public class URLSurvey {
public static final String MOBILE_SURVEY_NAME = "/mobile/";


    //    public static final String URL_BASE = "https://test.do1wan.top/hl_task/"+MOBILE_SURVEY_NAME; //外网测试

    //    public static final String URL_BASE = "http://172.16.173.214:8008/hl_task/"+MOBILE_SURVEY_NAME; // 继波临时

    //    public static final String URL_BASE = "http://47.94.13.224/hl_task/"+MOBILE_SURVEY_NAME; //外网生产


   //public static final String URL_BASE = "http://system.honglingtech.com.cn"+MOBILE_SURVEY_NAME; //生产地址
       public static final String URL_BASE = "http://mocktest.honglingtech.com.cn"+MOBILE_SURVEY_NAME; //测试地址


    //获取所有任务列表
    public static final String URL_LOAD_CONTRACT_ALL_TASK = URL_BASE+"mobileContractlist";

    //上传图片
    public static final String UPLOADPICTURE = URL_BASE+"uploadImage"; //
    //查询图片
    public static final String DOWNLOADPLUGIN = URL_BASE+"showPic";

    //增加合同
    public static final String ADD_CONTRACT = URL_BASE+"contractAdd";
    //public static final String ADD_CONTRACT = "http://ljl.tunnel.qydev.com/hl_task/mobile/contractAdd";

    public static final String ADD_CONTRACTS = URL_BASE+"checkDiscounts";
    //public static final String ADD_CONTRACTS = "http://ljl.tunnel.qydev.com/hl_task/mobile/checkDiscounts";

    //未付图片
    public static final String TO_PAY = URL_BASE+"topay";
    //zhifubao
    public static final String TO_PAYs = URL_BASE+"alipay";
 //baifenbi
 public static final String TO_BAIFENBI = URL_BASE+"selectBaifenbi1";

    /**登录*/
    public static final String URL_LOGIN =URL_BASE+ "mobileLogin";

    /**省市县*/
    public static final String URL_AREA = URL_BASE+"initArea";


    //获取车型列表
    public static final String URL_MODEL = URL_BASE+"loadcarmodel";

    //获取品牌列表
    public static final String URL_MODEL_BRAND = URL_BASE+"brandList";

    //获取车系列表
    public static final String URL_MODEL_FAMILY = URL_BASE+"carsysList";

    //获取车型列表
    public static final String URL_MODEL_VEHICLE = URL_BASE+"carModelList";


    //综合查询任务列表
    public static final String QUERY_TASK_LIST = URL_BASE+"/task/listQuery";
    //获取所有任务列表
    public static final String URL_LOAD_ALL_TASK = URL_BASE+"/task/taskDispatch";
    /**登出、*/
    public static final String URL_LOGIN_OUT = URL_BASE+"/user/logout";
    /**上班签到*/
    public static final String URL_SING_IN_AT_WORK=URL_BASE+"/user/sign";
    /**图片上传(单张)*/
    public static final String URL_IMAGE_UPLOAD_ONE_BY_ONE=URL_BASE+"/image/upload/";


}
