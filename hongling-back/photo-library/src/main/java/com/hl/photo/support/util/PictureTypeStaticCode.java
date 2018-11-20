package com.hl.photo.support.util;

import android.text.TextUtils;

import com.hl.core.lib.bean.TypeItem;

import java.util.ArrayList;

public final class PictureTypeStaticCode {
	/** 13701通用单证list */
	public static ArrayList<TypeItem> imageGeneralDocumentsTypeList;
	/** 13701三者通用单证list */
	public static ArrayList<TypeItem> imageThirdCarTypeList;
	/** 13702人伤list */
	public static ArrayList<TypeItem> imagePeopleHurtTypeList;
	/** 13703火烧list */
	public static ArrayList<TypeItem> imageFireTypeList;
	/** 13704盗抢车list */
	public static ArrayList<TypeItem> imageStealRobList;
	/** 13705自然灾害list */
	public static ArrayList<TypeItem> imageNaturalDisastersList;
	/** 13706现场查勘照片list */
	public static ArrayList<TypeItem> iamgeSurveyList;
	/** 13706本车定损照片list */
	public static ArrayList<TypeItem> iamgeEvalBDList;
	/** 13706三者定损照片list */
	public static ArrayList<TypeItem> iamgeEvalSZList;
	/** 13706物损定损照片list */
	public static ArrayList<TypeItem> iamgePopLossList;
	/** 13707其它list */
	public static ArrayList<TypeItem> imageOtherList;
	/** 13708支付资料list */
	public static ArrayList<TypeItem> iamgePaymentList;

	static {
		initItemList();
	}

	private static void initItemList() {
		/** 13701通用单证list */
		imageGeneralDocumentsTypeList = new ArrayList<TypeItem>();
		imageGeneralDocumentsTypeList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME,"1370101","索赔申请书"));
		imageGeneralDocumentsTypeList.add(new TypeItem("2", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME,"1370120","本车行驶证"));
