package com.hl.contract.business.setting.model;

import android.util.Log;

import com.hl.core.lib.bean.Response;
import com.hl.core.lib.network.ApiManager;
import com.hl.core.lib.network.retrofit.NetworkResponse;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.CoreModel;
import com.hl.contract.bean.MobileRequest;
import com.hl.contract.business.login.service.LoginService;
import com.hl.contract.business.login.service.dto.LoginDTO;
import com.hl.contract.business.login.service.dto.LoginQuestDTO;
import com.hl.contract.business.personalcenter.service.PersonalService;
import com.hl.contract.table.manager.DictManager;
import com.hl.contract.table.manager.UserManager;
import com.hl.contract.table.model.DictInfo;
import com.hl.contract.table.model.UserInfo;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;

import java.util.List;

import retrofit2.Call;

/**
 * @Describe: 用户model
 * @Author: liyu
 * @Date: 2018/3/23 16:20
 */
public class SettingModel extends CoreModel {


    public void getUserInfoFromDB(CoreLiveData<UserInfo> userInfo){
        userInfo.postValue(UserManager.getInstance().getUserInfo( UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID,"")));
    }

    public void exitAccount(boolean showLoadingDialog,CoreLiveData<Boolean> exitSuccess,UserInfo userInfo){
        //保存登录账户
        LoginService service = (LoginService) ApiManager.getInstance().getApiService(LoginService.class);
        MobileRequest request = new MobileRequest();
        request.setRequestSourceCode(request.getRequestSourceCode());
        LoginQuestDTO dto = new LoginQuestDTO();
        dto.setAccount(userInfo.getUserName());
        dto.setPwd(userInfo.getPassWord());
        request.setData(dto);
        Call<Response<LoginDTO>> call = service.logout(request);
        asyncNetWork(showLoadingDialog,TAG,0,call,new NetworkResponse<Response<LoginDTO>>(){
            @Override
            public void onDataReady(Response<LoginDTO> response) {


                if (response != null) {
                    if("0".equals(response.getCode())){
                        exitSuccess.setValue(true);
                    }else {
                        sendMessage(new CoreMessage(CoreMessage.LOGOUT,response.getMessage()));
                    }
                }else{
                    sendMessage(new CoreMessage(CoreMessage.LOGOUT,"注销失败"));
                }
            }

            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.LOGOUT,message));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    public void getLatestVersionInfoSystem(int versionCode, CoreLiveData<Object> updateData) {

    }
}
