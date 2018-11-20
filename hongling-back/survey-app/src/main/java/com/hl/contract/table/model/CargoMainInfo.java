package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create data： 2017/7/16.
 * Author: liyu
 * Function:财产损失信息：主信息
 */
@Entity
public class CargoMainInfo {

    /** 主信息*/
    @Id
    private String id;
    private String reportCode;//报案号
    private String flowId;
    private String evalId;//定损主键
    private String lossNo;//定损单号

    private String taskType;//任务类型
    private String taskTypeCode;//任务类型编码


    private String accidentAreaCode;//平台编码
    private String createBy;//创建人员
    private String createDate;//创建时间
    private String updateBy;//修改人员
    private String updateDate;//修改时间
    private String evaluationPersonCode;//定损人代码
    private String evaluationPersonId;//定损人ID
    private String evaluationPersonName;//定损人姓名
    private String approvePersonCode;//核损人代码
    private String approvePersonId;//核损人ID
    private String approvePersonName;//核损人姓名


    /** 基本信息*/
    private String linkName;//@物主姓名；联系人
    private String idCardType;//@证件类型
    private String idCardTypeCode;//证件类型编码
    private String idCardNo;//@证件号码
    private String telephone;//@联系电话


    private String lossType;//@损失类型  1-主车财产损失 2-第三者车财产损失 3-第三者其他财产损失
    private String lossTypeCode;//损失类型编码  1-主车财产损失 2-第三者车财产损失 3-第三者其他财产损失
    private String lossObjectName;//损失标的名称
    private String evalDate;//@定损日期
    private String evalRemainsSum;//@定损残值合计
    private String evalItemSum;//@定损明细合计
    private String evalForceSum;//@定损交强险金额
    private String evalSum;//@定损最终金额  （明细合计-残值合计）
    private String evalSumFirst;//@首次定损金额
    private String approveRemainsSum;//核损残值合计
    private String approveItemSum;//核损明细合计
    private String approveForceSum;//核损交强险金额
    private String approveSum;//核损最终金额
    private String evalNotion;//@定损、核价、核损意见;定损单意见

    private String riskCode;//险种代码
    private String riskName;//@险种名称
    private String itemCode;//险别代码
    private String itemName;//@险别名称
    private String cargoDuty;//@事故责任 0无责,1次责,2主责,3全责
    private String cargoDutyCode;//事故责任代码 0无责,1次责,2主责,3全责
    private String cargoDutyPercent;//@事故责任系数;事故责任比例



