package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create data： 2017/7/14.
 * Author: liyu
 * Function:人伤基本信息
 */
@Entity
public class InjuryMainInfo {
    /*** 主信息*/

    @Id
    private String id;
    private String reportCode;//报案号
    private String taskNo;
    private String evalId;
    private String taskType;//任务类型
    private String taskTypeCode;//任务类型编码
    private String lossType;//损失类型
    private String lossTypeCode;//损失类型
    private String operateTime;//操作时间
    private String completedflag;//完成标志
    private String rebackFlag;//退回标记
    private double medicalSumFee;//@医疗费用合计
    private double disabilitySumFee;//@死亡伤残费用合计
    private double sumFee;//@总费用
    private String createBy;//创建人员
    private String createDate;//创建时间
    private String updateBy;//修改人员
    private String updateDate;//修改时间

    /*** 基本信息*/
    private String injuryName;//@受伤人姓名
    private String injurySex;//@受伤人性别
    private String injuryAge;//@受伤人年龄
    private String injuryPhone;//@受伤人联系电话
    private String idCardType;//@证件类型
    private String idCardTypeCode;//证件类型编码
    private String idCardNo;//@证件号码
    private String injuryDate;//@人伤受理时间
    private String riskCode;//险种代码
    private String riskName;//@险种名称
    private String itemCode;//险别代码
    private String itemName;//@险别名称
    private String injuryPart;//@受伤部位
    private String dangerSeat;//@出险座位

    private String injuryType;//@人伤类型
    private String injuryTypeCode;//人伤类型编码
    private String casualtyType;//@伤亡类型
    private String casualtyTypeCode;//伤亡类型编码
    private String injuryGrade;//@伤残级别
    private String injuryGradeCode;//伤残级别编码
    private String injuryDegree;//@受伤程度
    private String injuryDegreeCode;//受伤程度编码
    private String injuryDescription;//@伤情描述
    private String surveyReport;//@查勘报告
    private String visitHospital;//@就诊医院
    private String visitMode;//@就诊方式
    private String visitModeCode;//@就诊方式编码
    private String isSelfMedical;//@是否自行就医标记    1是  0否

