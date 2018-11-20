package com.hl.contract.table.model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;


import com.hl.contract.BR;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create data： 2017/7/7.
 * Author: liyu
 * Function:用户
 */
@Entity
public class UserInfo extends BaseObservable {
    @Id
    private String id;
    private String userName;//用户名
    private String passWord;//密码
    private String userId;//用户代码
    private String handlerName;//用户姓名
    private String companyCode; //用户所在理赔机构代码
    private String companyName; //用户所在理赔机构名称
    private String branchCompanyCode; //用户所在理赔中支机构代码
    private String branchCompanyName; //用户所在理赔中支机构名称
    private String addr;//所在地区
    private String telephone;//电话
    private String surveyCeritCode;//身份证号
    private String deviceNo;//设备号
    private String machineModel;//机器型号
    private String signInId;// 签到id

    private String account;
    private String name;
    private String storeCode;
    private String storeName;
    private String roleCode;
    private String roleName;
    private String roleContent;
    @Generated(hash = 1898827618)
    public UserInfo(String id, String userName, String passWord, String userId,
            String handlerName, String companyCode, String companyName,
            String branchCompanyCode, String branchCompanyName, String addr,
            String telephone, String surveyCeritCode, String deviceNo,
            String machineModel, String signInId, String account, String name,
            String storeCode, String storeName, String roleCode, String roleName,
            String roleContent) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.userId = userId;
        this.handlerName = handlerName;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.branchCompanyCode = branchCompanyCode;
        this.branchCompanyName = branchCompanyName;
        this.addr = addr;
        this.telephone = telephone;
        this.surveyCeritCode = surveyCeritCode;
        this.deviceNo = deviceNo;
        this.machineModel = machineModel;
        this.signInId = signInId;
        this.account = account;
        this.name = name;
        this.storeCode = storeCode;
        this.storeName = storeName;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleContent = roleContent;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassWord() {
        return this.passWord;
    }
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getHandlerName() {
        return this.handlerName;
    }
    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
    public String getCompanyCode() {
        return this.companyCode;
    }
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getBranchCompanyCode() {
        return this.branchCompanyCode;
    }
    public void setBranchCompanyCode(String branchCompanyCode) {
        this.branchCompanyCode = branchCompanyCode;
    }
    public String getBranchCompanyName() {
        return this.branchCompanyName;
    }
    public void setBranchCompanyName(String branchCompanyName) {
        this.branchCompanyName = branchCompanyName;
    }
    public String getAddr() {
        return this.addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getSurveyCeritCode() {
        return this.surveyCeritCode;
    }
    public void setSurveyCeritCode(String surveyCeritCode) {
        this.surveyCeritCode = surveyCeritCode;
    }
    public String getDeviceNo() {
        return this.deviceNo;
    }
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
    public String getMachineModel() {
        return this.machineModel;
    }
    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel;
    }
    public String getSignInId() {
        return this.signInId;
    }
    public void setSignInId(String signInId) {
        this.signInId = signInId;
    }
    public String getAccount() {
        return this.account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStoreCode() {
        return this.storeCode;
    }
    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }
    public String getStoreName() {
        return this.storeName;
    }
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    public String getRoleCode() {
        return this.roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public String getRoleName() {
        return this.roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleContent() {
        return this.roleContent;
    }
    public void setRoleContent(String roleContent) {
        this.roleContent = roleContent;
    }


}
