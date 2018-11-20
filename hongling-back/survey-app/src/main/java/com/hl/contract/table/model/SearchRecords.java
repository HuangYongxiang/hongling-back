package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Describe:查询记录表
 * @Author: liyu
 * @Date: 2018/1/23 18:05
 * @Copyright: hl
 */
@Entity
public class SearchRecords {
    @Id(autoincrement = true)
    private Long id;
    private String handlerCode;
    private String handlerName;
    private String searchType;
    private String keyWord;
    private String operatingTime;
    @Generated(hash = 954486771)
    public SearchRecords(Long id, String handlerCode, String handlerName,
                         String searchType, String keyWord, String operatingTime) {
        this.id = id;
        this.handlerCode = handlerCode;
        this.handlerName = handlerName;
        this.searchType = searchType;
        this.keyWord = keyWord;
        this.operatingTime = operatingTime;
    }
    @Generated(hash = 69729993)
    public SearchRecords() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getHandlerCode() {
        return this.handlerCode;
    }
    public void setHandlerCode(String handlerCode) {
        this.handlerCode = handlerCode;
    }
    public String getHandlerName() {
        return this.handlerName;
    }
    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
    public String getSearchType() {
        return this.searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    public String getKeyWord() {
        return this.keyWord;
    }
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    public String getOperatingTime() {
        return this.operatingTime;
    }
    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }
}
