package com.hl.photo.ui.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hl.core.lib.activity.TitleActivity;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.core.lib.viewmodel.ViewModel;
import com.hl.photo.PhotoApp;
import com.hl.photo.R;
import com.hl.photo.business.entity.PictureTypePo;
import com.hl.photo.business.listener.DeleteListener;
import com.hl.photo.databinding.PhotoActivityManageBinding;
import com.hl.photo.support.util.FileUtil;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.support.util.PictureTypeStaticCode;
import com.hl.photo.table.manager.DictManager;
import com.hl.photo.table.manager.PhotoManager;
import com.hl.photo.table.model.DictInfo;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.table.model.ReportCar;
import com.hl.photo.table.model.SurveyInjury;
import com.hl.photo.table.model.SurveyProperty;
import com.hl.photo.table.model.TaskInfo;
import com.hl.photo.ui.adapter.BaseRecycleAdapter;
import com.hl.photo.ui.adapter.PictureTypeListAdapter;
import com.hl.photo.ui.viewmodel.PhotoManagerVM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liyu on 2017/1/21
 */

public class PhotoManagerActivity extends TitleActivity<TitleBar> implements DeleteListener,View.OnClickListener {

    @ViewModel
    PhotoManagerVM mPhotoVM;

    private String titleflag;
    private PhotoActivityManageBinding binding;
    private String mReportNo;
    private String mTaskType;
    private String mSearchType;
    private List<DictInfo> dictInfosByKey;
    private List<PictureTypePo> pictureTypeDataLists = new ArrayList<PictureTypePo>();
    private List<ReportCar> allSurveyCar;
    private List<SurveyProperty> surveyPropertyList;
    private List<SurveyInjury> surveyInjuryList;
    private List<TaskInfo> thirdCarList;
    private Context mContext;
    private PictureTypeListAdapter pictureTypeListAdapter;

    //图片缓存用来保存GridView中每个Item的图片，以便释放
    public static Map<String,Bitmap> gridviewBitmapCaches = new HashMap<String,Bitmap>();
    //选择获取照片方式
    private PopupWindow popupWindow;
    private String[] imageTypeCodeArray = null;
    private String[] imageTypeNameArray = null;
    private int[] imageTypeCountArray = null;

    public String upLoadDialogMessage = "";
    public String mUploadFilePathName = "";

    private List<PhotoInfo> surveyPhotoInfoList = new ArrayList<PhotoInfo>();
    private List<PhotoInfo> surveyPhotoListFromDB = new ArrayList<PhotoInfo>();
    private List<PhotoInfo> evalPhotoInfoList= new ArrayList<PhotoInfo>();
    private List<PhotoInfo> evalPhotoListFromDB = new ArrayList<PhotoInfo>();
    private List<PhotoInfo> thirdEvalPhotoInfoList= new ArrayList<PhotoInfo>();
    private List<PhotoInfo> thirdEvalPhotoListFromDB = new ArrayList<PhotoInfo>();
    private List<PhotoInfo> allUploadImagesFromDB = new ArrayList<PhotoInfo>();
    private List<DictInfo> allDictInfo;

    @Override
    protected Object entryInterceptor(Intent intent) {
        titleflag = intent.getStringExtra("Flag");
        mReportNo = intent.getStringExtra(PhotoClaimUtil.REPORT_NO);
        if (mReportNo.isEmpty()) {
            UtilManager.Toast.show(this,getResources().getString(R.string.photo_warm_not_report_no));
            finish();
        }
        mTaskType =  intent.getStringExtra(PhotoClaimUtil.TASK_TYPE);
        mSearchType =  intent.getStringExtra(PhotoClaimUtil.SEARCH_TYPE);

        String dictInfos = intent.getStringExtra("dictInfosByKey");
        allDictInfo = new Gson().fromJson(dictInfos, new TypeToken<List<DictInfo>>() {}.getType());
        DictManager.getInstance().insertDictInfoList(allDictInfo);

//        String photoInfos = intent.getStringExtra("photoInfoList");
//        List photoInfoList = new Gson().fromJson(photoInfos,new TypeToken<List<PhotoInfo>>(){}.getType());
//        PhotoManager.getInstance().savePhotoInfoAllData(photoInfoList);

        String surveyCars = intent.getStringExtra("allSurveyCar");
        allSurveyCar = new Gson().fromJson(surveyCars, new TypeToken<List<ReportCar>>() {
        }.getType());


        String surveyPropertys = intent.getStringExtra("surveyPropertyList");
        surveyPropertyList = new Gson().fromJson(surveyPropertys, new TypeToken<List<SurveyProperty>>() {
        }.getType());


        String surveyInjurys = intent.getStringExtra("surveyInjuryList");
        surveyInjuryList = new Gson().fromJson(surveyInjurys, new TypeToken<List<SurveyInjury>>() {
        }.getType());

        String thirdTasks = intent.getStringExtra("thirdTaskList");
        thirdCarList = new Gson().fromJson(thirdTasks, new TypeToken<List<TaskInfo>>() {
        }.getType());

        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        if(titleflag == null){
            titleBar.title = getString(R.string.photo_title);
            titleBar.rightBtnText= getResources().getString(R.string.photo_upload);
            titleBar.showRightBtn = true;
        }
    }

