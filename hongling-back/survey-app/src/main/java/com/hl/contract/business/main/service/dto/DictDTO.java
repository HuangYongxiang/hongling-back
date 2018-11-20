package com.hl.contract.business.main.service.dto;

/**
 * @Describe:  品牌DTO
 * @Author: liyu
 * @Date: 2018/1/6 11:08
 * @Copyright: hl
 */

public class DictDTO {
    private String dictType;         //类型
    private String dictCode; //编码
    private String dictName; //名称

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
