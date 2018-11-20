package com.hl.contract.business.login.service.dto;


/**
 * @Describe: 登录
 * @Package: com.hl.contract.business.login.service.dto
 * @Author: liyu
 * @Date: 2018/3/20 13:14
 * @Copyright: hl
 */

public class LoginQuestDTO {
    public String account;
    public String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
