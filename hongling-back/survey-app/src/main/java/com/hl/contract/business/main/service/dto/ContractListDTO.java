package com.hl.contract.business.main.service.dto;

/**
 * @Describe:  品牌DTO
 * @Author: liyu
 * @Date: 2018/1/6 11:08
 * @Copyright: hl
 */

public class ContractListDTO {
    private String name;         //
    private String startDate; //
    private String endDate; //
    private String account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
