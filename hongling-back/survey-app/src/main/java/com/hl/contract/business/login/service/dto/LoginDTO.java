package com.hl.contract.business.login.service.dto;


import com.hl.contract.table.model.DictInfo;
import com.hl.contract.table.model.UserInfo;

import java.util.List;

/**
 * @Describe: 登录响应DTO
 * @Package: com.hl.contract.table.model
 * @Author: liyu
 * @Date: 2018/3/21 14:44
 * @Copyright: hl
 */

public class LoginDTO {
    private UserInfo userInfo;
    private List<DictInfo> typeList;

    public List<DictInfo> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<DictInfo> typeList) {
        this.typeList = typeList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
