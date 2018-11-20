package com.hl.contract.business.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jock.pickerview.view.TimePickerView;
import com.hl.core.lib.util.UtilManager;
import com.hl.contract.R;
import com.hl.contract.business.main.service.dto.ContractListDTO;
import com.hl.contract.business.query.view.QueryResultActivity;
import com.hl.contract.core.BaseFragment;
import com.hl.contract.core.plugin.SurveyTitleBar;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @Describe: 产品选择
 * @Package: com.hl.contract.business.query.view
 * @Author: liyu
 * @Date: 2018/3/30 9:28
 * @Copyright: hl
 */
public class ProductFragment extends BaseFragment<SurveyTitleBar> {

    TextView product_1,product_2,product_3;

    @Override
    protected void initTitle(SurveyTitleBar titleBar) {
        super.initTitle(titleBar);
        titleBar.title = "产品选择";
    }

    @Override
    protected Object initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.survey_product_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        product_1 = (TextView) view.findViewById(R.id.product_1);
        product_2 = (TextView) view.findViewById(R.id.product_2);
        product_3 = (TextView) view.findViewById(R.id.product_3);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        Bundle bundle = new Bundle();
        switch (id){
            case R.id.product_1:
                bundle.putString(SurveyClaimUtil.ITEM_ID, "1");
                bundle.putString(SurveyClaimUtil.TASK_NO, "置换无忧");
                startActivity(ProductContentActivity.class,bundle);
                break;
            case R.id.product_2:
                bundle.putString(SurveyClaimUtil.ITEM_ID, "2");
                bundle.putString(SurveyClaimUtil.TASK_NO, "高枕无忧");
                startActivity(ProductContentActivity.class,bundle);
                break;
            case R.id.product_3:
                bundle.putString(SurveyClaimUtil.ITEM_ID, "3");
                bundle.putString(SurveyClaimUtil.TASK_NO, "延保无忧");
                startActivity(ProductContentActivity.class,bundle);
                break;
        }
    }

}
