package com.hl.contract.business.main.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.chenzg.mylibrary.OCRUtil;
import com.hl.contract.R;
import com.hl.contract.bean.StaticCode;
import com.hl.contract.databinding.SurveyActivityQueryContractBinding;
import com.hl.contract.business.main.adapter.PopupWindowAdapter;
import com.hl.contract.business.main.service.dto.PayDTO;
import com.hl.contract.business.main.viewmodel.CreateContractVM;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.table.manager.ContractManager;
import com.hl.contract.table.manager.ReportCarManager;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;
import com.hl.core.lib.bean.TypeItem;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.PopupWindowResponseUtil;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.core.lib.util.common.VinUtil;
import com.hl.core.lib.util.common.dialog.DialogUtil;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.ui.view.ImageBrowseActivity;
import com.jock.pickerview.view.TimePickerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import static com.hl.contract.util.ViewUtils.checkAllTextView;

/**
 * @Describe:  查询结果展示页面
 * @Package: com.hl.contract.business.survey.main.view
 * @Author: liyu
 * @Date: 2018/3/21/ 14:23
 * @Copyright: hongling
 */


public class QueryContractQueryActivity extends BaseActivity<TitleBar> implements PopupWindowResponseUtil.PopupWindowResponseCallBack, OCRUtil.OcrCallback {

    @ViewModel
    private CreateContractVM createContractVM;
    private TimePickerView timePickerView;
    private Contract mContract; //信息
    private boolean queryFlag;
    private SurveyActivityQueryContractBinding binding;
//    private String mId; // id有值代表是查看涉案车辆列表项，没有代表是新增涉案车辆
    private String mReportNo;
    private String mTaskNO;
    private boolean isAddThirdCard; // 是否新增三者车
    private String additionalFlag; // 是否新增
    private List<TypeItem> usePropertyDictInfos;//车辆用途
    private List<TypeItem> useRelationDictInfos;//与车主关系
    private List<TypeItem> driverDictInfos;//证件类型字典
    private List<TypeItem> serviceDriverDictInfos;//证件类型字典
    private List<TypeItem> licensePlateTypeDictList;//号牌种类字典表
    private String uuid;
    private int serialNo;
    private PopupWindow mPopupWindow;

    private PopupWindow popupWindow; // popwindow
    private PopupWindowAdapter recyclerViewAdapter; // popwindow adapter

