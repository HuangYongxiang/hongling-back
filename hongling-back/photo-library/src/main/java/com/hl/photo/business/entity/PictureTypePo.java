package com.hl.photo.business.entity;


import java.io.Serializable;

public class PictureTypePo implements Serializable {
    private int id;
    private String lossNo;
    private String pictureTypeCode;
    private String pictureTypeName;
    private String takeDonePictureNum;
    private String totalPictureNum;
//    private String plateNo;
//    private String injuryName;
//    private String propertyName;
    private String signId;
    private String seedKey;
    private String signName;//人名、物名、车牌号
    private String taskType;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLossNo() {
        return lossNo;
    }

    public void setLossNo(String lossNo) {
        this.lossNo = lossNo;
    }

    public String getPictureTypeCode() {
        return pictureTypeCode;
    }

    public void setPictureTypeCode(String pictureTypeCode) {
        this.pictureTypeCode = pictureTypeCode;
    }

    public String getPictureTypeName() {
        return pictureTypeName;
    }

    public void setPictureTypeName(String pictureTypeName) {
        this.pictureTypeName = pictureTypeName;
    }

    public String getTakeDonePictureNum() {
        return takeDonePictureNum;
    }

    public void setTakeDonePictureNum(String takeDonePictureNum) {
        this.takeDonePictureNum = takeDonePictureNum;
    }

    public String getTotalPictureNum() {
        return totalPictureNum;
    }

    public void setTotalPictureNum(String totalPictureNum) {
        this.totalPictureNum = totalPictureNum;
    }

    public String getSeedKey() {
        return seedKey;
    }

    public void setSeedKey(String seedKey) {
        this.seedKey = seedKey;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }
}
