package com.hl.contract.business.main.service.dto;

/**
 * Created by 15838 on 2018/11/11.
 */

public class Baifenbi {
     private String id;//": "1",
    private String brandCode;//": "BJEV",
    private String yearLimit;//": 2,
    private String baifenbi;//": 5,  百分比
    private String deleteFlag;//": 0

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public void setYearLimit(String yearLimit) {
        this.yearLimit = yearLimit;
    }

    public String getYearLimit() {
        return yearLimit;
    }

    public String getBaifenbi() {
        return baifenbi;
    }

    public void setBaifenbi(String baifenbi) {
        this.baifenbi = baifenbi;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