    @Override
    protected Object entryInterceptor(Intent intent) {

        mContract = (Contract) intent.getSerializableExtra("Contract");
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = "合同查询";
        titleBar.rightBtnText = getResources().getString(R.string.survey_take_pricture_upload);
        titleBar.showRightBtn = true;
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_activity_query_contract,null,false);
        binding  = DataBindingUtil.bind(bindView);
        return bindView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        createContractVM = new CreateContractVM(mContext);
        initInfo();
        setClick();
        initPickerTimeView();


    }

    @Override
    protected void onResume() {
        super.onResume();
//        showLossPartList();
    }


    private void setClick() {
        CreateContractClick createContractClick = new CreateContractClick();
        binding.setCreateContractClick(createContractClick);
        binding.bottomLayout.findViewById(R.id.sure_save_tv).setOnClickListener(v->{
            createContractClick.saveSurveyThirdCar();
        });
        binding.getRoot().getRootView().findViewById(R.id.title_right_btn).setOnClickListener(v-> {
            createContractClick.uploadPic();
        });
    }

    private void initInfo() {
        if(mContract!=null&&"0".equals(mContract.getExemptFlag())){
            createContractVM.goPay(true,mContract.getReportCode()).observeOnce(QueryContractQueryActivity.this, this::showPic);
        }
        bindReport(mContract);

    }
    private void showPic(@Nullable PayDTO payDTO) {
        if(payDTO!=null&&!"".equals(payDTO.getBase64())){
            byte[] bytes = Base64.decode(payDTO.getBase64(), Base64.DEFAULT);
            binding.paymentPic.setVisibility(View.VISIBLE);
            binding.paymentPic.setScaleType(ImageView.ScaleType.FIT_XY);
            binding.paymentPic.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
//            ContractManager.getInstance().saveSurveyCar(mContract);
//            UtilManager.Toast.show(getBaseContext(),"数据保存成功");
//
//            Bundle bundle = new Bundle();
//            bundle.putString(SurveyClaimUtil.IMAGE_NAME,payDTO.getBase64());
//            bundle.putString(SurveyClaimUtil.REPORT_NO,payDTO.getContractNo());
//            bundle.putString(SurveyClaimUtil.SIGN_NAME,payDTO.getOrder_price());
//            startActivity(PaymentActivity.class,bundle);
        }else{
            UtilManager.Toast.show(getBaseContext(),"数据保存失败");
        }
    }

    /**
     * 绑定报案信息 数据
     */
    private void bindReport(Contract contract) {

        binding.setContract(contract);


        //性别
        String ownerSex = mContract.getOwnerSex();
        if (!TextUtils.isEmpty(ownerSex)) {
            if (ownerSex.equals("1")) {
                binding.ownerSexTv.setText("男");

            } else if (ownerSex.equals("0")) {
                binding.ownerSexTv.setText("女");
            } else{
                binding.ownerSexTv.setText("男");//默认男
            }
        }else{
            binding.ownerSexTv.setText("男");//默认男
            mContract.setOwnerSex("1");
        }
        //证件类型
        if(TextUtils.isEmpty(mContract.getOwnerCertificateType())){
            String certificateTypeCode = mContract.getOwnerCertificateTypeCode();
            String certificateTypeName = null;
            if (TextUtils.isEmpty(certificateTypeCode)) {
                if (driverDictInfos == null) {
                    driverDictInfos = StaticCode.getInstance().getCertificateTypeList();
                    if (driverDictInfos != null && driverDictInfos.size() > 0) {
                        TypeItem typeItem = driverDictInfos.get(0);
                        if(typeItem!=null){
                            certificateTypeCode = typeItem.getID();
                            certificateTypeName = typeItem.getValue();
                        }
                    }
                }
            }else{
                if (driverDictInfos == null) {
                    driverDictInfos = StaticCode.getInstance().getCertificateTypeList();
                    certificateTypeName = StaticCode.getInstance().getValueByCode(driverDictInfos, certificateTypeCode);
                }
            }
            binding.ownerCertificateTypeSelectTv.setText(certificateTypeName);
            mContract.setOwnerCertificateType(certificateTypeName);
            mContract.setOwnerCertificateTypeCode(certificateTypeCode);
        } else {
            binding.ownerCertificateTypeSelectTv.setText(mContract.getOwnerCertificateType());//证件类型
        }


        //车辆用途
        if(TextUtils.isEmpty(mContract.getUsePropertyName())){
            String usePropertyCode = mContract.getUsePropertyCode();
            String usePropertyName = null;
            if (TextUtils.isEmpty(usePropertyCode)) {
                if (usePropertyDictInfos == null) {
                    usePropertyDictInfos = StaticCode.getInstance().getUsePropertyTypeList();
                    if (usePropertyDictInfos != null && usePropertyDictInfos.size() > 0) {
                        TypeItem typeItem = usePropertyDictInfos.get(0);
                        if(typeItem!=null){
                            usePropertyCode = typeItem.getID();
                            usePropertyName = typeItem.getValue();
                        }
                    }
                }
            }else{
                if (usePropertyDictInfos == null) {
                    usePropertyDictInfos = StaticCode.getInstance().getUsePropertyTypeList();
                    usePropertyName = StaticCode.getInstance().getValueByCode(usePropertyDictInfos, usePropertyCode);
                }
            }
            binding.usePropertySelectTv.setText(usePropertyName);
            mContract.setUsePropertyName(usePropertyName);
            mContract.setUsePropertyCode(usePropertyCode);
        } else {
            binding.usePropertySelectTv.setText(mContract.getUsePropertyName());//证件类型
        }
        //是否投保商业险
        String isInsureCommercial = mContract.getIsInsureCommercial();
        if (!TextUtils.isEmpty(isInsureCommercial)) {
            if (isInsureCommercial.equals("1")) {
                binding.isInsureCommercialTv.setText("是");

            } else if (isInsureCommercial.equals("0")) {
                binding.isInsureCommercialTv.setText("否");
            } else{
                binding.isInsureCommercialTv.setText("是");//默是
            }
        }else{
            binding.isInsureCommercialTv.setText("是");//默是
            mContract.setIsInsureCommercial("1");
        }

        //与车主关系
        if(TextUtils.isEmpty(mContract.getRelation())){
            String relationCode = mContract.getRelationCode();
            String relation = null;
            if (TextUtils.isEmpty(relationCode)) {
                if (useRelationDictInfos == null) {
                    useRelationDictInfos = StaticCode.getInstance().getRelationTypeList();
                    if (useRelationDictInfos != null && useRelationDictInfos.size() > 0) {
                        TypeItem typeItem = useRelationDictInfos.get(0);
                        if(typeItem!=null){
                            relationCode = typeItem.getID();
                            relation = typeItem.getValue();
                        }
                    }
                }
            }else{
                if (useRelationDictInfos == null) {
                    useRelationDictInfos = StaticCode.getInstance().getRelationTypeList();
                    relation = StaticCode.getInstance().getValueByCode(useRelationDictInfos, relationCode);
                }
            }
            binding.relationSelectTv.setText(relation);
            mContract.setRelation(relation);
            mContract.setRelationCode(relationCode);
        } else {
            binding.relationSelectTv.setText(mContract.getRelation());
        }

        //性别
        String serviceSex = mContract.getServiceSex();
        if (!TextUtils.isEmpty(ownerSex)) {
            if (ownerSex.equals("1")) {
                binding.serviceNameTv.setText("男");

            } else if (ownerSex.equals("0")) {
                binding.serviceNameTv.setText("女");
            } else{
                binding.serviceNameTv.setText("男");//默认男
            }
        }else{
            binding.serviceNameTv.setText("男");//默认男
            mContract.setOwnerSex("1");
        }
        //证件类型
        if(TextUtils.isEmpty(mContract.getServiceCertificateType())){
            String certificateTypeCode = mContract.getServiceCertificateTypeCode();
            String certificateTypeName = null;
            if (TextUtils.isEmpty(certificateTypeCode)) {
                if (serviceDriverDictInfos == null) {
                    serviceDriverDictInfos = StaticCode.getInstance().getCertificateTypeList();
                    if (serviceDriverDictInfos != null && serviceDriverDictInfos.size() > 0) {
                        TypeItem typeItem = serviceDriverDictInfos.get(0);
                        if(typeItem!=null){
                            certificateTypeCode = typeItem.getID();
                            certificateTypeName = typeItem.getValue();
                        }
                    }
                }
            }else{
                if (serviceDriverDictInfos == null) {
                    serviceDriverDictInfos = StaticCode.getInstance().getCertificateTypeList();
                    certificateTypeName = StaticCode.getInstance().getValueByCode(serviceDriverDictInfos, certificateTypeCode);
                }
            }
            binding.serviceCertificateTypeSelectTv.setText(certificateTypeName);
            mContract.setServiceCertificateType(certificateTypeName);
            mContract.setServiceCertificateTypeCode(certificateTypeCode);
        } else {
            binding.serviceCertificateTypeSelectTv.setText(mContract.getServiceCertificateType());//证件类型
        }


    }
    @Override
    protected void msgCallBack(CoreMessage msg) {
        super.msgCallBack(msg);
        if(msg.msgCode == CoreMessage.CREATE_CONTRACT){
            if(!TextUtils.isEmpty(msg.message)){
                UtilManager.Toast.show(this,msg.message);
            }
        }
    }

    @Override
    public void ocrRespone(int requestCode, String responseString) {
        switch (requestCode) {
            case OCRUtil.TAKE_XINGSHI_NO_ALL:
                parseXingShiJsonString(responseString);
                break;
            case OCRUtil.TAKE_SENFEN_NO_ALL:
                parseShenFenJsonString(responseString);
                break;
            case OCRUtil.TAKE_JIASHI_NO_ALL:
                parseDriveJsonString(responseString);
                break;
            default:
                break;
        }
    }


    public class CreateContractClick{


        /**
         * 服务起始时间
         */
        public void serviceStartDateSelected() {
            timePickerView.show();
            timePickerView.setOnTimeSelectListener(onTimeSelectListener, binding.serviceStartDateSelectTv);
        }
        /**
         * 服务起始时间
         */
        public void serviceEndDateSelected() {
            timePickerView.show();
            timePickerView.setOnTimeSelectListener(onTimeSelectListener, binding.serviceEndDateSelectTv);
        }
        /**
         * 产品服务：精英
         */
        public void setProductJingying() {
            mContract.setProductCode("1");
            mContract.setProductName("精英无忧服务");
        }

        /**
         * 产品服务：高枕
         */
        public void setProductGaozhen() {
            mContract.setProductCode("2");
            mContract.setProductName("高枕无忧服务");
        }

        /**
         * 号牌类型设置
         */
        public void setPlateType() {
            if(licensePlateTypeDictList == null){
                licensePlateTypeDictList = StaticCode.getInstance().getPlateTypeList();
            }
            showPopWindow(getResources().getString(R.string.survey_text_lisence_type), licensePlateTypeDictList, "2");
        }

        /**
         * owner识别
         */
        public void ownerOcr() {
            OCRUtil.takePhoto(QueryContractQueryActivity.this, OCRUtil.TAKE_SENFEN_NO_ALL);
        }

        /**
         * service识别
         */
        public void serviceOcr() {
            OCRUtil.takePhoto(QueryContractQueryActivity.this, OCRUtil.TAKE_SENFEN_NO_ALL);
        }

        /**
         * Vin识别
         */
        public void vinNoOcr() {
            OCRUtil.takePhoto(QueryContractQueryActivity.this, OCRUtil.TAKE_XINGSHI_NO_ALL);
        }

        public void uploadPic(){
            Bundle bundle = new Bundle();
            bundle.putString(PhotoClaimUtil.REPORT_NO,mContract.getReportCode());
            bundle.putString(PhotoClaimUtil.IMAGE_TYPE,"1");
            bundle.putString(PhotoClaimUtil.IMAGE_SUB_TYPE,"2");
            startActivity(ImageBrowseActivity.class,bundle);
        }

        /**
         * 保存数据
         */
        public void saveSurveyThirdCar() {

            int checkedCode = checkData();
            if (checkedCode == 0) {
                ContractManager.getInstance().saveSurveyCar(mContract);
                mContract.setAdditionalFlag("0");//保存，但未选择服务
                ContractManager.getInstance().saveSurveyCar(mContract);
                UtilManager.Toast.show(getBaseContext(),"数据保存成功");

                Bundle bundle = new Bundle();
                bundle.putString(SurveyClaimUtil.REPORT_NO, mContract.getReportCode());
                bundle.putString(SurveyClaimUtil.ITEM_ID, "");
                bundle.putSerializable("Contract", mContract);
                startActivity(ContractServiceActivity.class,bundle);

                onBackPressed();
                hideSoftInput();

//                createContractVM.addContractForService(true,mContract).observeOnce(CreateContractActivity.this, this::addContract);

            } else if(checkedCode == -1) {   //没有通过非空校验
                binding.errorTipTv.setVisibility(View.VISIBLE);
                Animation animSet = AnimationUtils.loadAnimation(QueryContractQueryActivity.this, R.anim.survey_fade_down_up_anim);
                binding.errorTipTv.setAnimation(animSet);
                animSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        binding.errorTipTv.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }
        private void addContract(@Nullable Boolean saveFlag) {
            if(saveFlag){
                mContract.setAdditionalFlag("0");//保存，但未选择服务
                ContractManager.getInstance().saveSurveyCar(mContract);
                UtilManager.Toast.show(getBaseContext(),"数据保存成功");

                Bundle bundle = new Bundle();
                bundle.putString(SurveyClaimUtil.REPORT_NO, mContract.getReportCode());
                bundle.putString(SurveyClaimUtil.ITEM_ID, "");
                bundle.putSerializable("Contract", mContract);
                startActivity(ContractServiceActivity.class,bundle);

                onBackPressed();
                hideSoftInput();
            }else{
                UtilManager.Toast.show(getBaseContext(),"数据保存失败");
            }
        }

        /**
         * 校验必填项
         *
         *      返回 0 表示校验通过，返回 1表示非空校验通过，但是有录入项不符合规则，返回-1表示检验非空失败
         */
        private int  checkData() {


            //第一步校验为空的必填项(所有需要做非空检车的View)
            boolean isChecked = checkViews(getBaseContext(),binding.ownerNameEt,
                    binding.ownerCertificateTypeSelectTv,
                    binding.ownerCertificateNoEt,
                    binding.ownerTelephoneEt,
                    binding.regionEt,
                    binding.plateNoEt,
                    binding.brandNameEt,
                    binding.familyNameEt,
                    binding.modelNameEt,
                    binding.buyCarDateSelectTv,
                    binding.usePropertySelectTv,
                    binding.batteryNumberEt,
                    binding.thirdVinNoEt,
                    binding.relationSelectTv,
                    binding.serviceNameEt,
                    binding.serviceCertificateTypeSelectTv,
                    binding.serviceCertificateNoEt,
                    binding.serviceTelephoneEt
                    );
            if(isChecked){

                //车牌号
//                if(!TextUtils.isEmpty(mContract.getPlateNo())){
//                    //校验车牌号
//                    if( !ValidateUtil.isPlateNo(mContract.getPlateNo())){
//                        UtilManager.Toast.show(getBaseContext(),"车牌号不正确");
//                        return 1;
//                    }
//                }
                //车架号
                if(!TextUtils.isEmpty(mContract.getVinNo())){
                    //校验VIN
                    if(!TextUtils.isEmpty(VinUtil.isVin(mContract.getVinNo()))){
                        UtilManager.Toast.show(getBaseContext(),"车架号不正确");
                        return 1;
                    }
                }

//                //证件号码
//                if(!TextUtils.isEmpty(mContract.getCertificateNo())){
//                    //   (当类型为身份证的时候校验，其他不校验)
//                    if(!TextUtils.isEmpty(mContract.getCertificateTypeCode())&& mContract.getCertificateType().contains(getResources().getString(R.string.survey_cardtype_8020001))){
//                        //身份证
//                        if(!IDCardUtil.isIDCard(mContract.getCertificateNo())){
//                            UtilManager.Toast.show(getBaseContext(),"请填写正确的身份证号码");
//                            return 1;
//                        }
//                    }
//                }

            }else{
                return -1;
            }

            //涉案车辆

            return 0;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case OCRUtil.UP_PICTURE:
                    OCRUtil.uploadPic(mContext, this, mContract.getReportCode());
                    break;

            }
        }
    }


