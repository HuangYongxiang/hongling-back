package com.hl.contract.business.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.hl.contract.R;
import com.hl.contract.business.login.service.dto.UserInfoDTO;
import com.hl.contract.business.login.viewmodel.LoginVM;
import com.hl.contract.business.main.view.MainPageActivity;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.databinding.SurveyLoginLayoutBinding;
import com.hl.contract.table.model.UserInfo;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.core.lib.network.util.NetWorkUtils;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;
import com.pgyersdk.update.DownloadFileListener;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.pgyersdk.update.javabean.AppBean;

/**
 * @Describe: 登录页
 * @Package: com.hl.contract.business.login.view
 * @Author: liyu
 * @Date: 2018/3/20 11:47
 * @Copyright: hl
 */
public class LoginActivity extends BaseActivity<TitleBar> {

    @ViewModel
    private LoginVM loginVM;
    private SurveyLoginLayoutBinding loginBinding;
    private UserInfo userInfo;
    private String userName, password;

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.hasTitleBar = false;
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.survey_login_layout, null, false);
        loginBinding = DataBindingUtil.bind(bindView);
        return bindView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                loginBinding.loginContentRl.setBackgroundResource(R.mipmap.survey_background);
                loginBinding.loginContentRl.getChildAt(0).setVisibility(View.VISIBLE);
            }
        }, 2000);


        /** 新版本 **/

        new PgyUpdateManager.Builder()
                .setForced(true)                //设置是否强制更新,非自定义回调更新接口此方法有用
                .setUserCanRetry(false)         //失败后是否提示重新下载，非自定义下载 apk 回调此方法有用
                .setDeleteHistroyApk(false)     // 检查更新前是否删除本地历史 Apk
                .setUpdateManagerListener(new UpdateManagerListener() {
                    @Override
                    public void onNoUpdateAvailable() {
                        //没有更新是回调此方法
                        Log.d("pgyer", "there is no new version");
                    }

                    @Override
                    public void onUpdateAvailable(AppBean appBean) {
                        //没有更新是回调此方法
                        Log.d("pgyer", "there is new version can update"
                                + "new versionCode is " + appBean.getVersionCode());

                        //调用以下方法，DownloadFileListener 才有效；如果完全使用自己的下载方法，不需要设置DownloadFileListener
                        PgyUpdateManager.downLoadApk(appBean.getDownloadURL());
                    }

                    @Override
                    public void checkUpdateFailed(Exception e) {
                        //更新检测失败回调
                        Log.e("pgyer", "check update failed ", e);

                    }
                })
                //注意 ：下载方法调用 PgyUpdateManager.downLoadApk(appBean.getDownloadURL()); 此回调才有效
                .setDownloadFileListener(new DownloadFileListener() {   // 使用蒲公英提供的下载方法，这个接口才有效。
                    @Override
                    public void downloadFailed() {
                        //下载失败
                        Log.e("pgyer", "download apk failed");
                    }

                    @Override
                    public void downloadSuccessful(Uri uri) {
                        Log.e("pgyer", "download apk failed");
                        PgyUpdateManager.installApk(uri);  // 使用蒲公英提供的安装方法提示用户 安装apk

                    }

                    @Override
                    public void onProgressUpdate(Integer... integers) {
                        Log.e("pgyer", "update download apk progress" + integers);
                    }
                })
                .register();


        loginBinding.editTextPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    loginBinding.loginButton.setBackgroundResource(R.drawable.survey_dolaig_sure_bload_line);
                    loginBinding.loginButton.setTextColor(getResources().getColor(R.color.core_white));
                    loginBinding.loginButton.setClickable(true);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void initData() {
        loginBinding.setLoginActivity(this);
        setUserData();
        //TODO        loginVM.checkVersionUpdate();
    }

    /**
     * set 用户名 密码
     */
    private void setUserData() {
        userInfo = new UserInfo();
        if (UtilManager.SP.survey().getBoolean(SurveyClaimUtil.IS_REMEMBER_USERINFO, false)) {
            userInfo.setUserId(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""));
            userInfo.setUserName(UtilManager.SP.survey().getString(SurveyClaimUtil.LOGIN_NAME, ""));
            userInfo.setPassWord(UtilManager.SP.survey().getString(SurveyClaimUtil.LOGIN_PWD, ""));
            loginBinding.saveCheckBox.setChecked(UtilManager.SP.survey().getBoolean(SurveyClaimUtil.LOGIN_SAVE_PWD, false));
        }
        loginBinding.setUserInfo(userInfo);
    }

    //登录按钮点击后执行
    public void gotoLogin() {
        //检查网络
        if (!NetWorkUtils.isNetConnect(mContext)) {
            UtilManager.Toast.show(this, getResources().getString(R.string.survey_net_error));
            return;
        }
        //非空校验
        if (userInfo.getUserName().equals("")) {
            UtilManager.Toast.show(this, getResources().getString(R.string.survey_please_input_user_name));
            return;
        } else if (userInfo.getPassWord().equals("")) {
            UtilManager.Toast.show(this, getResources().getString(R.string.survey_please_input_password));
            return;
        }
        //如果选中记住密码，保存登录信息
        if (loginBinding.saveCheckBox.isChecked()) {
            UtilManager.SP.survey().put(SurveyClaimUtil.IS_REMEMBER_USERINFO, true);
            UtilManager.SP.survey().put(SurveyClaimUtil.USER_ID, userInfo.getUserName());
            UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_NAME, userInfo.getUserName());
            UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_PWD, userInfo.getPassWord());
            UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_SAVE_PWD, true);
            UtilManager.SP.survey().put(SurveyClaimUtil.COM_CODE, "11");
            UtilManager.SP.survey().put(SurveyClaimUtil.COM_CODE_NAME, "北京分公司");
        } else {
            UtilManager.SP.survey().put(SurveyClaimUtil.IS_REMEMBER_USERINFO, false);
            UtilManager.SP.survey().put(SurveyClaimUtil.USER_ID, userInfo.getUserName());
            UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_NAME, userInfo.getUserName());
            UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_PWD, userInfo.getPassWord());
            UtilManager.SP.survey().put(SurveyClaimUtil.LOGIN_SAVE_PWD, true);
            UtilManager.SP.survey().put(SurveyClaimUtil.COM_CODE, "11");
            UtilManager.SP.survey().put(SurveyClaimUtil.COM_CODE_NAME, "北京分公司");
        }
        //TODO 获取字典临时使用，后期去掉，但是要根据保险公司存储
        UtilManager.SP.survey().put(SurveyClaimUtil.REQUEST_SOURCE_CODE, "HL");
        UtilManager.SP.survey().put(SurveyClaimUtil.REQUEST_SOURCE_NAME, "宏瓴");
