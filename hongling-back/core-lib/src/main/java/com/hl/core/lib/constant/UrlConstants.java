package com.hl.core.lib.constant;


import com.hl.core.lib.network.AppConfig;

/**
 * @Describe: url
 * @Package: com.hl.core.lib.constant
 * @Author: liyu
 * @Date: 2018/1/5 16:08
 * @Copyright: hl
 */
public class UrlConstants {

    public static final String SCHEME = "http://";
//    public static final String API_NAME = "/StanClaimProd-app";
    public static final String API_NAME = "/ClaimCloudProd-app";
    public static final String MOBILE_API_NAME = "/StanClaimProd-mobile-ifc";

//    public static final String DOMAIN_NAME = "124.42.1.2:9001" + API_NAME;//公司外网演示环境
    //public static final String DOMAIN_NAME = "192.168.80.122:7003" + API_NAME; // 测试服务器
    //public static final String DOMAIN_NAME = "test.lexiugo.com" + API_NAME; // 测试服务器
//    public static final String MOBILE_DOMAIN_NAME = "192.168.80.122:7003" + API_NAME; // 测试服务器
    //http://192.168.120.60:8080
//    public static final String IP_PORT="192.168.120.60:8080";//锦才
    public static final String IP_PORT="192.168.80.122:7003";//测试环境
//    public static final String IP_PORT="192.168.120.16:8080";//刘洋
//    public static final String IP_PORT="192.168.120.85:8080";//建中
//    public static final String IP_PORT="192.168.120.154:8080";//子虎
//    public static final String IP_PORT="124.42.1.16:9002";//国泰
//    public static final String IP_PORT="192.168.120.134:6010";//继波
    public static final String MOBILE_DOMAIN_NAME = IP_PORT + API_NAME; // 测试服务器
    public static final String MOBILE_SURVEY_DOMAIN_NAME = IP_PORT + MOBILE_API_NAME; // 测试服务器
//    public static final String MOBILE_DOMAIN_NAME = IP_PORT + MOBILE_API_NAME; // 测试服务器
    //public static final String DOMAIN_IP_PORT = SCHEME+"192.168.80.122:7003";
    public static final String DOMAIN_IP_PORT = SCHEME+IP_PORT;
    /**基础url*/
    public static final String BaseUrl= AppConfig.setBaseUrl(DOMAIN_IP_PORT);
//    public static String DOMAIN_NAME = UrlManager.init().getStandUrl() + API_NAME; // pc
//    public static String MOBILE_DOMAIN_NAME = UrlManager.init().getMobileStandUrl() + MOBILE_API_NAME; // mobile

    static {
        AppConfig.setBaseUrl(DOMAIN_IP_PORT);
    }


    public static final String URL_LOGIN = "/session";

    public static final String URL_LOGOUT = "/session";

    public static final String IFC ="/ifc";

    /**获取任务 pc */
    public static final String GET_TASK_LIST="/dispatch/taskDispatch";
    /**获取任务 mobile */
    public static final String GET_TASK_LIST_MOBILE="/task/taskDispatch";
    public static final String VIN_SEARCH=API_NAME+"/vehicleIdentify/getVehicleIdentifyByVin/";
    public static final String VIN_SEARCH_ANSWERQUESTION= API_NAME+"/mobile/getVehicleIdentifyByVinAnswerQuestion/";
//    public static final String VIN_SEARCH_ANSWERQUESTION= "/vehicleIdentify/getVehicleIdentifyByVinAnswerQuestion/";

    /**查询是否是最新版本*/
    public static final String VERSION_UPDATE_SYSTEM="/appVersion/isOrNotLaod";
    ///appVersion/isOrNotLaod?versionCode=%s&patchVersionCode=%s
    public static final String VERSION_UPDATE_TOOL="/appVersion/isOrNotLaod/";
    public static final String DOWN_LOAD="/appVersion/downLoad?fileName=";
    //云版版本升级
    public static final String VERSION_UPDATE_CLOUD= MOBILE_API_NAME + "/apkVersion/isOrNotLaod/";
    public static final String DOWN_LOAD_CLOUD = "/apkVersion/downLoad?companyCode=%s&fileName=%s";

