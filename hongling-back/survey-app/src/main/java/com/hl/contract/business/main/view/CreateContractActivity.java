package com.hl.contract.business.main.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.chenzg.mylibrary.OCRUtil;
import com.hl.contract.R;
import com.hl.contract.bean.StaticCode;
import com.hl.contract.business.main.adapter.PopupWindowAdapter;
import com.hl.contract.business.main.service.dto.CarModelDTO;
import com.hl.contract.business.main.service.dto.DictDTO;
import com.hl.contract.business.main.service.dto.PayDTO;
import com.hl.contract.business.main.viewmodel.CreateContractVM;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.core.FinishActivityManager;
import com.hl.contract.databinding.SurveyActivityCreateContractBinding;
import com.hl.contract.table.manager.ContractManager;
import com.hl.contract.table.manager.ReportCarManager;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;
import com.hl.contract.util.ViewUtils;
import com.hl.core.lib.bean.TypeItem;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.IDCardUtil;
import com.hl.core.lib.util.common.MathUtil;
import com.hl.core.lib.util.common.PopupWindowResponseUtil;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.core.lib.util.common.UUIDUtil;
import com.hl.core.lib.util.common.ValidateUtil;
import com.hl.core.lib.util.common.VinUtil;
import com.hl.core.lib.util.common.dialog.DialogUtil;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;
import com.jock.pickerview.view.TimePickerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hl.contract.util.ViewUtils.checkAllTextView;

/**
 * @Describe: 合同创建页，最主要页面
 * @Package: com.hl.contract.business.survey.main.view
 * @Author: liyu
 * @Date: 2018/3/21/ 14:23
 * @Copyright: hongling
 */


public class CreateContractActivity extends BaseActivity<TitleBar> implements PopupWindowResponseUtil.PopupWindowResponseCallBack, OCRUtil.OcrCallback  {

    @ViewModel
    private CreateContractVM createContractVM;
    private TimePickerView timePickerView;
    private Contract mContract; //信息
    private boolean queryFlag;
    private SurveyActivityCreateContractBinding binding;
    private String mId; // 任务类型
    private String mReportNo;
    private String mTaskNO;
    private boolean isAddThirdCard; // 是否新增三者车
    private String additionalFlag; // 是否新增
    private List<TypeItem> usePropertyDictInfos;//车辆用途
    private List<TypeItem> commercialCompanyInfos;//车辆用途
    private List<TypeItem> useRelationDictInfos;//与车主关系
    private List<TypeItem> driverDictInfos;//证件类型字典
    private List<TypeItem> productInfos;//证件类型字典
    private List<TypeItem> serviceDriverDictInfos;//证件类型字典
    private List<TypeItem> licensePlateTypeDictList;//号牌种类字典表
    private String uuid;
    private int serialNo;
    private PopupWindow mPopupWindow;

    private boolean isOwnerOcr = true;
    public final static String FLAG_BRAND = "Brand"; // 品牌
    public final static String FLAG_SERIAL = "Serial"; // 车系
    private String brandId;
    private String brandCode;
    private String familyId;//车系ID
    private String familyCode;//车系编码
    private PopupWindow popupWindow; // popwindow
    private PopupWindowAdapter recyclerViewAdapter; // popwindow adapter
    private List<TypeItem> mProvinceList = new ArrayList<>(); // 省信息
    private List<TypeItem> mCityList = new ArrayList<>(); // 市信息
    private List<TypeItem> mCountyList = new ArrayList<>(); // 县信息

    private List<TypeItem> mBrandList = new ArrayList<>(); // 品牌列表
    private List<TypeItem> mFamilyList = new ArrayList<>(); // 车系列表
    private List<TypeItem> mVehicleList = new ArrayList<>(); // 车型列表
    private List<TypeItem> serviceValidityTypeDictList;//年限列表


    private boolean hasGotToken = false;
    private AlertDialog.Builder alertDialog;
    private static final int REQUEST_CODE_VEHICLE_LICENSE = 120;
    private static final int REQUEST_CODE_CAMERA = 102;

