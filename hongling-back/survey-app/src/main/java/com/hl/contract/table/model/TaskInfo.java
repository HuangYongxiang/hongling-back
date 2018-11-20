package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create data： 2017/6/22.
 * Author: liyu
 * Function:调度信息，多条记录，一次传多条记录
 */
@Entity
public class TaskInfo {
    @Id
    private String id;
    private String reportCode;//报案号
    private String flowId;//案件主键
    private String dispatchType;//调度类型 1正常调度   2新增标的
    private String taskNo;//任务号
    private String taskCode;//任务类型代码（0101查勘 0201 本车 0202 三者  0301 物损  0401人伤）
    private String flowCode;//流程平台代码(05定损 06核价 07核损 )??lossType
    private String mainVeFlag;//主车标志 1主车    2	三者车
    private String lossName;//损失名称
    private String dispatchPersonId;//调度人id
    private String dispatchPersonName;//调度人姓名
    private String dispatchTime;//调度时间
    private String remark;//调度说明
    private String evaluationPersonId;//接收人代码
    private String evaluationPersonName;//接收人姓名,就是定损人
    private String dealCompCode;//处理机构ID
    private String dealCompName;//处理机构名称
    private String statusCode;//001调度、002改派STATE_CODE 案件任务状态（状态写在常量类中）
    private String sendPhoneFlag;//发送手机成功标志 手机手动接收标志
    private String accidentPlace;//出险地点 来自ReportInfo
    private String reportTime;//报案时间 来自ReportInfo
    private String reportPersonName;//报案人姓名 来自ReportInfo
    private String plateNo;//号牌号码 来自承保车辆信息（标的车）
    private double completeDegree;//填写完成程度
    private String completeFlag;//是否完成标记   0 未完成    1完成
    private String childStateCode;//案件子状态代码（状态写在常量类中） 057退回
    @Generated(hash = 1561123404)
    public TaskInfo(String id, String reportCode, String flowId,
                    String dispatchType, String taskNo, String taskCode, String flowCode,
                    String mainVeFlag, String lossName, String dispatchPersonId,
                    String dispatchPersonName, String dispatchTime, String remark,
                    String evaluationPersonId, String evaluationPersonName,
                    String dealCompCode, String dealCompName, String statusCode,
                    String sendPhoneFlag, String accidentPlace, String reportTime,
                    String reportPersonName, String plateNo, double completeDegree,
                    String completeFlag, String childStateCode) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.dispatchType = dispatchType;
        this.taskNo = taskNo;
        this.taskCode = taskCode;
        this.flowCode = flowCode;
        this.mainVeFlag = mainVeFlag;
        this.lossName = lossName;
        this.dispatchPersonId = dispatchPersonId;
        this.dispatchPersonName = dispatchPersonName;
        this.dispatchTime = dispatchTime;
        this.remark = remark;
        this.evaluationPersonId = evaluationPersonId;
        this.evaluationPersonName = evaluationPersonName;
        this.dealCompCode = dealCompCode;
        this.dealCompName = dealCompName;
        this.statusCode = statusCode;
        this.sendPhoneFlag = sendPhoneFlag;
        this.accidentPlace = accidentPlace;
        this.reportTime = reportTime;
        this.reportPersonName = reportPersonName;
        this.plateNo = plateNo;
        this.completeDegree = completeDegree;
        this.completeFlag = completeFlag;
        this.childStateCode = childStateCode;
    }
    @Generated(hash = 2022720704)
    public TaskInfo() {
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
    public String getFlowId() {
        return this.flowId;
    }
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    public String getDispatchType() {
        return this.dispatchType;
    }
    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }
    public String getTaskNo() {
        return this.taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
    public String getTaskCode() {
        return this.taskCode;
    }
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
    public String getFlowCode() {
        return this.flowCode;
    }
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }
    public String getMainVeFlag() {
        return this.mainVeFlag;
    }
    public void setMainVeFlag(String mainVeFlag) {
        this.mainVeFlag = mainVeFlag;
    }
    public String getLossName() {
        return this.lossName;
    }
    public void setLossName(String lossName) {
        this.lossName = lossName;
    }
    public String getDispatchPersonId() {
        return this.dispatchPersonId;
    }
    public void setDispatchPersonId(String dispatchPersonId) {
        this.dispatchPersonId = dispatchPersonId;
    }
    public String getDispatchPersonName() {
        return this.dispatchPersonName;
    }
    public void setDispatchPersonName(String dispatchPersonName) {
        this.dispatchPersonName = dispatchPersonName;
    }
    public String getDispatchTime() {
        return this.dispatchTime;
    }
    public void setDispatchTime(String dispatchTime) {
        this.dispatchTime = dispatchTime;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getEvaluationPersonId() {
        return this.evaluationPersonId;
    }
    public void setEvaluationPersonId(String evaluationPersonId) {
        this.evaluationPersonId = evaluationPersonId;
    }
    public String getEvaluationPersonName() {
        return this.evaluationPersonName;
    }
    public void setEvaluationPersonName(String evaluationPersonName) {
        this.evaluationPersonName = evaluationPersonName;
    }
    public String getDealCompCode() {
        return this.dealCompCode;
    }
    public void setDealCompCode(String dealCompCode) {
        this.dealCompCode = dealCompCode;
    }
    public String getDealCompName() {
        return this.dealCompName;
    }
    public void setDealCompName(String dealCompName) {
        this.dealCompName = dealCompName;
    }
    public String getStatusCode() {
        return this.statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getSendPhoneFlag() {
        return this.sendPhoneFlag;
    }
    public void setSendPhoneFlag(String sendPhoneFlag) {
        this.sendPhoneFlag = sendPhoneFlag;
    }
    public String getAccidentPlace() {
        return this.accidentPlace;
    }
    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }
    public String getReportTime() {
        return this.reportTime;
    }
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
    public String getReportPersonName() {
        return this.reportPersonName;
    }
    public void setReportPersonName(String reportPersonName) {
        this.reportPersonName = reportPersonName;
    }
    public String getPlateNo() {
        return this.plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public double getCompleteDegree() {
        return this.completeDegree;
    }
    public void setCompleteDegree(double completeDegree) {
        this.completeDegree = completeDegree;
    }
    public String getCompleteFlag() {
        return this.completeFlag;
    }
    public void setCompleteFlag(String completeFlag) {
        this.completeFlag = completeFlag;
    }
    public String getChildStateCode() {
        return this.childStateCode;
    }
    public void setChildStateCode(String childStateCode) {
        this.childStateCode = childStateCode;
    }
}
