package com.hl.contract.business.personalcenter.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.TextView;

import com.hl.contract.R;
import com.hl.contract.business.login.view.LoginActivity;
import com.hl.contract.business.personalcenter.viewmodel.PersonalCenterVM;
import com.hl.contract.business.setting.view.SettingActivity;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.databinding.SurveyPersonalCenterLayoutBinding;
import com.hl.contract.table.model.UserInfo;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.SystemUtil;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;

/**
 * @Describe: 个人中心页
 * @Author: liyu
 * @Date: 2018/3/22 10:27
 */
public class PersonalCenterActivity extends BaseActivity<TitleBar> {

    @ViewModel
    private PersonalCenterVM personalCenterVM;
    private SurveyPersonalCenterLayoutBinding personalCenterLayoutBinding;
    private Context mContext;
    private double lat;  //坐标 lat
    private double log;//坐标 log
    private String address;//位置
    private UserInfo userInfo;//用户信息
    private String deviceNo;//设备号 签到
    private String model;//机器型号 签到

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = getResources().getString(R.string.survey_person_info_center);
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_personal_center_layout, null, false);
        personalCenterLayoutBinding = DataBindingUtil.bind(bindView);
        return bindView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void initData() {
        personalCenterLayoutBinding.setPersonalCenterActivity(this);
        //TODO  location();
        getUserInfoFromDB();
        personalCenterLayoutBinding.currentVersionTv.setText(getResources().getString(R.string.survey_current_version)+ SystemUtil.getPackageVersion(mContext));
    }


    //从数据库中获取个人信息
    private void getUserInfoFromDB(){
        personalCenterVM.getUserInfoFromDB().observeOnce(this, this :: loadData);
    }


    //点击事件设置
    public void setting(){
        Intent intent = new Intent(mContext, SettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.survey_view_leftin, R.anim.survey_view_rightout);
    }

    //点击事件退出当前账号
    public void exitAccount(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
//        personalCenterVM.exitAccount(true,userInfo).observeOnce(this, this::exit);
    }

    //用户信息装载数据并刷新UI
    private void exit (Boolean exitSucess){
        if (exitSucess){
            //更新用戶信息
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    //用户信息装载数据并刷新UI
    private void loadData(UserInfo info){
        if (info!=null){
            personalCenterLayoutBinding.setUserInfo(info);
            userInfo = info;
            personalCenterLayoutBinding.currentCuntomerTv.setText(info.getUserName());
            boolean isSign = UtilManager.SP.survey().getBoolean(userInfo.getUserId(), true);
            userInfo.setSignInId( UtilManager.SP.survey().getString("signId",""));
        }
    }


    @Override
    protected void msgCallBack(CoreMessage msg) {
        super.msgCallBack(msg);
        if(msg.msgCode == CoreMessage.SIGN_IN){
            if(!TextUtils.isEmpty(msg.message)){
                UtilManager.Toast.show(this,msg.message);
            }
        }
        if(msg.msgCode == CoreMessage.LOGOUT){
            if(!TextUtils.isEmpty(msg.message)){
                UtilManager.Toast.show(this,msg.message);
            }
        }
    }


}
