package com.hl.contract.bean;

import com.hl.contract.table.model.SurveyMainInfo;

/**
 * @Describe: 查勘基本信息扩展类
 * @Package: com.hl.contract.bean
 * @Author: liyu
 * @Date: 2018/3/23 10:44
 * @Copyright: hl
 */
public class SurveyMainInfoExt extends BaseExt<SurveyMainInfo>{

    public boolean getIsFirstScene() {
        isFirstScene = strToBol(model.getIsFirstScene());
        return isFirstScene;
    }

    public void setIsFirstScene(boolean firstScene) {
        isFirstScene = firstScene;
        model().setIsFirstScene(bolToStr(firstScene));
    }

    public boolean getIsSingleCarAccident() {
        isSingleCarAccident = strToBol(model().getIsSingleCarAccident());
        return isSingleCarAccident;
    }

    public void setIsSingleCarAccident(boolean singleCarAccident) {
        isSingleCarAccident = singleCarAccident;
        model().setIsSingleCarAccident(bolToStr(singleCarAccident));
    }

    public boolean getIsSubrogation() {
        isSubrogation = strToBol(model().getIsSubrogation());
        return isSubrogation;
    }

    public void setIsSubrogation(boolean subrogation) {
        isSubrogation = subrogation;
        model().setIsSubrogation(bolToStr(subrogation));
    }

    public boolean getSubrogationRequisitionFlag() {
        subrogationRequisitionFlag = strToBol(model().getSubrogationRequisitionFlag());
        return subrogationRequisitionFlag;
    }

    public void setSubrogationRequisitionFlag(boolean subrogationRequisitionFlag) {
        this.subrogationRequisitionFlag = subrogationRequisitionFlag;
        model().setSubrogationRequisitionFlag(bolToStr(subrogationRequisitionFlag));
    }

    public boolean getIsDesignateddriver() {
        isDesignateddriver = strToBol(model().getIsDesignateddriver());
        return isDesignateddriver;
    }

    public void setIsDesignateddriver(boolean designateddriver) {
        isDesignateddriver = designateddriver;
        model().setIsDesignateddriver(bolToStr(designateddriver));
    }

    public boolean getBigCaseFlag() {
        bigCaseFlag = strToBol(model().getBigCaseFlag());
        return bigCaseFlag;
    }

    public void setBigCaseFlag(boolean bigCaseFlag) {
        this.bigCaseFlag = bigCaseFlag;
        model().setBigCaseFlag(bolToStr(bigCaseFlag));
    }

    public boolean getPaySelfFlag() {
        paySelfFlag = strToBol(model().getPaySelfFlag());
        return paySelfFlag;
    }

    public void setPaySelfFlag(boolean paySelfFlag) {
        this.paySelfFlag = paySelfFlag;
        model().setPaySelfFlag(bolToStr(paySelfFlag));
    }

    public boolean getIsNoFindThird() {
        isNoFindThird = strToBol(model().getIsNoFindThird());
        return isNoFindThird;
    }

    public void setIsNoFindThird(boolean noFindThird) {
        isNoFindThird = noFindThird;
        model().setIsNoFindThird(bolToStr(noFindThird));
    }

    public boolean getThiefLossFlag() {
        thiefLossFlag = strToBol(model().getThiefLossFlag());
        return thiefLossFlag;
    }

    public void setThiefLossFlag(boolean thiefLossFlag) {
        this.thiefLossFlag = thiefLossFlag;
        model().setThiefLossFlag(bolToStr(thiefLossFlag));
    }

    public String getNoLiabilityCompensation() {
        noLiabilityCompensation = objToStr(model().getNoLiabilityCompensation());
        return noLiabilityCompensation;
    }

    public void setNoLiabilityCompensation(String noLiabilityCompensation) {
        this.noLiabilityCompensation = noLiabilityCompensation;
        model().setNoLiabilityCompensation(strToDouble(noLiabilityCompensation));
    }

    public String getEstimateAmount() {
        estimateAmount = objToStr(model().getEstimateAmount());
        return estimateAmount;
    }

    public void setEstimateAmount(String estimateAmount) {
        this.estimateAmount = estimateAmount;
        model().setEstimateAmount(strToDouble(estimateAmount));
    }

    public String getEstimateSurveyAmount() {
        estimateSurveyAmount = objToStr(model().getEstimateSurveyAmount());
        return estimateSurveyAmount;
    }

    public void setEstimateSurveyAmount(String estimateSurveyAmount) {
        this.estimateSurveyAmount = estimateSurveyAmount;
        model().setEstimateSurveyAmount(strToDouble(estimateSurveyAmount));
    }

    public String getCarPayFromThird() {
        carPayFromThird = objToStr(model().getCarPayFromThird());
        return carPayFromThird;
    }

    public void setCarPayFromThird(String carPayFromThird) {
        this.carPayFromThird = carPayFromThird;
        model().setCarPayFromThird(strToDouble(carPayFromThird));
    }

    public String getSalvageChargesFromThird() {
        salvageChargesFromThird = objToStr(model().getSalvageChargesFromThird());
        return salvageChargesFromThird;
    }

    public void setSalvageChargesFromThird(String salvageChargesFromThird) {
        this.salvageChargesFromThird = salvageChargesFromThird;
        model().setSalvageChargesFromThird(strToDouble(salvageChargesFromThird));
    }

    private String salvageChargesFromThird;//已从第三方获得施救费赔偿金额
    private String carPayFromThird;//已从第三方获得车损赔偿金额
    private String estimateSurveyAmount; //预估查勘费用金额
    private String estimateAmount; //估损金额
    private String noLiabilityCompensation;//无责代赔费
    private boolean thiefLossFlag; //是否盗抢
    private boolean isNoFindThird;//是否找不到第三方
    private boolean paySelfFlag; //互碰自赔标记
    private boolean bigCaseFlag; //重大赔案标志
    private boolean isDesignateddriver;//是否指定驾驶员
    private boolean subrogationRequisitionFlag;//代位索赔申请书标志
    private boolean isSubrogation;//是否代位求偿
    private boolean isSingleCarAccident;//是否单车事故
    private boolean isFirstScene;//是否第一现场

}