    /*** 隐藏信息*/
    private String householdRegister;//@户籍
    private String profession;//@工作身份
    private String professionCode;//@工作身份编码
    private String workAddr;//@工作单位
    private String workType;//@工种分类
    private String workTypeCode;//工种分类编码
    private String income;//@收入情况
    private String incomeCode;//@收入情况编码
    private String incomeByMonth;//@月收入小计
    private String trackTimes;//@跟踪次数
    private String trackDetailDescription;//@跟踪明细描述
    private String deadTime;//@死亡时间
    private String identificationInstitution;//@伤残鉴定机构
    private String identificationInstitutionCode;  //伤残鉴定机构编码
    private String isInsuranceLiability;//@是否属于保险责任标记   1是  0否
    private String paySelfFlag;//@互碰自赔标记    1是  0否
    private String noLiabilityCompensation;//@无责代赔标记    1是  0否
    private String isTrackOver;//@是否跟踪完毕
    @Generated(hash = 1402032458)
    public InjuryMainInfo(String id, String reportCode, String taskNo,
            String evalId, String taskType, String taskTypeCode, String lossType,
            String lossTypeCode, String operateTime, String completedflag,
            String rebackFlag, double medicalSumFee, double disabilitySumFee,
            double sumFee, String createBy, String createDate, String updateBy,
            String updateDate, String injuryName, String injurySex,
            String injuryAge, String injuryPhone, String idCardType,
            String idCardTypeCode, String idCardNo, String injuryDate,
            String riskCode, String riskName, String itemCode, String itemName,
            String injuryPart, String dangerSeat, String injuryType,
            String injuryTypeCode, String casualtyType, String casualtyTypeCode,
            String injuryGrade, String injuryGradeCode, String injuryDegree,
            String injuryDegreeCode, String injuryDescription, String surveyReport,
            String visitHospital, String visitMode, String visitModeCode,
            String isSelfMedical, String householdRegister, String profession,
            String professionCode, String workAddr, String workType,
            String workTypeCode, String income, String incomeCode,
            String incomeByMonth, String trackTimes, String trackDetailDescription,
            String deadTime, String identificationInstitution,
            String identificationInstitutionCode, String isInsuranceLiability,
            String paySelfFlag, String noLiabilityCompensation,
            String isTrackOver) {
        this.id = id;
        this.reportCode = reportCode;
        this.taskNo = taskNo;
        this.evalId = evalId;
        this.taskType = taskType;
        this.taskTypeCode = taskTypeCode;
        this.lossType = lossType;
        this.lossTypeCode = lossTypeCode;
        this.operateTime = operateTime;
        this.completedflag = completedflag;
        this.rebackFlag = rebackFlag;
        this.medicalSumFee = medicalSumFee;
        this.disabilitySumFee = disabilitySumFee;
        this.sumFee = sumFee;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.injuryName = injuryName;
        this.injurySex = injurySex;
        this.injuryAge = injuryAge;
        this.injuryPhone = injuryPhone;
        this.idCardType = idCardType;
        this.idCardTypeCode = idCardTypeCode;
        this.idCardNo = idCardNo;
        this.injuryDate = injuryDate;
        this.riskCode = riskCode;
        this.riskName = riskName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.injuryPart = injuryPart;
        this.dangerSeat = dangerSeat;
        this.injuryType = injuryType;
        this.injuryTypeCode = injuryTypeCode;
        this.casualtyType = casualtyType;
        this.casualtyTypeCode = casualtyTypeCode;
        this.injuryGrade = injuryGrade;
        this.injuryGradeCode = injuryGradeCode;
        this.injuryDegree = injuryDegree;
        this.injuryDegreeCode = injuryDegreeCode;
        this.injuryDescription = injuryDescription;
        this.surveyReport = surveyReport;
        this.visitHospital = visitHospital;
        this.visitMode = visitMode;
        this.visitModeCode = visitModeCode;
        this.isSelfMedical = isSelfMedical;
        this.householdRegister = householdRegister;
        this.profession = profession;
        this.professionCode = professionCode;
        this.workAddr = workAddr;
        this.workType = workType;
        this.workTypeCode = workTypeCode;
        this.income = income;
        this.incomeCode = incomeCode;
        this.incomeByMonth = incomeByMonth;
        this.trackTimes = trackTimes;
        this.trackDetailDescription = trackDetailDescription;
        this.deadTime = deadTime;
        this.identificationInstitution = identificationInstitution;
        this.identificationInstitutionCode = identificationInstitutionCode;
        this.isInsuranceLiability = isInsuranceLiability;
        this.paySelfFlag = paySelfFlag;
        this.noLiabilityCompensation = noLiabilityCompensation;
        this.isTrackOver = isTrackOver;
    }
    @Generated(hash = 1340100270)
    public InjuryMainInfo() {
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
    public String getTaskNo() {
        return this.taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
    public String getEvalId() {
        return this.evalId;
    }
    public void setEvalId(String evalId) {
        this.evalId = evalId;
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
    public String getOperateTime() {
        return this.operateTime;
    }
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
    public String getCompletedflag() {
        return this.completedflag;
    }
    public void setCompletedflag(String completedflag) {
        this.completedflag = completedflag;
    }
    public String getRebackFlag() {
        return this.rebackFlag;
    }
    public void setRebackFlag(String rebackFlag) {
        this.rebackFlag = rebackFlag;
    }
    public double getMedicalSumFee() {
        return this.medicalSumFee;
    }
    public void setMedicalSumFee(double medicalSumFee) {
        this.medicalSumFee = medicalSumFee;
    }
    public double getDisabilitySumFee() {
        return this.disabilitySumFee;
    }
    public void setDisabilitySumFee(double disabilitySumFee) {
        this.disabilitySumFee = disabilitySumFee;
    }
    public double getSumFee() {
        return this.sumFee;
    }
    public void setSumFee(double sumFee) {
        this.sumFee = sumFee;
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
    public String getInjuryName() {
        return this.injuryName;
    }
    public void setInjuryName(String injuryName) {
        this.injuryName = injuryName;
    }
    public String getInjurySex() {
        return this.injurySex;
    }
    public void setInjurySex(String injurySex) {
        this.injurySex = injurySex;
    }
    public String getInjuryAge() {
        return this.injuryAge;
    }
    public void setInjuryAge(String injuryAge) {
        this.injuryAge = injuryAge;
    }
    public String getInjuryPhone() {
        return this.injuryPhone;
    }
    public void setInjuryPhone(String injuryPhone) {
        this.injuryPhone = injuryPhone;
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
    public String getInjuryDate() {
        return this.injuryDate;
    }
    public void setInjuryDate(String injuryDate) {
        this.injuryDate = injuryDate;
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
    public String getInjuryPart() {
        return this.injuryPart;
    }
    public void setInjuryPart(String injuryPart) {
        this.injuryPart = injuryPart;
    }
    public String getDangerSeat() {
        return this.dangerSeat;
    }
    public void setDangerSeat(String dangerSeat) {
        this.dangerSeat = dangerSeat;
    }
    public String getInjuryType() {
        return this.injuryType;
    }
    public void setInjuryType(String injuryType) {
        this.injuryType = injuryType;
    }
    public String getInjuryTypeCode() {
        return this.injuryTypeCode;
    }
    public void setInjuryTypeCode(String injuryTypeCode) {
        this.injuryTypeCode = injuryTypeCode;
    }
    public String getCasualtyType() {
        return this.casualtyType;
    }
    public void setCasualtyType(String casualtyType) {
        this.casualtyType = casualtyType;
    }
    public String getCasualtyTypeCode() {
        return this.casualtyTypeCode;
    }
    public void setCasualtyTypeCode(String casualtyTypeCode) {
        this.casualtyTypeCode = casualtyTypeCode;
    }
    public String getInjuryGrade() {
        return this.injuryGrade;
    }
    public void setInjuryGrade(String injuryGrade) {
        this.injuryGrade = injuryGrade;
    }
    public String getInjuryGradeCode() {
        return this.injuryGradeCode;
    }
    public void setInjuryGradeCode(String injuryGradeCode) {
        this.injuryGradeCode = injuryGradeCode;
    }
    public String getInjuryDegree() {
        return this.injuryDegree;
    }
    public void setInjuryDegree(String injuryDegree) {
        this.injuryDegree = injuryDegree;
    }
    public String getInjuryDegreeCode() {
        return this.injuryDegreeCode;
    }
    public void setInjuryDegreeCode(String injuryDegreeCode) {
        this.injuryDegreeCode = injuryDegreeCode;
    }
    public String getInjuryDescription() {
        return this.injuryDescription;
    }
    public void setInjuryDescription(String injuryDescription) {
        this.injuryDescription = injuryDescription;
    }
    public String getSurveyReport() {
        return this.surveyReport;
    }
    public void setSurveyReport(String surveyReport) {
        this.surveyReport = surveyReport;
    }
    public String getVisitHospital() {
        return this.visitHospital;
    }
    public void setVisitHospital(String visitHospital) {
        this.visitHospital = visitHospital;
    }
    public String getVisitMode() {
        return this.visitMode;
    }
    public void setVisitMode(String visitMode) {
        this.visitMode = visitMode;
    }
    public String getVisitModeCode() {
        return this.visitModeCode;
    }
    public void setVisitModeCode(String visitModeCode) {
        this.visitModeCode = visitModeCode;
    }
    public String getIsSelfMedical() {
        return this.isSelfMedical;
    }
    public void setIsSelfMedical(String isSelfMedical) {
        this.isSelfMedical = isSelfMedical;
    }
    public String getHouseholdRegister() {
        return this.householdRegister;
    }
    public void setHouseholdRegister(String householdRegister) {
        this.householdRegister = householdRegister;
    }
    public String getProfession() {
        return this.profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getProfessionCode() {
        return this.professionCode;
    }
    public void setProfessionCode(String professionCode) {
        this.professionCode = professionCode;
    }
    public String getWorkAddr() {
        return this.workAddr;
    }
    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }
    public String getWorkType() {
        return this.workType;
    }
    public void setWorkType(String workType) {
        this.workType = workType;
    }
    public String getWorkTypeCode() {
        return this.workTypeCode;
    }
    public void setWorkTypeCode(String workTypeCode) {
        this.workTypeCode = workTypeCode;
    }
    public String getIncome() {
        return this.income;
    }
    public void setIncome(String income) {
        this.income = income;
    }
    public String getIncomeCode() {
        return this.incomeCode;
    }
    public void setIncomeCode(String incomeCode) {
        this.incomeCode = incomeCode;
    }
    public String getIncomeByMonth() {
        return this.incomeByMonth;
    }
    public void setIncomeByMonth(String incomeByMonth) {
        this.incomeByMonth = incomeByMonth;
    }
    public String getTrackTimes() {
        return this.trackTimes;
    }
    public void setTrackTimes(String trackTimes) {
        this.trackTimes = trackTimes;
    }
    public String getTrackDetailDescription() {
        return this.trackDetailDescription;
    }
    public void setTrackDetailDescription(String trackDetailDescription) {
        this.trackDetailDescription = trackDetailDescription;
    }
    public String getDeadTime() {
        return this.deadTime;
    }
    public void setDeadTime(String deadTime) {
        this.deadTime = deadTime;
    }
    public String getIdentificationInstitution() {
        return this.identificationInstitution;
    }
    public void setIdentificationInstitution(String identificationInstitution) {
        this.identificationInstitution = identificationInstitution;
    }
    public String getIdentificationInstitutionCode() {
        return this.identificationInstitutionCode;
    }
    public void setIdentificationInstitutionCode(
            String identificationInstitutionCode) {
        this.identificationInstitutionCode = identificationInstitutionCode;
    }
    public String getIsInsuranceLiability() {
        return this.isInsuranceLiability;
    }
    public void setIsInsuranceLiability(String isInsuranceLiability) {
        this.isInsuranceLiability = isInsuranceLiability;
    }
    public String getPaySelfFlag() {
        return this.paySelfFlag;
    }
    public void setPaySelfFlag(String paySelfFlag) {
        this.paySelfFlag = paySelfFlag;
    }
    public String getNoLiabilityCompensation() {
        return this.noLiabilityCompensation;
    }
    public void setNoLiabilityCompensation(String noLiabilityCompensation) {
        this.noLiabilityCompensation = noLiabilityCompensation;
    }
    public String getIsTrackOver() {
        return this.isTrackOver;
    }
    public void setIsTrackOver(String isTrackOver) {
        this.isTrackOver = isTrackOver;
    }
  

}
