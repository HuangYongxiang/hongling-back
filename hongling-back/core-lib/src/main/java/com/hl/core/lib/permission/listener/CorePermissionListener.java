package com.hl.core.lib.permission.listener;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * @Describe: 权限检查：回调listener
 * @Author: liyu
 * @Date: 2018/1/10/010 17:05
 * @Copyright: hl
 */

public interface CorePermissionListener  {

    void onSucceed(@NonNull List<String> grantPermissions);

    void onFailed(@NonNull List<String> deniedPermissions);
}
