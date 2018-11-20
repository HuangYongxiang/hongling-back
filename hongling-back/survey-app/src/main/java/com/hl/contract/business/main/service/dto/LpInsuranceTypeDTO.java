package com.hl.contract.business.main.service.dto;

/**
 * Created by hl on 2017/9/19.
 */

public class LpInsuranceTypeDTO {
    private String id;
    private String policyId;
    private String reportId;
    private String policyCode;
    private String reportCode;
    private String riskCode;
    private String riskName;
    private String itemCode;
    private String itemName;
    private Short insuranceSuitYear;
    private Double totalInsSum;
    private Double totalInsFee;
    private String insuranceProperty;
    private String itemAttribute;
    private String nopayFlag;
    private String delFlag;
    private String riskType;

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

    public String getInsuranceProperty() {
        return insuranceProperty;
    }

    public void setInsuranceProperty(String insuranceProperty) {
        this.insuranceProperty = insuranceProperty;
    }

    public Short getInsuranceSuitYear() {
        return insuranceSuitYear;
    }

    public void setInsuranceSuitYear(Short insuranceSuitYear) {
        this.insuranceSuitYear = insuranceSuitYear;
    }

    public String getItemAttribute() {
        return itemAttribute;
    }

    public void setItemAttribute(String itemAttribute) {
        this.itemAttribute = itemAttribute;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getNopayFlag() {
        return nopayFlag;
    }

    public void setNopayFlag(String nopayFlag) {
        this.nopayFlag = nopayFlag;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public Double getTotalInsFee() {
        return totalInsFee;
    }

    public void setTotalInsFee(Double totalInsFee) {
        this.totalInsFee = totalInsFee;
    }

    public Double getTotalInsSum() {
        return totalInsSum;
    }

    public void setTotalInsSum(Double totalInsSum) {
        this.totalInsSum = totalInsSum;
    }
}
