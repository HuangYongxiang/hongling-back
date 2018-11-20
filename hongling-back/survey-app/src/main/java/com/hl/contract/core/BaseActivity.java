package com.hl.contract.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hl.core.lib.activity.TitleActivity;

/**
 * @Describe: Activity基类
 * @Package: com.hl.contract.core
 * @Author: liyu
 * @Date: 2018/3/16 16:18
 * @Copyright: hl
 */
public abstract class BaseActivity<T> extends TitleActivity<T>{

    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mContext = this;
        super.onCreate(savedInstanceState);
    }
}