    /**品牌查车系*/
    public static final String BRAND_SEARCH_CAR_SERIAL = API_NAME + "/vehicleIdentify/getSeriesListByBrandID/";
    /**车系查车组*/
    public static final String BRAND_SEARCH_CAR_GROUP = API_NAME + "/vehicleIdentify/getGroupListBySeriesID/";
    /**根据名称查询*/
//    public static final String GET_VEHICLEINFO_BY_COMPLEX = API_NAME + "/mobile/getVehicleInfoByComplex/";
    public static final String GET_VEHICLEINFO_BY_COMPLEX = API_NAME + "/mobile/getVehicleInfoByComplex/";
//    public static final String GET_VEHICLEINFO_BY_COMPLEX = API_NAME + "/vehicleIdentify/getVehicleInfoByComplex/";
    /**车组查车型*/
    public static final String BRAND_SEARCH_VECHILE_LIST = API_NAME + "/vehicleIdentify/getVehicleInfoByGroupID/";
    /**获得详细车型*/
    public static final String BRAND_SEARCH_VECHILE = API_NAME + "/vehicleIdentify/getVehicleInfoByVehCertainId/";
    /**品牌名称获取品牌列表接口*/
    public static final String BRAND_SEARCH_BY_BRAND_NAME = API_NAME + "/vehicleIdentify/getBrandListByName/";
    /**品牌id获取车系列表获取接口*/
    public static final String SERIES_SEARCH_BY_BRAND_ID = API_NAME + "/vehicleIdentify/getSeriesListByBrandID/";
    /**车系名称获取车系列表接口*/
    public static final String SERIES_SEARCH_BY_SERIES_NAME = API_NAME + "/vehicleIdentify/getSeriesListBySeriesName";
    //是否精细化车型
    public static final String REQUEST_IS_FINE_VEHICLE = API_NAME + "/vehicleIdentify/getSeriType/";
    //查询品牌列表
    public static final String SEARCH_BRAND_LIST = API_NAME + "/mobile/getBrandList/";
    //查询厂牌型号 (180227 这个接口现在没有用)
    public static final String SEARCH_PLATE_MODEL = API_NAME + "/mobile/getZccxbmBycxmc";

    /**修理厂查询*/
    public static final String SERCH_REPAIR_FACTORY = API_NAME + "/factory/querybyname/";
    public static final String SERCH_REPAIR_FACTORY_DETAIL = API_NAME + "/factory/getfactoryInfo/";
    /**根据机构 等级 类型 名称查询修理厂*/
    public static final String SERCH_REPAIR_FACTORY_BY_CONDITION = API_NAME + "/mobile/getFactoryByCondition";
    /**修理厂机构查询*/
    public static final String SERCH_REPAIR_FACTORY_COMCODE = API_NAME + "/factory/queryorg/";
    /**更换修理厂*/
    public static final String CHANGE_REPAIR_FACTORY = API_NAME + "/mobile/changefactory";
    /** 来源机构、等级、类型查询*/
    public static final String QUERY_FACTORY_ORGS = API_NAME + "/factory/queryOrgs";
    /**查询省*/
    //public static final String QUERY_FACTORY_PROVINCE = API_NAME + "/dict/getAqRegionDictInfo";
    public static final String QUERY_FACTORY_PROVINCE = API_NAME + "/common/getRegionDictInfo";
    /**查询市*/
    //public static final String QUERY_FACTORY_CITY_BY_PROVINCE_CODE = API_NAME + "/dict/getCitySelectByProvince";
    public static final String QUERY_FACTORY_CITY_BY_PROVINCE_CODE = API_NAME + "/common/getCitySelectByProvince";
    /**保存自定义修理厂*/
    public static final String SAVE_CUSTOM_FACTORY = API_NAME + "/mobile/saveRepairFactoryInfoByDefinedInfo";
    /**通过修理厂 id 查询修理厂(定损工具传修理厂时使用)*/
    public static final String SEARCH_FACTORY_BY_FACTORY_ID = API_NAME + "/mobile/getMobileFactoryInfoById/";
    /**查询外修修理擦汗那个*/
    public static final String SEARCH_OUT_FACTORY = API_NAME + "/mobile/getOutsideFactoryList/";