//		imageGeneralDocumentsTypeList.add(new TypeItem("3",CLaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE,PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME,"1370121","三者行驶证（正证、副证）"));
		imageGeneralDocumentsTypeList.add(new TypeItem("3", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370122","本车驾驶证"));
//		imageGeneralDocumentsTypeList.add(new TypeItem("5",CLaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE,PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370123","三者驾驶证（正证、副证）"));
		imageGeneralDocumentsTypeList.add(new TypeItem("4", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370104","身体条件回执"));
		imageGeneralDocumentsTypeList.add(new TypeItem("5", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370105","定损单"));
		imageGeneralDocumentsTypeList.add(new TypeItem("6", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370106","车、物维修发票"));
		imageGeneralDocumentsTypeList.add(new TypeItem("7", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370107","维修清单"));
		imageGeneralDocumentsTypeList.add(new TypeItem("8", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370108","施救费票据"));
		imageGeneralDocumentsTypeList.add(new TypeItem("9", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370109","权益转让书"));
		imageGeneralDocumentsTypeList.add(new TypeItem("10", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370110","交警责任认定书"));
		imageGeneralDocumentsTypeList.add(new TypeItem("11", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370111","交通事故调解书"));
		imageGeneralDocumentsTypeList.add(new TypeItem("12", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370112","法院调解/判决书"));
		imageGeneralDocumentsTypeList.add(new TypeItem("13", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370113","仲裁书"));
		imageGeneralDocumentsTypeList.add(new TypeItem("14", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370114","保险单（原件）"));
		imageGeneralDocumentsTypeList.add(new TypeItem("15", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370115","调查笔录"));
		imageGeneralDocumentsTypeList.add(new TypeItem("16", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370116","赔偿凭证"));
		imageGeneralDocumentsTypeList.add(new TypeItem("17", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370117","查勘报告"));
		imageGeneralDocumentsTypeList.add(new TypeItem("18", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370118","病历本改病历及诊断证明"));
		imageGeneralDocumentsTypeList.add(new TypeItem("19", PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME, "1370119","死者户籍证明改受害者及家庭成员户籍资料"));

		/** 13702人伤list */
		imagePeopleHurtTypeList = new ArrayList<TypeItem>();
		imagePeopleHurtTypeList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370201","人伤查勘照片"));
		imagePeopleHurtTypeList.add(new TypeItem("2", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME,"1370218","住院查勘照片"));
		imagePeopleHurtTypeList.add(new TypeItem("3", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370202","医疗费用票据"));
		imagePeopleHurtTypeList.add(new TypeItem("4", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370203","医疗费用清单及处方"));
		imagePeopleHurtTypeList.add(new TypeItem("5", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME,"1370204","病历本"));
		imagePeopleHurtTypeList.add(new TypeItem("6", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370205","收入减少证明"));
		imagePeopleHurtTypeList.add(new TypeItem("7", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370206","病假单"));
		imagePeopleHurtTypeList.add(new TypeItem("8", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370207","伤残鉴定证明"));
		imagePeopleHurtTypeList.add(new TypeItem("9", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME,"1370208","残疾器材证明"));
		imagePeopleHurtTypeList.add(new TypeItem("10", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME,"1370209","尸检证明"));
		imagePeopleHurtTypeList.add(new TypeItem("11", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME,"1370210","医学死亡证明"));
		imagePeopleHurtTypeList.add(new TypeItem("12", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME,"1370211","死者户籍证明"));
		imagePeopleHurtTypeList.add(new TypeItem("13", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370212","被抚养人证明"));
		imagePeopleHurtTypeList.add(new TypeItem("14", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370213","住宿费发票"));
		imagePeopleHurtTypeList.add(new TypeItem("15", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370214","交通费发票"));
		imagePeopleHurtTypeList.add(new TypeItem("16", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370215","垫支付通知书"));
		imagePeopleHurtTypeList.add(new TypeItem("17", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370216","抢救费用清单"));
		imagePeopleHurtTypeList.add(new TypeItem("18", PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_CODE, PhotoClaimUtil.PHOTO_TYPE_PEOPLE_HURT_NAME, "1370217","护理证明"));

		/** 13703火烧list */
		imageFireTypeList = new ArrayList<TypeItem>();
		imageFireTypeList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_FIRE_CODE, PhotoClaimUtil.PHOTO_TYPE_FIRE_NAME,"1370301","火灾消防证明"));

		/** 13704盗抢车list */
		imageStealRobList = new ArrayList<TypeItem>();
		imageStealRobList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME,"1370401","公安立（破）案表"));
		imageStealRobList.add(new TypeItem("2", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370402","公安机关报案回执"));
		imageStealRobList.add(new TypeItem("3", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME,"1370403","公安机关立案证明"));
		imageStealRobList.add(new TypeItem("4", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370404","公安机关未破获证明"));
		imageStealRobList.add(new TypeItem("5", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME,"1370405","车辆档案封存证明"));
		imageStealRobList.add(new TypeItem("6", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME,"1370406","报停证明"));
		imageStealRobList.add(new TypeItem("7", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370407","盗抢笔录"));
		imageStealRobList.add(new TypeItem("8", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370408","保险单（原件）"));
		imageStealRobList.add(new TypeItem("9", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME,"1370409","车辆购置税凭证"));
		imageStealRobList.add(new TypeItem("10", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370410","购车发票（原件）"));
		imageStealRobList.add(new TypeItem("11", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370411","车钥匙（全套）"));
		imageStealRobList.add(new TypeItem("12", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370412","车辆丢失登报证明"));
		imageStealRobList.add(new TypeItem("13", PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE, PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME, "1370413","机动车辆登记证书"));

		/** 13705自然灾害list */
		imageNaturalDisastersList = new ArrayList<TypeItem>();
		imageNaturalDisastersList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_NATURAL_DISASTER_CODE, PhotoClaimUtil.PHOTO_TYPE_NATURAL_DISASTER_NAME, "1370501","灾害气象证明"));

		/** 13706查勘照片list */
		iamgeSurveyList = new ArrayList<TypeItem>();
		iamgeSurveyList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_SURVEY_CODE, PhotoClaimUtil.PHOTO_TYPE_SURVEY_NAME, "1370611","现场查勘"));
		/** 13706本车定损照片list */
		iamgeEvalBDList = new ArrayList<TypeItem>();
		iamgeEvalBDList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_EVAL_BD_CODE, PhotoClaimUtil.PHOTO_TYPE_EVAL_BD_NAME, "1370621","本车定损"));
		/** 13706三者定损照片list */
		iamgeEvalSZList = new ArrayList<TypeItem>();
		iamgeEvalSZList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE, PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_NAME, "1370631","三者定损"));
		/** 13706物损定损照片list */
		iamgePopLossList = new ArrayList<TypeItem>();
		iamgePopLossList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_POPLOSS_CODE, PhotoClaimUtil.PHOTO_TYPE_POPLOSS_NAME, "1370641","物损定损"));

		/** 13701三者通用单证list */
		imageThirdCarTypeList = new ArrayList<TypeItem>();
		imageThirdCarTypeList.add(new TypeItem("3", PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE, PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_NAME, "1370631","三者定损"));
		imageThirdCarTypeList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE, PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_NAME,"1370121","三者行驶证（正证、副证）"));
		imageThirdCarTypeList.add(new TypeItem("2", PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE, PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_NAME, "1370123","三者驾驶证（正证、副证）"));


		/** 13707其它list */
		imageOtherList = new ArrayList<TypeItem>();
		imageOtherList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_OTHER_CODE, PhotoClaimUtil.PHOTO_TYPE_OTHER_NAME, "1370701","自定义"));

		/** 13708支付资料list */
		iamgePaymentList = new ArrayList<TypeItem>();
		iamgePaymentList.add(new TypeItem("1", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370801","被保人身份证"));
		iamgePaymentList.add(new TypeItem("2", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME,"1370802","被授权委托人身份证"));
		iamgePaymentList.add(new TypeItem("3", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370803","受益人身份证"));
		iamgePaymentList.add(new TypeItem("4", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370804","银行卡信息"));
		iamgePaymentList.add(new TypeItem("5", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370805","领取赔款授权书"));
		iamgePaymentList.add(new TypeItem("6", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370806","贷款银行出具同意支付证明"));
		iamgePaymentList.add(new TypeItem("7", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370807","赔款收据"));
		iamgePaymentList.add(new TypeItem("8", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370808","赔款计算书"));
		iamgePaymentList.add(new TypeItem("9", PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE, PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME, "1370809","保单抄件"));

	}

    public static ArrayList<TypeItem> getAllPhotoTypeList(){
        ArrayList<TypeItem> photoTypeAllList = new ArrayList<>();
        photoTypeAllList.addAll(PictureTypeStaticCode.imageGeneralDocumentsTypeList);
        photoTypeAllList.addAll(PictureTypeStaticCode.iamgeSurveyList);
        photoTypeAllList.addAll(PictureTypeStaticCode.iamgeEvalBDList);
        photoTypeAllList.addAll(PictureTypeStaticCode.iamgeEvalSZList);
        photoTypeAllList.addAll(PictureTypeStaticCode.iamgePopLossList);
        photoTypeAllList.addAll(PictureTypeStaticCode.imagePeopleHurtTypeList);
        photoTypeAllList.addAll(PictureTypeStaticCode.iamgePaymentList);
        photoTypeAllList.addAll(PictureTypeStaticCode.imageStealRobList);
        photoTypeAllList.addAll(PictureTypeStaticCode.imageNaturalDisastersList);
        photoTypeAllList.addAll(PictureTypeStaticCode.imageFireTypeList);
        photoTypeAllList.addAll(PictureTypeStaticCode.imageOtherList);
        return photoTypeAllList;
    }

    /**
     * 通过照片类型 ，获取 照片分类
     * @param pictureType
     * @param photoTypeAllList
     * @return
     */
    public static  ArrayList<TypeItem> getPhptoTypeList(String pictureType , ArrayList<TypeItem> photoTypeAllList){
        ArrayList<TypeItem> arrayList = new ArrayList<TypeItem>();
        if(photoTypeAllList != null && !TextUtils.isEmpty(pictureType)){
            for(TypeItem typeItem : photoTypeAllList){
                if(pictureType.equals(typeItem.getTypeCode())){
                    arrayList.add(typeItem);
                }
            }
        }
        return arrayList;
    }

}
