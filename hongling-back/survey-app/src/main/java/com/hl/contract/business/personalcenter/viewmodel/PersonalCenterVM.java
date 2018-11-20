package com.hl.contract.business.personalcenter.viewmodel;

import com.hl.contract.business.login.model.UserInfoModel;
import com.hl.contract.table.model.UserInfo;
import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.LiveData;
import com.hl.core.lib.viewmodel.Model;

public class PersonalCenterVM extends CoreViewModel{


    @LiveData
    private CoreLiveData<Boolean> exitSucess;//账户注销结果

    @LiveData
    private CoreLiveData<Boolean> saveSuccess;//用户信息保存结果

    @LiveData
    private CoreLiveData<UserInfo> userInfo;//用户信息

    @LiveData
    private CoreLiveData<String> signId;//签到Id

    @Model
    private UserInfoModel userInfoModel;

    //从数据库中获取个人信息
    public CoreLiveData<UserInfo> getUserInfoFromDB() {
        doAsync(() -> userInfoModel.getUserInfoFromDB(userInfo));
        return userInfo;
    }

    //保存用户信息到数据库中
    public CoreLiveData<Boolean> exitAccount(boolean showLoadingDialog,UserInfo userInfo) {
        doAsync(() -> userInfoModel.exitAccount(showLoadingDialog,exitSucess,userInfo));
        return exitSucess;
    }
}
