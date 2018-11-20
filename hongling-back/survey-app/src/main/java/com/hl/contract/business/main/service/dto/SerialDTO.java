package com.hl.contract.business.main.service.dto;

import java.util.Date;

/**
 * @Describe:  车系DTO
 * @Author: liyu
 * @Date: 2018/1/6 14:38
 * @Copyright: hl
 */

public class SerialDTO {
    private String id;//车系信息主键
    private String seriesName;//车系名称
    private String seriesID;//车系ID
    private String  seriesCode;//车系编码
    private String  description;//描述
    private String seriesAliasName;//车系别名
    private String brandID;//品牌ID
    private String delFlag;//
    private Date recordedDate;//
    private String recordedBy;//
    private Date updatedDate;//
    private String updatedBy;//
    private String  remark;//
    private String  spellQuery;//拼音查询




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSeriesAliasName() {
        return seriesAliasName;
    }

    public void setSeriesAliasName(String seriesAliasName) {
        this.seriesAliasName = seriesAliasName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(String seriesID) {
        this.seriesID = seriesID;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }

    public String getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(String recordedBy) {
        this.recordedBy = recordedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSpellQuery() {
        return spellQuery;
    }

    public void setSpellQuery(String spellQuery) {
        this.spellQuery = spellQuery;
    }
}
