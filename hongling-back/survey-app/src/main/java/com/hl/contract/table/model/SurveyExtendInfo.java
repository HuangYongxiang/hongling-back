package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create data： 2017/5/12.
 * Author: Liyu
 * Function:查勘扩展信息
 */

@Entity
public class SurveyExtendInfo {
    @Id
    private String id;
    private String reportNo;//报案号
    private String flowId;//任务流水号
    private String surveyreportCode;//勘察项id
    private String surveyreportName;//勘察项name
    private String surveyreportValue;//项值
    private String surveyreportMemo;//项说明
    private String date;//报告时间
    private String memo;//备注
    private String recordNme;//填写人
    private String empCde;//操作人账号
    private String requiredFlag; //是否必填项  1: 是 ，0 否
    private String spinnerType; //
    @Generated(hash = 787325193)
    public SurveyExtendInfo(String id, String reportNo, String flowId,
                            String surveyreportCode, String surveyreportName,
                            String surveyreportValue, String surveyreportMemo, String date,
                            String memo, String recordNme, String empCde, String requiredFlag,
                            String spinnerType) {
        this.id = id;
        this.reportNo = reportNo;
        this.flowId = flowId;
        this.surveyreportCode = surveyreportCode;
        this.surveyreportName = surveyreportName;
        this.surveyreportValue = surveyreportValue;
        this.surveyreportMemo = surveyreportMemo;
        this.date = date;
        this.memo = memo;
        this.recordNme = recordNme;
        this.empCde = empCde;
        this.requiredFlag = requiredFlag;
        this.spinnerType = spinnerType;
    }
    @Generated(hash = 1476705262)
    public SurveyExtendInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getReportNo() {
        return this.reportNo;
    }
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }
    public String getSurveyreportCode() {
        return this.surveyreportCode;
    }
    public void setSurveyreportCode(String surveyreportCode) {
        this.surveyreportCode = surveyreportCode;
    }
    public String getSurveyreportName() {
        return this.surveyreportName;
    }
    public void setSurveyreportName(String surveyreportName) {
        this.surveyreportName = surveyreportName;
    }
    public String getSurveyreportValue() {
        return this.surveyreportValue;
    }
    public void setSurveyreportValue(String surveyreportValue) {
        this.surveyreportValue = surveyreportValue;
    }
    public String getSurveyreportMemo() {
        return this.surveyreportMemo;
    }
    public void setSurveyreportMemo(String surveyreportMemo) {
        this.surveyreportMemo = surveyreportMemo;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getRecordNme() {
        return this.recordNme;
    }
    public void setRecordNme(String recordNme) {
        this.recordNme = recordNme;
    }
    public String getEmpCde() {
        return this.empCde;
    }
    public void setEmpCde(String empCde) {
        this.empCde = empCde;
    }
    public String getRequiredFlag() {
        return this.requiredFlag;
    }
    public void setRequiredFlag(String requiredFlag) {
        this.requiredFlag = requiredFlag;
    }
    public String getSpinnerType() {
        return this.spinnerType;
    }
    public void setSpinnerType(String spinnerType) {
        this.spinnerType = spinnerType;
    }
    public String getFlowId() {
        return this.flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }


}