    /** 隐藏信息*/
    private String subrogationFlag;//@是否代位求偿     1是  0否
    private String whetherLawsuit;//@是否诉讼/仲裁： 1是  0否
    private String fitBackPersonId;//旧件回收员ID
    private String fitBackPersonName;//@旧件回收员名称
    private String resurveyPersonId;//复勘员ID
    private String resurveyPersonName;//@复勘员名称
    private String recheckFitBackFlag;//@复勘标志
    private String recheckRemainsSum;//@复勘残值合计
    private String recheckItemSum;//@复勘明细合计
    private String recheckSum;//@复勘最终金额
    @Generated(hash = 1884469961)
    public CargoMainInfo(String id, String reportCode, String flowId, String evalId,
                         String lossNo, String taskType, String taskTypeCode,
                         String accidentAreaCode, String createBy, String createDate,
                         String updateBy, String updateDate, String evaluationPersonCode,
                         String evaluationPersonId, String evaluationPersonName,
                         String approvePersonCode, String approvePersonId,
                         String approvePersonName, String linkName, String idCardType,
                         String idCardTypeCode, String idCardNo, String telephone,
                         String lossType, String lossTypeCode, String lossObjectName,
                         String evalDate, String evalRemainsSum, String evalItemSum,
                         String evalForceSum, String evalSum, String evalSumFirst,
                         String approveRemainsSum, String approveItemSum, String approveForceSum,
                         String approveSum, String evalNotion, String riskCode, String riskName,
                         String itemCode, String itemName, String cargoDuty,
                         String cargoDutyCode, String cargoDutyPercent, String subrogationFlag,
                         String whetherLawsuit, String fitBackPersonId, String fitBackPersonName,
                         String resurveyPersonId, String resurveyPersonName,
                         String recheckFitBackFlag, String recheckRemainsSum,
                         String recheckItemSum, String recheckSum) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.evalId = evalId;
        this.lossNo = lossNo;
        this.taskType = taskType;
        this.taskTypeCode = taskTypeCode;
        this.accidentAreaCode = accidentAreaCode;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.evaluationPersonCode = evaluationPersonCode;
        this.evaluationPersonId = evaluationPersonId;
        this.evaluationPersonName = evaluationPersonName;
        this.approvePersonCode = approvePersonCode;
        this.approvePersonId = approvePersonId;
        this.approvePersonName = approvePersonName;
        this.linkName = linkName;
        this.idCardType = idCardType;
        this.idCardTypeCode = idCardTypeCode;
        this.idCardNo = idCardNo;
        this.telephone = telephone;
        this.lossType = lossType;
        this.lossTypeCode = lossTypeCode;
        this.lossObjectName = lossObjectName;
        this.evalDate = evalDate;
        this.evalRemainsSum = evalRemainsSum;
        this.evalItemSum = evalItemSum;
        this.evalForceSum = evalForceSum;
        this.evalSum = evalSum;
        this.evalSumFirst = evalSumFirst;
        this.approveRemainsSum = approveRemainsSum;
        this.approveItemSum = approveItemSum;
        this.approveForceSum = approveForceSum;
        this.approveSum = approveSum;
        this.evalNotion = evalNotion;
        this.riskCode = riskCode;
        this.riskName = riskName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.cargoDuty = cargoDuty;
        this.cargoDutyCode = cargoDutyCode;
        this.cargoDutyPercent = cargoDutyPercent;
        this.subrogationFlag = subrogationFlag;
        this.whetherLawsuit = whetherLawsuit;
        this.fitBackPersonId = fitBackPersonId;
        this.fitBackPersonName = fitBackPersonName;
        this.resurveyPersonId = resurveyPersonId;
        this.resurveyPersonName = resurveyPersonName;
        this.recheckFitBackFlag = recheckFitBackFlag;
        this.recheckRemainsSum = recheckRemainsSum;
        this.recheckItemSum = recheckItemSum;
        this.recheckSum = recheckSum;
    }
    @Generated(hash = 349313164)
    public CargoMainInfo() {
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
    public String getEvalId() {
        return this.evalId;
    }
    public void setEvalId(String evalId) {
        this.evalId = evalId;
    }
    public String getLossNo() {
        return this.lossNo;
    }
    public void setLossNo(String lossNo) {
        this.lossNo = lossNo;
    }
    public String getTaskType() {
        return this.taskType;
    }
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
    public String getTaskTypeCode() {
        return this.taskTypeCode;
    }
    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }
    public String getAccidentAreaCode() {
        return this.accidentAreaCode;
    }
    public void setAccidentAreaCode(String accidentAreaCode) {
        this.accidentAreaCode = accidentAreaCode;
    }
    public String getCreateBy() {
        return this.createBy;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public String getCreateDate() {
        return this.createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getUpdateBy() {
        return this.updateBy;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    public String getUpdateDate() {
        return this.updateDate;
    }
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
    public String getEvaluationPersonCode() {
        return this.evaluationPersonCode;
    }
    public void setEvaluationPersonCode(String evaluationPersonCode) {
        this.evaluationPersonCode = evaluationPersonCode;
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
    public String getApprovePersonCode() {
        return this.approvePersonCode;
    }
    public void setApprovePersonCode(String approvePersonCode) {
        this.approvePersonCode = approvePersonCode;
    }
    public String getApprovePersonId() {
        return this.approvePersonId;
    }
    public void setApprovePersonId(String approvePersonId) {
        this.approvePersonId = approvePersonId;
    }
    public String getApprovePersonName() {
        return this.approvePersonName;
    }
    public void setApprovePersonName(String approvePersonName) {
        this.approvePersonName = approvePersonName;
    }
    public String getLinkName() {
        return this.linkName;
    }
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
    public String getIdCardType() {
        return this.idCardType;
    }
    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }
    public String getIdCardTypeCode() {
        return this.idCardTypeCode;
    }
    public void setIdCardTypeCode(String idCardTypeCode) {
        this.idCardTypeCode = idCardTypeCode;
    }
    public String getIdCardNo() {
        return this.idCardNo;
    }
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getLossType() {
        return this.lossType;
    }
    public void setLossType(String lossType) {
        this.lossType = lossType;
    }
    public String getLossTypeCode() {
        return this.lossTypeCode;
    }
    public void setLossTypeCode(String lossTypeCode) {
        this.lossTypeCode = lossTypeCode;
    }
    public String getLossObjectName() {
        return this.lossObjectName;
    }
    public void setLossObjectName(String lossObjectName) {
        this.lossObjectName = lossObjectName;
    }
    public String getEvalDate() {
        return this.evalDate;
    }
    public void setEvalDate(String evalDate) {
        this.evalDate = evalDate;
    }
    public String getEvalRemainsSum() {
        return this.evalRemainsSum;
    }
    public void setEvalRemainsSum(String evalRemainsSum) {
        this.evalRemainsSum = evalRemainsSum;
    }
    public String getEvalItemSum() {
        return this.evalItemSum;
    }
    public void setEvalItemSum(String evalItemSum) {
        this.evalItemSum = evalItemSum;
    }
    public String getEvalForceSum() {
        return this.evalForceSum;
    }
    public void setEvalForceSum(String evalForceSum) {
        this.evalForceSum = evalForceSum;
    }
    public String getEvalSum() {
        return this.evalSum;
    }
    public void setEvalSum(String evalSum) {
        this.evalSum = evalSum;
    }
    public String getEvalSumFirst() {
        return this.evalSumFirst;
    }
    public void setEvalSumFirst(String evalSumFirst) {
        this.evalSumFirst = evalSumFirst;
    }
    public String getApproveRemainsSum() {
        return this.approveRemainsSum;
    }
    public void setApproveRemainsSum(String approveRemainsSum) {
        this.approveRemainsSum = approveRemainsSum;
    }
    public String getApproveItemSum() {
        return this.approveItemSum;
    }
    public void setApproveItemSum(String approveItemSum) {
        this.approveItemSum = approveItemSum;
    }
    public String getApproveForceSum() {
        return this.approveForceSum;
    }
    public void setApproveForceSum(String approveForceSum) {
        this.approveForceSum = approveForceSum;
    }
    public String getApproveSum() {
        return this.approveSum;
    }
    public void setApproveSum(String approveSum) {
        this.approveSum = approveSum;
    }
    public String getEvalNotion() {
        return this.evalNotion;
    }
    public void setEvalNotion(String evalNotion) {
        this.evalNotion = evalNotion;
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
    public String getCargoDuty() {
        return this.cargoDuty;
    }
    public void setCargoDuty(String cargoDuty) {
        this.cargoDuty = cargoDuty;
    }
    public String getCargoDutyCode() {
        return this.cargoDutyCode;
    }
    public void setCargoDutyCode(String cargoDutyCode) {
        this.cargoDutyCode = cargoDutyCode;
    }
    public String getCargoDutyPercent() {
        return this.cargoDutyPercent;
    }
    public void setCargoDutyPercent(String cargoDutyPercent) {
        this.cargoDutyPercent = cargoDutyPercent;
    }
    public String getSubrogationFlag() {
        return this.subrogationFlag;
    }
    public void setSubrogationFlag(String subrogationFlag) {
        this.subrogationFlag = subrogationFlag;
    }
    public String getWhetherLawsuit() {
        return this.whetherLawsuit;
    }
    public void setWhetherLawsuit(String whetherLawsuit) {
        this.whetherLawsuit = whetherLawsuit;
    }
    public String getFitBackPersonId() {
        return this.fitBackPersonId;
    }
    public void setFitBackPersonId(String fitBackPersonId) {
        this.fitBackPersonId = fitBackPersonId;
    }
    public String getFitBackPersonName() {
        return this.fitBackPersonName;
    }
    public void setFitBackPersonName(String fitBackPersonName) {
        this.fitBackPersonName = fitBackPersonName;
    }
    public String getResurveyPersonId() {
        return this.resurveyPersonId;
    }
    public void setResurveyPersonId(String resurveyPersonId) {
        this.resurveyPersonId = resurveyPersonId;
    }
    public String getResurveyPersonName() {
        return this.resurveyPersonName;
    }
    public void setResurveyPersonName(String resurveyPersonName) {
        this.resurveyPersonName = resurveyPersonName;
    }
    public String getRecheckFitBackFlag() {
        return this.recheckFitBackFlag;
    }
    public void setRecheckFitBackFlag(String recheckFitBackFlag) {
        this.recheckFitBackFlag = recheckFitBackFlag;
    }
    public String getRecheckRemainsSum() {
        return this.recheckRemainsSum;
    }
    public void setRecheckRemainsSum(String recheckRemainsSum) {
        this.recheckRemainsSum = recheckRemainsSum;
    }
    public String getRecheckItemSum() {
        return this.recheckItemSum;
    }
    public void setRecheckItemSum(String recheckItemSum) {
        this.recheckItemSum = recheckItemSum;
    }
    public String getRecheckSum() {
        return this.recheckSum;
    }
    public void setRecheckSum(String recheckSum) {
        this.recheckSum = recheckSum;
    }


}
