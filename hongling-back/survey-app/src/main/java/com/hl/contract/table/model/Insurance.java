package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Describe:  进入定损传报案、保单的数据的情况使用：承保险别
 * @Author: liyu
 * @Date: 2018/1/13 13:10
 * @Copyright: hl
 */
@Entity
public class Insurance {
    @Id
    private String id;//	主键
    private String reportCode;//报案号
    private String policyId;//所属保单主键
    private String riskCode;//险种代码
    private String riskName;//险种名称
    private String itemCode;//险别代码
    private String itemName;//险别名称
    private Short insuranceSuitYear;//险种适用年款
    private Double totalInsSum;//保险金额
    private Double totalInsFee;//保险费
    private String insuranceProperty;//险别属性
    private String itemAttribute;//条款属性
    private String nopayFlag;//投保不计免赔标志
    @Generated(hash = 613088160)
    public Insurance(String id, String reportCode, String policyId, String riskCode,
            String riskName, String itemCode, String itemName,
            Short insuranceSuitYear, Double totalInsSum, Double totalInsFee,
            String insuranceProperty, String itemAttribute, String nopayFlag) {
        this.id = id;
        this.reportCode = reportCode;
        this.policyId = policyId;
        this.riskCode = riskCode;
        this.riskName = riskName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.insuranceSuitYear = insuranceSuitYear;
        this.totalInsSum = totalInsSum;
        this.totalInsFee = totalInsFee;
        this.insuranceProperty = insuranceProperty;
        this.itemAttribute = itemAttribute;
        this.nopayFlag = nopayFlag;
    }
    @Generated(hash = 247287177)
    public Insurance() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public String getPolicyId() {
        return this.policyId;
    }
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    public String getRiskCode() {
        return this.riskCode;
    }
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }
    public String getRiskName() {
        return this.riskName;
    }
    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }
    public String getItemCode() {
        return this.itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Short getInsuranceSuitYear() {
        return this.insuranceSuitYear;
    }
    public void setInsuranceSuitYear(Short insuranceSuitYear) {
        this.insuranceSuitYear = insuranceSuitYear;
    }
    public Double getTotalInsSum() {
        return this.totalInsSum;
    }
    public void setTotalInsSum(Double totalInsSum) {
        this.totalInsSum = totalInsSum;
    }
    public Double getTotalInsFee() {
        return this.totalInsFee;
    }
    public void setTotalInsFee(Double totalInsFee) {
        this.totalInsFee = totalInsFee;
    }
    public String getInsuranceProperty() {
        return this.insuranceProperty;
    }
    public void setInsuranceProperty(String insuranceProperty) {
        this.insuranceProperty = insuranceProperty;
    }
    public String getItemAttribute() {
        return this.itemAttribute;
    }
    public void setItemAttribute(String itemAttribute) {
        this.itemAttribute = itemAttribute;
    }
    public String getNopayFlag() {
        return this.nopayFlag;
    }
    public void setNopayFlag(String nopayFlag) {
        this.nopayFlag = nopayFlag;
    }
}