    /**查询换件组*/
    public static final String GET_PART_GROUP_TREE = API_NAME + "/mobile/getPartGroupTree";
    public static final String GET_PART_LIST_GROUP = API_NAME + "/mobile/getPartListForGroup";
    /**查询标准件*/
    public static final String GET_STAND_PART_LIST_GROUP = API_NAME + "/mobile/getStandPartListForGroup";
    /**查询零件 工时关联关系*/
    public static final String GET_PART_MANHOUR_RELATION = API_NAME + "/mobile/getPartMobileRelation";
    /**名称查询*/
    public static final String GET_PART_LIST_FOR_NAME = API_NAME + "/mobile/getPartListForName";
    /**碰撞查询*/
    public static final String GET_PART_LIST_COLLISION = API_NAME + "/mobile/getPartListForCollision";
    public static final String SAVE_BASE_EVAL_INFO = API_NAME + "/saveEval/mobileBasic";
    /**自定义件查询*/
    public static final String CUSTOMER_PART_SEARCH = API_NAME + "/mobile/getPartListForManualPart";
    /**保存车型信息 */
    public static final String SAVE_VEHICLE_INFO = API_NAME + "/mobile/saveVehicleInfo";
    /*新增，根据回答完问题，获取车辆信息*/
    public static final String GET_VEHICLEINFO_BY_VEHIDVIN_STAGE = API_NAME+"/vehicleIdentify/getVehicleInfoByVehIdVinStage/";
    /**换件带出附加工时 */
    public static final String GET_REPAIR_LIST_BY_FITID = API_NAME + "/partQuery/getPartAppendHour/";
    /**恢复换件 */
    public static final String PART_RECOVERY = API_NAME + "/evalPart/recoveryPart";
    /**维修带出附加工时 */
    public static final String GET_REPAIR_LIST_BY_REPAIR_ID = API_NAME + "/repairQuery/getRepairAppendHour/";
    /**查询维修项目*/
    public static final String GET_REPAIR_GROUP_TREE = API_NAME + "/mobile/getRepairGroupTree/";
    public static final String GET_REPAIR_LIST_FOR_GROUP = API_NAME + "/mobile/getRepairListForGroup";
    public static final String GET_REPAIR_LIST_FOR_NAME = API_NAME + "/mobile/getRepairListForName";
    /**自定义维修项目查询是否存在系统件*/
    public static final String GET_REPAIR_LIST_FOR_NAME_FROM = API_NAME + "/repairQuery/getRepairListForName";
    /**查询工时互斥关系*/
    public static final String GET_MUTEX_REPAIR_LIST = API_NAME + "/repairQuery/getRepairExclusionItem";
    /**3.32	工时添加查询关联关系(工时互斥 附加工时 附加辅料)*/
    public static final String GET_REPAIR_RELATION_LIST = API_NAME + "/mobile/getRepairMobileRelation";
    /**3.32	工时添加查询关联关系(工时互斥 附加工时 附加辅料)*/
    public static final String GET_OUTER_REPAIR_MOBILE_RELATION = API_NAME + "/mobile/getOuterRepairMobileRelation";
    /**查询辅料*/
    public static final String GET_MATERIAL_LIST = API_NAME + "/materialQuery/getMaterialList";
    /**查询辅料 工时 关联关系*/
    public static final String GET_MATERIAL_MANHOUR_RELATION = API_NAME + "/mobile/getRepairByAppendMate/";
    /**定损提交*/
    public static final String SAVE_EVAL_INFO = API_NAME + "/mobile/saveMobileBasic";
    /**极速推修*/
    public static final String PUSH_REPAIR="http://www.lexiugo.com/lexiugo/ccip/getPushTask.do";
//    public static final String PUSH_REPAIR="http://192.168.120.162:8080/lexiugo/ccip/getPushTask.do";
    /**推修查询*/
    public static final String PUSH_REPAIR_QUERY="http://www.lexiugo.com/lexiu/PushWebServlet";
    /**问题平台 创建问题 */
    public static final String CREATE_FEED_BACK_PROBLEM="http://192.168.120.172:8080/receiveTaskUrlIns/receiveTaskUrlJson";
    /**问题平台 查看列表 */
    public static final String QUERY_FEED_BACK_LIST="http://192.168.120.172:8080/queryTaskUrlIns/queryTaskUrlJson";
    //获取当前任务的状态更新
    public static final String GET_TASK_STATUS="/mobile/gettaskstatus/";
    //拿到当前车辆的定损单ＩＤ
    public static final String REQUEST_EVALID_TOOL = API_NAME + "/mobile/claimRequestMoblieTool";
    public static final String REQUEST_EVALID_SYSTEM = API_NAME + "/mobile/claimRequestMoblieSystem";
    public static final String REQUEST_EVALID_SYSTEM_CIC = API_NAME + "/mobile/claimRequestMoblieCIC";
    //外修查询
    public static final String GET_OUTSIDE_REPAIR_LIST = API_NAME + "/mobile/getOuterRepairList";
    //综合查询任务列表
    public static final String QUERY_TASK_LIST="/task/listQuery";
    //综合查询详情
    public static final String QUERY_TASK_DETAIL="/task/detailQuery";
    //风险平台接口 /risk/getSelfRisk?lossNo=
    public static final String GET_SELF_RISK = API_NAME + "/risk/getSelfRisk";
    //风险接口(查询pc本地数据)
    public static final String GET_RISK = API_NAME + "/risk/getRisk?evalId=";
    //提示风险详情接口
    public static final String GET_RISK_DETAIL = API_NAME + "/risk/getRiskDetailed";
    //碰撞部位关联的配件
    public static final String GET_COLLISION_RELATED_PARTS = API_NAME + "/eval/getEvalCollisionRelation/";
    /**国泰 -- 请求理赔获取定损信息*/
    public static final String REQUEST_EVAL_INFO_FROM_LP_BY_GUO_TAI = "http://139.224.38.83/claim/claimReceivePreApprovalController.do?receiveDataFromPreApproval";

