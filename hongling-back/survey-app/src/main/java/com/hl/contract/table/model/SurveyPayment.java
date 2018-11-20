package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Create data： 2017/7/16.
 * Author: liyu
 * Function:支付信息
 */
@Entity
public class SurveyPayment  implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String reportCode ;//报案号
    private String flowId;
    private String claimNo;//赔案号
    private String policyCode;//保单号
    private String claimTimes ;//赔付次数
    private String editTimes ;//编辑次数
    private String payType ;//@支付方式
    private String payTypeCode;//支付方式编码
    private String customerType ;//@客户类型
    private String customerTypeCode;//客户类型编码
    private String customerName ;//@客户名称
    private String bankProvince ;//@开户银行(省)
    private String bankCity ;//@开户银行(市)
    private String bankCounty ;//@开户银行（区/县/镇/乡）
    private String bankType ;//@银行类型
    private String bankTypeCode ;//银行类型编码
    private String bankNetName ;//@网点名称/开户行名称
    private String bankNetCode ;//网点代码
    private String accountType ;//@账户类型/银行卡类型
    private String accountTypeCode;//账户类型编码/银行卡类型编码
    private String accountNo ;//@账户号/账号
    private String relation ;//@领款人与被保险人关系
    private String relationCode ;//@领款人与被保险人关系编码
    private String cardType ;//@领款证件类型
    private String cardTypeCode ;//领款证件类型编码
    private String idCardType;//@证件类型
    private String idCardTypeCode;//证件类型编码
    private String idCardNo;//@证件号码
    private String paySum ;//@支付金额
    private String mobileNo ;//@移动电话
    private String isSendMsg ;//@是否发送短信  是：1 否：0
    private String remark ;//@备注/统一支付备注
    private String moneyType ;//@币种
    private String moneyTypeCode ;//币种代码
    private String exceptMsg ;//@例外事故原因
    private String repairfacPayFlag;//@修理厂支付标记  是：1 否：0
    private String additionalFlag;//新增标记
    private Integer serialNo;//序号，理赔同步过来
    @Generated(hash = 1240720622)
    public SurveyPayment(String id, String reportCode, String flowId,
            String claimNo, String policyCode, String claimTimes, String editTimes,
            String payType, String payTypeCode, String customerType,
            String customerTypeCode, String customerName, String bankProvince,
            String bankCity, String bankCounty, String bankType,
            String bankTypeCode, String bankNetName, String bankNetCode,
            String accountType, String accountTypeCode, String accountNo,
            String relation, String relationCode, String cardType,
            String cardTypeCode, String idCardType, String idCardTypeCode,
            String idCardNo, String paySum, String mobileNo, String isSendMsg,
            String remark, String moneyType, String moneyTypeCode, String exceptMsg,
            String repairfacPayFlag, String additionalFlag, Integer serialNo) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.claimNo = claimNo;
        this.policyCode = policyCode;
        this.claimTimes = claimTimes;
        this.editTimes = editTimes;
        this.payType = payType;
        this.payTypeCode = payTypeCode;
        this.customerType = customerType;
        this.customerTypeCode = customerTypeCode;
        this.customerName = customerName;
        this.bankProvince = bankProvince;
        this.bankCity = bankCity;
        this.bankCounty = bankCounty;
        this.bankType = bankType;
        this.bankTypeCode = bankTypeCode;
        this.bankNetName = bankNetName;
        this.bankNetCode = bankNetCode;
        this.accountType = accountType;
        this.accountTypeCode = accountTypeCode;
        this.accountNo = accountNo;
        this.relation = relation;
        this.relationCode = relationCode;
        this.cardType = cardType;
        this.cardTypeCode = cardTypeCode;
        this.idCardType = idCardType;
        this.idCardTypeCode = idCardTypeCode;
        this.idCardNo = idCardNo;
        this.paySum = paySum;
        this.mobileNo = mobileNo;
        this.isSendMsg = isSendMsg;
        this.remark = remark;
        this.moneyType = moneyType;
        this.moneyTypeCode = moneyTypeCode;
        this.exceptMsg = exceptMsg;
        this.repairfacPayFlag = repairfacPayFlag;
        this.additionalFlag = additionalFlag;
        this.serialNo = serialNo;
    }
    @Generated(hash = 152955032)
    public SurveyPayment() {
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
    public String getClaimNo() {
        return this.claimNo;
    }
    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }
    public String getPolicyCode() {
        return this.policyCode;
    }
    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }
    public String getClaimTimes() {
        return this.claimTimes;
    }
    public void setClaimTimes(String claimTimes) {
        this.claimTimes = claimTimes;
    }
    public String getEditTimes() {
        return this.editTimes;
    }
    public void setEditTimes(String editTimes) {
        this.editTimes = editTimes;
    }
    public String getPayType() {
        return this.payType;
    }
    public void setPayType(String payType) {
        this.payType = payType;
    }
    public String getPayTypeCode() {
        return this.payTypeCode;
    }
    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }
    public String getCustomerType() {
        return this.customerType;
    }
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    public String getCustomerTypeCode() {
        return this.customerTypeCode;
    }
    public void setCustomerTypeCode(String customerTypeCode) {
        this.customerTypeCode = customerTypeCode;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getBankProvince() {
        return this.bankProvince;
    }
    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }
    public String getBankCity() {
        return this.bankCity;
    }
    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }
    public String getBankCounty() {
        return this.bankCounty;
    }
    public void setBankCounty(String bankCounty) {
        this.bankCounty = bankCounty;
    }
    public String getBankType() {
        return this.bankType;
    }
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }
    public String getBankTypeCode() {
        return this.bankTypeCode;
    }
    public void setBankTypeCode(String bankTypeCode) {
        this.bankTypeCode = bankTypeCode;
    }
    public String getBankNetName() {
        return this.bankNetName;
    }
    public void setBankNetName(String bankNetName) {
        this.bankNetName = bankNetName;
    }
    public String getBankNetCode() {
        return this.bankNetCode;
    }
    public void setBankNetCode(String bankNetCode) {
        this.bankNetCode = bankNetCode;
    }
    public String getAccountType() {
        return this.accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getAccountTypeCode() {
        return this.accountTypeCode;
    }
    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }
    public String getAccountNo() {
        return this.accountNo;
    }
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    public String getRelation() {
        return this.relation;
    }
    public void setRelation(String relation) {
        this.relation = relation;
    }
    public String getRelationCode() {
        return this.relationCode;
    }
    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }
    public String getCardType() {
        return this.cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getCardTypeCode() {
        return this.cardTypeCode;
    }
    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
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
    public String getPaySum() {
        return this.paySum;
    }
    public void setPaySum(String paySum) {
        this.paySum = paySum;
    }
    public String getMobileNo() {
        return this.mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getIsSendMsg() {
        return this.isSendMsg;
    }
    public void setIsSendMsg(String isSendMsg) {
        this.isSendMsg = isSendMsg;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getMoneyType() {
        return this.moneyType;
    }
    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }
    public String getMoneyTypeCode() {
        return this.moneyTypeCode;
    }
    public void setMoneyTypeCode(String moneyTypeCode) {
        this.moneyTypeCode = moneyTypeCode;
    }
    public String getExceptMsg() {
        return this.exceptMsg;
    }
    public void setExceptMsg(String exceptMsg) {
        this.exceptMsg = exceptMsg;
    }
    public String getRepairfacPayFlag() {
        return this.repairfacPayFlag;
    }
    public void setRepairfacPayFlag(String repairfacPayFlag) {
        this.repairfacPayFlag = repairfacPayFlag;
    }
    public String getAdditionalFlag() {
        return this.additionalFlag;
    }
    public void setAdditionalFlag(String additionalFlag) {
        this.additionalFlag = additionalFlag;
    }
    public Integer getSerialNo() {
        return this.serialNo;
    }
    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }



}
