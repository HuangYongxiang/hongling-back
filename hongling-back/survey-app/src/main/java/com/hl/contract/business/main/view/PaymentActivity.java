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
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.chenzg.mylibrary.OCRUtil;
import com.hl.contract.R;
import com.hl.contract.bean.StaticCode;
import com.hl.contract.business.main.adapter.PopupWindowAdapter;
import com.hl.contract.business.main.service.dto.PayDTO;
import com.hl.contract.business.main.service.dto.CarModelDTO;
import com.hl.contract.business.main.service.dto.DictDTO;
import com.hl.contract.business.main.viewmodel.CreateContractVM;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.core.FinishActivityManager;
import com.hl.contract.databinding.SurveyActivityPaymentBinding;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;
import com.hl.core.lib.bean.TypeItem;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.PopupWindowResponseUtil;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;
import com.jock.pickerview.view.TimePickerView;

import java.util.Date;
import java.util.List;

import static com.hl.contract.util.ViewUtils.checkAllTextView;

/**
 * @Describe: 支付二维码页面
 * @Package: com.hl.contract.business.survey.main.view
 * @Author: liyu
 * @Date: 2018/3/21/ 14:23
 * @Copyright: hongling
 */


public class PaymentActivity extends BaseActivity<TitleBar> implements PopupWindowResponseUtil.PopupWindowResponseCallBack, OCRUtil.OcrCallback {

    @ViewModel
    private CreateContractVM createContractVM;
    private TimePickerView timePickerView;
    private Contract mContract; //信息
    private boolean queryFlag;
    private SurveyActivityPaymentBinding binding;
    private String mReportNo;
    private String mTaskNO;
    private String uuid;
    private int serialNo;
    private PopupWindow mPopupWindow;

    private PopupWindow popupWindow; // popwindow
    private PopupWindowAdapter recyclerViewAdapter; // popwindow adapter
    private String mId;//产品号
    private String picString;//64位图片
    private String contractNo;//合同号，订单号
    private String orderPrice;//价格
    private String youhui;//是否有优惠

    @Override
    protected Object entryInterceptor(Intent intent) {
        picString = intent.getStringExtra(SurveyClaimUtil.IMAGE_NAME);
        contractNo = intent.getStringExtra(SurveyClaimUtil.REPORT_NO);
        orderPrice = intent.getStringExtra(SurveyClaimUtil.SIGN_NAME);
        youhui = intent.getStringExtra("youhui");
//        if (TextUtils.isEmpty(mTaskNO)){
//            return R.string.survey_flow_id_empty;
//        }
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = "合同费用支付";
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_activity_payment,null,false);
        binding  = DataBindingUtil.bind(bindView);
        return bindView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        createContractVM = new CreateContractVM(mContext);
        initInfo();
        setClick();
        FinishActivityManager.getManager().addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        showLossPartList();
    }

    private void setClick() {
        binding.agreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FinishActivityManager.getManager().finishAllActivity();
            }
        });
        binding.payWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.payWay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(
                        R.mipmap.survey_check_box_selected),null,null,null);
                binding.payFu.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(
                        R.mipmap.survey_check_box_default),null,null,null);
                byte[] bytes = Base64.decode(picString, Base64.DEFAULT);
                binding.paymentPic.setScaleType(ImageView.ScaleType.FIT_XY);
                binding.paymentPic.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        });
        binding.payFu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.payWay.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(
                        R.mipmap.survey_check_box_default),null,null,null);
                binding.payFu.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(
                        R.mipmap.survey_check_box_selected),null,null,null);
                //createContractVM.zhifuPay(true,contractNo).observeOnce(PaymentActivity.this, this::showPics);

                createContractVM.zhifuPay(true,contractNo,youhui).observeOnce(PaymentActivity.this,dictCountyDTOList -> showPics(dictCountyDTOList));

            }
        });
    }
    private void showPics(@Nullable PayDTO payDTO) {
        if(payDTO!=null&&!"".equals(payDTO.getBase64())){
            byte[] bytes = Base64.decode(payDTO.getBase64(), Base64.DEFAULT);
            binding.paymentPic.setVisibility(View.VISIBLE);
            binding.paymentPic.setScaleType(ImageView.ScaleType.FIT_XY);
            binding.paymentPic.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

        }else{
            UtilManager.Toast.show(getBaseContext(),"数据保存失败");
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
    private void initInfo() {

        binding.payContractNo.setText(contractNo);
        binding.payMoney.setText(orderPrice+"元");


        byte[] bytes = Base64.decode(picString, Base64.DEFAULT);
        binding.paymentPic.setScaleType(ImageView.ScaleType.FIT_XY);
        binding.paymentPic.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));

    }


    @Override
    public void ocrRespone(int requestCode, String responseString) {
        switch (requestCode) {
            default:
                break;
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


    private boolean checkViews(Context context, TextView... views){
        boolean checkFlag =
                checkAllTextView(context,views);
        return checkFlag;
    }

    //初始化 时间选择器
    private void initPickerTimeView() {
        // 时间选择器
        timePickerView = new TimePickerView(PaymentActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
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


        }
        cancle();
    }

}
