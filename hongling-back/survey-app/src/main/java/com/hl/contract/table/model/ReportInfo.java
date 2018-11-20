package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * @Describe:  进入定损传报案、保单的数据的情况使用：报案信息
 * @Author: liyu
 * @Date: 2018/1/13 13:07
 * @Copyright: hl
 */
@Entity
public class ReportInfo {
    @Id
    private String id;
    private String userId;//登录人id
    private String reportCode;//报案号
    private String accidentCauseCode;//出险原因代码
    private String accidentCauseName;//出险原因名称
    private String accidentDutyCode;//事故责任代码
    private String accidentDutyName;//事故责任名称
    private String reportPersonName;//报案人姓名
    private String reportMoblePhone;//报案人手机
    private String reportTime;//报案时间
    private String accidentTime;//出险时间
    private String isCurrentReport;//现场报案    1是   0否
    private String manageType;//处理方式
    private String accidentReasonCode;//事故原因代码
    private String accidentReasonName;//事故原因名称
    private String hugeType;//巨灾类型
    private String hugeTypeName;//巨灾类型名称
    private String hugeCode;//巨灾代码
    private String hugeName;//巨灾名称
    private String insuranceCompanyCode;//承保机构编码
    private String insuranceCompanyName;//承保机构名称
    private String reportType;//报案类型
    private String accidentPlace;//出险地点
    private String longitude;//出险地点经度
    private String latitude;//出险地点纬度
    private String accidentCourse;//出险经过
    private String carLossFlag;//是否有车损
    private String injureLossFlag;//是否有人伤
    private String cargoLossFlag;//是否有物损
    private String thiefLossFlag;//是否有盗窃
    private Long injureNum;//死伤人数
    private String accidentArea;//出险区域
    private String accidentWeather;//出险天气
    private String channelCode;//业务渠道代码
    private String channelName;//业务渠道名称

