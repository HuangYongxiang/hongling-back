package com.hl.photo.table.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by liyu on 2017/1/9.
 */

@Entity
public class EvalMaterial {
    @Id
    private String id;//随机生成的UUID
    private String flowId;
    private String evalId;
    private String mateName;//辅料项目名称
    private String mateCode;//辅料项目编码
    private String mateHandaddFlag;//手工标记
    private Double evalMateAmount;//定损辅料数量
    private Double evalUnitPrice;//定损单价
    private Double evalMateSum;//小计
    private String groupGradeName;//车组等级名称
    private String evalRemark;//辅料备注
    private String mateId;//查询工时关联关系使用
    private String repairId;//修理项目ID (附加工时父工时的主键id,同步删除使用)
    private String mateItemCode;//险别代码
    private String mateItemName;//险别名称
    private Double apprUnitPrice;//核损单价
    private Double apprMateAmount;//核损数量
    private Double apprMateSum;//核损小计
    private String apprRemark;// /**核损备注*/
    private String mateApprCheckState;// /**01通过 02价格异议 03建议剔除 */
    private String apprState;  // 核损状态 A-新增  U-修改  D-删除
    private String formateMateApprState; //核损状态汉字 (可用来判断该项目是否为退回核损状态)
    private String delFlag;//删除标志             默认0
    private String additionalFlag;//退回新增标记 ; 默认 0 ；1 : 退回新增
    /** 项目风险信息 */
    private String ruleModel;
    @Transient
    private String serialNo;//序号 从 1 开始
    @Transient
    private String isAddFlag;//是否添加过
    @Generated(hash = 1435857735)
    public EvalMaterial(String id, String flowId, String evalId, String mateName,
            String mateCode, String mateHandaddFlag, Double evalMateAmount,
            Double evalUnitPrice, Double evalMateSum, String groupGradeName,
            String evalRemark, String mateId, String repairId, String mateItemCode,
            String mateItemName, Double apprUnitPrice, Double apprMateAmount,
            Double apprMateSum, String apprRemark, String mateApprCheckState,
            String apprState, String formateMateApprState, String delFlag,
            String additionalFlag, String ruleModel) {
        this.id = id;
        this.flowId = flowId;
        this.evalId = evalId;
        this.mateName = mateName;
        this.mateCode = mateCode;
        this.mateHandaddFlag = mateHandaddFlag;
        this.evalMateAmount = evalMateAmount;
        this.evalUnitPrice = evalUnitPrice;
        this.evalMateSum = evalMateSum;
        this.groupGradeName = groupGradeName;
        this.evalRemark = evalRemark;
        this.mateId = mateId;
        this.repairId = repairId;
        this.mateItemCode = mateItemCode;
        this.mateItemName = mateItemName;
        this.apprUnitPrice = apprUnitPrice;
        this.apprMateAmount = apprMateAmount;
        this.apprMateSum = apprMateSum;
        this.apprRemark = apprRemark;
        this.mateApprCheckState = mateApprCheckState;
        this.apprState = apprState;
        this.formateMateApprState = formateMateApprState;
        this.delFlag = delFlag;
        this.additionalFlag = additionalFlag;
        this.ruleModel = ruleModel;
    }
    @Generated(hash = 2093873421)
    public EvalMaterial() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getMateName() {
        return this.mateName;
    }
    public void setMateName(String mateName) {
        this.mateName = mateName;
    }
    public String getMateCode() {
        return this.mateCode;
    }
    public void setMateCode(String mateCode) {
        this.mateCode = mateCode;
    }
    public String getMateHandaddFlag() {
        return this.mateHandaddFlag;
    }
    public void setMateHandaddFlag(String mateHandaddFlag) {
        this.mateHandaddFlag = mateHandaddFlag;
    }
    public Double getEvalMateAmount() {
        return this.evalMateAmount;
    }
    public void setEvalMateAmount(Double evalMateAmount) {
        this.evalMateAmount = evalMateAmount;
    }
    public Double getEvalUnitPrice() {
        return this.evalUnitPrice;
    }
    public void setEvalUnitPrice(Double evalUnitPrice) {
        this.evalUnitPrice = evalUnitPrice;
    }
    public Double getEvalMateSum() {
        return this.evalMateSum;
    }
    public void setEvalMateSum(Double evalMateSum) {
        this.evalMateSum = evalMateSum;
    }
    public String getGroupGradeName() {
        return this.groupGradeName;
    }
    public void setGroupGradeName(String groupGradeName) {
        this.groupGradeName = groupGradeName;
    }
    public String getEvalRemark() {
        return this.evalRemark;
    }
    public void setEvalRemark(String evalRemark) {
        this.evalRemark = evalRemark;
    }
    public String getMateId() {
        return this.mateId;
    }
    public void setMateId(String mateId) {
        this.mateId = mateId;
    }
    public String getRepairId() {
        return this.repairId;
    }
    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }
    public String getMateItemCode() {
        return this.mateItemCode;
    }
    public void setMateItemCode(String mateItemCode) {
        this.mateItemCode = mateItemCode;
    }
    public String getMateItemName() {
        return this.mateItemName;
    }
    public void setMateItemName(String mateItemName) {
        this.mateItemName = mateItemName;
    }
    public Double getApprUnitPrice() {
        return this.apprUnitPrice;
    }
    public void setApprUnitPrice(Double apprUnitPrice) {
        this.apprUnitPrice = apprUnitPrice;
    }
    public Double getApprMateAmount() {
        return this.apprMateAmount;
    }
    public void setApprMateAmount(Double apprMateAmount) {
        this.apprMateAmount = apprMateAmount;
    }
    public Double getApprMateSum() {
        return this.apprMateSum;
    }
    public void setApprMateSum(Double apprMateSum) {
        this.apprMateSum = apprMateSum;
    }
    public String getApprRemark() {
        return this.apprRemark;
    }
    public void setApprRemark(String apprRemark) {
        this.apprRemark = apprRemark;
    }
    public String getMateApprCheckState() {
        return this.mateApprCheckState;
    }
    public void setMateApprCheckState(String mateApprCheckState) {
        this.mateApprCheckState = mateApprCheckState;
    }
    public String getApprState() {
        return this.apprState;
    }
    public void setApprState(String apprState) {
        this.apprState = apprState;
    }
    public String getFormateMateApprState() {
        return this.formateMateApprState;
    }
    public void setFormateMateApprState(String formateMateApprState) {
        this.formateMateApprState = formateMateApprState;
    }
    public String getDelFlag() {
        return this.delFlag;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getIsAddFlag() {
        return isAddFlag;
    }

    public void setIsAddFlag(String isAddFlag) {
        this.isAddFlag = isAddFlag;
    }
    public String getAdditionalFlag() {
        return this.additionalFlag;
    }
    public void setAdditionalFlag(String additionalFlag) {
        this.additionalFlag = additionalFlag;
    }
    public String getRuleModel() {
        return this.ruleModel;
    }
    public void setRuleModel(String ruleModel) {
        this.ruleModel = ruleModel;
    }
}
