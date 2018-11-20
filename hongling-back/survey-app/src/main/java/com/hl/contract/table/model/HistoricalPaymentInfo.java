package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/12/11.
 */

@Entity
public class HistoricalPaymentInfo {
    @Id
    private String id;
    /**本案报案号*/
    private String reportCode;
    /**历史报案号*/
    private String hisReportCode;
    /**历史保单号*/
    private String hisPolicyCode;
    /**历史赔案号*/
    private String hisPaycaseCode;
    /**历史赔付金额*/
    private Double hisPaySum;
    /**历史赔付次数*/
    private Double hisPayNum;
    /**历史核赔人*/
    private String hisClaimsExaminers;
    /**历史核赔时间*/
    private String hisNuclearDate;
    /**本案当前状态*/
    private String hisClaimState;
    /**历史赔付方式*/
    private String hisPayWay;
    /**历史赔付结算时间*/
    private String hisPayclearTime;
    @Generated(hash = 887127867)
    public HistoricalPaymentInfo(String id, String reportCode, String hisReportCode,
            String hisPolicyCode, String hisPaycaseCode, Double hisPaySum,
            Double hisPayNum, String hisClaimsExaminers, String hisNuclearDate,
            String hisClaimState, String hisPayWay, String hisPayclearTime) {
        this.id = id;
        this.reportCode = reportCode;
        this.hisReportCode = hisReportCode;
        this.hisPolicyCode = hisPolicyCode;
        this.hisPaycaseCode = hisPaycaseCode;
        this.hisPaySum = hisPaySum;
        this.hisPayNum = hisPayNum;
        this.hisClaimsExaminers = hisClaimsExaminers;
        this.hisNuclearDate = hisNuclearDate;
        this.hisClaimState = hisClaimState;
        this.hisPayWay = hisPayWay;
        this.hisPayclearTime = hisPayclearTime;
    }
    @Generated(hash = 1809199717)
    public HistoricalPaymentInfo() {
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
    public String getHisPolicyCode() {
        return this.hisPolicyCode;
    }
    public void setHisPolicyCode(String hisPolicyCode) {
        this.hisPolicyCode = hisPolicyCode;
    }
    public String getHisPaycaseCode() {
        return this.hisPaycaseCode;
    }
    public void setHisPaycaseCode(String hisPaycaseCode) {
        this.hisPaycaseCode = hisPaycaseCode;
    }
    public Double getHisPaySum() {
        return this.hisPaySum;
    }
    public void setHisPaySum(Double hisPaySum) {
        this.hisPaySum = hisPaySum;
    }
    public Double getHisPayNum() {
        return this.hisPayNum;
    }
    public void setHisPayNum(Double hisPayNum) {
        this.hisPayNum = hisPayNum;
    }
    public String getHisClaimsExaminers() {
        return this.hisClaimsExaminers;
    }
    public void setHisClaimsExaminers(String hisClaimsExaminers) {
        this.hisClaimsExaminers = hisClaimsExaminers;
    }
    public String getHisNuclearDate() {
        return this.hisNuclearDate;
    }
    public void setHisNuclearDate(String hisNuclearDate) {
        this.hisNuclearDate = hisNuclearDate;
    }
    public String getHisClaimState() {
        return this.hisClaimState;
    }
    public void setHisClaimState(String hisClaimState) {
        this.hisClaimState = hisClaimState;
    }
    public String getHisPayWay() {
        return this.hisPayWay;
    }
    public void setHisPayWay(String hisPayWay) {
        this.hisPayWay = hisPayWay;
    }
    public String getHisPayclearTime() {
        return this.hisPayclearTime;
    }
    public void setHisPayclearTime(String hisPayclearTime) {
        this.hisPayclearTime = hisPayclearTime;
    }


}

