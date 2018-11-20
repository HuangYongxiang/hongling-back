package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create data： 2017/7/14.
 * Author: liyu
 * Function:人伤费用信息
 */
@Entity
public class InjuryFeeInfo{
    @Id
    private String id;
    private String taskNo;
    private String reportCode;

    private String feename; //
    private String feenamecode; //费用编码（类别）赔付损失赔偿类型明细
    private String unitAmount; //单位金额
    private String amount; //数量
    private String lossAmount; //损失金额（小计）
    private String riskCode;//险种代码
    private String riskName;//险种名称
    private String itemCode;//险别代码
    private String itemName;//险别名称
    private String feeRemark;//费用备注


    private String deleteAmount; //剔除金额
    private String evalAmount; //剔除金额
    private String deleteComment; //剔除情况说明
    @Generated(hash = 113416934)
    public InjuryFeeInfo(String id, String taskNo, String reportCode,
            String feename, String feenamecode, String unitAmount, String amount,
            String lossAmount, String riskCode, String riskName, String itemCode,
            String itemName, String feeRemark, String deleteAmount,
            String evalAmount, String deleteComment) {
        this.id = id;
        this.taskNo = taskNo;
        this.reportCode = reportCode;
        this.feename = feename;
        this.feenamecode = feenamecode;
        this.unitAmount = unitAmount;
        this.amount = amount;
        this.lossAmount = lossAmount;
        this.riskCode = riskCode;
        this.riskName = riskName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.feeRemark = feeRemark;
        this.deleteAmount = deleteAmount;
        this.evalAmount = evalAmount;
        this.deleteComment = deleteComment;
    }
    @Generated(hash = 725301954)
    public InjuryFeeInfo() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTaskNo() {
        return this.taskNo;
    }
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }
    public String getReportCode() {
        return this.reportCode;
    }
    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }
    public String getFeename() {
        return this.feename;
    }
    public void setFeename(String feename) {
        this.feename = feename;
    }
    public String getFeenamecode() {
        return this.feenamecode;
    }
    public void setFeenamecode(String feenamecode) {
        this.feenamecode = feenamecode;
    }
    public String getUnitAmount() {
        return this.unitAmount;
    }
    public void setUnitAmount(String unitAmount) {
        this.unitAmount = unitAmount;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getLossAmount() {
        return this.lossAmount;
    }
    public void setLossAmount(String lossAmount) {
        this.lossAmount = lossAmount;
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
    public String getFeeRemark() {
        return this.feeRemark;
    }
    public void setFeeRemark(String feeRemark) {
        this.feeRemark = feeRemark;
    }
    public String getDeleteAmount() {
        return this.deleteAmount;
    }
    public void setDeleteAmount(String deleteAmount) {
        this.deleteAmount = deleteAmount;
    }
    public String getEvalAmount() {
        return this.evalAmount;
    }
    public void setEvalAmount(String evalAmount) {
        this.evalAmount = evalAmount;
    }
    public String getDeleteComment() {
        return this.deleteComment;
    }
    public void setDeleteComment(String deleteComment) {
        this.deleteComment = deleteComment;
    }

}
