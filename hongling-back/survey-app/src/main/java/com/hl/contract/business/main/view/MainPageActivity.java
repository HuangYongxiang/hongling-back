package com.hl.contract.business.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hl.contract.R;
import com.hl.contract.business.personalcenter.view.PersonalCenterActivity;
import com.hl.contract.business.query.view.QueryCenterFragment;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.core.plugin.SurveyTitleBar;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.core.lib.util.UtilManager;

/**
 * @Describe: 主页：任务列表
 * @Package: com.hl.contract.business.main.view
 * @Author: liyu
 * @Date: 2018/3/13 16:00
 * @Copyright: hl
 */
public class MainPageActivity extends BaseActivity<SurveyTitleBar> implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radio_group;
    private FrameLayout container;

    private TaskFragment taskFragment;//任务列表
    private QueryCenterFragment queryCenterFragment;//综合查询
    private ProductFragment productFragment;//产品


    @Override
    protected Object entryInterceptor(Intent intent) {
        String userId = UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID,"");
        if(TextUtils.isEmpty(userId)){
            return R.string.survey_user_id_empty;
        }
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(SurveyTitleBar titleBar) {
//        titleBar.rightBtnText = "新建";
    }

    @Override
    protected Object initLayout() {
        return R.layout.survey_activity_main_page;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = (FrameLayout) findViewById(R.id.container);
        radio_group = (RadioGroup) findViewById(R.id.radio_group);
        radio_group.setOnCheckedChangeListener(this);
        initFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        radio_group.postDelayed(() -> {
            int height = radio_group.getMeasuredHeight();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) container.getLayoutParams();
            layoutParams.bottomMargin = height;
            container.setLayoutParams(layoutParams);
        },500);
        //默认显示案件处理页面
        radio_group.check(R.id.radio_btn_more);
    }

    private void initFragment(){
        addFragment(R.id.container,productFragment = new ProductFragment());
        addFragment(R.id.container,taskFragment = new TaskFragment());
        addFragment(R.id.container,queryCenterFragment = new QueryCenterFragment());
    }

    @Override
    public void onCheckedChanged(RadioGroup group,final int checkedId) {
        switch (checkedId){
            case R.id.radio_btn_task://案件处理页面
                showFragment(taskFragment);
                break;
            case R.id.radio_btn_live://视频任务页面
//                UtilManager.Toast.show(this,"视频任务");
                showFragment(taskFragment);
                break;
            case R.id.radio_btn_query://综合查询页面
                showFragment(queryCenterFragment);
                break;
            case R.id.radio_btn_more://更多页面
                showFragment(productFragment);
//                UtilManager.Toast.show(this,"更多");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        final int id = view.getId();
        switch (id){
//            case R.id.title_right_btn:
//                final String txt = TextUtil.safeText((TextView) view);
//                //点击刷新按钮
//                if("新建".equals(txt)){
//                    Bundle bundle = new Bundle();
//                    bundle.putString(SurveyClaimUtil.REPORT_NO, "");
//                    bundle.putString(SurveyClaimUtil.ITEM_ID, "");
//                    bundle.putSerializable("Contract", "");
//                    startActivity(CreateContractActivity.class,bundle);
//
////                    taskFragment.getTaskFromNet(true);
//                }
//                break;
            case R.id.title_left_img:
                //点击个人中心按钮
                startActivity(PersonalCenterActivity.class);
                break;
        }
    }





}