    private double completeDegree;//填写完成程度
    private String completeFlag;//是否完成标记
    //以下4个数据类是用于在首页里显示案件状态的，数值在查询案件列表的时候,完成1,未完成0,本案件没有改条task时值是-1.该数值的更新在把手机上数据传到服务器上时触发。
    private String surveyFlag;//是否完成查勘标记
    private String aimCarFlag;//是否完成标的标记
    private String damageFlag;//是否完成物损标记
    private String thirdCarFlag;//是否完成三者标记
    private String injuryFlag;//是否完成人伤标记
    private String plateNum;//车牌号码 承保车辆信息（标的车）
    private String reportId;//报案ID 业务需求要加
    @Transient
    private List<TaskInfo> taskInfoList;
    @Generated(hash = 1723842263)
    public ReportInfo(String id, String userId, String reportCode, String accidentCauseCode,
                      String accidentCauseName, String accidentDutyCode, String accidentDutyName,
                      String reportPersonName, String reportMoblePhone, String reportTime,
                      String accidentTime, String isCurrentReport, String manageType,
                      String accidentReasonCode, String accidentReasonName, String hugeType,
                      String hugeTypeName, String hugeCode, String hugeName,
                      String insuranceCompanyCode, String insuranceCompanyName, String reportType,
                      String accidentPlace, String longitude, String latitude, String accidentCourse,
                      String carLossFlag, String injureLossFlag, String cargoLossFlag,
                      String thiefLossFlag, Long injureNum, String accidentArea, String accidentWeather,
                      String channelCode, String channelName, double completeDegree,
                      String completeFlag, String surveyFlag, String aimCarFlag, String damageFlag,
                      String thirdCarFlag, String injuryFlag, String plateNum, String reportId) {
        this.id = id;
        this.userId = userId;
        this.reportCode = reportCode;
        this.accidentCauseCode = accidentCauseCode;
        this.accidentCauseName = accidentCauseName;
        this.accidentDutyCode = accidentDutyCode;
        this.accidentDutyName = accidentDutyName;
        this.reportPersonName = reportPersonName;
        this.reportMoblePhone = reportMoblePhone;
        this.reportTime = reportTime;
        this.accidentTime = accidentTime;
        this.isCurrentReport = isCurrentReport;
        this.manageType = manageType;
        this.accidentReasonCode = accidentReasonCode;
        this.accidentReasonName = accidentReasonName;
        this.hugeType = hugeType;
        this.hugeTypeName = hugeTypeName;
        this.hugeCode = hugeCode;
        this.hugeName = hugeName;
        this.insuranceCompanyCode = insuranceCompanyCode;
        this.insuranceCompanyName = insuranceCompanyName;
        this.reportType = reportType;
        this.accidentPlace = accidentPlace;
        this.longitude = longitude;
        this.latitude = latitude;
        this.accidentCourse = accidentCourse;
        this.carLossFlag = carLossFlag;
        this.injureLossFlag = injureLossFlag;
        this.cargoLossFlag = cargoLossFlag;
        this.thiefLossFlag = thiefLossFlag;
        this.injureNum = injureNum;
        this.accidentArea = accidentArea;
        this.accidentWeather = accidentWeather;
        this.channelCode = channelCode;
        this.channelName = channelName;
        this.completeDegree = completeDegree;
        this.completeFlag = completeFlag;
        this.surveyFlag = surveyFlag;
        this.aimCarFlag = aimCarFlag;
        this.damageFlag = damageFlag;
        this.thirdCarFlag = thirdCarFlag;
        this.injuryFlag = injuryFlag;
        this.plateNum = plateNum;
        this.reportId = reportId;
    }
    @Generated(hash = 1519617947)
    public ReportInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public String getAccidentCauseCode() {
        return this.accidentCauseCode;
    }
    public void setAccidentCauseCode(String accidentCauseCode) {
        this.accidentCauseCode = accidentCauseCode;
    }
    public String getAccidentCauseName() {
        return this.accidentCauseName;
    }
    public void setAccidentCauseName(String accidentCauseName) {
        this.accidentCauseName = accidentCauseName;
    }
    public String getAccidentDutyCode() {
        return this.accidentDutyCode;
    }
    public void setAccidentDutyCode(String accidentDutyCode) {
        this.accidentDutyCode = accidentDutyCode;
    }
    public String getAccidentDutyName() {
        return this.accidentDutyName;
    }
    public void setAccidentDutyName(String accidentDutyName) {
        this.accidentDutyName = accidentDutyName;
    }
    public String getReportPersonName() {
        return this.reportPersonName;
    }
    public void setReportPersonName(String reportPersonName) {
        this.reportPersonName = reportPersonName;
    }
    public String getReportMoblePhone() {
        return this.reportMoblePhone;
    }
    public void setReportMoblePhone(String reportMoblePhone) {
        this.reportMoblePhone = reportMoblePhone;
    }
    public String getReportTime() {
        return this.reportTime;
    }
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
    public String getAccidentTime() {
        return this.accidentTime;
    }
    public void setAccidentTime(String accidentTime) {
        this.accidentTime = accidentTime;
    }
    public String getIsCurrentReport() {
        return this.isCurrentReport;
    }
    public void setIsCurrentReport(String isCurrentReport) {
        this.isCurrentReport = isCurrentReport;
    }
    public String getManageType() {
        return this.manageType;
    }
    public void setManageType(String manageType) {
        this.manageType = manageType;
    }
    public String getAccidentReasonCode() {
        return this.accidentReasonCode;
    }
    public void setAccidentReasonCode(String accidentReasonCode) {
        this.accidentReasonCode = accidentReasonCode;
    }
    public String getAccidentReasonName() {
        return this.accidentReasonName;
    }
    public void setAccidentReasonName(String accidentReasonName) {
        this.accidentReasonName = accidentReasonName;
    }
    public String getHugeType() {
        return this.hugeType;
    }
    public void setHugeType(String hugeType) {
        this.hugeType = hugeType;
    }
    public String getHugeTypeName() {
        return this.hugeTypeName;
    }
    public void setHugeTypeName(String hugeTypeName) {
        this.hugeTypeName = hugeTypeName;
    }
    public String getHugeCode() {
        return this.hugeCode;
    }
    public void setHugeCode(String hugeCode) {
        this.hugeCode = hugeCode;
    }
    public String getHugeName() {
        return this.hugeName;
    }
    public void setHugeName(String hugeName) {
        this.hugeName = hugeName;
    }
    public String getInsuranceCompanyCode() {
        return this.insuranceCompanyCode;
    }
    public void setInsuranceCompanyCode(String insuranceCompanyCode) {
        this.insuranceCompanyCode = insuranceCompanyCode;
    }
    public String getInsuranceCompanyName() {
        return this.insuranceCompanyName;
    }
    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }
    public String getReportType() {
        return this.reportType;
    }
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    public String getAccidentPlace() {
        return this.accidentPlace;
    }
    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }
    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getAccidentCourse() {
        return this.accidentCourse;
    }
    public void setAccidentCourse(String accidentCourse) {
        this.accidentCourse = accidentCourse;
    }
    public String getCarLossFlag() {
        return this.carLossFlag;
    }
    public void setCarLossFlag(String carLossFlag) {
        this.carLossFlag = carLossFlag;
    }
    public String getInjureLossFlag() {
        return this.injureLossFlag;
    }
    public void setInjureLossFlag(String injureLossFlag) {
        this.injureLossFlag = injureLossFlag;
    }
    public String getCargoLossFlag() {
        return this.cargoLossFlag;
    }
    public void setCargoLossFlag(String cargoLossFlag) {
        this.cargoLossFlag = cargoLossFlag;
    }
    public String getThiefLossFlag() {
        return this.thiefLossFlag;
    }
    public void setThiefLossFlag(String thiefLossFlag) {
        this.thiefLossFlag = thiefLossFlag;
    }
    public Long getInjureNum() {
        return this.injureNum;
    }
    public void setInjureNum(Long injureNum) {
        this.injureNum = injureNum;
    }
    public String getAccidentArea() {
        return this.accidentArea;
    }
    public void setAccidentArea(String accidentArea) {
        this.accidentArea = accidentArea;
    }
    public String getAccidentWeather() {
        return this.accidentWeather;
    }
    public void setAccidentWeather(String accidentWeather) {
        this.accidentWeather = accidentWeather;
    }
    public String getChannelCode() {
        return this.channelCode;
    }
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
    public String getChannelName() {
        return this.channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
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
    public String getSurveyFlag() {
        return this.surveyFlag;
    }
    public void setSurveyFlag(String surveyFlag) {
        this.surveyFlag = surveyFlag;
    }
    public String getAimCarFlag() {
        return this.aimCarFlag;
    }
    public void setAimCarFlag(String aimCarFlag) {
        this.aimCarFlag = aimCarFlag;
    }
    public String getDamageFlag() {
        return this.damageFlag;
    }
    public void setDamageFlag(String damageFlag) {
        this.damageFlag = damageFlag;
    }
    public String getThirdCarFlag() {
        return this.thirdCarFlag;
    }
    public void setThirdCarFlag(String thirdCarFlag) {
        this.thirdCarFlag = thirdCarFlag;
    }
    public String getInjuryFlag() {
        return this.injuryFlag;
    }
    public void setInjuryFlag(String injuryFlag) {
        this.injuryFlag = injuryFlag;
    }
    public String getPlateNum() {
        return this.plateNum;
    }
    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }
    public String getReportId() {
        return this.reportId;
    }
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public List<TaskInfo> getTaskInfoList() {
        return taskInfoList;
    }

    public void setTaskInfoList(List<TaskInfo> taskInfoList) {
        this.taskInfoList = taskInfoList;
    }


}