    @Override
    protected Object initLayout() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.photo_activity_manage, null, false);
        return binding.getRoot();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PhotoApp.instance().init();
        mContext = this;

    }

    @Override
    protected void onResume() {
        super.onResume();

        mPhotoVM.inSertDictInfoList(allDictInfo).observeOnce(this, result->{
            dictInfosByKey =  DictManager.getInstance().getDictInfosByKey(PhotoClaimUtil.DICT_IMAGE_TYPE);
            getImageTypeFromDict();
            showAllPhoto();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放bitmap造成的缓存
        if (surveyPhotoListFromDB!=null&&surveyPhotoListFromDB.size()>0){
            for(int del=0;del<surveyPhotoListFromDB.size();del++){
                Bitmap delBitmap = gridviewBitmapCaches.get(surveyPhotoListFromDB.get(del).getImageName());
                if(delBitmap != null){
                    //如果非空则表示有缓存的bitmap，需要清理
                    Log.d("", "release position:"+ del);
                    //从缓存中移除该del->bitmap的映射
                    gridviewBitmapCaches.remove(surveyPhotoListFromDB.get(del).getImageName());
                    delBitmap.recycle();
                    delBitmap = null;
                }
            }
        }
        System.gc();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.title_right_btn){
            setOnActionButtonClick();
        }
        else if (i == R.id.button_submit) {
            bottomButtonSubmit();
        }
    }


    private void bottomButtonSubmit() {
        if(checkImages()){
            hideInputMethod();
            uploadTest();
        }else{
//                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText(getString(R.string.dialog_remind))
//                        .setContentText(upLoadDialogMessage)
//                        .setCancelText(getString(R.string.dialog_cancel))
//                        .setConfirmText(getString(R.string.dialog_confirm))
//                        .showCancelButton(true)
//                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                upload();
//                                hideInputMethod();
//                                sDialog.dismiss();
//                            }
//                        })
//                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sDialog) {
//                                sDialog.dismiss();
//                            }
//                        })
//                        .show();
        }
    }

    /*
   设置上传按钮监听
 */
    private void setOnActionButtonClick(){
        List<PhotoInfo> photoList = PhotoManager.getInstance().getUnUploadPhotoListByReportCode(mReportNo);
        if(photoList!= null && photoList.size() > 0){
            Bundle bundle = new Bundle();
            //bundle.putSerializable("photoList", (Serializable) photoList);
            bundle.putString(PhotoClaimUtil.REPORT_NO, mReportNo);
            // PhotoManageFragment.this.openFragment(PhotoManageFragment.this,UploadFileFragment.class,bundle);
            //for test
            startActivity(UploadPictureActivity.class,bundle);
        }else
            UtilManager.Toast.show(mContext,getString(R.string.photo_need_add_photo));
    }


    public void showAllPhoto(){
        pictureTypeListAdapter = new PictureTypeListAdapter(mContext, pictureTypeDataLists, this);
        binding.pictureTypeListView.setAdapter(pictureTypeListAdapter);
        pictureTypeListAdapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {
                PictureTypePo pictureTypePo = pictureTypeDataLists.get(position);
                String taskType = pictureTypePo.getTaskType();
                String signName = pictureTypePo.getSignName();
                if(PhotoClaimUtil.TASK_TYPE_EVAL_BD.equals(taskType) || PhotoClaimUtil.TASK_TYPE_EVAL_SZ.equals(taskType)){
                    if(TextUtils.isEmpty(signName)){
                        UtilManager.Toast.show(mContext,"请先填写该定损任务下的车牌号");
                        return;
                    }
                }else if(PhotoClaimUtil.TASK_TYPE_POP.equals(taskType)){
                    if(TextUtils.isEmpty(signName)){
                        UtilManager.Toast.show(mContext,"请先填写该物损任务下的损失名称");
                        return;
                    }
                }else if(PhotoClaimUtil.TASK_TYPE_INJURY.equals(taskType)){
                    if(TextUtils.isEmpty(signName)){
                        UtilManager.Toast.show(mContext,"请先填写该人伤任务下的伤者姓名");
                        return;
                    }
                }
                gotoShowPictureAndTextFragment(pictureTypePo);
            }
        });
    }

    /**
     * 跳转图片小类
     * @param po
     */
    private void gotoShowPictureAndTextFragment(PictureTypePo po){
        Bundle bundle = new Bundle();
        bundle.putString("reportNo",mReportNo);
//        bundle.putString("flowId", flowId);
        bundle.putString("pictureType", po.getPictureTypeCode());
        bundle.putString("pictureTypeName", po.getPictureTypeName());
        bundle.putString("signId",po.getSignId());
        bundle.putString("signName",po.getSignName());
        bundle.putString(PhotoClaimUtil.SEED_KEY,po.getSeedKey());
        bundle.putString(PhotoClaimUtil.TASK_TYPE,po.getTaskType());
        startActivity(ShowPictureAndTextActivity.class,bundle);
    }



    private void getImageTypeFromDict() {
        pictureTypeDataLists.clear();
        if(dictInfosByKey != null && dictInfosByKey.size() > 0){
            DictInfo dictInfo = null;
            PictureTypePo picType = null;
            for(int i = 0, size = dictInfosByKey.size(); i < size ; i ++){
                dictInfo = dictInfosByKey.get(i);
                final String imageTypeCode = dictInfo.getTypeCode();
                final String seedKey = dictInfo.getSeedKey();
                if("03".equals(imageTypeCode)||"04".equals(imageTypeCode)||"05".equals(imageTypeCode)){

                }else{
                    picType = new PictureTypePo();
                    picType.setPictureTypeCode(imageTypeCode);//取大类
                    picType.setPictureTypeName(dictInfo.getTypeName());
                    int doneCount = PhotoManager.getInstance().getDistinctImageListByReportNoAndImageType(mReportNo,imageTypeCode);
                    picType.setTakeDonePictureNum(String.valueOf(doneCount));
                    List<DictInfo> seedDictList = DictManager.getInstance().getDictInfosByKey(seedKey);
                    picType.setTotalPictureNum(String.valueOf(seedDictList.isEmpty()?1:seedDictList.size()));
                    picType.setSeedKey(seedKey);
                    pictureTypeDataLists.add(picType);
                }
            }
            if (pictureTypeDataLists != null && pictureTypeDataLists.size() > 0) {
                List<PictureTypePo> tempList = getReportCarPropertyInjuerInfo();
                if(tempList != null && tempList.size() > 0){
                    pictureTypeDataLists.addAll(2,tempList);
                }
            }
        }
    }

    private void initImageTypeData(){
        imageTypeCodeArray = new String[]{
                PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE,
                PhotoClaimUtil.PHOTO_TYPE_SURVEY_CODE,
                PhotoClaimUtil.PHOTO_TYPE_EVAL_BD_CODE,
                PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE,
                PhotoClaimUtil.PHOTO_TYPE_POPLOSS_CODE,
                PhotoClaimUtil.PHOTO_TYPE_PAYMENT_CODE,
                PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_CODE,
                PhotoClaimUtil.PHOTO_TYPE_NATURAL_DISASTER_CODE,
                PhotoClaimUtil.PHOTO_TYPE_FIRE_CODE,
                PhotoClaimUtil.PHOTO_TYPE_OTHER_CODE
        };
        imageTypeNameArray = new String[]{
                PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_NAME,
                PhotoClaimUtil.PHOTO_TYPE_SURVEY_NAME,
                PhotoClaimUtil.PHOTO_TYPE_EVAL_BD_NAME,
                PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_NAME,
                PhotoClaimUtil.PHOTO_TYPE_POPLOSS_NAME,
                PhotoClaimUtil.PHOTO_TYPE_PAYMENT_NAME,
                PhotoClaimUtil.PHOTO_TYPE_STEAL_ROB_NAME,
                PhotoClaimUtil.PHOTO_TYPE_NATURAL_DISASTER_NAME,
                PhotoClaimUtil.PHOTO_TYPE_FIRE_NAME,
                PhotoClaimUtil.PHOTO_TYPE_OTHER_NAME
        };
        imageTypeCountArray = new int[]{
                PictureTypeStaticCode.imageGeneralDocumentsTypeList.size(),
                PictureTypeStaticCode.iamgeSurveyList.size(),
                PictureTypeStaticCode.iamgeEvalBDList.size(),
                PictureTypeStaticCode.iamgeEvalSZList.size(),
                PictureTypeStaticCode.iamgePopLossList.size(),
                PictureTypeStaticCode.iamgePaymentList.size(),
                PictureTypeStaticCode.imageStealRobList.size(),
                PictureTypeStaticCode.imageNaturalDisastersList.size(),
                PictureTypeStaticCode.imageFireTypeList.size(),
                PictureTypeStaticCode.imageOtherList.size()
        };
    }

    private void setPictureTypeDataLists(){
        initImageTypeData();
        pictureTypeDataLists =  new ArrayList<>();
        PictureTypePo picType = null;
        for(int i = 0; i< imageTypeCodeArray.length;i++){
            picType = new PictureTypePo();
            String imageTypeCode = imageTypeCodeArray[i];
            //非三者类型
            if(!PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE.equals(imageTypeCode)){
                picType.setPictureTypeCode(imageTypeCode);//取大类
                picType.setPictureTypeName(imageTypeNameArray[i]);
                int doneCount = PhotoManager.getInstance().getDistinctImageListByReportNoAndImageType(mReportNo, imageTypeCode);
                picType.setTakeDonePictureNum(String.valueOf(doneCount));
                picType.setTotalPictureNum(String.valueOf(imageTypeCountArray[i]));
                pictureTypeDataLists.add(picType);
            }else{
                if (thirdCarList != null && thirdCarList.size() > 0) {
                    TaskInfo taskInfo = null;
                    for (int j = 0; j < thirdCarList.size(); j++) {
                        taskInfo = thirdCarList.get(j);
                        picType.setPictureTypeCode(imageTypeCode);//取大类
                        picType.setPictureTypeName(imageTypeNameArray[i]);
                        int doneCount = PhotoManager.getInstance().getDistinctImageListByReportNoAndImageType(mReportNo, imageTypeCode);
                        picType.setTakeDonePictureNum(String.valueOf(doneCount));
                        picType.setTotalPictureNum(String.valueOf(imageTypeCountArray[i]));
                        picType.setLossNo(taskInfo.getFlowId());
                        if(taskInfo.getFlowId()!=null&&!"".equals(taskInfo.getFlowId())){
                            picType.setSignName(taskInfo.getPlateNo());
                        }
                        if(taskInfo.getPlateNo()!=null&&!"".equals(taskInfo.getPlateNo())){
                            picType.setSignName(taskInfo.getPlateNo());
                        }else{
                            picType.setSignName("车辆信息暂无");
                        }
                        pictureTypeDataLists.add(picType);
                    }
                }
            }

        }
    }



   //获取涉案车辆 物损 人伤信息，动态生成字典表
    private List<PictureTypePo> getReportCarPropertyInjuerInfo(){
        List<PictureTypePo> tempList = new ArrayList<>();
        PictureTypePo picType = null;
//        List<ReportCar> allSurveyCar = ReportCarManager.getInstance().getAllSurveyCar(reportNo);
        if(allSurveyCar != null && allSurveyCar.size() > 0){
            for(ReportCar reportCar : allSurveyCar){
                picType = new PictureTypePo();
                String carTypeCode = reportCar.getCarTypeCode();
                String plateNo = reportCar.getPlateNo();
                if("1".equals(carTypeCode)){
                    //标的
                    picType.setTaskType(PhotoClaimUtil.TASK_TYPE_EVAL_BD);
                    picType.setPictureTypeName("标的车:"+plateNo);
                }else{
                    //三者
                    picType.setTaskType(PhotoClaimUtil.TASK_TYPE_EVAL_SZ);
                    picType.setPictureTypeName("三者车:"+plateNo);
                }
                String imageTypeCode = PhotoClaimUtil.PHOTO_TYPE_EVAL_TYPE_CODE;
                String seedKey = PhotoClaimUtil.PHOTO_TYPE_EVAL_SEED_KEY;
                picType.setPictureTypeCode(imageTypeCode);//取大类
                int doneCount = PhotoManager.getInstance().getDistinctImageListByReportNoAndImageTypeAndSignName(mReportNo,imageTypeCode,plateNo);
                picType.setTakeDonePictureNum(String.valueOf(doneCount));
                List<DictInfo> seedDictList = DictManager.getInstance().getDictInfosByKey(seedKey);
                picType.setTotalPictureNum(String.valueOf(seedDictList.isEmpty()?1:seedDictList.size()));
                picType.setSeedKey(seedKey);
                picType.setSignName(plateNo);

                tempList.add(picType);
            }
        }

//        List<SurveyProperty> surveyPropertyList = SurveyPropertyManager.getInstance().getSurveyPropertyList(mReportNo);
        if(surveyPropertyList != null && surveyPropertyList.size() > 0){
            for(SurveyProperty surveyProperty : surveyPropertyList) {
                picType = new PictureTypePo();
                String lossName = surveyProperty.getLossName();
                picType.setTaskType(PhotoClaimUtil.TASK_TYPE_POP);
                picType.setPictureTypeName("物损:" + lossName);
                String imageTypeCode = PhotoClaimUtil.PHOTO_TYPE_PROPERTY_TYPE_CODE;
                String seedKey = PhotoClaimUtil.PHOTO_TYPE_PROPERTY_SEED_KEY;
                picType.setPictureTypeCode(imageTypeCode);//取大类
                int doneCount = PhotoManager.getInstance().getDistinctImageListByReportNoAndImageTypeAndSignName(mReportNo, imageTypeCode,lossName);
                picType.setTakeDonePictureNum(String.valueOf(doneCount));
                List<DictInfo> seedDictList = DictManager.getInstance().getDictInfosByKey(seedKey);
                picType.setTotalPictureNum(String.valueOf(seedDictList.isEmpty() ? 1 : seedDictList.size()));
                picType.setSeedKey(seedKey);
                picType.setSignName(lossName);

                tempList.add(picType);
            }
        }

//        List<SurveyInjury> surveyInjuryList = SurveyInjuryManager.getInstance().getSurveyInjuryList(mReportNo);
        if(surveyInjuryList != null && surveyInjuryList.size() > 0){
            for(SurveyInjury surveyInjury : surveyInjuryList){
                picType = new PictureTypePo();
                String injuryId = String.valueOf(surveyInjury.getSerialNo());
                String injuryName = surveyInjury.getInjuryName();
                picType.setTaskType(PhotoClaimUtil.TASK_TYPE_INJURY);
                picType.setPictureTypeName("人伤:" + injuryName);
                String imageTypeCode = PhotoClaimUtil.PHOTO_TYPE_INJURY_TYPE_CODE;
                String seedKey = PhotoClaimUtil.PHOTO_TYPE_INJURY_SEED_KEY;
                picType.setPictureTypeCode(imageTypeCode);//取大类
                int doneCount = PhotoManager.getInstance().getDistinctImageListByReportNoAndImageTypeAndSignName(mReportNo, imageTypeCode,injuryName);
                picType.setTakeDonePictureNum(String.valueOf(doneCount));
                List<DictInfo> seedDictList = DictManager.getInstance().getDictInfosByKey(seedKey);
                picType.setTotalPictureNum(String.valueOf(seedDictList.isEmpty() ? 1 : seedDictList.size()));
                picType.setSeedKey(seedKey);
                picType.setSignId(injuryId);
                picType.setSignName(injuryName);

                tempList.add(picType);
            }
        }

        return tempList;

    }

    @Override
    public <T> void deleteData(T data, int dataFlag) {
        //1驾驶员信息 2三者车信息 3人伤信息 4物损信息
        switch (dataFlag) {
        }
    }

    private void showPopWindow(final PhotoInfo photo, final float ratio) {
        View popwindowLayout = LayoutInflater.from(mContext).inflate(R.layout.photo_popwindow_layout, null, false);
        bindPopWindow(popwindowLayout, photo);
        popupWindow = PopupWindowUtil.getInitince(getWindow()).initPopuptWindowNoCancelBtn(binding.getRoot(), popwindowLayout);
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void setWindowAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    private void bindPopWindow(View popView,final PhotoInfo photo) {
        TextView fromCameraTv = (TextView) popView.findViewById(R.id.photo_pop_txt_camera);
        TextView fromLocalTv = (TextView) popView.findViewById(R.id.photo_pop_txt_local);
        TextView cancleTv = (TextView) popView.findViewById(R.id.dialog_button);
        fromCameraTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CameraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("reportCode",photo.getReportCode());
//                bundle.putString("flowId",photo.getFlowId());
                bundle.putString("taskType",photo.getTaskType());
                intent.putExtras(bundle);
                startActivity(intent);
                cancle();
            }
        });
        fromLocalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("reportCode",photo.getReportCode());
