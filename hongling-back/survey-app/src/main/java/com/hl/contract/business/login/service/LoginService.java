package com.hl.contract.business.login.service;

import com.hl.core.lib.bean.Response;
import com.hl.contract.bean.MobileRequest;
import com.hl.contract.business.login.service.dto.LoginDTO;
import com.hl.core.lib.constant.URLSurvey;
import com.hl.contract.business.login.service.dto.LoginQuestDTO;
import com.hl.contract.business.login.service.dto.UserInfoDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @Describe: 登录
 * @Package: com.hl.contract.business.login.service
 * @Author: liyu
 * @Date: 2018/3/20 13:13
 * @Copyright: hl
 */
public interface LoginService {


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(URLSurvey.URL_LOGIN)
    Call<Response<UserInfoDTO>> login(@Body LoginQuestDTO dto);


    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST(URLSurvey.URL_LOGIN_OUT)
    Call<Response<LoginDTO>> logout(@Body MobileRequest dto);

}
