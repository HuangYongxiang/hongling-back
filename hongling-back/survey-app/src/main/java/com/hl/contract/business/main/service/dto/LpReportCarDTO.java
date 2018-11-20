package com.hl.contract.business.main.service.dto;

import java.math.BigDecimal;

/**
 * Created by hl on 2017/9/19.
 */

public class LpReportCarDTO {
    private String id;
    private String reportCode;
    private String carType;
    private String vin;
    private String brandModel;
    private String plateNo;
    private String engineNo;
    private String plateType;
    private String insurancePolicyNo;
    private String insuranceCompany;
    private String commercialPolicyNo;
    private String commercialCompany;
    private String dutyRatio;
    private BigDecimal amountDamages;
    private String delFlag;
    private Integer serialNo;

    public BigDecimal getAmountDamages() {
        return amountDamages;
    }

    public void setAmountDamages(BigDecimal amountDamages) {
        this.amountDamages = amountDamages;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCommercialCompany() {
        return commercialCompany;
    }

    public void setCommercialCompany(String commercialCompany) {
        this.commercialCompany = commercialCompany;
    }

    public String getCommercialPolicyNo() {
        return commercialPolicyNo;
    }

    public void setCommercialPolicyNo(String commercialPolicyNo) {
        this.commercialPolicyNo = commercialPolicyNo;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDutyRatio() {
        return dutyRatio;
    }

    public void setDutyRatio(String dutyRatio) {
        this.dutyRatio = dutyRatio;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsurancePolicyNo() {
        return insurancePolicyNo;
    }

    public void setInsurancePolicyNo(String insurancePolicyNo) {
        this.insurancePolicyNo = insurancePolicyNo;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }
}
