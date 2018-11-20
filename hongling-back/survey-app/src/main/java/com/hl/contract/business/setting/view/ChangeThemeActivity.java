package com.hl.contract.business.setting.view;

import android.os.Bundle;
import android.view.View;

import com.hl.core.lib.plugin.theme.ThemeConstant;
import com.hl.core.lib.plugin.theme.ThemeManager;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.contract.R;
import com.hl.contract.core.BaseActivity;

/**
 * @Describe: 主题示例
 * @Author: liyu
 * @Date: 2018/1/2 11:03
 */
public class ChangeThemeActivity extends BaseActivity<TitleBar> {

    @Override
    protected Object initLayout() {
        return R.layout.survey_activity_demo;
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = "主题示例";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void changeTheme(View v){
        int i = v.getId();
        if (i == R.id.theme_default) {
            ThemeManager.get().setCurrentTheme(this, ThemeConstant.THEME_DEFAULT);

        } else if (i == R.id.theme_night) {
            ThemeManager.get().setCurrentTheme(this, ThemeConstant.THEME_NIGHT);

        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int i = view.getId();
    }
}
