package com.hl.contract.business.main.model;

import android.net.Uri;

import com.hl.contract.business.main.service.dto.Baifenbi;
import com.hl.contract.business.main.service.dto.BaifenbiDao;
import com.hl.contract.business.main.service.dto.ContractDTOS;
import com.hl.core.lib.bean.Response;
import com.hl.core.lib.inter.BaseLoadListener;
import com.hl.core.lib.network.ApiManager;
import com.hl.core.lib.network.retrofit.NetworkResponse;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.TimeUtil;
import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.CoreModel;
import com.hl.contract.bean.MobileRequest;
import com.hl.contract.business.login.service.LoginService;
import com.hl.contract.business.main.service.MainPageService;
import com.hl.contract.business.main.service.dto.BrandDTO;
import com.hl.contract.business.main.service.dto.CarModelDTO;
import com.hl.contract.business.main.service.dto.ContractDTO;
import com.hl.contract.business.main.service.dto.ContractListDTO;
import com.hl.contract.business.main.service.dto.DictDTO;
import com.hl.contract.business.main.service.dto.PayDTO;
import com.hl.contract.business.main.service.dto.QuestDictDTO;
import com.hl.contract.business.main.service.dto.SerialDTO;
import com.hl.contract.business.main.view.MainPageActivity;
import com.hl.contract.table.manager.DriverInfoManager;
import com.hl.contract.table.manager.ContractManager;
import com.hl.contract.table.model.Contract;
import com.hl.contract.table.model.DriverInfo;
import com.hl.contract.table.model.UserInfo;
import com.hl.contract.util.SurveyClaimUtil;

import java.util.List;

import retrofit2.Call;

/**
 * @Describe:
 * @Package: com.hl.survey.business.survey.main.model
 * @Author: liyu
 * @Date: 2018/3/21/ 17:52
 * @Copyright: hl
 */


public class CreateContractModel extends CoreModel {


    private static int GET_BRAND_LIST_BY_BRAND_NAME = 0x41;
    private static int GET_SERIES_LIST_BY_BRAND_NAME = 0x42;
    private static int GET_SERIES_LIST_BY_BARNDID = 0x43;


    public Contract getSurveyThirdCar(String reportCode, String id) {
        return ContractManager.getInstance().getSurveyThirdCar(reportCode);
    }

    public void getContractFromDB(String reportCode, CoreLiveData<Contract> reportCarData) {
        Contract reportCar = ContractManager.getInstance().getSurveyThirdCar(reportCode);
        reportCarData.postValue(reportCar);
    }

    public void getDriverInfoFromDB(String reportCode, Integer serialNo, CoreLiveData<DriverInfo> driverInfoData) {
        DriverInfo driverInfo = DriverInfoManager.getInstance().getDriverInfoBySerialNo(reportCode, serialNo);
        driverInfoData.postValue(driverInfo);

    }

    public void deleteSurveyCarById(String id) {
        ContractManager.getInstance().deleteSurveyCarById(id);
    }

    public void deleteDriverInfoById(String id) {
        DriverInfoManager.getInstance().deleteDriverInfoById(id);
    }

