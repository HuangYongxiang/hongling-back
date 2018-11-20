package com.hl.contract.table.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Describe:  备忘录基本信息
 * @Author: liyu
 * @Date: 2018/5/2 17:38
 * @Copyright: hl
 */
@Entity
public class MemoInfo {
    @Id
    private String  id			;//	主键
    private String  taskNo		;//		任务号
    private String  caseNo		;//报案号
    private String  userCode	;//	用户编码
    private String  userName	;//	用户名称
    private String  orgCode		;//	所属机构编码
    private String  orgName		;//	所属机构名称
    private String  memoTitle	;//标题
    private String  memoContent	;//内容
    private String createTime;//创建时间
    @Generated(hash = 219905192)
    public MemoInfo(String id, String taskNo, String caseNo, String userCode,
            String userName, String orgCode, String orgName, String memoTitle,
            String memoContent, String createTime) {
        this.id = id;
        this.taskNo = taskNo;
        this.caseNo = caseNo;
        this.userCode = userCode;
        this.userName = userName;
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.memoTitle = memoTitle;
        this.memoContent = memoContent;
        this.createTime = createTime;
    }
    @Generated(hash = 926778804)
    public MemoInfo() {
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
    public String getCaseNo() {
        return this.caseNo;
    }
    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }
    public String getUserCode() {
        return this.userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getOrgCode() {
        return this.orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    public String getOrgName() {
        return this.orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getMemoTitle() {
        return this.memoTitle;
    }
    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }
    public String getMemoContent() {
        return this.memoContent;
    }
    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