    @Override
    protected Object entryInterceptor(Intent intent) {

        mContract = (Contract) intent.getSerializableExtra("Contract");
//        if (mContract != null) queryFlag = true;
//
        mId = intent.getStringExtra(SurveyClaimUtil.ITEM_ID);
//
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = "创建合同";
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_activity_create_contract,null,false);
        binding  = DataBindingUtil.bind(bindView);
        return bindView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        createContractVM = new CreateContractVM(mContext);
        alertDialog = new AlertDialog.Builder(this);
        initInfo();
        setClick();
        initPickerTimeView();
//        initAccessToken();
//        initAccessTokenWithAkSk();
        FinishActivityManager.getManager().addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        showLossPartList();
    }

    private void setClick() {
        CreateContractClick createContractClick = new CreateContractClick();
        binding.setCreateContractClick(createContractClick);

        if(binding.agreeYes.isChecked()){
            binding.agreeButton.setBackgroundResource(R.drawable.survey_dolaig_sure_bload_line);
            binding.agreeButton.setTextColor(getResources().getColor(R.color.core_white));
            binding.agreeButton.setClickable(true);
        }else{
            binding.agreeButton.setBackgroundResource(R.drawable.survey_login_btn_default_bg);
            binding.agreeButton.setTextColor(getResources().getColor(R.color.survey_gray_btn_bg_color));
            binding.agreeButton.setClickable(false);
        }
    }

    private void initInfo() {
        isAddThirdCard = true;
        mReportNo = UUIDUtil.getUUID();
        uuid = UUIDUtil.getUUID();
        mContract = createContractVM.newCar(uuid,mReportNo, mTaskNO); // 车辆信息
        bindReport(mContract);

        if (mProvinceList.size() == 0) getProvinceFromNet(false);
        if (mBrandList.size() == 0) getBrandFromNet(false);

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
                binding.ownerSexMale.setChecked(true);

            } else if (ownerSex.equals("0")) {
                binding.ownerSexFemale.setChecked(true);
            } else{
                binding.ownerSexMale.setChecked(true);//默认男
            }
        }else{
            binding.ownerSexMale.setChecked(true);//默认男
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

        //延保无忧产品类型
        if(TextUtils.isEmpty(mContract.getProductType())){
            String productTypeCode = mContract.getProductTypeCode();
            String productTypeName = null;
            if (TextUtils.isEmpty(productTypeCode)) {
                if (productInfos == null) {
                    productInfos = StaticCode.getInstance().getProductTypeList();
                    if (productInfos != null && productInfos.size() > 0) {
                        TypeItem typeItem = productInfos.get(0);
                        if(typeItem!=null){
                            productTypeCode = typeItem.getID();
                            productTypeName = typeItem.getValue();
                        }
                    }
                }
            }else{
                if (productInfos == null) {
                    productInfos = StaticCode.getInstance().getProductTypeList();
                    productTypeName = StaticCode.getInstance().getValueByCode(productInfos, productTypeCode);
                }
            }
            binding.ownerProductTypeSelectTv.setText(productTypeName);
            mContract.setProductType(productTypeName);
            mContract.setProductTypeCode(productTypeCode);
        } else {
            binding.ownerProductTypeSelectTv.setText(mContract.getProductType());//证件类型
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
                binding.isInsureCommercialYes.setChecked(true);

            } else if (isInsureCommercial.equals("0")) {
                binding.isInsureCommercialNo.setChecked(true);
            } else{
                binding.isInsureCommercialYes.setChecked(true);//默认男
            }
        }else{
            binding.isInsureCommercialYes.setChecked(true);//默认男
            mContract.setIsInsureCommercial("1");
        }

        //保险公司
        if(TextUtils.isEmpty(mContract.getCommercialCompany())){
            String commercialCompanyCode = mContract.getCommercialCompanyCode();
            String commercialCompanyName = null;
            if (TextUtils.isEmpty(commercialCompanyCode)) {
                if (commercialCompanyInfos == null) {
                    commercialCompanyInfos = StaticCode.getInstance().getCommercialCompanyTypeList();
                    if (commercialCompanyInfos != null && commercialCompanyInfos.size() > 0) {
                        TypeItem typeItem = commercialCompanyInfos.get(0);
                        if(typeItem!=null){
                            commercialCompanyCode = typeItem.getID();
                            commercialCompanyName = typeItem.getValue();
                        }
                    }
                }
            }else{
                if (commercialCompanyInfos == null) {
                    commercialCompanyInfos = StaticCode.getInstance().getCommercialCompanyTypeList();
                    commercialCompanyName = StaticCode.getInstance().getValueByCode(commercialCompanyInfos, commercialCompanyCode);
                }
            }
            binding.insuranceCompanyNameSelectTv.setText(commercialCompanyName);
            mContract.setCommercialCompany(commercialCompanyName);
            mContract.setCommercialCompanyCode(commercialCompanyCode);
        } else {
            binding.insuranceCompanyNameSelectTv.setText(mContract.getUsePropertyName());//证件类型
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
                binding.serviceSexMale.setChecked(true);

            } else if (ownerSex.equals("0")) {
                binding.serviceSexFemale.setChecked(true);
            } else{
                binding.serviceSexMale.setChecked(true);//默认男
            }
        }else{
            binding.serviceSexMale.setChecked(true);//默认男
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


        binding.thirdVinNoEt.setTransformationMethod(ViewUtils.getViewUtils().getTransformationMethod()); //将输入的小写字母转换成大写字母


        if("1".equals(mId)){
            binding.surveyLin1.setVisibility(View.GONE);
            binding.surveyLin2.setVisibility(View.GONE);
            binding.surveyLine1.setVisibility(View.GONE);
            binding.surveyLine2.setVisibility(View.GONE);
            binding.surveyLin3.setVisibility(View.GONE);
            binding.surveyLine3.setVisibility(View.GONE);
            binding.surveyLin4.setVisibility(View.GONE);
            binding.surveyLine4.setVisibility(View.GONE);
            binding.gotoContractContent.setText("《置换无忧服务合同》及《章程》");
        }else if("2".equals(mId)){
            binding.surveyLin1.setVisibility(View.GONE);
            binding.surveyLin2.setVisibility(View.GONE);
            binding.surveyLine1.setVisibility(View.GONE);
            binding.surveyLine2.setVisibility(View.GONE);
            binding.surveyLin3.setVisibility(View.GONE);
            binding.surveyLine3.setVisibility(View.GONE);
            binding.surveyLin4.setVisibility(View.GONE);
            binding.surveyLine4.setVisibility(View.GONE);
            binding.gotoContractContent.setText("《高枕无忧服务合同》及《章程》");
        }else if("3".equals(mId)){
            binding.surveyLin1.setVisibility(View.VISIBLE);
            binding.surveyLin2.setVisibility(View.VISIBLE);
            binding.surveyLine1.setVisibility(View.VISIBLE);
            binding.surveyLine2.setVisibility(View.VISIBLE);
            binding.surveyLin3.setVisibility(View.VISIBLE);
            binding.surveyLine3.setVisibility(View.VISIBLE);
            binding.surveyLin4.setVisibility(View.VISIBLE);
            binding.surveyLine4.setVisibility(View.VISIBLE);
            binding.gotoContractContent.setText("《延保无忧服务合同》及《章程》");
        }


        binding.isInsureCommercialNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    UtilManager.Toast.show(getBaseContext(),"该产品必须商业保险");
                }
            }

        });

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
    //从网络获取省列表
    public void getProvinceFromNet(boolean showLoadingDialog){
        createContractVM.getProvinceFromNet(showLoadingDialog,"0").observeOnce(this, dictDTOList -> initProvinceData(dictDTOList));
    }
    //从网络获取市列表
    public void getCityFromNet(boolean showLoadingDialog,String code){
        createContractVM.getCityFromNet(showLoadingDialog,"1",code).observeOnce(this, dictCityDTOList -> initCityData(dictCityDTOList));
    }
    //从网络获取县列表
    public void getCountyFromNet(boolean showLoadingDialog,String code){
        createContractVM.getCountyFromNet(showLoadingDialog,"1",code).observeOnce(this, dictCountyDTOList -> initCountyData(dictCountyDTOList));
    }

    private void initProvinceData(List<DictDTO> dictDTOList){
        if (mProvinceList.size() == 0){
            if(dictDTOList!=null&&dictDTOList.size()>0){
                for (DictDTO dto : dictDTOList) {
                    TypeItem item = new TypeItem();
                    item.setCode(dto.getDictCode());
                    item.setValue(dto.getDictName());
                    mProvinceList.add(item);
                }
            }
        }
    }
    private void initCityData(List<DictDTO> dictCityDTOList){
        if (mCityList.size() != 0){
            mCityList.clear();
        }
        if(dictCityDTOList!=null&&dictCityDTOList.size()>0){
            for (DictDTO dto : dictCityDTOList) {
                TypeItem item = new TypeItem();
                item.setCode(dto.getDictCode());
                item.setValue(dto.getDictName());
                mCityList.add(item);
            }
        }
    }
    private void initCountyData(List<DictDTO> dictCountyDTOList){
        if (mCountyList.size() != 0){
            mCountyList.clear();
        }
        if(dictCountyDTOList!=null&&dictCountyDTOList.size()>0){
            for (DictDTO dto : dictCountyDTOList) {
                TypeItem item = new TypeItem();
                item.setCode(dto.getDictCode());
                item.setValue(dto.getDictName());
                mCountyList.add(item);
            }
        }
    }
    //从网络获取省列表
    public void getBrandFromNet(boolean showLoadingDialog){
        createContractVM.getBrandFromNet(showLoadingDialog).observeOnce(this, brandDTOList -> initBrandData(brandDTOList));
    }
    //从网络获取市列表
    public void getFamilyFromNet(boolean showLoadingDialog,String code){
        createContractVM.getFamilyFromNet(showLoadingDialog,"1",code).observeOnce(this, familyDTOList -> initFamilyData(familyDTOList));
    }
    //从网络获取县列表
    public void getVehicleFromNet(boolean showLoadingDialog,String code){
        createContractVM.getVehicleFromNet(showLoadingDialog,"1",code).observeOnce(this, vehicleDTOList -> initVehicleData(vehicleDTOList));
    }
    private void initBrandData(List<CarModelDTO> brandDTOList){
        if (mBrandList.size() == 0){
            if(brandDTOList!=null&&brandDTOList.size()>0){
                for (CarModelDTO dto : brandDTOList) {
                    TypeItem item = new TypeItem();
                    item.setID(dto.getId());
                    item.setCode(dto.getCarBrandCode());
                    item.setValue(dto.getCarBrandName());
                    mBrandList.add(item);
                }
            }
        }
    }
    private void initFamilyData(List<CarModelDTO> familyDTOList){
//        if (mFamilyList.size() != 0){
        mFamilyList.clear();
//        }
        if(familyDTOList!=null&&familyDTOList.size()>0){
            for (CarModelDTO dto : familyDTOList) {
                TypeItem item = new TypeItem();
                item.setID(dto.getId());
                item.setCode(dto.getCarSysCode());
                item.setValue(dto.getCarSysName());
                mFamilyList.add(item);
            }
        }
    }
    private void initVehicleData(List<CarModelDTO> vehicleDTOList){
//        if (mVehicleList.size() != 0){
        mVehicleList.clear();
//        }
        if(vehicleDTOList!=null&&vehicleDTOList.size()>0){
            for (CarModelDTO dto : vehicleDTOList) {
                TypeItem item = new TypeItem();
                item.setID(dto.getId());
                item.setCode(dto.getCarModelCode());
                item.setValue(dto.getCarModelName());
                mVehicleList.add(item);
            }
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
         * 行驶里程及年份
         */
        public void serviceValiditySelected() {
            if(serviceValidityTypeDictList == null){
                if("3".equals(mId)){
                    serviceValidityTypeDictList = StaticCode.getInstance().getValidityList2();
                }else{
                serviceValidityTypeDictList = StaticCode.getInstance().getValidityList();}
            }
            showPopWindow(getResources().getString(R.string.survey_service_validity_type), serviceValidityTypeDictList, "14");
        }


//        /**
//         * 显示服务内容
//         */
//        public void showContractContent() {
//            if(View.VISIBLE==binding.contractContent.getVisibility()){
//                binding.contractContent.setVisibility(View.GONE);
//            }else{
//                binding.contractContent.setVisibility(View.VISIBLE);
//            }
//        }

        /**
         * 显示服务内容
         */
        public void gotoContractContent() {
            Bundle bundle = new Bundle();
            bundle.putString(SurveyClaimUtil.REPORT_NO, "");
            bundle.putString(SurveyClaimUtil.ITEM_ID, mId);
            bundle.putString(SurveyClaimUtil.YEAR, mContract.getMileage());

            bundle.putString("carBrandCode", mContract.getBrandCode());

            bundle.putString(SurveyClaimUtil.START_TIME, binding.serviceStartDateSelectTv.getText().toString().trim());

            Contract contract = new Contract();
            bundle.putSerializable("Contract", contract);
            startActivity(ContractContentActivity.class,bundle);
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
         * 同意
         */
        public void setAgree() {

            binding.agreeButton.setBackgroundResource(R.drawable.survey_dolaig_sure_bload_line);
            binding.agreeButton.setTextColor(getResources().getColor(R.color.core_white));
            binding.agreeButton.setClickable(true);


        }

        /**
         * 不同意
         */
        public void setNotAgree() {
            binding.agreeButton.setBackgroundResource(R.drawable.survey_login_btn_default_bg);
            binding.agreeButton.setTextColor(getResources().getColor(R.color.survey_gray_btn_bg_color));
            binding.agreeButton.setClickable(false);

        }


        /**
         * 开户银行（省）设置
         */
        public void setBrand() {
            showPopWindow(getResources().getString(R.string.survey_text_product_model), mBrandList, "11");

        }

        /**
         * 开户银行（市）设置
         */
        public void setFamily() {
            showPopWindow(getResources().getString(R.string.survey_text_product_family), mFamilyList, "12");
        }
        /**
         * 开户银行（市）设置
         */
        public void setVehicle() {
            showPopWindow(getResources().getString(R.string.survey_text_product_vehicle), mVehicleList, "13");
        }


        /**
         * 开户银行（省）设置
         */
        public void setBankProvince() {
            showPopWindow(getResources().getString(R.string.survey_text_province), mProvinceList, "8");

        }

        /**
         * 开户银行（市）设置
         */
        public void setBankCity() {
            showPopWindow(getResources().getString(R.string.survey_text_city), mCityList, "9");
        }
        /**
         * 开户银行（区县）设置
         */
        public void setBankCounty() {
            showPopWindow(getResources().getString(R.string.survey_text_county), mCountyList, "10");
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
            isOwnerOcr = true;
            OCRUtil.takePhoto(CreateContractActivity.this, OCRUtil.TAKE_SENFEN_NO_ALL);
        }

        /**
         * service识别
         */
        public void serviceOcr() {
            isOwnerOcr = false;
            OCRUtil.takePhoto(CreateContractActivity.this, OCRUtil.TAKE_SENFEN_NO_ALL);
        }

        /**
         * Vin识别
         */
        public void vinNoOcr() {
            OCRUtil.takePhoto(CreateContractActivity.this, OCRUtil.TAKE_XINGSHI_NO_ALL);
//
        }

        /**
         * 使用性质
         */
        public void setUsePropertyType() {
            if (usePropertyDictInfos == null) {
                usePropertyDictInfos = StaticCode.getInstance().getUsePropertyTypeList();
            }
            showPopWindow("车辆用途", usePropertyDictInfos, "6");
        }


        /**
         * 与车主关系
         */
        public void setRelationType() {
            if (useRelationDictInfos == null) {
                useRelationDictInfos = StaticCode.getInstance().getRelationTypeList();
            }
            showPopWindow("与车主关系", useRelationDictInfos, "7");
        }
        /**
         * 保险公司
         */
        public void setInsuranceCompanyNameType() {
            if (commercialCompanyInfos == null) {
                commercialCompanyInfos = StaticCode.getInstance().getCommercialCompanyTypeList();
            }
            showPopWindow("保险公司", commercialCompanyInfos, "15");
        }

        /**
         * 证件类型设置，车主
         */
        public void setOwnerCertificateType() {
            if (driverDictInfos == null) {
                driverDictInfos = StaticCode.getInstance().getCertificateTypeList();
            }
            showPopWindow(getResources().getString(R.string.survey_text_credentials_typ), driverDictInfos, "2");
        }
        /**
         * 产品类型
         */
        public void setProductType() {
            if (productInfos == null) {
                productInfos = StaticCode.getInstance().getProductTypeList();
            }
            showPopWindow(getResources().getString(R.string.survey_text_products_typ), productInfos, "20");
        }

        /**
         * 证件类型设置，服务商
         */
        public void setServiceCertificateType() {
            if (serviceDriverDictInfos == null) {
                serviceDriverDictInfos = StaticCode.getInstance().getCertificateTypeList();
            }
            showPopWindow(getResources().getString(R.string.survey_text_credentials_typ), serviceDriverDictInfos, "3");
        }

        /**
         * 初登日期
         */
        public void driverIssueDateSelected() {
            timePickerView.show();
            timePickerView.setOnTimeSelectListener(onTimeSelectListener, binding.buyCarDateSelectTv);
        }


        /**
         * 是否投保商业车损险 是
         */
        public void setIsInsureCommercialYes() {
            mContract.setIsInsureCommercial("1");
        }

        /**
         * 是否投保商业车损险 否
         */
        public void setIsInsureCommercialNo() {
            mContract.setIsInsureCommercial("0");
        }

        /**
         * 车主性别 男
         */
        public void setOwnerSexMale() {
            mContract.setOwnerSex("1");
        }

        /**
         * 车主性别女
         */
        public void setOwnerSexFemale() {
            mContract.setOwnerSex("0");
        }

        /**
         * 服务购买人性别 男
         */
        public void setServiceSexMale() {
            mContract.setServiceSex("1");
        }

        /**
         * 服务购买人性别女
         */
        public void setServiceSexFemale() {
            mContract.setServiceSex("0");
        }

//        /**
//         * 身份证号识别
//         */
//        public void idCardNoOcr() {
//            if(!TextUtils.isEmpty(mContract.getCertificateTypeCode())&& mContract.getCertificateType().contains(getResources().getString(R.string.survey_cardtype_8020001))){
//                OCRUtil.takePhoto(CreateContractActivity.this, OCRUtil.TAKE_SENFEN_NO_ALL);
//            }else{
//                UtilManager.Toast.show(getApplicationContext(),"证件类型是身份才可识别");
//            }
//        }

        /**
         * 驾驶员姓名识别
         */
        public void driverNameOcr() {
            OCRUtil.takePhoto(CreateContractActivity.this, OCRUtil.TAKE_JIASHI_NO_ALL);
        }

        /**
         * 驾驶证识别
         */
        public void driverNoOcr() {
            OCRUtil.takePhoto(CreateContractActivity.this, OCRUtil.TAKE_JIASHI_NO_ALL);
        }

        /**
         * 准驾车型识别
         */
        public void driverTypeOcr() {
            OCRUtil.takePhoto(CreateContractActivity.this, OCRUtil.TAKE_JIASHI_NO_ALL);
        }
        /**
         * 保存数据
         */
        public void gotoSave() {

            int checkedCode = checkData();
            mContract.setDiscountType(binding.serviceYou.isChecked()?"1":"0");
            if (checkedCode == 0) {
//                mContract.setAdditionalFlag("0");//保存，但未选择服务
//                ContractManager.getInstance().saveSurveyCar(mContract);
//                UtilManager.Toast.show(getBaseContext(),"数据保存成功");
                if("3".equals(mId)){
                }else{
                    mContract.setProductTypeCode("0");
                }
                ContractManager.getInstance().saveSurveyCar(mContract);
                createContractVM.addContractForService(true,mContract).observeOnce(CreateContractActivity.this, this::addContract);
                UtilManager.Toast.show(mContext,"提交");

            } else if(checkedCode == -1) {   //没有通过非空校验
                binding.errorTipTv.setVisibility(View.VISIBLE);
                Animation animSet = AnimationUtils.loadAnimation(CreateContractActivity.this, R.anim.survey_fade_down_up_anim);
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
//            if (checkedCode == 0) {
////                mContract.setAdditionalFlag("0");//保存，但未选择服务
////                ContractManager.getInstance().saveSurveyCar(mContract);
////                UtilManager.Toast.show(getBaseContext(),"数据保存成功");
//
//                Bundle bundle = new Bundle();
//                bundle.putString(SurveyClaimUtil.REPORT_NO, mContract.getReportCode());
//                bundle.putString(SurveyClaimUtil.ITEM_ID, mId);
//                bundle.putSerializable("Contract", mContract);
//                startActivity(ContractServiceActivity.class,bundle);
////                onBackPressed();
//                hideSoftInput();
//
////                createContractVM.addContractForService(true,mContract).observeOnce(CreateContractActivity.this, this::addContract);
//
//            }
        }
        private void addContract(@Nullable PayDTO payDTO) {
            if(payDTO!=null&&!"".equals(payDTO.getBase64())){
                ContractManager.getInstance().saveSurveyCar(mContract);
                UtilManager.Toast.show(getBaseContext(),"数据保存成功");

                Bundle bundle = new Bundle();
                bundle.putString(SurveyClaimUtil.IMAGE_NAME,payDTO.getBase64());
                bundle.putString(SurveyClaimUtil.REPORT_NO,payDTO.getContractNo());
                bundle.putString(SurveyClaimUtil.SIGN_NAME,payDTO.getOrder_price());
                bundle.putString("youhui",mContract.getDiscountType());
                startActivity(PaymentActivity.class,bundle);

//                onBackPressed();
                hideSoftInput();
            }else{
                UtilManager.Toast.show(getBaseContext(),"数据保存失败");
            }
        }
//
//        private void addContract(@Nullable Boolean saveFlag) {
//            if(saveFlag){
//                mContract.setAdditionalFlag("0");//保存，但未选择服务
//                ContractManager.getInstance().saveSurveyCar(mContract);
//                UtilManager.Toast.show(getBaseContext(),"数据保存成功");
//
//                Bundle bundle = new Bundle();
//                bundle.putString(SurveyClaimUtil.REPORT_NO, mContract.getReportCode());
//                bundle.putString(SurveyClaimUtil.ITEM_ID, "");
//                bundle.putSerializable("Contract", mContract);
//                startActivity(ContractServiceActivity.class,bundle);
//
//                onBackPressed();
//                hideSoftInput();
//            }else{
//                UtilManager.Toast.show(getBaseContext(),"数据保存失败");
//            }
//        }

        /**
         * 校验必填项
         *
         *      返回 0 表示校验通过，返回 1表示非空校验通过，但是有录入项不符合规则，返回-1表示检验非空失败
         */
        private int  checkData() {

            setSurveythirdCar();  // 为车型信息赋值

            boolean isChecked;
            if (mId!=null&&"3".equals(mId)){
                //第一步校验为空的必填项(所有需要做非空检车的View)
                isChecked = checkViews(getBaseContext(),binding.ownerNameEt,
                        binding.ownerCertificateTypeSelectTv,
                        binding.ownerCertificateNoEt,
                        binding.ownerTelephoneEt,
                        binding.ownerEmailEt,
//                    binding.regionEt,
                        binding.brandNameEt,
                        binding.familyNameEt,
//                    binding.modelNameEt,
                        binding.buyCarDateSelectTv,
                        binding.usePropertySelectTv,
                        binding.batteryNumberEt,
                        binding.policyNoEt,
                        binding.thirdVinNoEt,
                        binding.relationSelectTv,
                        binding.serviceNameEt,
                        binding.serviceCertificateTypeSelectTv,
                        binding.serviceCertificateNoEt,
                        binding.serviceTelephoneEt,
                        binding.serviceValidityDateSelectTv,
                        binding.serviceEmailEt,
                        binding.serviceStartDateSelectTv,
                        binding.serviceAdressEt,
                        binding.batteryNumbersEt,
                        binding.batteryNumberssEt
                );
            }else{
                isChecked = checkViews(getBaseContext(),binding.ownerNameEt,
                        binding.ownerCertificateTypeSelectTv,
                        binding.ownerCertificateNoEt,
                        binding.ownerTelephoneEt,
                        binding.ownerEmailEt,
//                    binding.regionEt,
                        binding.brandNameEt,
                        binding.familyNameEt,
//                    binding.modelNameEt,
                        binding.buyCarDateSelectTv,
                        binding.usePropertySelectTv,
                        binding.batteryNumberEt,
                        binding.policyNoEt,
                        binding.thirdVinNoEt,
                        binding.relationSelectTv,
                        binding.serviceNameEt,
                        binding.serviceCertificateTypeSelectTv,
                        binding.serviceCertificateNoEt,
                        binding.serviceTelephoneEt,
                        binding.serviceValidityDateSelectTv,
                        binding.serviceEmailEt,
                        binding.serviceStartDateSelectTv,
                        binding.serviceAdressEt

                );
            }

            if(isChecked){


                //车主电话号码
                if(!TextUtils.isEmpty(mContract.getOwnerTelePhone())){
                    if( !ValidateUtil.isMobileNO(mContract.getOwnerTelePhone())){
                        UtilManager.Toast.show(getBaseContext(),"请填写正确的车主电话号码");
                        return 1;
                    }
                }
                //购买人电话号码
                if(!TextUtils.isEmpty(mContract.getServiceTelePhone())){
                    if( !ValidateUtil.isMobileNO(mContract.getServiceTelePhone())){
                        UtilManager.Toast.show(getBaseContext(),"请填写正确的购买人电话号码");
                        return 1;
                    }
                }
                //地址
                if(!TextUtils.isEmpty(mContract.getServiceTelePhone())){
                    if( !ValidateUtil.isMobileNO(mContract.getServiceTelePhone())){
                        UtilManager.Toast.show(getBaseContext(),"请填写正确的购买人电话号码");
                        return 1;
                    }
                }
                //车主邮箱
                if(!TextUtils.isEmpty(mContract.getOwnerEmail())){
                    if( !ValidateUtil.isEmail(mContract.getOwnerEmail())){
                        UtilManager.Toast.show(getBaseContext(),"请填写正确的车主邮箱");
                        return 1;
                    }
                }
                //购买人邮箱
                if(!TextUtils.isEmpty(mContract.getServiceEmail())){
                    if( !ValidateUtil.isEmail(mContract.getServiceEmail())){
                        UtilManager.Toast.show(getBaseContext(),"请填写正确的购买人邮箱");
                        return 1;
                    }
                }
                //车牌号
//                if(!TextUtils.isEmpty(mContract.getPlateNo())){
//                    //校验车牌号
//                    if( !ValidateUtil.isPlateNo(mContract.getPlateNo())){
//                        UtilManager.Toast.show(getBaseContext(),"车牌号不正确");
//                        return 1;
//                    }
//                }
                if("00".equals(mContract.getRelationCode())){
                    UtilManager.Toast.show(getBaseContext(),"需选择服务购买人与车主关系");
                    return 1;
                }
                if(binding.isInsureCommercialNo.isChecked()){
                    UtilManager.Toast.show(getBaseContext(),"需选择投保商业险");
                    return 1;
                }
                //车架号
                if(!TextUtils.isEmpty(mContract.getVinNo())){
                    //校验VIN
                    if(!TextUtils.isEmpty(VinUtil.isVin(mContract.getVinNo()))){
                        UtilManager.Toast.show(getBaseContext(),"车架号不正确");
                        return 1;
                    }
                }

                String buyCarStartTime = binding.buyCarDateSelectTv.getText().toString().trim();
                String buyCarCurrentTime = TimestampTool.getCurrentDate();
                if(!TextUtils.isEmpty(buyCarStartTime) && !TextUtils.isEmpty(buyCarCurrentTime)){
                    double i = TimestampTool.compareTo(buyCarStartTime,buyCarCurrentTime);
                    if("3".equals(mId)){
                        if (i < 0) {
                            UtilManager.Toast.show(getBaseContext(), "购车时间不能超过当前时间");
                            return 1;
                        }
                    }else {
                        if (i < 0) {
                            UtilManager.Toast.show(getBaseContext(), "购车时间不能超过当前时间");
                            return 1;
                        }
                        if (i > 21) {
                            UtilManager.Toast.show(getBaseContext(), "购车时间只能早于当前时间21天");
                            return 1;
                       }
                    }
                }
                String startTime = binding.serviceStartDateSelectTv.getText().toString().trim();
                String currentTime = TimestampTool.getCurrentDate();
                if(!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(currentTime)){
                    double i = TimestampTool.compareTo(startTime,currentTime);
                    if("3".equals(mId)){
                        if(i>0){
                            UtilManager.Toast.show(getBaseContext(),"生效日期不能早于当前时间");
                            return 1;
                        }
                    }else{
                    if(i<-1){
                        UtilManager.Toast.show(getBaseContext(),"生效日期不能超过当前时间1天");
                        return 1;
                    }
                    if(i>0){
                        UtilManager.Toast.show(getBaseContext(),"生效日期不能早于当前时间");
                        return 1;
                    }}
                }
//                //证件号码
                if(!TextUtils.isEmpty(mContract.getOwnerCertificateTypeCode())&& mContract.getOwnerCertificateType().contains(getResources().getString(R.string.survey_cardtype_8020001))){
                    //身份证
                    if(!IDCardUtil.isIDCard(mContract.getOwnerCertificateNo())){
                        UtilManager.Toast.show(getBaseContext(),"请填写正确的车主身份证号码");
                        return 1;
                    }
                }
                if(!TextUtils.isEmpty(mContract.getServiceCertificateTypeCode())&& mContract.getServiceCertificateType().contains(getResources().getString(R.string.survey_cardtype_8020001))){
                    //身份证
                    if(!IDCardUtil.isIDCard(mContract.getServiceCertificateNo())){
                        UtilManager.Toast.show(getBaseContext(),"请填写正确的购买人身份证号码");
                        return 1;
                    }
                }

            }else{
                return -1;
            }
            return 0;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case OCRUtil.TAKE_XINGSHI_NO_ALL:
                    OCRUtil.requestOcr(mContext, this, OCRUtil.TAKE_XINGSHI_NO_ALL);
                    break;
                case OCRUtil.TAKE_SENFEN_NO_ALL:
                    OCRUtil.requestOcr(mContext, this, OCRUtil.TAKE_SENFEN_NO_ALL);
                    break;
                case OCRUtil.TAKE_JIASHI_NO_ALL:
                    OCRUtil.requestOcr(mContext, this, OCRUtil.TAKE_JIASHI_NO_ALL);
                    break;
            }
        }
    }


    /**
     * 为model赋值
     */
    private void setSurveythirdCar() {

        mContract.setOwnerName(binding.ownerNameEt.getText().toString().trim());
        mContract.setOwnerSex(binding.ownerSexMale.isChecked()?"1":"0");
        mContract.setOwnerCertificateNo(binding.ownerCertificateNoEt.getText().toString().trim());
        mContract.setOwnerTelePhone(binding.ownerTelephoneEt.getText().toString().trim());
        mContract.setOwnerEmail(binding.ownerEmailEt.getText().toString().trim());

        mContract.setRegion(binding.regionEt.getText().toString().trim());
        mContract.setPlateNo(binding.plateNoEt.getText().toString().trim().toUpperCase());  //车牌号
        mContract.setVinNo(binding.thirdVinNoEt.getText().toString().trim().toUpperCase()); //车架号
        mContract.setEngineNo(binding.thirdEngineNoEt.getText().toString().trim());  //发动机号
        mContract.setVehiclePrice(MathUtil.setScale(Double.parseDouble(TextUtils.isEmpty(binding.vehiclePriceEt.getText()) ? "0.0" : binding.vehiclePriceEt.getText().toString().trim())));//新车购置价
        mContract.setBrandName(binding.brandNameEt.getText().toString().trim());
        mContract.setFamilyName(binding.familyNameEt.getText().toString().trim());
        mContract.setModelName(binding.modelNameEt.getText().toString().trim());
        mContract.setBuyCarDate(binding.buyCarDateSelectTv.getText().toString().trim());
        mContract.setBatteryNumber(binding.batteryNumberEt.getText().toString().trim());
        mContract.setPolicyNo(binding.policyNoEt.getText().toString().trim());

        mContract.setServiceName(binding.serviceNameEt.getText().toString().trim());
        mContract.setServiceSex(binding.serviceSexMale.isChecked()?"1":"0");
        mContract.setServiceCertificateNo(binding.serviceCertificateNoEt.getText().toString().trim());
        mContract.setServiceTelePhone(binding.serviceTelephoneEt.getText().toString().trim());
        mContract.setServiceEmail(binding.serviceEmailEt.getText().toString().trim());

        mContract.setExemptFlag("0");

        if(mId!=null&&"1".equals(mId)){
            mContract.setProductCode("ZHWY");
            mContract.setProductName("置换无忧");
        }else if (mId!=null&&"2".equals(mId)){
            mContract.setProductCode("GZWY");
            mContract.setProductName("高枕无忧");
        }else if (mId!=null&&"3".equals(mId)){
            mContract.setProductCode("YBWY");
            mContract.setProductName("延保无忧");
        }


        mContract.setServiceStartDate(binding.serviceStartDateSelectTv.getText().toString().trim());

        if(isAddThirdCard){
            mContract.setAdditionalFlag("1");//新增标记
            serialNo = ReportCarManager.getInstance().getReportCarMaxSerialNo(mReportNo);
            mContract.setSerialNo(serialNo + 1);
        }
        mContract.setMotorNum(binding.batteryNumbersEt.getText().toString().trim());
        mContract.setYanbaoStartMiles(binding.batteryNumberssEt.getText().toString().trim());
        mContract.setYanbaoStartMiles(binding.batteryNumberssEt.getText().toString().trim());
        mContract.setAddress(binding.serviceAdressEt.getText().toString().trim());
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
                String responseCode = jsonObject.getString("responseCode");
                String responseMessage = jsonObject.getString("responseMessage");
                if (responseCode.equals("0000")) {
                    if(isOwnerOcr){
                        binding.ownerNameEt.setText(jsonObject.getString("userName"));
                        if("男".equals(jsonObject.getString("sex"))){
                            binding.ownerSexMale.setChecked(true);
                        }else  if("女".equals(jsonObject.getString("sex"))){
                            binding.ownerSexFemale.setChecked(true);
                        }else {
                            binding.ownerSexMale.setChecked(true);
                        }
                        binding.ownerCertificateNoEt.setText(jsonObject.getString("idCardNo"));
                        binding.serviceNameEt.setText(jsonObject.getString("userName"));
                        if("男".equals(jsonObject.getString("sex"))){
                            binding.serviceSexMale.setChecked(true);
                        }else  if("女".equals(jsonObject.getString("sex"))){
                            binding.serviceSexFemale.setChecked(true);
                        }else {
                            binding.serviceSexMale.setChecked(true);
                        }
                        binding.serviceCertificateNoEt.setText(jsonObject.getString("idCardNo"));
                    }else{
                        binding.serviceNameEt.setText(jsonObject.getString("userName"));
                        if("男".equals(jsonObject.getString("sex"))){
                            binding.serviceSexMale.setChecked(true);
                        }else  if("女".equals(jsonObject.getString("sex"))){
                            binding.serviceSexFemale.setChecked(true);
                        }else {
                            binding.serviceSexMale.setChecked(true);
                        }
                        binding.serviceCertificateNoEt.setText(jsonObject.getString("idCardNo"));
                    }

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
            String responseCode = jsonObject.getString("responseCode");
            String responseMessage = jsonObject.getString("responseMessage");
            if (responseCode.equals("0000")) {
                binding.plateNoEt.setText(jsonObject.getString("plateNo"));
                binding.thirdVinNoEt.setText(jsonObject.getString("vin"));
                binding.thirdVinNoEt.setTransformationMethod(IDCardUtil.inputLowerToUpper);//小写变大写
                binding.thirdEngineNoEt.setText(jsonObject.getString("engineNo"));
                binding.modelNameEt.setText(jsonObject.getString("vehicleModels"));
                binding.buyCarDateSelectTv.setText(jsonObject.getString("registerDate"));

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
        timePickerView = new TimePickerView(CreateContractActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
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
            String code = typeItem.getCode();

            if (flag.equals("1")) {  //车辆类型
//                binding.thirdCarTypeSelectTv.setText(value);
                mContract.setCarType(value);
                mContract.setCarTypeCode(id);
            } else if (flag.equals("2")) {  //证件类型 车主
                binding.ownerCertificateTypeSelectTv.setText(value);
                mContract.setOwnerCertificateType(value);
                mContract.setOwnerCertificateTypeCode(id);
            } else if (flag.equals("3")) {  //证件类型 服务商
                binding.serviceCertificateTypeSelectTv.setText(value);
                mContract.setServiceCertificateType(value);
                mContract.setServiceCertificateTypeCode(id);
            } else if (flag.equals("20")) {  //产品类型
                binding.ownerProductTypeSelectTv.setText(value);
                mContract.setProductType(value);
                mContract.setProductTypeCode(id);
            } else if (flag.equals("4")) {    //车辆种类
//                binding.thirdVehicleTypeTv.setText(value);
                mContract.setVehicleType(value);
                mContract.setVehicleTypeCode(id);
            } else if (flag.equals("5")) {    //准驾车型
//                binding.driverDriveTypeSelectTv.setText(value);
                mContract.setDriveType(value);
                mContract.setDriveTypeCode(id);
            }else if (flag.equals("6")) {    //使用性质
                binding.usePropertySelectTv.setText(value);
                mContract.setUseProperty(value);
                mContract.setUsePropertyCode(id);
            }else if (flag.equals("7")) {    //车主关系
                binding.relationSelectTv.setText(value);
                mContract.setRelation(value);
                mContract.setRelationCode(id);


                if("01".equals(id)){
                    binding.serviceNameEt.setText(binding.ownerNameEt.getText().toString().trim());
                    binding.serviceNameEt.setEnabled(false);
                    if(binding.ownerSexMale.isChecked()){
                        binding.serviceSexMale.setChecked(true);
                        binding.serviceSexFemale.setClickable(false);
                    }else  if(binding.ownerSexFemale.isChecked()){
                        binding.serviceSexFemale.setChecked(true);
                        binding.serviceSexMale.setClickable(false);
                    }else {
                        binding.serviceSexMale.setChecked(true);
                        binding.serviceSexFemale.setClickable(false);
                    }
                    binding.serviceCertificateTypeSelectTv.setText(binding.ownerCertificateTypeSelectTv.getText().toString().trim());
                    mContract.setServiceCertificateType(mContract.getOwnerCertificateType());
                    mContract.setServiceCertificateTypeCode(mContract.getOwnerCertificateTypeCode());
                    binding.serviceCertificateTypeSelectTv.setClickable(false);
                    binding.serviceCertificateNoEt.setText(binding.ownerCertificateNoEt.getText().toString().trim());
                    binding.serviceCertificateNoEt.setEnabled(false);
                    binding.serviceTelephoneEt.setText(binding.ownerTelephoneEt.getText().toString().trim());
                    binding.serviceTelephoneEt.setEnabled(false);
                    binding.serviceEmailEt.setText(binding.ownerEmailEt.getText().toString().trim());
                    binding.serviceEmailEt.setEnabled(false);
                }else{
                    binding.serviceNameEt.setText("");
                    binding.serviceNameEt.setEnabled(true);
                    binding.serviceSexMale.setChecked(true);
                    binding.serviceSexMale.setClickable(true);
                    binding.serviceSexFemale.setClickable(true);
                    binding.serviceCertificateTypeSelectTv.setClickable(true);
                    binding.serviceCertificateNoEt.setText("");
                    binding.serviceCertificateNoEt.setEnabled(true);
                    binding.serviceTelephoneEt.setText("");
                    binding.serviceTelephoneEt.setEnabled(true);
                    binding.serviceEmailEt.setText("");
                    binding.serviceEmailEt.setEnabled(true);
                }
            }else if(flag.equals("8")){
                binding.paymentBankProvinceTv.setText(value);
                getCityFromNet(true,id);
                binding.paymentBankCityTv.setText("");

                mContract.setProComName(value);
                mContract.setProComCode(id);
            }else if(flag.equals("9")){
                binding.paymentBankCityTv.setText(value);
                getCountyFromNet(true,id);
                binding.paymentBankCountyTv.setText("");

                mContract.setCityComCode(id);
                mContract.setCityComName(value);
            }else if(flag.equals("10")){
                binding.paymentBankCountyTv.setText(value);

                mContract.setCountyComCode(id);
                mContract.setCountyComName(value);
            }else if(flag.equals("11")){
                binding.brandNameEt.setText(value);
                getFamilyFromNet(true,id);
                binding.familyNameEt.setText("");

                mContract.setBrandCode(code);
                mContract.setBrandName(value);
            }else if(flag.equals("12")){
                binding.familyNameEt.setText(value);
                getVehicleFromNet(true,id);
                binding.modelNameEt.setText("");

                mContract.setFamilyCode(code);
                mContract.setFamilyName(value);
            }else if(flag.equals("13")){
                binding.modelNameEt.setText(value);

                mContract.setVehicleModelCode(code);
                mContract.setVehicleModel(value);
            }else if (flag.equals("14")) {  //年限列表
                mContract.setTermOfValidityDate(value);
                mContract.setMileage(id);
                binding.serviceValidityDateSelectTv.setText(value);

                //  ContractManager.getInstance().saveSurveyCar(mContract);
                int codess=checkDatas();
                if(codess==0){
                    createContractVM.addContractService(true,mContract).observeOnce(CreateContractActivity.this, this::addContracts);
                }else{
                    binding.serviceValidityDateSelectTv.setText("");
                   // UtilManager.Toast.show(getBaseContext(),"填写信息有误");
                }


            }else if (flag.equals("15")) {  //保险公司
                mContract.setCommercialCompanyCode(id);
                mContract.setCommercialCompany(value);
                binding.insuranceCompanyNameSelectTv.setText(value);
            }

        }
        cancle();
    }
    private void addContracts(@Nullable String payDTO) {
        if(payDTO!=null&&!"".equals(payDTO)){
            if(payDTO.equals("1")){
                binding.serviceYouhuis.setVisibility(View.VISIBLE);
                binding.lineYouhuis.setVisibility(View.VISIBLE);
                binding.serviceYou.setChecked(true);
                //mContract.setDiscountType("1");

            }else{
                binding.serviceYouhuis.setVisibility(View.GONE);
                binding.lineYouhuis.setVisibility(View.GONE);
                binding.serviceMeiyou.setChecked(true);
                //mContract.setDiscountType("0");
            }
        }else{

        }
    }
    private int  checkDatas() {

        setSurveythirdCar();  // 为车型信息赋值

        //第一步校验为空的必填项(所有需要做非空检车的View)
        boolean isChecked = checkViews(getBaseContext(),binding.ownerNameEt,
                binding.ownerCertificateTypeSelectTv,
                binding.ownerCertificateNoEt,
                binding.ownerTelephoneEt,
                binding.ownerEmailEt,
//                    binding.regionEt,
                binding.brandNameEt,
                binding.familyNameEt,
//                    binding.modelNameEt,
                binding.buyCarDateSelectTv,
                binding.usePropertySelectTv,
                binding.batteryNumberEt,
                binding.policyNoEt,
                binding.thirdVinNoEt,
                binding.relationSelectTv,
                binding.serviceNameEt,
                binding.serviceCertificateTypeSelectTv,
                binding.serviceCertificateNoEt,
                binding.serviceTelephoneEt,
                binding.serviceValidityDateSelectTv,
                binding.serviceEmailEt
        );
        if(isChecked){


            //车主电话号码
            if(!TextUtils.isEmpty(mContract.getOwnerTelePhone())){
                if( !ValidateUtil.isMobileNO(mContract.getOwnerTelePhone())){
                    UtilManager.Toast.show(getBaseContext(),"请填写正确的车主电话号码");
                    return 1;
                }
            }
            //购买人电话号码
            if(!TextUtils.isEmpty(mContract.getServiceTelePhone())){
                if( !ValidateUtil.isMobileNO(mContract.getServiceTelePhone())){
                    UtilManager.Toast.show(getBaseContext(),"请填写正确的购买人电话号码");
                    return 1;
                }
            }

            //车主邮箱
            if(!TextUtils.isEmpty(mContract.getOwnerEmail())){
                if( !ValidateUtil.isEmail(mContract.getOwnerEmail())){
                    UtilManager.Toast.show(getBaseContext(),"请填写正确的车主邮箱");
                    return 1;
                }
            }
            //购买人邮箱
            if(!TextUtils.isEmpty(mContract.getServiceEmail())){
                if( !ValidateUtil.isEmail(mContract.getServiceEmail())){
                    UtilManager.Toast.show(getBaseContext(),"请填写正确的购买人邮箱");
                    return 1;
                }
            }
            //车牌号
//                if(!TextUtils.isEmpty(mContract.getPlateNo())){
//                    //校验车牌号
//                    if( !ValidateUtil.isPlateNo(mContract.getPlateNo())){
//                        UtilManager.Toast.show(getBaseContext(),"车牌号不正确");
//                        return 1;
//                    }
//                }
            if("00".equals(mContract.getRelationCode())){
                UtilManager.Toast.show(getBaseContext(),"需选择服务购买人与车主关系");
                return 1;
            }
            if(binding.isInsureCommercialNo.isChecked()){
                UtilManager.Toast.show(getBaseContext(),"需选择投保商业险");
                return 1;
            }
            //车架号
            if(!TextUtils.isEmpty(mContract.getVinNo())){
                //校验VIN
                if(!TextUtils.isEmpty(VinUtil.isVin(mContract.getVinNo()))){
                    UtilManager.Toast.show(getBaseContext(),"车架号不正确");
                    return 1;
                }
            }

            String buyCarStartTime = binding.buyCarDateSelectTv.getText().toString().trim();
            String buyCarCurrentTime = TimestampTool.getCurrentDate();
            if(!TextUtils.isEmpty(buyCarStartTime) && !TextUtils.isEmpty(buyCarCurrentTime)){
                double i = TimestampTool.compareTo(buyCarStartTime,buyCarCurrentTime);
                if("3".equals(mId)){
                    if (i < 0) {
                        UtilManager.Toast.show(getBaseContext(), "购车时间不能超过当前时间");
                        return 1;
                    }
                }else {
                    if (i < 0) {
                        UtilManager.Toast.show(getBaseContext(), "购车时间不能超过当前时间");
                        return 1;
                    }
                    if (i > 21) {
                        UtilManager.Toast.show(getBaseContext(), "购车时间只能早于当前时间21天");
                        return 1;
                    }
                }
            }

/*
            String startTime = binding.serviceStartDateSelectTv.getText().toString().trim();
            String currentTime = TimestampTool.getCurrentDate();
            if(!TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(currentTime)){
                double i = TimestampTool.compareTo(startTime,currentTime);
                if(i<-1){
                    UtilManager.Toast.show(getBaseContext(),"生效日期不能超过当前时间1天");
                    return 1;
                }
                if(i>0){
                    UtilManager.Toast.show(getBaseContext(),"生效日期不能早于当前时间");
                    return 1;
                }
            }*/
//                //证件号码
            if(!TextUtils.isEmpty(mContract.getOwnerCertificateTypeCode())&& mContract.getOwnerCertificateType().contains(getResources().getString(R.string.survey_cardtype_8020001))){
                //身份证
                if(!IDCardUtil.isIDCard(mContract.getOwnerCertificateNo())){
                    UtilManager.Toast.show(getBaseContext(),"请填写正确的车主身份证号码");
                    return 1;
                }
            }
            if(!TextUtils.isEmpty(mContract.getServiceCertificateTypeCode())&& mContract.getServiceCertificateType().contains(getResources().getString(R.string.survey_cardtype_8020001))){
                //身份证
                if(!IDCardUtil.isIDCard(mContract.getServiceCertificateNo())){
                    UtilManager.Toast.show(getBaseContext(),"请填写正确的购买人身份证号码");
                    return 1;
                }
            }

        }else{
            return -1;
        }
        return 0;
    }
}
