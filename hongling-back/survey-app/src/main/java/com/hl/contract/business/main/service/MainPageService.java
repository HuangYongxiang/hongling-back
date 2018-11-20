package com.hl.contract.business.main.service;

import com.hl.contract.business.main.service.dto.Baifenbi;
import com.hl.contract.business.main.service.dto.BaifenbiDao;
import com.hl.contract.business.main.service.dto.CarModelDTO;
import com.hl.contract.business.main.service.dto.ContractDTO;
import com.hl.contract.business.main.service.dto.ContractDTOS;
import com.hl.contract.business.main.service.dto.ContractListDTO;
import com.hl.contract.business.main.service.dto.DictDTO;
import com.hl.contract.business.main.service.dto.PayDTO;
import com.hl.contract.business.main.service.dto.QuestDictDTO;
import com.hl.contract.business.main.service.dto.SerialDTO;
import com.hl.core.lib.bean.Response;
import com.hl.core.lib.constant.URLSurvey;
import com.hl.core.lib.constant.UrlConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @Describe: 主页面 service
 * @Package: com.hl.contract.business.main.service
 * @Author: liyu
 * @Date: 2018/3/16 11:49
 * @Copyright: hl
 */
public interface MainPageService {


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.URL_LOAD_CONTRACT_ALL_TASK)
    Call<Response<List<ContractDTO>>> getAllTask(@Body ContractListDTO dto);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.ADD_CONTRACT)
    Call<Response<PayDTO>> addContract(@Body ContractDTO dto);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.TO_PAY)
    Call<Response<PayDTO>> goPay(@Body ContractDTO dto);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.TO_PAYs)
    Call<Response<PayDTO>> zhifuPay(@Body ContractDTO dto);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.ADD_CONTRACTS)
    Call<Response<String>> addContracts(@Body ContractDTOS dto);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.TO_BAIFENBI)
    Call<Response<Baifenbi>> baifenbi(@Body BaifenbiDao dto);
    /**
     * 根据品牌名称关键字，获取品牌列表
     *
     * @return
     */

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.URL_MODEL)
    Call<Response<List<CarModelDTO>>> getBrandListByBrandName(@Body ContractListDTO dto);


    /**
     * 根据品牌Id获得车系列表
     *
     * @param brandId
     * @return
     */
    @GET(UrlConstants.SERIES_SEARCH_BY_BRAND_ID + "{brandId}" + "/")
    Call<Response<List<SerialDTO>>> getSeriesListByBrandId(@Path("brandId") String brandId);

    /**
     * 省市县
     *
     * @param dto
     * @return
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.URL_AREA)
    Call<Response<List<DictDTO>>> getArea(@Body QuestDictDTO dto);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.URL_MODEL_BRAND)
    Call<Response<List<CarModelDTO>>> getBrand(@Body CarModelDTO dto);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.URL_MODEL_FAMILY)
    Call<Response<List<CarModelDTO>>> getFamily(@Body CarModelDTO dto);


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST(URLSurvey.URL_MODEL_VEHICLE)
    Call<Response<List<CarModelDTO>>> getVehicle(@Body CarModelDTO dto);
}
