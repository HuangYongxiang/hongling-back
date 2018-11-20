package com.hl.contract.business.main.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.chenzg.mylibrary.OCRUtil;
import com.hl.contract.R;
import com.hl.contract.bean.StaticCode;
import com.hl.contract.business.main.adapter.PopupWindowAdapter;
import com.hl.contract.business.main.service.dto.PayDTO;
import com.hl.contract.business.main.viewmodel.CreateContractVM;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.databinding.SurveyActivityContractServiceBinding;
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
import com.hl.core.lib.util.common.dialog.DialogUtil;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;
import com.jock.pickerview.view.TimePickerView;

import java.util.Date;
import java.util.List;

import static com.hl.contract.util.ViewUtils.checkAllTextView;

/**
 * @Describe: 服务选择页，不用了
 * @Package: com.hl.contract.business.survey.main.view
 * @Author: liyu
 * @Date: 2018/3/21/ 14:23
 * @Copyright: hongling
 */


public class ContractServiceActivity extends BaseActivity<TitleBar> implements PopupWindowResponseUtil.PopupWindowResponseCallBack, OCRUtil.OcrCallback {

    @ViewModel
    CreateContractVM createContractVM;
    private TimePickerView timePickerView;
    private Contract mContract; //信息
    private boolean queryFlag;
    private SurveyActivityContractServiceBinding binding;
    private String mId; //产品ID
    private String mReportNo;
    private String mTaskNO;
    private String uuid;
    private int serialNo;
    private PopupWindow mPopupWindow;

    private PopupWindow popupWindow; // popwindow
    private PopupWindowAdapter recyclerViewAdapter; // popwindow adapter
    private List<TypeItem> serviceValidityTypeDictList;//年限

