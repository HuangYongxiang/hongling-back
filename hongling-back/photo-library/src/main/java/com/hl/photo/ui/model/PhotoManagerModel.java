package com.hl.photo.ui.model;

import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreModel;
import com.hl.photo.table.manager.DictManager;
import com.hl.photo.table.model.DictInfo;

import java.util.List;

/**
 * @Describe:
 * @Package: com.hl.photo.ui.model
 * @Author: liyu
 * @Date: 2018/4/18/ 14:51
 * @Copyright: hl
 */


public class PhotoManagerModel extends CoreModel{


    public void inSertDictInfoList(List<DictInfo> allDictInfo, CoreLiveData<Boolean> dictInfoListData) {
        DictManager.getInstance().insertDictInfoList(allDictInfo);
        dictInfoListData.postValue(true);
    }
}
