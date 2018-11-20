package com.hl.photo.ui.viewmodel;

import com.hl.core.lib.viewmodel.CoreLiveData;
import com.hl.core.lib.viewmodel.CoreViewModel;
import com.hl.core.lib.viewmodel.LiveData;
import com.hl.core.lib.viewmodel.Model;
import com.hl.photo.table.model.DictInfo;
import com.hl.photo.ui.model.PhotoManagerModel;

import java.util.List;

/**
 * @Describe:
 * @Package: com.hl.photo.view.viewmodel
 * @Author: liyu
 * @Date: 2018/4/18/ 14:37
 * @Copyright: hl
 */


public class PhotoManagerVM extends CoreViewModel {

    @Model
    PhotoManagerModel model;
    @LiveData
    CoreLiveData<Boolean> mDictInfoListData;

    public CoreLiveData<Boolean> inSertDictInfoList(List<DictInfo> allDictInfo) {
        doAsync(()->model.inSertDictInfoList(allDictInfo,mDictInfoListData));
        return mDictInfoListData;
    }
}
