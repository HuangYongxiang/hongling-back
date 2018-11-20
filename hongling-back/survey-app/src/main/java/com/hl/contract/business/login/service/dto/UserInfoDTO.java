package com.hl.contract.business.login.service.dto;

import java.io.Serializable;

public class UserInfoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
private String userId;
private String account;
private String name;
private String storeCode;
private String storeName;
private String roleCode;
private String roleName;
private String roleContent;
private String addr;

public String getAddr() {
	return addr;
}
public void setAddr(String addr) {
	this.addr = addr;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getAccount() {
	return account;
}
public void setAccount(String account) {
	this.account = account;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStoreCode() {
	return storeCode;
}
public void setStoreCode(String storeCode) {
	this.storeCode = storeCode;
}
public String getStoreName() {
	return storeName;
}
public void setStoreName(String storeName) {
	this.storeName = storeName;
}
public String getRoleCode() {
	return roleCode;
}
public void setRoleCode(String roleCode) {
	this.roleCode = roleCode;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public String getRoleContent() {
	return roleContent;
}
public void setRoleContent(String roleContent) {
	this.roleContent = roleContent;
}
@Override
public String toString() {
	return "UserInfoDTO [userId=" + userId + ", account=" + account + ", name=" + name + ", storeCode=" + storeCode
			+ ", storeName=" + storeName + ", roleCode=" + roleCode + ", roleName=" + roleName + ", roleContent="
			+ roleContent + "]";
}



}
