package com.hl.photo.support.util;

import com.hl.photo.R;

/**
 * Created by liyu on 2016/12/30.
 */

public class CameraConstant {
    public static final long FOCUS_MODE_CONTINUOUS_TIME = 200;
    public static final long MAX_RECORD_LIMITED_DURATION = 6 * 60 * 1000;
    public static final long MAX_RECORD_WARNING_DURATION = MAX_RECORD_LIMITED_DURATION - 30 * 1000;
    public static final int MIN_SDCARD_AVAILABLE_SIZE = 100; //M
    public static final String BASE_VIDEO_FOLDER = "/youku/paike/";
    public static final int PHOTO_MAX_SAVE_SIDELEN = 1600;//相机最大保存

    /**
     *
     * 视频质量
     * @Package com.youku.paike.camera
     * @ClassName: Quality
     * @author Luke
     * @mail luchen@youku.com
     * @date 2013-7-4 下午8:35:09
     */
    public enum Quality {

        QUALITY_NORMAL(0x0),
        QUALITY_HIGH(0x1),
        QUALITY_SUPER(0x2);

        private int mIntValue;

        Quality(int qualityInt) {
            mIntValue = qualityInt;
        }

        public int getIntValue(){
            return mIntValue;
        }

        public static Quality getDefault(){
            return QUALITY_NORMAL;
        }

        static Quality mapIntToValue(final int qualityInt){
            for (Quality value : Quality.values()) {
                if (qualityInt == value.getIntValue()) {
                    return value;
                }
            }
            return null;
//			return getDefault();
        }

    }

    /**
     *
     * 设备方向
     * @Package com.youku.paike.camera
     * @ClassName: Oriention
     * @author Luke
     * @mail luchen@youku.com
     * @date 2013-7-4 下午8:35:33
     */
    public enum Oriention {

        ORIENTION_0(0),
        ORIENTION_90(90),
        ORIENTION_180(180),
        ORIENTION_270(270);

        private int mIntValue;

        Oriention(int orientionInt) {
            mIntValue = orientionInt;
        }

        public int getIntValue(){
            return mIntValue;
        }

        public static Oriention getDefault(){
            return ORIENTION_0;
        }

        static Oriention mapIntToValue(final int orientionInt){
            for (Oriention value : Oriention.values()) {
                if (orientionInt == value.getIntValue()) {
                    return value;
                }
            }
            return getDefault();
        }
    }

    /**
     *
     * 视频滤镜
     * @Package com.youku.paike.camera
     * @ClassName: Filter
     * @author Luke
     * @mail luchen@youku.com
     * @date 2013-7-4 下午8:55:17
     */
    public enum Filter {

