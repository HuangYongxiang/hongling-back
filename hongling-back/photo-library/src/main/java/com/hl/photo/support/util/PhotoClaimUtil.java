package com.hl.photo.support.util;

/**
 * Created by liyu on 2017/1/13.
 */

public class PhotoClaimUtil {

    public static final String TASK_TYPE_SURVEY = "0101";//
    public static final String TASK_TYPE_EVAL_BD = "0201";//标的
    public static final String TASK_TYPE_EVAL_SZ = "0202";//三者
    public static final String TASK_TYPE_POP = "0301";//物损
    public static final String TASK_TYPE_INJURY = "0401";//人伤

    public static final String DICT_IMAGE_TYPE="IMAGE_TYPE";//图片分类


    public static final String YES = "1";
    public static final String NO = "0";

    public static final String LOGIN_SAVE_PWD="LOGIN_SAVE_PWD";

    public static final String REPORT_NO="ReportNo";
    public static final String TASK_NO="TaskNo";
    public static final String TASK_TYPE="TaskType";
    public static final String SEARCH_TYPE="SearchType";
    public static final String IMAGE_TYPE= "ImageType";
    public static final String IMAGE_SUB_TYPE= "ImageSubtype";
    public static final String IMAGE_NAME= "ImageName";
    public static final String SIGN_ID= "SignId";
    public static final String SIGN_NAME= "SignName";
    public static final String SEED_KEY= "SEED_KEY";

    //请求来源
    public static final String  REQUEST_SOURCE_CODE = "REQUEST_SOURCE_CODE";
    public static final String  REQUEST_SOURCE_NAME = "REQUEST_SOURCE_NAME";

    // 登录
    public static final String LOGIN_NAME="LOGIN_NAME";//登录账户
    public static final String LOGIN_PWD="LOGIN_PWD";//登录密码
    public static final String USER_ID="USER_ID";//用户ID
    public static final String HANDLER_NAME="HANDLER_NAME";//操作人姓名
    public static final String COM_CODE = "COM_CODE";//保存 COM_CODE
    public static final String COM_CODE_NAME = "COM_CODE_NAME";//保存 COM_CODE_NAME
    public static final String BRA_COM_CODE = "BRA_COM_CODE";//保存 COM_CODE
    public static final String BRA_COM_CODE_NAME = "BRA_COM_CODE_NAME";//保存 BRA_COM_CODE_NAME

    //照片添加类型
    public static final String PHOTO_IMAGE_TYPE_NO = "noPhoto";
    public static final String PHOTO_IMAGE_TYPE_ADD = "addPhoto";
    public static final String PHOTO_IMAGE_TYPE_CAMERA = "cameraPhoto";

    /**涉及人伤单证*/
    public static final String PHOTO_TYPE_INJURY_TYPE_CODE = "05";
    public static final String PHOTO_TYPE_INJURY_SEED_KEY = "IMAGE_TYPE_05";
    /**涉及车损单证*/
    public static final String PHOTO_TYPE_EVAL_TYPE_CODE = "03";
    public static final String PHOTO_TYPE_EVAL_SEED_KEY = "IMAGE_TYPE_03";
    /**涉及物损单证*/
    public static final String PHOTO_TYPE_PROPERTY_TYPE_CODE = "04";
    public static final String PHOTO_TYPE_PROPERTY_SEED_KEY = "IMAGE_TYPE_04";

    /**通用单证*/
    public static final String PHOTO_TYPE_GENERAL_DOCUMENT_CODE = "13701";
    public static final String PHOTO_TYPE_GENERAL_DOCUMENT_NAME = "通用单证";
    /**现场查勘*/
    public static final String PHOTO_TYPE_SURVEY_CODE= "13702";
    public static final String PHOTO_TYPE_SURVEY_NAME= "现场查勘";
    /**本车定损*/
    public static final String PHOTO_TYPE_EVAL_BD_CODE= "13703";
    public static final String PHOTO_TYPE_EVAL_BD_NAME= "本车定损";
    /**三者定损*/
    public static final String PHOTO_TYPE_EVAL_SZ_CODE= "13704";
    public static final String PHOTO_TYPE_EVAL_SZ_NAME= "三者定损";
    /**物损定损*/
    public static final String PHOTO_TYPE_POPLOSS_CODE= "13705";
    public static final String PHOTO_TYPE_POPLOSS_NAME= "物损定损";
    /**人伤*/
    public static final String PHOTO_TYPE_PEOPLE_HURT_CODE= "13706";
    public static final String PHOTO_TYPE_PEOPLE_HURT_NAME= "人伤查勘";
    /**支付资料*/
    public static final String PHOTO_TYPE_PAYMENT_CODE= "13707";
    public static final String PHOTO_TYPE_PAYMENT_NAME= "支付资料";
    /**盗抢车*/
    public static final String PHOTO_TYPE_STEAL_ROB_CODE= "13708";
    public static final String PHOTO_TYPE_STEAL_ROB_NAME= "盗抢车";
    /**自然灾害*/
    public static final String PHOTO_TYPE_NATURAL_DISASTER_CODE= "13709";
    public static final String PHOTO_TYPE_NATURAL_DISASTER_NAME= "自然灾害";
    /**火烧*/
    public static final String PHOTO_TYPE_FIRE_CODE= "13710";
    public static final String PHOTO_TYPE_FIRE_NAME= "火烧";
    /**其他*/
    public static final String PHOTO_TYPE_OTHER_CODE= "13711";
    public static final String PHOTO_TYPE_OTHER_NAME= "其他";

    public static final int PHOTO_MAX_SAVE_SIDELEN = 1600;//相机最大保存

}
