package com.hl.contract.business.main.service.dto;

/**
 * Created by hl on 2017/9/19.
 */

public class LpInsuranceCarDTO {
    private String id;
    private String reportId;
    private String reportCode;
    private Double realPrice;
    private Double vehiclePrice;
    private String isImport;
    private String vehicleType;
    private String carColor;
    private String enrolDate;
    private String driverArea;
    private Long seat;
    private Double power;
    private String displacement;
    private Double tonnage;
    private String vinno;
    private String engineNo;
    private String vehicleModel;
    private String plateNum;
    private String plateColor;
    private String createTime;
    private String makeDate;
    private String guardAlarm;
    private String exemptFlag;
    private String useProperty;
    private String belongProperty;

    public String getBelongProperty() {
        return belongProperty;
    }

    public void setBelongProperty(String belongProperty) {
        this.belongProperty = belongProperty;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getDriverArea() {
        return driverArea;
    }

    public void setDriverArea(String driverArea) {
        this.driverArea = driverArea;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getEnrolDate() {
        return enrolDate;
    }

    public void setEnrolDate(String enrolDate) {
        this.enrolDate = enrolDate;
    }

    public String getExemptFlag() {
        return exemptFlag;
    }

    public void setExemptFlag(String exemptFlag) {
        this.exemptFlag = exemptFlag;
    }

    public String getGuardAlarm() {
        return guardAlarm;
    }

    public void setGuardAlarm(String guardAlarm) {
        this.guardAlarm = guardAlarm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(String plateColor) {
        this.plateColor = plateColor;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }

    public Double getTonnage() {
        return tonnage;
    }

    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }

    public String getUseProperty() {
        return useProperty;
    }

    public void setUseProperty(String useProperty) {
        this.useProperty = useProperty;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(Double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVinno() {
        return vinno;
    }

    public void setVinno(String vinno) {
        this.vinno = vinno;
    }
}