        imageGeneralDocumentsTypeList_1("1370101", R.mipmap.photo_camera_filter_none_normal, "索赔申请书", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_2("1370120", R.mipmap.photo_camera_filter_none_normal, "本车行驶证", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_3("1370122", R.mipmap.photo_camera_filter_none_normal, "本车驾驶证", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_4("1370104", R.mipmap.photo_camera_filter_none_normal,"身体条件回执", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_5("1370105", R.mipmap.photo_camera_filter_none_normal, "定损单", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_6("1370106", R.mipmap.photo_camera_filter_none_normal, "车、物维修发票", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_7("1370107", R.mipmap.photo_camera_filter_none_normal, "维修清单", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_8("1370108", R.mipmap.photo_camera_filter_none_normal, "施救费票据", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_9("1370109", R.mipmap.photo_camera_filter_none_normal, "权益转让书", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_10("1370110", R.mipmap.photo_camera_filter_none_normal, "交警责任认定书", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_11("1370111", R.mipmap.photo_camera_filter_none_normal, "交通事故调解书", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_12("1370112", R.mipmap.photo_camera_filter_none_normal, "法院调解", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_13("1370113", R.mipmap.photo_camera_filter_none_normal, "仲裁书", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_14("1370114", R.mipmap.photo_camera_filter_none_normal, "保险单（原件）", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_15("1370115", R.mipmap.photo_camera_filter_none_normal, "调查笔录", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_16("1370116", R.mipmap.photo_camera_filter_none_normal, "赔偿凭证", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_17("1370117", R.mipmap.photo_camera_filter_none_normal, "查勘报告", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_18("1370118", R.mipmap.photo_camera_filter_none_normal, "病历本改病历及诊断证明", R.mipmap.photo_camera_filter_none_down),
        imageGeneralDocumentsTypeList_19("1370119", R.mipmap.photo_camera_filter_none_normal, "死者户籍证明改受害者及家庭成员户籍资料", R.mipmap.photo_camera_filter_none_down),

        imagePeopleHurtTypeList_1("1370201", R.mipmap.photo_camera_filter_none_normal, "人伤查勘照片", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_2("1370218", R.mipmap.photo_camera_filter_none_normal, "住院查勘照片", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_3("1370202", R.mipmap.photo_camera_filter_none_normal, "医疗费用票据", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_4("1370203", R.mipmap.photo_camera_filter_none_normal, "医疗费用清单及处方", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_5("1370204", R.mipmap.photo_camera_filter_none_normal, "病历本", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_6("1370205", R.mipmap.photo_camera_filter_none_normal, "收入减少证明", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_7("1370206", R.mipmap.photo_camera_filter_none_normal, "病假单", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_8("1370207", R.mipmap.photo_camera_filter_none_normal, "伤残鉴定证明", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_9("1370208", R.mipmap.photo_camera_filter_none_normal, "残疾器材证明", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_10("1370209", R.mipmap.photo_camera_filter_none_normal, "尸检证明", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_11("1370210", R.mipmap.photo_camera_filter_none_normal, "医学死亡证明", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_12("1370211", R.mipmap.photo_camera_filter_none_normal, "死者户籍证明", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_13("1370212", R.mipmap.photo_camera_filter_none_normal, "被抚养人证明", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_14("1370213", R.mipmap.photo_camera_filter_none_normal, "住宿费发票", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_15("1370214", R.mipmap.photo_camera_filter_none_normal, "交通费发票", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_16("1370215", R.mipmap.photo_camera_filter_none_normal, "垫支付通知书", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_17("1370216", R.mipmap.photo_camera_filter_none_normal, "抢救费用清单", R.mipmap.photo_camera_filter_none_down),
        imagePeopleHurtTypeList_18("1370217", R.mipmap.photo_camera_filter_none_normal, "护理证明", R.mipmap.photo_camera_filter_none_down),

        imageFireTypeList_1("1370301", R.mipmap.photo_camera_filter_none_normal, "火灾消防证明", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_1("1370401", R.mipmap.photo_camera_filter_none_normal, "公安立（破）案表", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_2("1370402", R.mipmap.photo_camera_filter_none_normal, "公安机关报案回执", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_3("1370403", R.mipmap.photo_camera_filter_none_normal, "公安机关立案证明", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_4("1370404", R.mipmap.photo_camera_filter_none_normal, "公安机关未破获证明", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_5("1370405", R.mipmap.photo_camera_filter_none_normal, "车辆档案封存证明", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_6("1370406", R.mipmap.photo_camera_filter_none_normal, "报停证明", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_7("1370407", R.mipmap.photo_camera_filter_none_normal, "盗抢笔录", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_8("1370408", R.mipmap.photo_camera_filter_none_normal, "保险单（原件）", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_9("1370409", R.mipmap.photo_camera_filter_none_normal, "车辆购置税凭证", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_10("1370410", R.mipmap.photo_camera_filter_none_normal, "购车发票（原件）", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_11("1370411", R.mipmap.photo_camera_filter_none_normal, "车钥匙（全套）", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_12("1370412", R.mipmap.photo_camera_filter_none_normal, "车辆丢失登报证明", R.mipmap.photo_camera_filter_none_down),
        imageStealRobList_13("1370413", R.mipmap.photo_camera_filter_none_normal, "机动车辆登记证书", R.mipmap.photo_camera_filter_none_down),

        imageNaturalDisastersList_1("1370501", R.mipmap.photo_camera_filter_none_normal, "灾害气象证明", R.mipmap.photo_camera_filter_none_down),

        iamgeEvalList_1("1370611", R.mipmap.photo_camera_filter_none_normal, "现场查勘", R.mipmap.photo_camera_filter_none_down),
        iamgeEvalList_2("1370621", R.mipmap.photo_camera_filter_none_normal, "本车定损", R.mipmap.photo_camera_filter_none_down),
        iamgeEvalList_3("1370631", R.mipmap.photo_camera_filter_none_normal, "三者定损", R.mipmap.photo_camera_filter_none_down),
        iamgeEvalList_4("1370641", R.mipmap.photo_camera_filter_none_normal, "物损定损", R.mipmap.photo_camera_filter_none_down),

        imageThirdCarGeneralDocumentsTypeList_1("1370121", R.mipmap.photo_camera_filter_none_normal, "三者行驶证（正证、副证）", R.mipmap.photo_camera_filter_none_down),
        imageThirdCarGeneralDocumentsTypeList_2("1370123", R.mipmap.photo_camera_filter_none_normal, "三者驾驶证（正证、副证）", R.mipmap.photo_camera_filter_none_down),

        imageOtherList_1("1370701", R.mipmap.photo_camera_filter_none_normal, "其它", R.mipmap.photo_camera_filter_none_down),

        iamgePaymentList_1("1370801", R.mipmap.photo_camera_filter_none_normal, "被保人身份证", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_2("1370802", R.mipmap.photo_camera_filter_none_normal, "被授权委托人身份证", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_3("1370803", R.mipmap.photo_camera_filter_none_normal, "受益人身份证", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_4("1370804", R.mipmap.photo_camera_filter_none_normal, "银行卡信息", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_5("1370805", R.mipmap.photo_camera_filter_none_normal, "领取赔款授权书", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_6("1370806", R.mipmap.photo_camera_filter_none_normal, "贷款银行出具同意支付证明", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_7("1370807", R.mipmap.photo_camera_filter_none_normal, "赔款收据", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_8("1370808", R.mipmap.photo_camera_filter_none_normal, "赔款计算书", R.mipmap.photo_camera_filter_none_down),
        iamgePaymentList_9("1370809", R.mipmap.photo_camera_filter_none_normal, "保单抄件", R.mipmap.photo_camera_filter_none_down);


        private String mFilterName;
        private int mFilterIconResId;
        private String mFilterTitleResId;
        private int mFilterIconSelectedResId = mFilterIconResId;

        Filter(String filterName, int filterResId, String mFilterTitleResId, int mFilterIconSelectedResId) {
            this.mFilterName = filterName;
            this.mFilterIconResId = filterResId;
            this.mFilterTitleResId = mFilterTitleResId;
            this.mFilterIconSelectedResId = mFilterIconSelectedResId;
        }

        public String getFilterName(){
            return mFilterName;
        }

        public int getFilterIconResId(){
            return mFilterIconResId;
        }

        public int getFilterIconSelectedResId(){
            return mFilterIconSelectedResId;
        }

        public String getFilterTitleResId(){
            return mFilterTitleResId;
        }

        public static Filter getDefault(){
            return null;
        }

        public static Filter mapFilterNameToFilter(final String filterName){

            if(filterName != null){
                for (Filter value : Filter.values()) {
                    if (filterName.equals(value.getFilterName())) {
                        return value;
                    }
                }
            }

            return null;
//			return getDefault();
        }
        public static Filter mapFilterTitleToFilter(final String filterName){

            if(filterName != null){
                for (Filter value : Filter.values()) {
                    if (filterName.equals(value.getFilterName())) {
                        return value;
                    }
                }
            }

            return null;
//			return getDefault();
        }
    }

}
