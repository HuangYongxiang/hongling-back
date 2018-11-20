package com.hl.core.lib.permission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @Describe:
 * @Package: com.hl.core.lib.permission
 * @Author: liyu
 * @Date: 2018/1/15/015 18:25
 * @Copyright: hl
 */


public class PermissionBean {
    private boolean isNecessary = true;
    private List<String> permissions = new ArrayList<>();  // 需要申请的权限数组

    public PermissionBean(){
    }

    public PermissionBean(boolean isNecessary, List<String> permissions) {
        this.isNecessary = isNecessary;
        this.permissions = permissions;
    }

    public boolean isNecessary() {
        return isNecessary;
    }

    public void setNecessary(boolean necessary) {
        isNecessary = necessary;
    }

    public List<String> getPermissions() {
        return permissions;
    }


    public void addPermission(String permission){
        this.permissions.add(permission);
    }
    public void addPermissionAll(Collection<String> permissions){
        this.permissions.addAll(permissions);
    }

    public void addPermissionArray(String[] permissions){
        this.permissions.addAll(Arrays.asList(permissions));
    }
}
