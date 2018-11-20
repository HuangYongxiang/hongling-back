package com.hl.photo.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Create data： 2017/6/22.
 * Author: liyu
 * Function:财产损失信息：列表（多条）
 */
@Entity
public class SurveyProperty implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String reportCode;//报案号
	private String flowId;//任务流水号
    private String ascriptionType;//归属（类别）
    private String ascriptionTypeCode;//归属（类别）编码
    private String lossName;//损失名称

    private String riskName;//险别
    private String riskCode;//险别代码
    private String expensesName;//费用名称
    private double lossPrice;//损失金额
    private String lossDegreeDescription;//损失程度描述
    private Integer serialNo;//序号，理赔同步过来
    private String additionalFlag;//新增标记
    @Generated(hash = 1352664496)
    public SurveyProperty(String id, String reportCode, String flowId,
                          String ascriptionType, String ascriptionTypeCode, String lossName,
                          String riskName, String riskCode, String expensesName, double lossPrice,
                          String lossDegreeDescription, Integer serialNo, String additionalFlag) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.ascriptionType = ascriptionType;
        this.ascriptionTypeCode = ascriptionTypeCode;
        this.lossName = lossName;
        this.riskName = riskName;
        this.riskCode = riskCode;
        this.expensesName = expensesName;
        this.lossPrice = lossPrice;
        this.lossDegreeDescription = lossDegreeDescription;
        this.serialNo = serialNo;
        this.additionalFlag = additionalFlag;
    }
    @Generated(hash = 363925750)
    public SurveyProperty() {
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
    public String getAscriptionType() {
        return this.ascriptionType;
    }
    public void setAscriptionType(String ascriptionType) {
        this.ascriptionType = ascriptionType;
    }
    public String getAscriptionTypeCode() {
        return this.ascriptionTypeCode;
    }
    public void setAscriptionTypeCode(String ascriptionTypeCode) {
        this.ascriptionTypeCode = ascriptionTypeCode;
    }
    public String getLossName() {
        return this.lossName;
    }
    public void setLossName(String lossName) {
        this.lossName = lossName;
    }
    public String getRiskName() {
        return this.riskName;
    }
    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }
    public String getRiskCode() {
        return this.riskCode;
    }
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }
    public String getExpensesName() {
        return this.expensesName;
    }
    public void setExpensesName(String expensesName) {
        this.expensesName = expensesName;
    }
    public double getLossPrice() {
        return this.lossPrice;
    }
    public void setLossPrice(double lossPrice) {
        this.lossPrice = lossPrice;
    }
    public String getLossDegreeDescription() {
        return this.lossDegreeDescription;
    }
    public void setLossDegreeDescription(String lossDegreeDescription) {
        this.lossDegreeDescription = lossDegreeDescription;
    }
    public String getFlowId() {
        return this.flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    public Integer getSerialNo() {
        return this.serialNo;
    }
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }
    public String getAdditionalFlag() {
        return this.additionalFlag;
    }
    public void setAdditionalFlag(String additionalFlag) {
        this.additionalFlag = additionalFlag;
    }
}
