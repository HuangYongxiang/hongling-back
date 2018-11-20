package com.hl.contract.business.main.service.dto;

import com.hl.core.lib.view.indexBar.bean.BaseIndexPinyinBean;

import java.io.Serializable;

/**
 * @Describe: 品牌信息DTO（移动）
 * @Author: liyu
 * @Date: 2018/1/6 14:14
 * @Copyright: hl
 */

public class ScpMobileBrandInfoDTO extends BaseIndexPinyinBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;//主键
    private String brandName;//品牌名称
    private String brandCode;//品牌编码
    private String brandInitial;//品牌首字母
    private String tpmc;//图片名称
    private String ccbz;//图片名称

    public String getBrandInitial() {
        return brandInitial;
    }

    public void setBrandInitial(String brandInitial) {
        this.brandInitial = brandInitial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getTpmc() {
        return tpmc;
    }

    public void setTpmc(String tpmc) {
        this.tpmc = tpmc;
    }

    public String getCcbz() {
        return ccbz;
    }

    public void setCcbz(String ccbz) {
        this.ccbz = ccbz;
    }

    @Override
    public String getTarget() {
        return brandInitial;
    }
}
