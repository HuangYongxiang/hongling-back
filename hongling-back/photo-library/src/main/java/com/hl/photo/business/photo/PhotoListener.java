package com.hl.photo.business.photo;

import android.view.View;


import com.hl.photo.table.model.EvalMaterial;

import java.util.List;

/**
 * Created by liyu on 2017/1/5.
 */

public interface PhotoListener {
    void showProgressBar();
    void hidenProgressBar();
    void showMaterialData(List<EvalMaterial> list);
    void addAnimOperate(View view, Object itemData);
    void showDetailDialogByName(EvalMaterial evalMaterial);

}
