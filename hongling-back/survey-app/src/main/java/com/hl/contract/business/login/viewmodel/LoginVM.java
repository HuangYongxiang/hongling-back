package com.hl.contract.business.login.viewmodel;

import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.LiveData;
import com.hl.core.lib.viewmodel.Model;
import com.hl.contract.business.login.model.UserInfoModel;
import com.hl.contract.business.login.service.dto.LoginDTO;
import com.hl.contract.business.login.service.dto.UserInfoDTO;
import com.hl.contract.table.model.UserInfo;

/**
 * @Describe: 主页VM
 * @Package: com.hl.contract.business.login.viewmodel
 * @Author: liyu
 * @Date: 2018/3/20 14:29
 * @Copyright: hl
 */
public class LoginVM extends CoreViewModel{


    @LiveData
    private CoreLiveData<Boolean> saveSuccess;//用户信息保存结果

    @LiveData
    private CoreLiveData<UserInfoDTO> loginDTO;//用户信息/字典表

    @Model
    private UserInfoModel userInfoModel;


    /**
     * 登录并保存到数据库中
     */
    public CoreLiveData<UserInfoDTO> login(UserInfo userInfo) {
        doAsync(() -> userInfoModel.login(loginDTO,userInfo));
        return loginDTO;
    }

//    //保存用户信息到数据库中
//    public CoreLiveData<Boolean> saveUserInfoToDB(LoginDTO dto) {
//        doAsync(() -> userInfoModel.saveUserInfoToDB(saveSuccess,dto));
//        return saveSuccess;
//    }
}
