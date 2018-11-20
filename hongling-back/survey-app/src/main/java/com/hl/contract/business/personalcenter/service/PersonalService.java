package com.hl.contract.business.personalcenter.service;

import com.hl.core.lib.bean.Response;
import com.hl.contract.bean.MobileRequest;
import com.hl.core.lib.constant.URLSurvey;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PersonalService {
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(URLSurvey.URL_SING_IN_AT_WORK)
    Call<Response<String>> signIn(@Body MobileRequest dto);
}
