package com.hl.contract.business.main.service.dto;

/**
 * Created by hl on 2017/9/19.
 */

public class LpReportLossesDTO {
    private String id;
    private String aScription;
    private String reportCode;
    private String lossName;
    private String lossDegreeDescription;
    private String delFlag;
    private Integer serialNo;

    public String getaScription() {
        return aScription;
    }

    public void setaScription(String aScription) {
        this.aScription = aScription;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLossDegreeDescription() {
        return lossDegreeDescription;
    }

    public void setLossDegreeDescription(String lossDegreeDescription) {
        this.lossDegreeDescription = lossDegreeDescription;
    }

    public String getLossName() {
        return lossName;
    }

    public void setLossName(String lossName) {
        this.lossName = lossName;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }
}