    /**移动*/
    //定损提交理赔
    public static final String SUBMIT_TO_LP_BY_EVAL="/eval/carSubmit";
    //查勘提交理赔
    public static final String SUBMIT_TO_LP_BY_SURVEY="/survey/submit";
    //获取字典表
    public static final String GET_DICT_INFO_LIST="/typeConstants/sync";
    /**照片提交*/
    public static final String URL_UPLOAD_PHOTO="/survey/taskSubmit";
    /**获取上传文件断点接口*/
    public static final String URL_GET_FILELEN="/picture/getFileLen";

    /**上班签到*/
    public static final String SING_IN_AT_WORK="/sign";
    /**备忘录 列表*/
    public static final String MEMO_RESULT_LIST="/memo/memoList";
    /**备忘录 删除*/
    public static final String MEMO_DEL_LIST="/memo/delMemo";
    /**备忘录 修改*/
    public static final String MEMO_EDIT_LIST="/memo/editMemo";
    /**备忘录 新建提交*/
    public static final String MEMO_ADD_LIST="/memo/addMemo";

    /**
     * 获取 pc Url
     * @param url
     * @return
     */
    public static String getRequestUrl(String url) {
        String reqUrl = SCHEME + MOBILE_DOMAIN_NAME + url;
        return reqUrl;
    }

    /**
     * 获取移动
     * @param url
     * @return
     */
    public static String getMobileRequestUrl(String url) {
        String reqUrl = SCHEME + MOBILE_DOMAIN_NAME + url;
        return reqUrl;
    }

    /**
     * 获取移动查勘
     * @param url
     * @return
     */
    public static String getMobilSurveyRequestUrl(String url) {
        String reqUrl = SCHEME + MOBILE_SURVEY_DOMAIN_NAME + url;
        return reqUrl;
    }

    public static String getRetrofitRequestUrl(String url) {
        String reqUrl = API_NAME + url;
        return reqUrl;
    }


    /*测试*/
    public static final String URL_LOGIN_IN = "/login";
    public static final String URL_CARGO =  "/eval/cargoSubmit";
    public static final String URL_INJURY = "/eval/injurySubmit";
    public static final String URL_SURVEY = "http://192.168.120.49:8282/StanClaimProd-mobile-ifc/survey/submit";

    //获取token
    public static final String URL_TOKEN = API_NAME + "/mobile/auth";
    public static String TOKEN_APP;

}
