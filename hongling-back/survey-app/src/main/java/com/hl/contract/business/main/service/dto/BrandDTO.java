package com.hl.contract.business.main.service.dto;

/**
 * @Describe:  品牌DTO
 * @Author: liyu
 * @Date: 2018/1/6 11:08
 * @Copyright: hl
 */

public class BrandDTO {
    private String id;         //品牌ID
    private String brandCode; //品牌编码
    private String brandName; //品牌名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
