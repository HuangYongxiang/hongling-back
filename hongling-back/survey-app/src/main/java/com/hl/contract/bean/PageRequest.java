package com.hl.contract.bean;

/**
 * @Describe:
 * @Package: com.hl.contract.business.survey.bean
 * @Author: liyu
 * @Date: 2018/5/4/ 10:47
 * @Copyright: hl
 */


public class PageRequest<T> {

    private int pageNo=0;//当前页
    private int pageSize=0;//每页显示数
    private int startRow ;
    private int endRow;
    private int totalRow;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
