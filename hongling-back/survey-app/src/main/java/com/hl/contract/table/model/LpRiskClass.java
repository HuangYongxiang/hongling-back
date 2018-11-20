package com.hl.contract.table.model;

/**
 * Created by liyu on 2017/11/27.
 * 风险等级
 */

public class LpRiskClass {
    /**主键*/
    private String id;
    /**报案表主键*/
    private String reportId;
    /**定损单ID lp_evaluation_car 主键*/
    private String evalId;
    /**风险分类*/
    private String riskClass;
    /**风险分值*/
    private Integer classScore;
    /**删除标志*/
    private String delFlag;
    /**出险次数*/
    private Double num;
    /**调用时间*/
    private String time;
    /**风险调用标示*/
    private String riskFlag;
    /**批次号*/
    private Double bacthNo;

    public Double getBacthNo() {
        return bacthNo;
    }

    public void setBacthNo(Double bacthNo) {
        this.bacthNo = bacthNo;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
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

    public String getRiskClass() {
        return riskClass;
    }

    public void setRiskClass(String riskClass) {
        this.riskClass = riskClass == null ? null : riskClass.trim();
    }

    public Integer getClassScore() {
        return classScore;
    }

    public void setClassScore(Integer classScore) {
        this.classScore = classScore;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getRiskFlag() {
        return riskFlag;
    }

    public void setRiskFlag(String riskFlag) {
        this.riskFlag = riskFlag == null ? null : riskFlag.trim();
    }
}
