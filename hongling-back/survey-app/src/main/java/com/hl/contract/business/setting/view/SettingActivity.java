package com.hl.contract.business.setting.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.hl.contract.R;
import com.hl.contract.business.setting.viewmodel.SettingVM;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.databinding.SurveySettingLayoutBinding;
import com.hl.contract.table.model.UserInfo;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;

/**
 * @Describe: 设置页
 * @Author: liyu
 * @Date: 2018/3/21 11:47
 */
public class SettingActivity extends BaseActivity<TitleBar> {

    @ViewModel
    private SettingVM settingVM;
    private SurveySettingLayoutBinding settingBinding;
    private UserInfo userInfo;
    private Context mContext;
    private String userName,password;

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = getResources().getString(R.string.survey_setting);
//        titleBar.hasTitleBar = false;
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_setting_layout, null, false);
        settingBinding = DataBindingUtil.bind(bindView);
        return bindView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void initData() {
        settingBinding.setSettingActivity(this);
    }
    /**
     * 更换主题
     */
    public void changeTheme(){
        startActivity(new Intent(SettingActivity.this,ChangeThemeActivity.class));
    }

    @Override
    protected void msgCallBack(CoreMessage msg) {
        super.msgCallBack(msg);
        if(msg.msgCode == CoreMessage.SYNC_DICT){
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

//    /**
//     * 版本检测点击事件
//     */
//    public void checkVerson(){
//        settingVM.syncDictInfoList(true,userInfo,signIn).observeOnce(this, userInfo -> getDictInfoSucess(userInfo));
//    }

//    /**
//     * 获取字典成功
//     * @param dictInfoEvent
//     */
//    public void getDictInfoSucess(Boolean sucess){
//        ToastUtil.showToast(getContext(),"同步字典表成功");
//    }

}
