package com.hl.contract.table.inter;


import com.hl.contract.table.model.UserInfo;

public interface UserListener {
    
    public void onUserLoginInProgress();

    public void onUserLogin(UserInfo user);

    public void onUserLoginFailed(int errorCode, String msg);

    public void onUserLogout();

}
