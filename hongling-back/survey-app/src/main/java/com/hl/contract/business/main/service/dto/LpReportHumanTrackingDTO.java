package com.hl.contract.business.main.service.dto;

/**
 * Created by hl on 2017/9/19.
 */

public class LpReportHumanTrackingDTO {
    private String id;
    private String reportCode;
    private String name;
    private String injuryType;
    private String casualtyType;
    private String certificateType;
    private String certificateNo;
    private String injuryDescription;
    private String hospital;
    private String delFlag;
    private Integer serialNo;

    public String getCasualtyType() {
        return casualtyType;
    }

    public void setCasualtyType(String casualtyType) {
        this.casualtyType = casualtyType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInjuryDescription() {
        return injuryDescription;
    }

    public void setInjuryDescription(String injuryDescription) {
        this.injuryDescription = injuryDescription;
    }

    public String getInjuryType() {
        return injuryType;
    }

    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
