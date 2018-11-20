package com.hl.contract.business.main.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Describe: 品牌DTO
 * @Author: liyu
 * @Date: 2018/1/6 14:05
 * @Copyright: hl
 */

public class ScpBrandDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String brandInitial;
    private List<ScpMobileBrandInfoDTO> brandList;

    public String getBrandInitial() {
        return brandInitial;
    }

    public void setBrandInitial(String brandInitial) {
        this.brandInitial = brandInitial;
    }

    public List<ScpMobileBrandInfoDTO> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<ScpMobileBrandInfoDTO> brandList) {
        this.brandList = brandList;
    }
}
