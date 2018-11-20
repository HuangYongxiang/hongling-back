package com.hl.contract.business.setting.viewmodel;

import com.hl.core.lib.util.common.SystemUtil;
import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.LiveData;
import com.hl.core.lib.viewmodel.Model;
import com.hl.contract.business.login.model.UserInfoModel;
import com.hl.contract.business.login.service.dto.LoginDTO;
import com.hl.contract.business.setting.model.SettingModel;
import com.hl.contract.business.setting.view.SettingActivity;
import com.hl.contract.core.SurveyApplication;
import com.hl.contract.table.model.UserInfo;

/**
 * @Describe: 主页VM
 * @Author: liyu
 * @Date: 2018/3/22 11:49
 */
public class SettingVM extends CoreViewModel{


    @LiveData
    private CoreLiveData<Boolean> saveDictInfoSuccess;//字典表保存
    @LiveData
    private CoreLiveData<Object> updateData;


    @Model
    private SettingModel settingModel;


    public CoreLiveData<Object> getLatestVersionInfoSystem(){
        int packageVersionCode = SystemUtil.getPackageVersionCode(SurveyApplication.get());
        settingModel.getLatestVersionInfoSystem(packageVersionCode,updateData);
        return updateData;
    }

}
