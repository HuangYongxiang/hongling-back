package com.hl.contract.table.model;

/**
 * Created by liyu on 2017/11/27.
 * 风险规则
 */

public class LpRiskRules {
    /**主键*/
    private String id;
    /**报案表主键*/
    private String reportId;
    /**定速单ID LP_EVALUATION_CAR主键*/
    private String evalId;
    /**分类主键*/
    private String classId;
    /**规则编码*/
    private String ruleCode;
    /**规则分类*/
    private String ruleType;
    /**模型规则*/
    private String ruleModel;
    /**模型标准*/
    private String ruleStandard;
    /**风险项目*/
    private String riskItem;
    /**当前值 */
    private String currentValue;
    /**标准值*/
    private String standardValue;
    /**偏差值*/
    private String deviationValue;
    /**规则分值*/
    private Integer ruleScore;
    /**击中标志*/
    private String hitFlag;
    /**删除标志*/
    private String delFlag;
    /**风险项目所在的表*/
    private String itemTable;
    /**风险项目主键*/
    private String itemTableId;
    /**风险项目当前定损金额*/
    private String itemCurrentEvalFee;
    /**风险KPI考核标记*/
    private String itemKpiFlag;
    /**风险项目等级（高、中、低）*/
    private String itemRuleGrade;
    /**风险类名称*/
    private String ruleTypeName;
    /**批次号*/
    private Double bacthNo;

    public Double getBacthNo() {
        return bacthNo;
    }

    public void setBacthNo(Double bacthNo) {
        this.bacthNo = bacthNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEvalId() {
        return evalId;
    }

    public void setEvalId(String evalId) {
        this.evalId = evalId == null ? null : evalId.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode == null ? null : ruleCode.trim();
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }

    public String getRuleModel() {
        return ruleModel;
    }

    public void setRuleModel(String ruleModel) {
        this.ruleModel = ruleModel == null ? null : ruleModel.trim();
    }

    public String getRuleStandard() {
        return ruleStandard;
    }

    public void setRuleStandard(String ruleStandard) {
        this.ruleStandard = ruleStandard == null ? null : ruleStandard.trim();
    }

    public String getRiskItem() {
        return riskItem;
    }

    public void setRiskItem(String riskItem) {
        this.riskItem = riskItem == null ? null : riskItem.trim();
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue == null ? null : currentValue.trim();
    }

    public String getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(String standardValue) {
        this.standardValue = standardValue == null ? null : standardValue.trim();
    }

    public String getDeviationValue() {
        return deviationValue;
    }

    public void setDeviationValue(String deviationValue) {
        this.deviationValue = deviationValue == null ? null : deviationValue.trim();
    }

    public Integer getRuleScore() {
        return ruleScore;
    }

    public void setRuleScore(Integer ruleScore) {
        this.ruleScore = ruleScore;
    }

    public String getHitFlag() {
        return hitFlag;
    }

    public void setHitFlag(String hitFlag) {
        this.hitFlag = hitFlag == null ? null : hitFlag.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public String getItemTable() {
        return itemTable;
    }

    public void setItemTable(String itemTable) {
        this.itemTable = itemTable == null ? null : itemTable.trim();
    }

    public String getItemTableId() {
        return itemTableId;
    }

    public void setItemTableId(String itemTableId) {
        this.itemTableId = itemTableId == null ? null : itemTableId.trim();
    }



    public String getItemCurrentEvalFee() {
        return itemCurrentEvalFee;
    }

    public void setItemCurrentEvalFee(String itemCurrentEvalFee) {
        this.itemCurrentEvalFee = itemCurrentEvalFee;
    }

    public String getItemKpiFlag() {
        return itemKpiFlag;
    }

    public void setItemKpiFlag(String itemKpiFlag) {
        this.itemKpiFlag = itemKpiFlag == null ? null : itemKpiFlag.trim();
    }

    public String getItemRuleGrade() {
        return itemRuleGrade;
    }

    public void setItemRuleGrade(String itemRuleGrade) {
        this.itemRuleGrade = itemRuleGrade == null ? null : itemRuleGrade.trim();
    }
    public String getReportId() {
        return reportId;
    }
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getRuleTypeName() {
        return ruleTypeName;
    }

    public void setRuleTypeName(String ruleTypeName) {
        this.ruleTypeName = ruleTypeName;
    }
}