    @Override
    protected Object entryInterceptor(Intent intent) {

        mContract = (Contract) intent.getSerializableExtra("Contract");
//
        mId = intent.getStringExtra(SurveyClaimUtil.ITEM_ID);
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = "选择服务";
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_activity_contract_service,null,false);
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
    }

    private void setClick() {
        ContractServiceClick contractServiceClick = new ContractServiceClick();
        binding.setContractServiceClick(contractServiceClick);

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
                serialNo = mContract.getSerialNo();
                bindReport(mContract);

    }


    private void bindReport(Contract contract) {

        binding.setContract(contract);

        //服务产品
        String productCode = mContract.getProductCode();
        if (!TextUtils.isEmpty(productCode)) {
            if (productCode.equals("1")) {
                binding.productJingying.setChecked(true);

            } else if (productCode.equals("2")) {
                binding.productGaozhen.setChecked(true);
            } else{
                binding.productJingying.setChecked(true);//默认男
            }
        }else{
            binding.productJingying.setChecked(true);//默认男
            mContract.setProductCode("1");
            mContract.setProductName("精英无忧服务");
        }

        if("1".equals(mId)){
            binding.gotoContractContent.setText("《电池无忧合同》及《会员权益及义务》");
        }else if("2".equals(mId)){
            binding.gotoContractContent.setText("《电池无忧合同》及《会员权益及义务》");
        }else if("3".equals(mId)){
            binding.gotoContractContent.setText("《电池无忧合同》及《会员权益及义务》");
        }
    }

    /**
     * 设置右上角 删除案件按钮状态
     * 1. 新增案件以及调度过来的涉案车辆: 隐藏删除按钮
     * 2. 查看: 删除案件功能
     */
    private void setTitleBarState(boolean isAddThirdCard) {
        if (isAddThirdCard) { // carType 1 为标的车
            titleBar.showRightBtn = false;
            titleBar.updateTitle();
        } else {
            titleBar.showRightBtn = true;
            titleBar.rightBtnText = "删除";
            titleBar.updateTitle();
            titleBar.title_right_btn.setOnClickListener((view) -> {
                if (mReportNo != null) {
                    View inflateView = LayoutInflater.from(this).inflate(R.layout.survey_textview_content_layout, null);
                    TextView textView = (TextView) inflateView.findViewById(R.id.content_tv);
                    textView.setText("\n确定要删除此涉案车辆信息吗？");


                    DialogUtil.dialogDeleteOrSure(this,inflateView,"提示",R.string.survey_dialog_cancel,R.string.survey_dialog_delete,deleteView->{
                        createContractVM.deleteSurveyCarById(mReportNo);
                        UtilManager.Toast.show(this,"删除成功");
                        deleteView.dismiss();
                        finish();
                    },sureView->{
                        sureView.dismiss();
                    });
                }
            });

        }

    }

    @Override
    public void ocrRespone(int requestCode, String responseString) {
        switch (requestCode) {
            default:
                break;
        }
    }


    public class ContractServiceClick{

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
            showPopWindow(getResources().getString(R.string.survey_service_validity_type), serviceValidityTypeDictList, "1");
        }


        /**
         * 显示服务内容
         */
        public void showContractContent() {
            if(View.VISIBLE==binding.contractContent.getVisibility()){
                binding.contractContent.setVisibility(View.GONE);
            }else{
                binding.contractContent.setVisibility(View.VISIBLE);
            }
        }

        /**
         * 显示服务内容
         */
        public void gotoContractContent() {
            Bundle bundle = new Bundle();
            bundle.putString(SurveyClaimUtil.REPORT_NO, "");
            bundle.putString(SurveyClaimUtil.ITEM_ID, mId);
            Contract contract = new Contract();
            bundle.putSerializable("Contract", contract);
            startActivity(ContractContentActivity.class,bundle);
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

//        /**
//         * 身份证号识别
//         */
//        public void idCardNoOcr() {
//            if(!TextUtils.isEmpty(mContract.getCertificateTypeCode())&& mContract.getCertificateType().contains(getResources().getString(R.string.survey_cardtype_8020001))){
//                OCRUtil.takePhoto(ContractServiceActivity.this, OCRUtil.TAKE_SENFEN_NO_ALL);
//            }else{
//                UtilManager.Toast.show(getApplicationContext(),"证件类型是身份才可识别");
//            }
//        }

        /**
         * 驾驶员姓名识别
         */
        public void driverNameOcr() {
            OCRUtil.takePhoto(ContractServiceActivity.this, OCRUtil.TAKE_JIASHI_NO_ALL);
        }

        /**
         * 驾驶证识别
         */
        public void driverNoOcr() {
            OCRUtil.takePhoto(ContractServiceActivity.this, OCRUtil.TAKE_JIASHI_NO_ALL);
        }

        /**
         * 准驾车型识别
         */
        public void driverTypeOcr() {
            OCRUtil.takePhoto(ContractServiceActivity.this, OCRUtil.TAKE_JIASHI_NO_ALL);
        }


        /**
         * 保存数据
         */
        public void gotoSave() {
            int checkedCode = checkData();
            if (checkedCode == 0) {
                ContractManager.getInstance().saveSurveyCar(mContract);
                createContractVM.addContractForService(true,mContract).observeOnce(ContractServiceActivity.this, this::addContract);
                UtilManager.Toast.show(mContext,"提交");

            } else if(checkedCode == -1) {   //没有通过非空校验
                binding.errorTipTv.setVisibility(View.VISIBLE);
                Animation animSet = AnimationUtils.loadAnimation(ContractServiceActivity.this, R.anim.survey_fade_down_up_anim);
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
        private void addContract(@Nullable PayDTO payDTO) {
            if(payDTO!=null&&!"".equals(payDTO.getBase64())){
                ContractManager.getInstance().saveSurveyCar(mContract);
                UtilManager.Toast.show(getBaseContext(),"数据保存成功");

                Bundle bundle = new Bundle();
                bundle.putString(SurveyClaimUtil.IMAGE_NAME,payDTO.getBase64());
                bundle.putString(SurveyClaimUtil.REPORT_NO,payDTO.getContractNo());
                bundle.putString(SurveyClaimUtil.SIGN_NAME,payDTO.getOrder_price());
                startActivity(PaymentActivity.class,bundle);

//                onBackPressed();
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

            setSurveythirdCar();  // 为车型信息赋值

            //第一步校验为空的必填项(所有需要做非空检车的View)
            boolean isChecked = checkViews(getBaseContext(),
//                    binding.mileageEt,
                    binding.serviceValidityDateSelectTv,
                    binding.serviceStartDateSelectTv
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
//                if(!TextUtils.isEmpty(mContract.getVinNo())){
//                    //校验VIN
//                    if(!TextUtils.isEmpty(VinUtil.isVin(mContract.getVinNo()))){
//                        UtilManager.Toast.show(getBaseContext(),"车架号不正确");
//                        return 1;
//                    }
//                }

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

//        //车辆类型
//        if(TextUtils.isEmpty(mContract.getCarType())||TextUtils.isEmpty(mContract.getCarTypeCode())){
//            showToast("请选择车辆类型");
//            return false;
//        }




//        //号牌种类
//        if(TextUtils.isEmpty(mContract.getPlateType())||TextUtils.isEmpty(mContract.getPlateTypeCode())){
//            showToast("请选择号牌种类");
//            return false;
//        }



//        //本车是否受损
//         if(TextUtils.isEmpty(mContract.getIsMainCarDamaged())){
//             showToast("请选择本车是否受损");
//             return false;
//          }



            //驾驶员信息

//        //驾驶员姓名
//        if(TextUtils.isEmpty(mContract.getDriverName())){
//            showToast("请填写驾驶员姓名");
//            return false;
//         }

//        //联系电话
//        if(TextUtils.isEmpty(mContract.getContactNumber())){
//            showToast("请填写联系电话");
//            return false;
//        }

//        //证件类型
//        if(TextUtils.isEmpty(mContract.getCertificateType())||TextUtils.isEmpty(mContract.getCertificateTypeCode())){
//            showToast("请选择驾驶员证件类型");
//            return false;
//        }


            //车辆种类(没有字段)






//        //责任比例
//        if (TextUtils.isEmpty(mContract.getDutyRatio().trim())) {
//            third_duty_ratio.setHintTextColor(ContextCompat.getColor(getContext(), R.color.manhour_edittext));
//            requestEditTextFocus(checkFlag, third_duty_ratio);
//            checkFlag = false;
//        }
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

        mContract.setServiceStartDate(binding.serviceStartDateSelectTv.getText().toString().trim());
//        mContract.setTermOfValidityDate(binding.termOfValidityDateEt.getText().toString().trim());
//        mContract.setMileage(binding.mileageEt.getText().toString().trim());
        mContract.setExemptFlag("0");

    }

    private boolean checkViews(Context context, TextView... views){
        boolean checkFlag =
                checkAllTextView(context,views);
        return checkFlag;
    }

    //初始化 时间选择器
    private void initPickerTimeView() {
        // 时间选择器
        timePickerView = new TimePickerView(ContractServiceActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
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
                mContract.setTermOfValidityDate(value);
                mContract.setMileage(code);
                binding.serviceValidityDateSelectTv.setText(value);
            }
        }
        cancle();
    }

}