    public void goPay(boolean showLoadingDialog, CoreLiveData<PayDTO> picString, String contractNo){
        //保存登录账户
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        ContractDTO dto = new ContractDTO();
//        {"carOwnerName":"李阳","carOwnerSex":"1","carOwnerTypeCode":"02","carOwnerTypeName":"驾驶证","carOwnerCardNo":"440304198511080031",
//                "carOwnerTelephone":"13215641254","carOwnerEmail":"119942111@qq.com","carBrand":"奥迪","carSystem":"奥迪A6",
//                "carModel":"奥迪A6-S200","carAddr":"北京市","carPlateNo":"京MN1654","carVinNo":"ASDFGHJK321478ZXC","carPurchasePrice":600000.0,
//                "useTypeCode":"01","useTypeName":"01","batteryNo":"sjk652144","carBussinessDamageInsuranceOrnot":"1",
//                "purchaseDate":"May 21, 2018 8:35:04 PM","carOwnerRelationshipCode":"02","carOwnerRelationshipName":"02",
//                "servicePurchaserSex":"1","servicePurchaserName":"孙阳","servicePurchaserCardTypeCode":"01","servicePurchaserCardTypeName":"01",
//                "servicePurchaserCardNo":"440304198511060031","servicePurchaserTelephone":"13214561452","servicePurchaserEmail":"111@qq.com"}
        dto.setContractNo(contractNo);
        Call<Response<PayDTO>> call = service.goPay(dto);
        asyncNetWork(TAG,0,call,new NetworkResponse<Response<PayDTO>>(){
            @Override
            public void onDataReady(Response<PayDTO> response) {
                if(response!=null){
                    PayDTO pay = response.getData();
                    if (pay != null) {
                        if("1".equals(response.getCode())){
                            picString.setValue(pay);
                        }else {
                            sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                        }
                    }else{
                        sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                    }
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,message));
            }
        });
    }
    public void zhifuPay(boolean showLoadingDialog, CoreLiveData<PayDTO> picString, String contractNo,String youhui){
        //保存登录账户
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        ContractDTO dto = new ContractDTO();
//        {"carOwnerName":"李阳","carOwnerSex":"1","carOwnerTypeCode":"02","carOwnerTypeName":"驾驶证","carOwnerCardNo":"440304198511080031",
//                "carOwnerTelephone":"13215641254","carOwnerEmail":"119942111@qq.com","carBrand":"奥迪","carSystem":"奥迪A6",
//                "carModel":"奥迪A6-S200","carAddr":"北京市","carPlateNo":"京MN1654","carVinNo":"ASDFGHJK321478ZXC","carPurchasePrice":600000.0,
//                "useTypeCode":"01","useTypeName":"01","batteryNo":"sjk652144","carBussinessDamageInsuranceOrnot":"1",
//                "purchaseDate":"May 21, 2018 8:35:04 PM","carOwnerRelationshipCode":"02","carOwnerRelationshipName":"02",
//                "servicePurchaserSex":"1","servicePurchaserName":"孙阳","servicePurchaserCardTypeCode":"01","servicePurchaserCardTypeName":"01",
//                "servicePurchaserCardNo":"440304198511060031","servicePurchaserTelephone":"13214561452","servicePurchaserEmail":"111@qq.com"}
        dto.setContractNo(contractNo);
        dto.setDiscountType(youhui);
        Call<Response<PayDTO>> call = service.zhifuPay(dto);
        asyncNetWork(TAG,0,call,new NetworkResponse<Response<PayDTO>>(){
            @Override
            public void onDataReady(Response<PayDTO> response) {
                if(response!=null){
                    PayDTO pay = response.getData();
                    if (pay != null) {
                        if("1".equals(response.getCode())){
                            picString.setValue(pay);
                        }else {
                            sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                        }
                    }else{
                        sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                    }
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,message));
            }
        });
    }

    public void baifenbi(boolean showLoadingDialog, CoreLiveData<Baifenbi> picString, String year, String bai){
        //保存登录账户
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        BaifenbiDao dto = new BaifenbiDao();
//        {"carOwnerName":"李阳","carOwnerSex":"1","carOwnerTypeCode":"02","carOwnerTypeName":"驾驶证","carOwnerCardNo":"440304198511080031",
//                "carOwnerTelephone":"13215641254","carOwnerEmail":"119942111@qq.com","carBrand":"奥迪","carSystem":"奥迪A6",
//                "carModel":"奥迪A6-S200","carAddr":"北京市","carPlateNo":"京MN1654","carVinNo":"ASDFGHJK321478ZXC","carPurchasePrice":600000.0,
//                "useTypeCode":"01","useTypeName":"01","batteryNo":"sjk652144","carBussinessDamageInsuranceOrnot":"1",
//                "purchaseDate":"May 21, 2018 8:35:04 PM","carOwnerRelationshipCode":"02","carOwnerRelationshipName":"02",
//                "servicePurchaserSex":"1","servicePurchaserName":"孙阳","servicePurchaserCardTypeCode":"01","servicePurchaserCardTypeName":"01",
//                "servicePurchaserCardNo":"440304198511060031","servicePurchaserTelephone":"13214561452","servicePurchaserEmail":"111@qq.com"}
        dto.setCarBrandCode(bai);
        dto.setTimeLength(year);
        Call<Response<Baifenbi>> call = service.baifenbi(dto);
        asyncNetWork(TAG,0,call,new NetworkResponse<Response<Baifenbi>>(){
            @Override
            public void onDataReady(Response<Baifenbi> response) {
                if(response!=null){
                    Baifenbi bili = response.getData();
                    if (bili != null) {
                        if("1".equals(response.getCode())){
                            picString.setValue(bili);
                        }else {
                            sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                        }
                    }else{
                        sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                    }
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,message));
            }
        });
    }
    public void addContractForService(boolean showLoadingDialog, CoreLiveData<PayDTO> picString, Contract contract){
        //保存登录账户
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        ContractDTO dto = new ContractDTO();
//        {"carOwnerName":"李阳","carOwnerSex":"1","carOwnerTypeCode":"02","carOwnerTypeName":"驾驶证","carOwnerCardNo":"440304198511080031",
//                "carOwnerTelephone":"13215641254","carOwnerEmail":"119942111@qq.com","carBrand":"奥迪","carSystem":"奥迪A6",
//                "carModel":"奥迪A6-S200","carAddr":"北京市","carPlateNo":"京MN1654","carVinNo":"ASDFGHJK321478ZXC","carPurchasePrice":600000.0,
//                "useTypeCode":"01","useTypeName":"01","batteryNo":"sjk652144","carBussinessDamageInsuranceOrnot":"1",
//                "purchaseDate":"May 21, 2018 8:35:04 PM","carOwnerRelationshipCode":"02","carOwnerRelationshipName":"02",
//                "servicePurchaserSex":"1","servicePurchaserName":"孙阳","servicePurchaserCardTypeCode":"01","servicePurchaserCardTypeName":"01",
//                "servicePurchaserCardNo":"440304198511060031","servicePurchaserTelephone":"13214561452","servicePurchaserEmail":"111@qq.com"}
        dto.setCarOwnerName(contract.getOwnerName());
        dto.setCarOwnerSex(contract.getOwnerSex());
        dto.setCarOwnerTypeCode(contract.getOwnerCertificateTypeCode());
        dto.setCarOwnerTypeName(contract.getOwnerCertificateType());
        dto.setCarOwnerCardNo(contract.getOwnerCertificateNo());
        dto.setCarOwnerTelephone(contract.getOwnerTelePhone());
        dto.setCarOwnerEmail(contract.getOwnerEmail());
        dto.setCarBrand(contract.getBrandName());
        dto.setCarBrandCode(contract.getBrandCode());
        dto.setCarSystemCode(contract.getFamilyCode());
        dto.setCarSystem(contract.getFamilyName());
//        dto.setCarModel("BMW 1系运动型两厢轿车 118i 领先型");
//        dto.setCarModelCode("BM5IAT2.0");
        dto.setCarAddr(contract.getRegion());
        dto.setCarPlateNo(contract.getPlateNo());
        dto.setCarVinNo(contract.getVinNo());
        dto.setCarPurchasePrice(contract.getVehiclePrice());
        dto.setUseTypeCode(contract.getUsePropertyCode());
        dto.setUseTypeName(contract.getUsePropertyName());
        dto.setBatteryNo(contract.getBatteryNumber());
        dto.setPolicyNo(contract.getPolicyNo());
        dto.setPolicyOrgName(contract.getCommercialCompany());
        dto.setCarBussinessDamageInsuranceOrnot(contract.getIsInsureCommercial());
        dto.setPurchaseDate(TimeUtil.date(contract.getBuyCarDate()));
        dto.setCarOwnerRelationshipCode(contract.getRelationCode());
        dto.setCarOwnerRelationshipName(contract.getRelation());
        dto.setServicePurchaserName(contract.getServiceName());
        dto.setServicePurchaserSex(contract.getServiceSex());
        dto.setServicePurchaserCardTypeCode(contract.getServiceCertificateTypeCode());
        dto.setServicePurchaserCardTypeName(contract.getServiceCertificateType());
        dto.setServicePurchaserCardNo(contract.getServiceCertificateNo());
        dto.setServicePurchaserTelephone(contract.getServiceTelePhone());
        dto.setServicePurchaserEmail(contract.getServiceEmail());
        dto.setStartTime(TimeUtil.date(contract.getServiceStartDate()));
        dto.setTimeLength(Integer.valueOf(contract.getMileage()));
        dto.setProductCode(contract.getProductCode());
        dto.setProductName(contract.getProductName());
        dto.setContractState(contract.getExemptFlag());
        dto.setAccount(UtilManager.SP.survey().getString(SurveyClaimUtil.LOGIN_NAME,""));
        dto.setDiscountType(contract.getDiscountType());
        dto.setMotorNum(contract.getMotorNum());
        dto.setYanbaoStartMiles(contract.getYanbaoStartMiles());
        dto.setAddress(contract.getAddress());
        dto.setProductType(contract.getProductTypeCode());
        //        MobileRequest request = new MobileRequest();
//        request.setRequestSourceCode(request.getRequestSourceCode());
//        LoginQuestDTO dto = new LoginQuestDTO();
//        dto.setUserName(userInfo.getUserName());
//        dto.setPassWord(userInfo.getPassWord());
//        request.setData(dto);
        Call<Response<PayDTO>> call = service.addContract(dto);
        asyncNetWork(TAG,0,call,new NetworkResponse<Response<PayDTO>>(){
            @Override
            public void onDataReady(Response<PayDTO> response) {

                if(response!=null){
                    PayDTO pay = response.getData();
                    if (pay != null) {
                        if("1".equals(response.getCode())){
                            picString.setValue(pay);
                        }else {
                            sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                        }
                    }else{
                        sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                    }
                }

            }

            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,message));
            }
        });
    }

    //获取是否有优惠
    public void addContractService(boolean showLoadingDialog, CoreLiveData<String> picString, Contract contract){
        //保存登录账户
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        ContractDTOS dto = new ContractDTOS();
//        {"carOwnerName":"李阳","carOwnerSex":"1","carOwnerTypeCode":"02","carOwnerTypeName":"驾驶证","carOwnerCardNo":"440304198511080031",
//                "carOwnerTelephone":"13215641254","carOwnerEmail":"119942111@qq.com","carBrand":"奥迪","carSystem":"奥迪A6",
//                "carModel":"奥迪A6-S200","carAddr":"北京市","carPlateNo":"京MN1654","carVinNo":"ASDFGHJK321478ZXC","carPurchasePrice":600000.0,
//                "useTypeCode":"01","useTypeName":"01","batteryNo":"sjk652144","carBussinessDamageInsuranceOrnot":"1",
//                "purchaseDate":"May 21, 2018 8:35:04 PM","carOwnerRelationshipCode":"02","carOwnerRelationshipName":"02",
//                "servicePurchaserSex":"1","servicePurchaserName":"孙阳","servicePurchaserCardTypeCode":"01","servicePurchaserCardTypeName":"01",
//                "servicePurchaserCardNo":"440304198511060031","servicePurchaserTelephone":"13214561452","servicePurchaserEmail":"111@qq.com"}
        dto.setCarBrandCode(contract.getBrandCode());//....
        dto.setCarSystemCode(contract.getFamilyCode());//....
        dto.setTimeLength(Integer.valueOf(contract.getMileage()));//.........
        dto.setProductCode(contract.getProductCode());//.....
        dto.setAccount(UtilManager.SP.survey().getString(SurveyClaimUtil.LOGIN_NAME,""));//............

        Call<Response<String>> call = service.addContracts(dto);
        asyncNetWork(TAG,0,call,new NetworkResponse<Response<String>>(){
            @Override
            public void onDataReady(Response<String> response) {

                if(response!=null){

                        if("1".equals(response.getCode())){
                            picString.setValue("1");
                        }else {
                            picString.setValue("0");
                            //sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,response.getMessage()));
                        }
                }
            }

            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CREATE_CONTRACT,message));
            }
        });
    }

    /**
     * 根据关键字获取品牌列表
     * @param brandName 关键字
     * @param listener
     */
    public void getBrandListByBrandName(String brandName, BaseLoadListener listener) {
        MainPageService vehicleService = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        ContractListDTO dto = new ContractListDTO();
        brandName = Uri.encode(brandName);
        dto.setName(brandName);
        Call<Response<List<CarModelDTO>>> call = vehicleService.getBrandListByBrandName(dto);
        listener.loadStart();
        ApiManager.getInstance().asyncNetWork(TAG, GET_BRAND_LIST_BY_BRAND_NAME, call, new NetworkResponse<Response<List<CarModelDTO>>>() {

            @Override
            public void onDataReady(Response<List<CarModelDTO>> response) {
                listener.loadSuccess(response.getData(),null);
                listener.loadComplete();
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                listener.loadFailure(message);
            }
        });
    }

    public void getSeriesListBySeriesName(String carSerial, BaseLoadListener listener) {
        MainPageService vehicleService = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        ContractListDTO dto = new ContractListDTO();
        carSerial = Uri.encode(carSerial);
        dto.setName(carSerial);
        Call<Response<List<CarModelDTO>>> call = vehicleService.getBrandListByBrandName(dto);
        listener.loadStart();
        ApiManager.getInstance().asyncNetWork(TAG, GET_SERIES_LIST_BY_BRAND_NAME, call, new NetworkResponse<Response<List<CarModelDTO>>>() {

            @Override
            public void onDataReady(Response<List<CarModelDTO>> response) {
                listener.loadSuccess(response.getData(),null);
                listener.loadComplete();
            }

            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                listener.loadFailure(message);
            }
        });
    }

    public void getSeriesListByBrandId(String brandId, BaseLoadListener listener) {
        MainPageService vehicleService = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        Call<Response<List<SerialDTO>>> call = vehicleService.getSeriesListByBrandId(brandId);
        listener.loadStart();
        ApiManager.getInstance().asyncNetWork(TAG, GET_SERIES_LIST_BY_BARNDID, call, new NetworkResponse<Response<List<SerialDTO>>>(){
            @Override
            public void onDataReady(Response<List<SerialDTO>> response) {
                listener.loadSuccess(response.getResult(),null);
                listener.loadComplete();
            }

            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                listener.loadFailure(message);
            }
        });
    }


    /**
     * 获取省列表
     * @param showLoadingDialog
     * @param type
     * @param task
     */

    public void getProvinceFromNet(boolean showLoadingDialog,String type, CoreLiveData<List<DictDTO>> task){
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        QuestDictDTO dto = new QuestDictDTO();
        dto.setType(type);
        Call<Response<List<DictDTO>>> call = service.getArea(dto);
        asyncNetWork(showLoadingDialog,TAG,0,call,new NetworkResponse<Response<List<DictDTO>>>(){
            @Override
            public void onDataReady(Response<List<DictDTO>> response) {
                List<DictDTO> list = response.getData();
                if (list != null && list.size() > 0) {
                    task.postValue(list);
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,""));
                } else {
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,"暂未查询到任务"));
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,message));
            }
        });
    }
    /**
     * 获取市县列表
     * @param showLoadingDialog
     * @param type
     * @param task
     */

    public void getCityFromNet(boolean showLoadingDialog,String type, String code,CoreLiveData<List<DictDTO>> task){
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        QuestDictDTO dto = new QuestDictDTO();
        dto.setType(type);
        dto.setCode(code);
        Call<Response<List<DictDTO>>> call = service.getArea(dto);
        asyncNetWork(showLoadingDialog,TAG,0,call,new NetworkResponse<Response<List<DictDTO>>>(){
            @Override
            public void onDataReady(Response<List<DictDTO>> response) {
                List<DictDTO> list = response.getData();
                if (list != null && list.size() > 0) {
                    task.postValue(list);
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,""));
                } else {
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,"暂未查询到任务"));
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,message));
            }
        });
    }


    public void getBrandFromNet(boolean showLoadingDialog,CoreLiveData<List<CarModelDTO>> task){
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        CarModelDTO dto = new CarModelDTO();
        Call<Response<List<CarModelDTO>>> call = service.getBrand(dto);
        asyncNetWork(showLoadingDialog,TAG,0,call,new NetworkResponse<Response<List<CarModelDTO>>>(){
            @Override
            public void onDataReady(Response<List<CarModelDTO>> response) {
                List<CarModelDTO> list = response.getData();
                if (list != null && list.size() > 0) {
                    task.postValue(list);
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,""));
                } else {
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,"暂未查询到任务"));
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,message));
            }
        });
    }
    public void getFamilyFromNet(boolean showLoadingDialog,String type, String code,CoreLiveData<List<CarModelDTO>> task){
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        CarModelDTO dto = new CarModelDTO();
        dto.setId(code);
        Call<Response<List<CarModelDTO>>> call = service.getFamily(dto);
        asyncNetWork(showLoadingDialog,TAG,0,call,new NetworkResponse<Response<List<CarModelDTO>>>(){
            @Override
            public void onDataReady(Response<List<CarModelDTO>> response) {
                List<CarModelDTO> list = response.getData();
                if (list != null && list.size() > 0) {
                    task.postValue(list);
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,""));
                } else {
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,"暂未查询到任务"));
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,message));
            }
        });
    }
    public void getVehicleFromNet(boolean showLoadingDialog,String type, String code,CoreLiveData<List<CarModelDTO>> task){
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        CarModelDTO dto = new CarModelDTO();
        dto.setId(code);
        Call<Response<List<CarModelDTO>>> call = service.getVehicle(dto);
        asyncNetWork(showLoadingDialog,TAG,0,call,new NetworkResponse<Response<List<CarModelDTO>>>(){
            @Override
            public void onDataReady(Response<List<CarModelDTO>> response) {
                List<CarModelDTO> list = response.getData();
                if (list != null && list.size() > 0) {
                    task.postValue(list);
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,""));
                } else {
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,"暂未查询到任务"));
                }
            }
            @Override
            public void onDataError(int requestCode, int responseCode, String message) {
                sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,message));
            }
        });
    }
}
