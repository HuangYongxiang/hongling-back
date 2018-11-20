package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by hl on 2017/8/30.
 * 字典表信息
 */

@Entity
public class DictInfo {
    @Id(autoincrement = true)
    private Long id;
    //key
    private String key;
    //子key
    private String seedKey;
    private String typeCode;
    private String typeName;
    //说明
    private String explain;
    @Generated(hash = 536227880)
    public DictInfo(Long id, String key, String seedKey, String typeCode,
                    String typeName, String explain) {
        this.id = id;
        this.key = key;
        this.seedKey = seedKey;
        this.typeCode = typeCode;
        this.typeName = typeName;
        this.explain = explain;
    }
    @Generated(hash = 965549942)
    public DictInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getSeedKey() {
        return this.seedKey;
    }
    public void setSeedKey(String seedKey) {
        this.seedKey = seedKey;
    }
    public String getTypeCode() {
        return this.typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getExplain() {
        return this.explain;
    }
    public void setExplain(String explain) {
        this.explain = explain;
    }
}