//        startActivity(MainPageActivity.class);
        login(userInfo);
    }

    //登录网络请求
    private void login(UserInfo userInfo) {
        loginVM.login(userInfo).observeOnce(this, this::saveUser);
    }

    //装载数据保存用户信息并跳转
    private void saveUser(UserInfoDTO dto) {
        if (dto != null) {
            UtilManager.Toast.show(this, getResources().getString(R.string.survey_login_success));
            startActivity(MainPageActivity.class);
            this.finish();
        } else {
            UtilManager.Toast.show(this, getResources().getString(R.string.survey_login_fail));
        }
//        if(dto!=null) {
//            loginVM.saveUserInfoToDB(dto).observeOnce(this, this::gotoMainPage);
//        }
    }

    //跳转主页
    private void gotoMainPage(Boolean saveSuccess) {
        if (saveSuccess) {
            UtilManager.Toast.show(this, getResources().getString(R.string.survey_login_success));
            startActivity(MainPageActivity.class);
            this.finish();
        } else {
            UtilManager.Toast.show(this, getResources().getString(R.string.survey_login_fail));
        }
    }

    @Override
    protected void msgCallBack(CoreMessage msg) {
        super.msgCallBack(msg);
        if (msg.msgCode == CoreMessage.LOGIN) {
            if (!TextUtils.isEmpty(msg.message)) {
                UtilManager.Toast.show(this, msg.message);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
