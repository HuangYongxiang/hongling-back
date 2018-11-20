package com.hl.contract.business.main.service.dto;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LpHistoricalpaymentInfoDTO {
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

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getHisReportCode() {
        return hisReportCode;
    }

    public void setHisReportCode(String hisReportCode) {
        this.hisReportCode = hisReportCode;
    }

    public String getHisPolicyCode() {
        return hisPolicyCode;
    }

    public void setHisPolicyCode(String hisPolicyCode) {
        this.hisPolicyCode = hisPolicyCode;
    }

    public String getHisPaycaseCode() {
        return hisPaycaseCode;
    }

    public void setHisPaycaseCode(String hisPaycaseCode) {
        this.hisPaycaseCode = hisPaycaseCode;
    }

    public Double getHisPaySum() {
        return hisPaySum;
    }

    public void setHisPaySum(Double hisPaySum) {
        this.hisPaySum = hisPaySum;
    }

    public Double getHisPayNum() {
        return hisPayNum;
    }

    public void setHisPayNum(Double hisPayNum) {
        this.hisPayNum = hisPayNum;
    }

    public String getHisClaimsExaminers() {
        return hisClaimsExaminers;
    }

    public void setHisClaimsExaminers(String hisClaimsExaminers) {
        this.hisClaimsExaminers = hisClaimsExaminers;
    }

    public String getHisNuclearDate() {
        return hisNuclearDate;
    }

    public void setHisNuclearDate(String hisNuclearDate) {
        this.hisNuclearDate = hisNuclearDate;
    }

    public String getHisClaimState() {
        return hisClaimState;
    }

    public void setHisClaimState(String hisClaimState) {
        this.hisClaimState = hisClaimState;
    }

    public String getHisPayWay() {
        return hisPayWay;
    }

    public void setHisPayWay(String hisPayWay) {
        this.hisPayWay = hisPayWay;
    }

    public String getHisPayclearTime() {
        return hisPayclearTime;
    }

    public void setHisPayclearTime(String hisPayclearTime) {
        this.hisPayclearTime = hisPayclearTime;
    }
}
