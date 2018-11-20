package com.hl.contract.business.main.service.dto;

/**
 * Created by hl on 2017/9/19.
 */

public class LpReportDTO {
    private String id;
    private String reportCode;
    private String accidentCauseCode;
    private String accidentCauseName;
    private String accidentDutyCode;
    private String accidentDutyName;
    private String companyCode;
    private String companyName;
    private String branchCompanyCode;
    private String branchCompanyName;
    private String insuranceCompanyCode;
    private String insuranceCompanyName;
    private String reportType;
    private String reportPersonName;
    private String reportTime;
    private String reportPhone;
    private String accidentTime;
    private String isCurrentReport;
    private String accidentPlace;
    private String accidentCourse;
    private String carLossFlag;
    private String injureLossFlag;
    private String cargoLossFlag;
    private String thiefLossFlag;
    private Long injureNum;
    private String accidentArea;
    private String accidentWeather;
    private String channelCode;
    private String channelName;
    private String hugeType;
    private String hugeCode;
    private String delFlag;
    private String accidentReasonCode;
    private String manageType;

    public String getAccidentArea() {
        return accidentArea;
    }

    public void setAccidentArea(String accidentArea) {
        this.accidentArea = accidentArea;
    }

    public String getAccidentCauseCode() {
        return accidentCauseCode;
    }

    public void setAccidentCauseCode(String accidentCauseCode) {
        this.accidentCauseCode = accidentCauseCode;
    }

    public String getAccidentCauseName() {
        return accidentCauseName;
    }

    public void setAccidentCauseName(String accidentCauseName) {
        this.accidentCauseName = accidentCauseName;
    }

    public String getAccidentCourse() {
        return accidentCourse;
    }

    public void setAccidentCourse(String accidentCourse) {
        this.accidentCourse = accidentCourse;
    }

    public String getAccidentDutyCode() {
        return accidentDutyCode;
    }

    public void setAccidentDutyCode(String accidentDutyCode) {
        this.accidentDutyCode = accidentDutyCode;
    }

    public String getAccidentDutyName() {
        return accidentDutyName;
    }

    public void setAccidentDutyName(String accidentDutyName) {
        this.accidentDutyName = accidentDutyName;
    }

    public String getAccidentPlace() {
        return accidentPlace;
    }

    public void setAccidentPlace(String accidentPlace) {
        this.accidentPlace = accidentPlace;
    }

    public String getAccidentReasonCode() {
        return accidentReasonCode;
    }

    public void setAccidentReasonCode(String accidentReasonCode) {
        this.accidentReasonCode = accidentReasonCode;
    }

    public String getAccidentTime() {
        return accidentTime;
    }

    public void setAccidentTime(String accidentTime) {
        this.accidentTime = accidentTime;
    }

    public String getAccidentWeather() {
        return accidentWeather;
    }

    public void setAccidentWeather(String accidentWeather) {
        this.accidentWeather = accidentWeather;
    }

    public String getBranchCompanyCode() {
        return branchCompanyCode;
    }

    public void setBranchCompanyCode(String branchCompanyCode) {
        this.branchCompanyCode = branchCompanyCode;
    }

    public String getBranchCompanyName() {
        return branchCompanyName;
    }

    public void setBranchCompanyName(String branchCompanyName) {
        this.branchCompanyName = branchCompanyName;
    }

    public String getCargoLossFlag() {
        return cargoLossFlag;
    }

    public void setCargoLossFlag(String cargoLossFlag) {
        this.cargoLossFlag = cargoLossFlag;
    }

    public String getCarLossFlag() {
        return carLossFlag;
    }

    public void setCarLossFlag(String carLossFlag) {
        this.carLossFlag = carLossFlag;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getHugeCode() {
        return hugeCode;
    }

    public void setHugeCode(String hugeCode) {
        this.hugeCode = hugeCode;
    }

    public String getHugeType() {
        return hugeType;
    }

    public void setHugeType(String hugeType) {
        this.hugeType = hugeType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInjureLossFlag() {
        return injureLossFlag;
    }

    public void setInjureLossFlag(String injureLossFlag) {
        this.injureLossFlag = injureLossFlag;
    }

    public Long getInjureNum() {
        return injureNum;
    }

    public void setInjureNum(Long injureNum) {
        this.injureNum = injureNum;
    }

    public String getInsuranceCompanyCode() {
        return insuranceCompanyCode;
    }

    public void setInsuranceCompanyCode(String insuranceCompanyCode) {
        this.insuranceCompanyCode = insuranceCompanyCode;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public String getIsCurrentReport() {
        return isCurrentReport;
    }

    public void setIsCurrentReport(String isCurrentReport) {
        this.isCurrentReport = isCurrentReport;
    }

    public String getManageType() {
        return manageType;
    }

    public void setManageType(String manageType) {
        this.manageType = manageType;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportPersonName() {
        return reportPersonName;
    }

    public void setReportPersonName(String reportPersonName) {
        this.reportPersonName = reportPersonName;
    }

    public String getReportPhone() {
        return reportPhone;
    }

    public void setReportPhone(String reportPhone) {
        this.reportPhone = reportPhone;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getThiefLossFlag() {
        return thiefLossFlag;
    }

    public void setThiefLossFlag(String thiefLossFlag) {
        this.thiefLossFlag = thiefLossFlag;
    }
}