//                bundle.putString("flowId",photo.getFlowId());
                bundle.putString("taskType",photo.getTaskType());
                startActivity(PhotoLocalChooseActivity.class,bundle);
//                PhotoManageFragment.this.openFragment(PhotoManageFragment.this,PhotoLocalChooseFragment.class, bundle);
                cancle();
            }
        });
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });
    }
    private void cancle() {
        if (popupWindow != null && popupWindow.isShowing()) {
            setWindowAlpha(1.0f);
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    public boolean checkImages(){
        if(surveyPhotoListFromDB!=null&&surveyPhotoListFromDB.size()>0){
        }else{
            upLoadDialogMessage = "缺少查勘照，是否继续上传？";
            return false;
        }

        if(evalPhotoListFromDB!=null&&evalPhotoListFromDB.size()>0){
        }else{
            upLoadDialogMessage = "缺少标的定损照，是否继续上传？";
            return false;
        }
        if(thirdEvalPhotoListFromDB!=null&&thirdEvalPhotoListFromDB.size()>0){
        }else{
            upLoadDialogMessage = "缺少三者定损照，是否继续上传？";
            return false;
        }

        return true;
    }

    private void uploadTest() {

        PhotoInfo info;
        info = new PhotoInfo();
        info.setReportCode(mReportNo);
        //所有未上传的照片
        allUploadImagesFromDB = PhotoManager.getInstance().getPhotoListByReportCodeAndUpload(info);
        if (allUploadImagesFromDB != null && allUploadImagesFromDB.size() > 0) {
            for (PhotoInfo i : allUploadImagesFromDB) {
                i.setImageUpload("1");
                PhotoManager.getInstance().updatePhotoInfo(i);
            }
        }
        showAllPhoto();
    }
    private void upload() {

        PhotoInfo info ;
        info = new PhotoInfo();
        info.setReportCode(mReportNo);
        //所有未上传的照片
        allUploadImagesFromDB = PhotoManager.getInstance().getPhotoListByReportCodeAndUpload(info);
        List<File> upLoadImageList = new ArrayList<File>();
        if(allUploadImagesFromDB!=null&&allUploadImagesFromDB.size()>0){
            for (PhotoInfo i:allUploadImagesFromDB) {
                //假修改
                i.setImageUpload("1");
                PhotoManager.getInstance().updatePhotoInfo(i);
//                File f = new File(i.getImagePath());
//                upLoadImageList.add(f);
            }
        }

        final File file = new File(mUploadFilePathName);

    }


    private void hideInputMethod(){
        View view = getCurrentFocus();
        if(view != null){
            ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * show or hide progress bar
     */
    private void showProgressBar(boolean show){
        View view = findViewById(R.id.photo_upload_progress_layout);
        if(show){
            binding.buttonSubmit.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
            ProgressBar uploadProgress = (ProgressBar)view.findViewById(R.id.progressBar);
            TextView uploadProgressText = (TextView)view.findViewById(R.id.photo_upload_progress_text);
//			uploadProgress.setProgress(0);
//			View bar = view.findViewById(R.id.photo_upload_progress_bar);
//			TextView text = (TextView)view.findViewById(R.id.photo_upload_progress_text);
//			TableLayout.LayoutParams params = new TableLayout.LayoutParams();
//			text.setText(textContent);
//			params.height = TableLayout.LayoutParams.FILL_PARENT;
//			params.width = 3;
//			bar.setLayoutParams(params);
        }else{
            binding.buttonSubmit.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
            binding.progressBar.setProgress(0);
            binding.photoUploadProgressText.setText("照片上传中,上传进度：");
        }
    }
}
