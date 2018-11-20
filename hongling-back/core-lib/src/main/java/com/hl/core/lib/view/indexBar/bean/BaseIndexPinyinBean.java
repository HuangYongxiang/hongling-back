package com.hl.core.lib.view.indexBar.bean;

/**
 * @Describe: 索引类的汉语拼音的接口
 * @Package: com.hl.core.lib.view.indexBar.bean
 * @Author: liyu
 * @Date: 2018/1/6 14:14
 * @Copyright: hl
 */

public abstract class BaseIndexPinyinBean extends BaseIndexTagBean implements IndexTargetInterface {
    private String baseIndexPinyin;//城市的拼音

    public String getBaseIndexPinyin() {
        return baseIndexPinyin;
    }

    public void setBaseIndexPinyin(String baseIndexPinyin) {
        this.baseIndexPinyin = baseIndexPinyin;
    }
}
