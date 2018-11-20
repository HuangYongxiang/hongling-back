package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create data： 2017/7/16.
 * Author: liyu
 * Function:财产损失信息：列表（多条）
 */
@Entity
public class CargoFeeInfo {
    /** 主信息*/
    @Id
    private String id;
    private String reportCode;//报案号
    private String flowId;
    private String evalId;//定损主键
    private String createBy;//创建人员
    private String createDate;//创建时间
    private String updateBy;//修改人员
    private String updateDate;//修改时间

    /** 基本信息*/
    private String cargoType;//@物损类别名称；归属（类别）名称
    private String cargoTypeCode;//物损类别编码；归属（类别）编码
    private String cargoItemName;//@项目名称
    private String riskCode;//险种代码
    private String riskName;//@险种名称
    private String itemCode;//险别代码
    private String itemName;//@险别名称
    private String feeName;//@费用名称
    private String feeCode;//费用编码
    private double evalUnitPrice;//@单价；查勘点单价
    private double evalAmount;//@数量；查勘点数量
    private double evalLossDegree;//@损失程度（百分比表示）
    private double evalLossSum;//@损失金额合计；查勘点损失小计
    private double evalRemains;//@定损残值
    private double approveUnitPrice;//核损单价
    private double approveAmount;//核损数量
    private double approveLossDegree;//核损损失程度（百分比表示）
    private double approveRemains;//核损残值
    private double approveLossSum;//核损损失小计
    private String approveCheckState;//核损审核状态
    private String lossType;//@损失类型  1-主车财产损失 2-第三者车财产损失 3-第三者其他财产损失
    private String lossTypeCode;//损失类型编码  1-主车财产损失 2-第三者车财产损失 3-第三者其他财产损失
    private String fitBackFlag;//@旧件回收标记     1是  0否
    private String approveFitBackFlag;//核损回收标记     1是  0否
    private String remark;//备注；定损核损描述

    /** 隐藏信息*/

