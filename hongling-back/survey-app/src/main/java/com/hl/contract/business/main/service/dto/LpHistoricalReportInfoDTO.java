package com.hl.contract.business.main.service.dto;

/**
 * Created by Administrator on 2017/12/11.
 */

public class LpHistoricalReportInfoDTO {
    /**本案报案号*/
    private String reportCode;
    /**历史报案号*/
    private String hisReportCode;
    /**历史报案人*/
    private String hisReporter;
    /**历史报案时间*/
    private String hisReportTime;
    /**历史出险时间*/
    private String hisAccidentTime;
    /**历史出险地点*/
    private String hisAccidentPlace;
    /**历史出险原因*/
    private String hisAccidentCause;
    /**历史报案人手机*/
    private String hisReportMoblephone;
    /**历史处理方式*/
    private String hisManageType;


    public String getReportCode() {
        return reportCode;
    }

    public String getHisReportCode() {
        return hisReportCode;
    }

    public void setHisReportCode(String hisReportCode) {
        this.hisReportCode = hisReportCode;
    }

    public String getHisReporter() {
        return hisReporter;
    }

    public void setHisReporter(String hisReporter) {
        this.hisReporter = hisReporter;
    }

    public String getHisReportTime() {
        return hisReportTime;
    }

    public void setHisReportTime(String hisReportTime) {
        this.hisReportTime = hisReportTime;
    }

    public String getHisAccidentTime() {
        return hisAccidentTime;
    }

    public void setHisAccidentTime(String hisAccidentTime) {
        this.hisAccidentTime = hisAccidentTime;
    }

    public String getHisAccidentPlace() {
        return hisAccidentPlace;
    }

    public void setHisAccidentPlace(String hisAccidentPlace) {
        this.hisAccidentPlace = hisAccidentPlace;
    }

    public String getHisAccidentCause() {
        return hisAccidentCause;
    }

    public void setHisAccidentCause(String hisAccidentCause) {
        this.hisAccidentCause = hisAccidentCause;
    }

    public String getHisReportMoblephone() {
        return hisReportMoblephone;
    }

    public void setHisReportMoblephone(String hisReportMoblephone) {
        this.hisReportMoblephone = hisReportMoblephone;
    }

    public String getHisManageType() {
        return hisManageType;
    }

    public void setHisManageType(String hisManageType) {
        this.hisManageType = hisManageType;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
}
