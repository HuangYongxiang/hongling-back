package com.hl.contract.business.main.viewmodel;

import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.LiveData;
import com.hl.core.lib.viewmodel.Model;
import com.hl.contract.business.main.model.MainPageModel;
import com.hl.contract.business.main.service.dto.ContractListDTO;
import com.hl.contract.table.model.Contract;
import com.hl.contract.table.model.Contract;

import java.util.List;

/**
 * @Describe: 主页VM
 * @Package: com.hl.contract.business.main.model
 * @Author: liyu
 * @Date: 2018/3/14 17:06
 * @Copyright: hl
 */
public class MainPageVM extends CoreViewModel{

    @LiveData
    private CoreLiveData<List<Contract>> taskFromNet;//网络任务列表
    @LiveData
    private CoreLiveData<List<Contract>> taskFromDB;//数据库任务列表
    @LiveData
    private CoreLiveData<List<Contract>> taskFromScheduleJob;//定时任务列表
    @LiveData
    private CoreLiveData<List<String>> claimTaskNumForDeal;//需处理案件数+处理任务数

    @Model
    private MainPageModel mainModel;


//    /**
//     * 网络获取任务列表并保存到数据库中
//     * @param showLoadingDialog 是否显示加载框
//     */
//    public CoreLiveData<List<Contract>> getTaskFromNet(boolean showLoadingDialog) {
//        doAsync(() -> mainModel.getTaskFromNet(showLoadingDialog,false,taskFromNet));
//        return taskFromNet;
//    }

    /**
     * 网络获取任务列表并保存到数据库中
     * @param showLoadingDialog 是否显示加载框
     */
    public CoreLiveData<List<Contract>> getTaskFromNetByContent(boolean showLoadingDialog,ContractListDTO queryTaskDTO) {
        doAsync(() -> mainModel.getTaskFromNetByContent(showLoadingDialog,false,taskFromNet,queryTaskDTO));
        return taskFromNet;
    }

//    /**
//     * 定时从网络获取任务列表并保存到数据库中
//     */
//    public CoreLiveData<List<Contract>> getTaskFromNetScheduleJob(){
//        doAsync(() -> mainModel.getTaskFromNet(true,true,taskFromScheduleJob));
//        return taskFromScheduleJob;
//    }

    //从数据库中获取任务列表
    public CoreLiveData<List<Contract>> getTaskFromDB() {
        doAsync(() -> mainModel.getTaskFromDB(taskFromDB));
        return taskFromDB;
    }

    //获取需处理案件+任务数
    public CoreLiveData<List<String>> getClaimTaskNumForDeal(){
        doAsync(() -> mainModel.getClaimTaskNumForDeal(claimTaskNumForDeal));
        return claimTaskNumForDeal;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mainModel = null;
    }


}