    private String recheckFlag;//@复勘标记
    private double recheckUnitPrice;//@复勘单价
    private double recheckAmount;//@复勘数量
    private double recheckLossDegree;//@复勘程度
    private double recheckLossSum;//@复勘合计
    private double recheckUnitRemains;//@复勘残值
    private String recheckFitBackFlag;//@复勘旧件回收标记
    @Generated(hash = 163545258)
    public CargoFeeInfo(String id, String reportCode, String flowId, String evalId,
                        String createBy, String createDate, String updateBy, String updateDate,
                        String cargoType, String cargoTypeCode, String cargoItemName,
                        String riskCode, String riskName, String itemCode, String itemName,
                        String feeName, String feeCode, double evalUnitPrice, double evalAmount,
                        double evalLossDegree, double evalLossSum, double evalRemains,
                        double approveUnitPrice, double approveAmount, double approveLossDegree,
                        double approveRemains, double approveLossSum, String approveCheckState,
                        String lossType, String lossTypeCode, String fitBackFlag,
                        String approveFitBackFlag, String remark, String recheckFlag,
                        double recheckUnitPrice, double recheckAmount, double recheckLossDegree,
                        double recheckLossSum, double recheckUnitRemains,
                        String recheckFitBackFlag) {
        this.id = id;
        this.reportCode = reportCode;
        this.flowId = flowId;
        this.evalId = evalId;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.cargoType = cargoType;
        this.cargoTypeCode = cargoTypeCode;
        this.cargoItemName = cargoItemName;
        this.riskCode = riskCode;
        this.riskName = riskName;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.feeName = feeName;
        this.feeCode = feeCode;
        this.evalUnitPrice = evalUnitPrice;
        this.evalAmount = evalAmount;
        this.evalLossDegree = evalLossDegree;
        this.evalLossSum = evalLossSum;
        this.evalRemains = evalRemains;
        this.approveUnitPrice = approveUnitPrice;
        this.approveAmount = approveAmount;
        this.approveLossDegree = approveLossDegree;
        this.approveRemains = approveRemains;
        this.approveLossSum = approveLossSum;
        this.approveCheckState = approveCheckState;
        this.lossType = lossType;
        this.lossTypeCode = lossTypeCode;
        this.fitBackFlag = fitBackFlag;
        this.approveFitBackFlag = approveFitBackFlag;
        this.remark = remark;
        this.recheckFlag = recheckFlag;
        this.recheckUnitPrice = recheckUnitPrice;
        this.recheckAmount = recheckAmount;
        this.recheckLossDegree = recheckLossDegree;
        this.recheckLossSum = recheckLossSum;
        this.recheckUnitRemains = recheckUnitRemains;
        this.recheckFitBackFlag = recheckFitBackFlag;
    }
    @Generated(hash = 732937636)
    public CargoFeeInfo() {
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
    public String getCargoType() {
        return this.cargoType;
    }
    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }
    public String getCargoTypeCode() {
        return this.cargoTypeCode;
    }
    public void setCargoTypeCode(String cargoTypeCode) {
        this.cargoTypeCode = cargoTypeCode;
    }
    public String getCargoItemName() {
        return this.cargoItemName;
    }
    public void setCargoItemName(String cargoItemName) {
        this.cargoItemName = cargoItemName;
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
    public String getFeeName() {
        return this.feeName;
    }
    public void setFeeName(String feeName) {
        this.feeName = feeName;
    }
    public String getFeeCode() {
        return this.feeCode;
    }
    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }
    public double getEvalUnitPrice() {
        return this.evalUnitPrice;
    }
    public void setEvalUnitPrice(double evalUnitPrice) {
        this.evalUnitPrice = evalUnitPrice;
    }
    public double getEvalAmount() {
        return this.evalAmount;
    }
    public void setEvalAmount(double evalAmount) {
        this.evalAmount = evalAmount;
    }
    public double getEvalLossDegree() {
        return this.evalLossDegree;
    }
    public void setEvalLossDegree(double evalLossDegree) {
        this.evalLossDegree = evalLossDegree;
    }
    public double getEvalLossSum() {
        return this.evalLossSum;
    }
    public void setEvalLossSum(double evalLossSum) {
        this.evalLossSum = evalLossSum;
    }
    public double getEvalRemains() {
        return this.evalRemains;
    }
    public void setEvalRemains(double evalRemains) {
        this.evalRemains = evalRemains;
    }
    public double getApproveUnitPrice() {
        return this.approveUnitPrice;
    }
    public void setApproveUnitPrice(double approveUnitPrice) {
        this.approveUnitPrice = approveUnitPrice;
    }
    public double getApproveAmount() {
        return this.approveAmount;
    }
    public void setApproveAmount(double approveAmount) {
        this.approveAmount = approveAmount;
    }
    public double getApproveLossDegree() {
        return this.approveLossDegree;
    }
    public void setApproveLossDegree(double approveLossDegree) {
        this.approveLossDegree = approveLossDegree;
    }
    public double getApproveRemains() {
        return this.approveRemains;
    }
    public void setApproveRemains(double approveRemains) {
        this.approveRemains = approveRemains;
    }
    public double getApproveLossSum() {
        return this.approveLossSum;
    }
    public void setApproveLossSum(double approveLossSum) {
        this.approveLossSum = approveLossSum;
    }
    public String getApproveCheckState() {
        return this.approveCheckState;
    }
    public void setApproveCheckState(String approveCheckState) {
        this.approveCheckState = approveCheckState;
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
    public String getFitBackFlag() {
        return this.fitBackFlag;
    }
    public void setFitBackFlag(String fitBackFlag) {
        this.fitBackFlag = fitBackFlag;
    }
    public String getApproveFitBackFlag() {
        return this.approveFitBackFlag;
    }
    public void setApproveFitBackFlag(String approveFitBackFlag) {
        this.approveFitBackFlag = approveFitBackFlag;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRecheckFlag() {
        return this.recheckFlag;
    }
    public void setRecheckFlag(String recheckFlag) {
        this.recheckFlag = recheckFlag;
    }
    public double getRecheckUnitPrice() {
        return this.recheckUnitPrice;
    }
    public void setRecheckUnitPrice(double recheckUnitPrice) {
        this.recheckUnitPrice = recheckUnitPrice;
    }
    public double getRecheckAmount() {
        return this.recheckAmount;
    }
    public void setRecheckAmount(double recheckAmount) {
        this.recheckAmount = recheckAmount;
    }
    public double getRecheckLossDegree() {
        return this.recheckLossDegree;
    }
    public void setRecheckLossDegree(double recheckLossDegree) {
        this.recheckLossDegree = recheckLossDegree;
    }
    public double getRecheckLossSum() {
        return this.recheckLossSum;
    }
    public void setRecheckLossSum(double recheckLossSum) {
        this.recheckLossSum = recheckLossSum;
    }
    public double getRecheckUnitRemains() {
        return this.recheckUnitRemains;
    }
    public void setRecheckUnitRemains(double recheckUnitRemains) {
        this.recheckUnitRemains = recheckUnitRemains;
    }
    public String getRecheckFitBackFlag() {
        return this.recheckFitBackFlag;
    }
    public void setRecheckFitBackFlag(String recheckFitBackFlag) {
        this.recheckFitBackFlag = recheckFitBackFlag;
    }



}
