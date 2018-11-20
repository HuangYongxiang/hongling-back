package com.hl.contract.bean;

import java.util.List;

/**
 * @Describe: 分页dto
 * @Package: com.hl.contract.business.survey.bean
 * @Author: liyu
 * @Date: 2018/4/26/ 11:50
 * @Copyright: hl
 */


public class PageDTO<DATA> {


    private int pageNo=0;//当前页
    private int pageSize=0;//每页显示数
    private int startRow ;
    private int endRow;
    private int totalRow;
    private int totalPage;

    private List<DATA> data; // 实体类

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DATA> getData() {
        return data;
    }

    public void setData(List<DATA> data) {
        this.data = data;
    }
}