//    /**
//     * 搜索品牌
//     */
//    private void searchBrandListByBrandName() {
//        String brandNameString = binding.brandNameEt.getText().toString();
//        if (!TextUtils.isEmpty(brandNameString)) {
//            hideSoftInput();
//            createContractVM.getBrandListByBrandName(brandNameString);
//        } else {
//            UtilManager.Toast.show(mContext, "品牌至少输入一个字符");
//        }
//    }
//
//    /**
//     * 搜索车系
//     */
//    private void searchSeriesList() {
//        String serialName = binding.familyNameEt.getText().toString();
//        if (!TextUtils.isEmpty(serialName)) {
//            hideSoftInput();
//            createContractVM.getSeriesListBySeriesName(serialName);
//        } else {
//            UtilManager.Toast.show(mContext, "车系至少输入一个字符");
//        }
//    }

    private boolean checkViews(Context context, TextView... views){
        boolean checkFlag =
                checkAllTextView(context,views);
        return checkFlag;
    }
    private void parseShenFenJsonString (String jsonString){
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            //{"ResponseCode":"0000","ResponseMessage":"请求成功","UserName":"摊振毅","Sex":"男","Nation":"汉","Birthday":"1987-09-23","Address":"山东省荣成市唐头镇家村968","IdCardNo":"371082198709230731"}
            if(jsonObject!=null){
                String responseCode = jsonObject.getString("ResponseCode");
                String responseMessage = jsonObject.getString("ResponseMessage");
                if (responseCode.equals("0000")) {
//                    if(isOwnerOcr){
//                        binding.ownerNameEt.setText(jsonObject.getString("UserName"));
//                        if("男".equals(jsonObject.getString("Sex"))){
//                            binding.ownerSexMale.setChecked(true);
//                        }else  if("男".equals(jsonObject.getString("Sex"))){
//                            binding.ownerSexFemale.setChecked(true);
//                        }else {
//                            binding.ownerSexMale.setChecked(true);
//                        }
//                        binding.ownerCertificateNoEt.setText(jsonObject.getString("IdCardNo"));
//                    }else{
//                        binding.serviceNameEt.setText(jsonObject.getString("UserName"));
//                        if("男".equals(jsonObject.getString("Sex"))){
//                            binding.serviceSexMale.setChecked(true);
//                        }else  if("男".equals(jsonObject.getString("Sex"))){
//                            binding.serviceSexFemale.setChecked(true);
//                        }else {
//                            binding.serviceSexMale.setChecked(true);
//                        }
//                        binding.serviceCertificateNoEt.setText(jsonObject.getString("IdCardNo"));
//                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void parseXingShiJsonString (String jsonString){
        try {
//            {"ResponseCode":"0000","ResponseMessage":"请求成功","Address":"北京西城区西直门外大137号","PlateNo":"京PMV727","VehicleType":"小型普通客车","CustomerName":"刘胜利","UseType":"非营运","VehicleModels":"奥德赛HG7240","VIN":"LSVCH49F262182589","EngineNo":"BFF079938","RegisterDate":"2010-08-16","IssueDate":"2010-08-16"}
            JSONObject jsonObject = new JSONObject(jsonString);
            String responseCode = jsonObject.getString("ResponseCode");
            String responseMessage = jsonObject.getString("ResponseMessage");
            if (responseCode.equals("0000")) {
                binding.plateNoEt.setText(jsonObject.getString("PlateNo"));
                binding.thirdVinNoEt.setText(jsonObject.getString("VIN"));
                binding.thirdEngineNoEt.setText(jsonObject.getString("EngineNo"));
                binding.modelNameEt.setText(jsonObject.getString("VehicleModels"));
                binding.buyCarDateSelectTv.setText(jsonObject.getString("RegisterDate"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void parseDriveJsonString (String jsonString){
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            String responseCode = jsonObject.getString("ResponseCode");
//            String responseMessage = jsonObject.getString("ResponseMessage");
//            if (responseCode.equals("0000")) {
//                binding.driverNameNameEt.setText(jsonObject.getString("UserName"));
//                binding.driverLicenseNumberEt.setText(jsonObject.getString("CardNo"));
//                binding.driverLicenseNumberEt.setTransformationMethod(IDCardUtil.inputLowerToUpper);//小写变大写
//                int index = StaticCode.getInstance().getIndexByUnsureValue(StaticCode.getInstance().getDriverTypeList(),jsonObject.getString("DrivingType"));
//                String value = StaticCode.getInstance().getValueByIndex(StaticCode.getInstance().getDriverTypeList(),index);
//                binding.driverDriveTypeSelectTv.setText(value);
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    //初始化 时间选择器
    private void initPickerTimeView() {
        // 时间选择器
        timePickerView = new TimePickerView(QueryContractQueryActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(true);
    }

    //时间选择器回调
    TimePickerView.OnTimeSelectListener onTimeSelectListener =new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, TextView textView) {
            if(textView != null){
                textView.setText(TimestampTool.getStrDate(TimestampTool.date2String(date)));
            }
        }
    };

    private void showPopWindow(String title, final List<TypeItem> popList, final String flag) {
        View popwindowLayout = LayoutInflater.from(this).inflate(R.layout.survey_popwindow_layout, null, false);
        PopupWindowResponseUtil.bindPopWindow(this, popwindowLayout, title, R.id.dialog_tilte,R.id.recycle_view, R.id.cancle_tv,popList, flag,null,this );
        mPopupWindow = PopupWindowUtil.getInitince(getWindow()).initPopuptWindowNoCancelBtn(binding.getRoot(), popwindowLayout);
    }
    private void cancle() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    @Override
    public void popupWindowCancle() {
        cancle();
    }

    @Override
    public void popupWindowResponse(TypeItem typeItem, String flag, List<? extends Object> data) {
        if (!TextUtils.isEmpty(flag)) {
            String id = typeItem.getID();
            String value = typeItem.getValue();
            String code = typeItem.getID();

            if (flag.equals("1")) {  //车辆类型
//                binding.thirdCarTypeSelectTv.setText(value);
                mContract.setCarType(value);
                mContract.setCarTypeCode(code);
            } else if (flag.equals("2")) {  //证件类型 车主
                binding.ownerCertificateTypeSelectTv.setText(value);
                mContract.setOwnerCertificateType(value);
                mContract.setOwnerCertificateTypeCode(code);
            } else if (flag.equals("3")) {  //证件类型 服务商
                binding.serviceCertificateTypeSelectTv.setText(value);
                mContract.setServiceCertificateType(value);
                mContract.setServiceCertificateTypeCode(code);
            } else if (flag.equals("4")) {    //车辆种类
//                binding.thirdVehicleTypeTv.setText(value);
                mContract.setVehicleType(value);
                mContract.setVehicleTypeCode(id);
            } else if (flag.equals("5")) {    //准驾车型
//                binding.driverDriveTypeSelectTv.setText(value);
                mContract.setDriveType(value);
                mContract.setDriveTypeCode(code);
            }else if (flag.equals("6")) {    //使用性质
                binding.usePropertySelectTv.setText(value);
                mContract.setUseProperty(value);
                mContract.setUsePropertyCode(code);
            }else if (flag.equals("7")) {    //车主关系
                binding.relationSelectTv.setText(value);
                mContract.setRelation(value);
                mContract.setRelationCode(code);
            }

        }
        cancle();
    }


}
