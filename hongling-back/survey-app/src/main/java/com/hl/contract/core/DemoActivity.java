package com.hl.contract.core;

import android.os.Bundle;
import android.view.View;

import com.hl.core.lib.plugin.theme.ThemeConstant;
import com.hl.core.lib.plugin.theme.ThemeManager;
import com.hl.contract.R;
import com.hl.contract.core.plugin.SurveyTitleBar;

/**
 * @Describe: 主题示例
 * @Package: com.hl.eval
 * @Author: liyu
 * @Date: 2018/1/2 11:03
 * @Copyright: hl
 */
public class DemoActivity extends BaseActivity<SurveyTitleBar> {

    @Override
    protected Object initLayout() {
        return R.layout.survey_activity_demo;
    }

    @Override
    protected void initTitle(SurveyTitleBar titleBar) {
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
        int i = view.getId();
        super.onClick(view);
//        if (i == R.id.title_speed_btn) {
//            UtilManager.Toast.show(this, "您点击了极速定损");
//
//        } else if (i == R.id.title_detail_btn) {
//            UtilManager.Toast.show(this, "您点击了标准定损");
//
//        }
    }
}
