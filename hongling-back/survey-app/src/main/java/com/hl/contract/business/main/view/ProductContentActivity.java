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
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.chenzg.mylibrary.OCRUtil;
import com.hl.contract.R;
import com.hl.contract.business.main.adapter.PopupWindowAdapter;
import com.hl.contract.business.main.viewmodel.CreateContractVM;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.core.FinishActivityManager;
import com.hl.contract.databinding.SurveyActivityProductContentBinding;
import com.hl.contract.table.manager.ReportCarManager;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;
import com.hl.core.lib.bean.TypeItem;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.common.PopupWindowResponseUtil;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.core.lib.viewmodel.ViewModel;
import com.jock.pickerview.view.TimePickerView;

import java.util.Date;
import java.util.List;

import static com.hl.contract.util.ViewUtils.checkAllTextView;

/**
 * @Describe:  产品简介页面
 * @Package: com.hl.contract.business.survey.main.view
 * @Author: liyu
 * @Date: 2018/3/21/ 14:23
 * @Copyright: hongling
 */


public class ProductContentActivity extends BaseActivity<TitleBar> implements PopupWindowResponseUtil.PopupWindowResponseCallBack, OCRUtil.OcrCallback {

    @ViewModel
    private CreateContractVM createContractVM;
    private TimePickerView timePickerView;
    private Contract mContract; //信息
    private boolean queryFlag;
    private SurveyActivityProductContentBinding binding;
    private String mReportNo;
    private String mTaskNO;
    private String uuid;
    private int serialNo;
    private PopupWindow mPopupWindow;

    private PopupWindow popupWindow; // popwindow
    private PopupWindowAdapter recyclerViewAdapter; // popwindow adapter
    private String mId;//产品号

    @Override
    protected Object entryInterceptor(Intent intent) {

        mId = intent.getStringExtra(SurveyClaimUtil.ITEM_ID);
//
        mTaskNO = intent.getStringExtra(SurveyClaimUtil.TASK_NO);
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        if("1".equals(mId)){
            titleBar.title = mTaskNO;
        }else if("2".equals(mId)){
            titleBar.title = mTaskNO;
        }else if("3".equals(mId)){
            titleBar.title = mTaskNO;
        }
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_activity_product_content,null,false);
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
                Bundle bundle = new Bundle();
                bundle.putString(SurveyClaimUtil.REPORT_NO, "");
                bundle.putString(SurveyClaimUtil.ITEM_ID, mId);
                Contract contract = new Contract();
                bundle.putSerializable("Contract", contract);
                startActivity(CreateContractActivity.class,bundle);

                onBackPressed();
                hideSoftInput();

            }
        });
    }
    private void initInfo() {
        if("1".equals(mId)){
            binding.content.setText("置换无忧是一款为新能源汽车消费者打造的贴心产品，为新能源汽车电池提供了性能保障，当消费者未来出现汽车置换需求时，我们会为消费者提供性能保障服务。");
        }else if("2".equals(mId)){
            binding.content.setText("高枕无忧是一款为新能源汽车消费者打造的贴心产品，为新能源汽车电池提供了性能保障，随时随地为消费者拥有的新能源汽车提供了性能保障服务。");
        }else if("3".equals(mId)){
            binding.content.setText("延保无忧是一款为新能源汽车消费者打造的贴心产品，为新能源汽车电机、电控、电池（简称“三电”）或电池提供了贴心延保服务，在厂家质保到期后，依然为客户提供高品质的质保服务。");
        }
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
