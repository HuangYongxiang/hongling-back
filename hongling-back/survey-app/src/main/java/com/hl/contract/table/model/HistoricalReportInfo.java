package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/12/11.
 */

@Entity
public class HistoricalReportInfo {
    @Id
    private String id;
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
    @Generated(hash = 1584647149)
    public HistoricalReportInfo(String id, String reportCode, String hisReportCode,
                                String hisReporter, String hisReportTime, String hisAccidentTime,
                                String hisAccidentPlace, String hisAccidentCause,
                                String hisReportMoblephone, String hisManageType) {
        this.id = id;
        this.reportCode = reportCode;
        this.hisReportCode = hisReportCode;
        this.hisReporter = hisReporter;
        this.hisReportTime = hisReportTime;
        this.hisAccidentTime = hisAccidentTime;
        this.hisAccidentPlace = hisAccidentPlace;
        this.hisAccidentCause = hisAccidentCause;
        this.hisReportMoblephone = hisReportMoblephone;
        this.hisManageType = hisManageType;
    }
    @Generated(hash = 1534136563)
    public HistoricalReportInfo() {
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
    public String getHisReportCode() {
        return this.hisReportCode;
    }
    public void setHisReportCode(String hisReportCode) {
        this.hisReportCode = hisReportCode;
    }
    public String getHisReporter() {
        return this.hisReporter;
    }
    public void setHisReporter(String hisReporter) {
        this.hisReporter = hisReporter;
    }
    public String getHisReportTime() {
        return this.hisReportTime;
    }
    public void setHisReportTime(String hisReportTime) {
        this.hisReportTime = hisReportTime;
    }
    public String getHisAccidentTime() {
        return this.hisAccidentTime;
    }
    public void setHisAccidentTime(String hisAccidentTime) {
        this.hisAccidentTime = hisAccidentTime;
    }
    public String getHisAccidentPlace() {
        return this.hisAccidentPlace;
    }
    public void setHisAccidentPlace(String hisAccidentPlace) {
        this.hisAccidentPlace = hisAccidentPlace;
    }
    public String getHisAccidentCause() {
        return this.hisAccidentCause;
    }
    public void setHisAccidentCause(String hisAccidentCause) {
        this.hisAccidentCause = hisAccidentCause;
    }
    public String getHisReportMoblephone() {
        return this.hisReportMoblephone;
    }
    public void setHisReportMoblephone(String hisReportMoblephone) {
        this.hisReportMoblephone = hisReportMoblephone;
    }
    public String getHisManageType() {
        return this.hisManageType;
    }
    public void setHisManageType(String hisManageType) {
        this.hisManageType = hisManageType;
    }



}

