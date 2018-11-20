package com.hl.contract.business.main.viewmodel;

import android.content.Context;

import com.hl.contract.business.main.model.CreateContractModel;
import com.hl.contract.business.main.service.dto.Baifenbi;
import com.hl.contract.business.main.service.dto.BrandDTO;
import com.hl.contract.business.main.service.dto.CarModelDTO;
import com.hl.contract.business.main.service.dto.DictDTO;
import com.hl.contract.business.main.service.dto.PayDTO;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.LiveData;
import com.hl.core.lib.viewmodel.Model;

import java.util.List;

/**
 * @Describe:
 * @Package: com.hl.contract.business.survey.main.viewmodel
 * @Author: liyu
 * @Date: 2018/3/21/ 17:51
 * @Copyright: hl
 */


public class CreateContractVM extends CoreViewModel{

    @LiveData
    private CoreLiveData<Contract> contractData;//信息
    @LiveData
    private CoreLiveData<PayDTO> picString;//保存结果
    @LiveData
    private CoreLiveData<String> picStrings;//保存结果
    @LiveData
    private CoreLiveData<Baifenbi> baifenbis;//baifenbi
    @LiveData
    private CoreLiveData<Boolean> saveSuccess;//保存结果
    @LiveData
    private CoreLiveData<List<DictDTO>> dictDTOCoreLiveData;//省市县
    @LiveData
    private CoreLiveData<List<DictDTO>> dictCityDTOCoreLiveData;//省市县
    @LiveData
    private CoreLiveData<List<DictDTO>> dictCountyDTOCoreLiveData;//省市县

    @LiveData
    private CoreLiveData<List<CarModelDTO>> brandDTOCoreLiveData;//省市县
    @LiveData
    private CoreLiveData<List<CarModelDTO>> familyDTOCoreLiveData;//省市县
    @LiveData
    private CoreLiveData<List<CarModelDTO>> vehicleDTOCoreLiveData;//省市县


    private String id;
    private String reportCode;

    @Model
    private CreateContractModel createContractModel;

/*    public String getAdditionalFlag(String reportCode, String id) {
        String additionalFlag = null; //是否新增
        if (!TextUtils.isEmpty(id)) {
            ReportCar reportCar = surveyThirdCarModel.getSurveyThirdCar(reportCode, id);
            additionalFlag = reportCar.getAdditionalFlag();
        }
        return additionalFlag;
    }*/
    private final static String FLAG_BRAND = "Brand"; // 品牌
    private final static String FLAG_SERIAL = "Serial"; // 车系
    public boolean isApFlag = false;
    private Context mContext;
    private String TAG = "VechileCustomVM";
    private List<BrandDTO> brandDTOList;
    private  List<TypeItemModelView> serialList;

    /**
     * 创建报案信息
     * @param id
     * @param reportCode
     * @param taskNo
     * @return
     */
    public Contract newCar(String id,String reportCode,String taskNo) {
        Contract reportCar = new Contract();
        reportCar.setId(id);
        reportCar.setUserId(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""));
        reportCar.setReportCode(reportCode);
        return reportCar;
    }


    /**
     * 数据库中获取
     * @param reportCode
     * @return
     */
    public CoreLiveData<Contract> getReportCarFromDB(String reportCode){
        doAsync(() ->  createContractModel.getContractFromDB(reportCode,contractData));
        return contractData;

    }


    public void deleteSurveyCarById(String id) {
        doAsync(()->createContractModel.deleteSurveyCarById(id));
    }


    public CoreLiveData<PayDTO> addContractForService(boolean showLoadingDialog, Contract contract) {
        doAsync(() -> createContractModel.addContractForService(showLoadingDialog,picString,contract));
        return picString;
    }
    public CoreLiveData<String> addContractService(boolean showLoadingDialog, Contract contract) {
        doAsync(() -> createContractModel.addContractService(showLoadingDialog,picStrings,contract));
        return picStrings;
    }
    public CoreLiveData<PayDTO> goPay(boolean showLoadingDialog, String contractNo) {
        doAsync(() -> createContractModel.goPay(showLoadingDialog,picString,contractNo));
        return picString;
    }
    public CoreLiveData<PayDTO> zhifuPay(boolean showLoadingDialog, String contractNo,String youhui) {
        doAsync(() -> createContractModel.zhifuPay(showLoadingDialog,picString,contractNo,youhui));
        return picString;
    }

    public CoreLiveData<Baifenbi> baifenbi(boolean showLoadingDialog, String contractNo, String youhui) {
        doAsync(() -> createContractModel.baifenbi(showLoadingDialog,baifenbis,contractNo,youhui));
        return baifenbis;
    }
//    /**
//     * 根据关键字请求品牌列表
//     * @param brandNameString
//     */
//    public void getBrandListByBrandName(String brandNameString) {
//        createContractModel.getBrandListByBrandName(brandNameString,this);
//    }
//
//    /**
//     * 根据关键字请求车系列表
//     * @param carSerial
//     */
//    public void getSeriesListBySeriesName(String carSerial) {
//        createContractModel.getSeriesListBySeriesName(carSerial,this);
//    }
//
//
//    public void getSeriesListByBrandId(String brandId) {
//        createContractModel.getSeriesListByBrandId(brandId,this);
//    }


    /**
     * 获取省列表
     * @param showLoadingDialog
     * @param type
     */

    public  CoreLiveData<List<DictDTO>>  getProvinceFromNet(boolean showLoadingDialog,String type) {
        doAsync(() -> createContractModel.getProvinceFromNet(showLoadingDialog,type,dictDTOCoreLiveData));
        return dictDTOCoreLiveData;
    }

    /**
     * 获取市列表
     * @param showLoadingDialog
     * @param type
     * @param code
     * @return
     */

    public  CoreLiveData<List<DictDTO>>  getCityFromNet(boolean showLoadingDialog,String type,String code) {
        doAsync(() -> createContractModel.getCityFromNet(showLoadingDialog,type,code,dictCityDTOCoreLiveData));
        return dictCityDTOCoreLiveData;
    }

    /**
     * 获取县列表
     * @param showLoadingDialog
     * @param type
     * @param code
     * @return
     */

    public  CoreLiveData<List<DictDTO>>  getCountyFromNet(boolean showLoadingDialog,String type,String code) {
        doAsync(() -> createContractModel.getCityFromNet(showLoadingDialog,type,code,dictCountyDTOCoreLiveData));
        return dictCountyDTOCoreLiveData;
    }


    public  CoreLiveData<List<CarModelDTO>>  getBrandFromNet(boolean showLoadingDialog) {
        doAsync(() -> createContractModel.getBrandFromNet(showLoadingDialog,brandDTOCoreLiveData));
        return brandDTOCoreLiveData;
    }

    public  CoreLiveData<List<CarModelDTO>>  getFamilyFromNet(boolean showLoadingDialog,String type,String code) {
        doAsync(() -> createContractModel.getFamilyFromNet(showLoadingDialog,type,code,familyDTOCoreLiveData));
        return familyDTOCoreLiveData;
    }

    public  CoreLiveData<List<CarModelDTO>>  getVehicleFromNet(boolean showLoadingDialog,String type,String code) {
        doAsync(() -> createContractModel.getVehicleFromNet(showLoadingDialog,type,code,vehicleDTOCoreLiveData));
        return vehicleDTOCoreLiveData;
    }
}
