package com.hl.contract.business.login.model;

import android.text.TextUtils;
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
import com.hl.contract.business.login.service.dto.UserInfoDTO;
import com.hl.contract.business.personalcenter.service.PersonalService;
import com.hl.contract.table.manager.UserManager;
import com.hl.contract.table.model.UserInfo;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;

import retrofit2.Call;

/**
 * @Describe: 用户model
 * @Package: com.hl.contract.business.login.model
 * @Author: liyu
 * @Date: 2018/3/21 11:20
 * @Copyright: hl
 */
public class UserInfoModel extends CoreModel {


    /**
     * 登录
     * @param userInfo
     * @param user
     */
    public void login(CoreLiveData<UserInfoDTO> userInfo,UserInfo user){
        LoginService service = (LoginService) ApiManager.getInstance().getApiService(LoginService.class);
//        MobileRequest request = new MobileRequest();
//        request.setRequestSourceCode(request.getRequestSourceCode());
        LoginQuestDTO dto = new LoginQuestDTO();
        dto.setAccount(user.getUserName());
        dto.setPwd(user.getPassWord());
//        request.setData(dto);
        Call<Response<UserInfoDTO>> call = service.login(dto);
        asyncNetWork(TAG,0,call,new NetworkResponse<Response<UserInfoDTO>>(){
            @Override
            public void onDataReady(Response<UserInfoDTO> response) {
                UserInfoDTO loginDTO = response.getData();
                if (loginDTO!=null) {
//                    loginDTO.setUserName(user.getUserName());
//                    loginDTO.setPassWord(user.getPassWord());
                    // 保存UserInfo到manager
//                    UserManager.getInstance().setmUser(loginDTO);
                    UserManager.getInstance().saveUserInfo( loginDTO,user);
                    userInfo.postValue(loginDTO);
                    sendMessage(new CoreMessage(CoreMessage.LOGIN,""));
                } else {
                    sendMessage(new CoreMessage(CoreMessage.LOGIN,"用户名或密码错误"));
                }
            }

            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.LOGIN,message));
            }
        });
    }

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

}
