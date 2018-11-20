package com.hl.contract.business.main.service.dto;

public class PayDTO {
    private String contractNo;//合同号，订单号
    private String order_price;//价格
    private String base64;//64位图片

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }


}
