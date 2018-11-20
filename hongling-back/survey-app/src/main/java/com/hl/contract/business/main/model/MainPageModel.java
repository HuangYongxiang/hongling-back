package com.hl.contract.business.main.model;

import android.util.Log;

import com.hl.core.lib.bean.Response;
import com.hl.core.lib.network.ApiManager;
import com.hl.core.lib.network.retrofit.NetworkResponse;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.TimeUtil;
import com.hl.core.lib.util.common.UUIDUtil;
import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.CoreModel;
import com.hl.contract.bean.MobileRequest;
import com.hl.contract.business.main.service.MainPageService;
import com.hl.contract.business.main.service.dto.AcceptTaskResult;
import com.hl.contract.business.main.service.dto.ContractDTO;
import com.hl.contract.business.main.service.dto.ContractListDTO;
import com.hl.contract.business.main.service.dto.TaskDTO;
import com.hl.contract.table.manager.ContractManager;
import com.hl.contract.table.manager.SurveyPropertyManager;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;

/**
 * @Describe: 主页model
 * @Package: com.hl.survey.business.main.viewmodel
 * @Author: liyu
 * @Date: 2018/3/15 13:20
 * @Copyright: hl
 */
public class MainPageModel extends CoreModel {

    /**
     * 网络获取任务列表
     * @param showLoadingDialog 是否显示加载框
     * @param scheduleJob 是否定时任务请求
     * @param task 数据模型
     */
    public void getTaskFromNetByContent(boolean showLoadingDialog,boolean scheduleJob, CoreLiveData<List<Contract>> task,ContractListDTO dto){
        MainPageService service = (MainPageService) ApiManager.getInstance().getApiService(MainPageService.class);
        Call<Response<List<ContractDTO>>> call = service.getAllTask(dto);
        asyncNetWork(showLoadingDialog,TAG,0,call,new NetworkResponse<Response<List<ContractDTO>>>(){
            @Override
            public void onDataReady(Response<List<ContractDTO>> response) {
                AcceptTaskResult acceptTaskResult = new AcceptTaskResult();
                acceptTaskResult.setReportNum(0);
                acceptTaskResult.setTaskNum(0);
                List<ContractDTO> list = response.getData();
                if (list != null && list.size() > 0) {
                    List<Contract> listReturn = new ArrayList<>();
                    for (ContractDTO drd : list) {
                        Contract c = new Contract();
                        c.setId(UUIDUtil.getUUID());
                        c.setBatteryNumber(drd.getBatteryNo());
                        c.setRegion(drd.getCarAddr());
                        c.setBrandName(drd.getCarBrand());
                        c.setBrandCode(drd.getCarBrandCode());
                        c.setIsInsureCommercial(drd.getCarBussinessDamageInsuranceOrnot());
                        c.setModelName(drd.getCarModel());
                        c.setModelCode(drd.getCarModelCode());
                        c.setOwnerCertificateNo(drd.getCarOwnerCardNo());
                        c.setOwnerEmail(drd.getCarOwnerEmail());
                        c.setOwnerName(drd.getCarOwnerName());
                        c.setRelationCode(drd.getCarOwnerRelationshipCode());
                        c.setRelation(drd.getCarOwnerRelationshipName());
                        c.setOwnerSex(drd.getCarOwnerSex());
                        c.setOwnerTelePhone(drd.getCarOwnerTelephone());
                        c.setOwnerCertificateTypeCode(drd.getCarOwnerTypeCode());
                        c.setOwnerCertificateType(drd.getCarOwnerTypeName());
                        c.setPlateNo(drd.getCarPlateNo());
                        c.setVehiclePrice(drd.getCarPurchasePrice());
                        c.setFamilyName(drd.getCarSystem());
                        c.setFamilyCode(drd.getCarSystemCode());
                        c.setCarTelePhone(drd.getCarTelephone());
                        c.setVinNo(drd.getCarVinNo());
                        c.setReportCode(drd.getContractNo());
                        c.setExemptFlag(drd.getContractState());//审核标识
                        c.setProductCode(drd.getProductCode());
                        c.setProductName(drd.getProductName());
                        if(drd.getPurchaseDate()!=null){
                            c.setBuyCarDate(TimeUtil.dateToStringYMD(drd.getPurchaseDate()));
                        }else{
                            c.setBuyCarDate("");
                        }
                        c.setServiceCertificateNo(drd.getServicePurchaserCardNo());
                        c.setServiceCertificateType(drd.getServicePurchaserCardTypeName());
                        c.setServiceCertificateTypeCode(drd.getServicePurchaserCardTypeCode());
                        c.setServiceEmail(drd.getServicePurchaserEmail());
                        c.setServiceName(drd.getServicePurchaserName());
                        c.setServiceSex(drd.getServicePurchaserSex());
                        c.setServiceTelePhone(drd.getServicePurchaserTelephone());
                        c.setUsePropertyCode(drd.getUseTypeCode());
                        c.setUsePropertyName(drd.getUseTypeName());
                        c.setTermOfValidityDate(String.valueOf(drd.getTimeLength()));
                        c.setServiceStartDate(TimeUtil.dateToStringYMD(drd.getStartTime()));
                        c.setAmountDamages(drd.getPaySum());
                        listReturn.add(c);

                    }
                    task.postValue(listReturn);
                    sendMessage(new CoreMessage(CoreMessage.CLOSE_REFRESH,""));
                    //定时任务时自动获取案件数目
                    if(scheduleJob){
                        String msg = getClaimTaskNumForDeal(null);
                        //发送消息给UI提示发送通知
                        if(msg != null){
                            sendMessage(new CoreMessage(1,msg));
                        }
                    }
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
    //从数据库中获取任务列表
    public void getTaskFromDB(CoreLiveData<List<Contract>> task){
        List<Contract> mContract = ContractManager.getInstance().getContractList(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""));
        task.postValue(mContract);
    }

    //获取需处理案件+任务数
    public String getClaimTaskNumForDeal(CoreLiveData<List<String>> claimNum){
        List<String> list = new ArrayList<>();
        List<Contract> mContractShenPi = ContractManager.getInstance().getContractListShenPi(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""));
        //需处理案件数
        int claim = mContractShenPi == null ? 0:mContractShenPi.size();
        list.add(String.valueOf(claim));
        List<Contract> mContract = ContractManager.getInstance().getContractList(UtilManager.SP.survey().getString(SurveyClaimUtil.USER_ID, ""));
        //需处理任务数
        int task = mContract == null ? 0:mContract.size();
        list.add(String.valueOf(task));
        if(claimNum != null){
            claimNum.postValue(list);
        }
        if(claim + task > 0){
            return "您有"+claim+"条报案，"+task+"条任务未处理";
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

}
