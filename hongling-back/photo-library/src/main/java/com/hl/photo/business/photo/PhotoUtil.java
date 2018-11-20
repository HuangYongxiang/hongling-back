package com.hl.photo.business.photo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hl.core.lib.bean.TypeItem;
import com.hl.photo.R;
import com.hl.photo.table.model.DictInfo;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.support.util.PhotoClaimUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyu on 2017/8/7.
 */

public class PhotoUtil {

    public interface TakePicCallBack{
        //从相机拍摄
        void takePicFromCamera(Bundle bundle);
        //从相册选择
        void choicePicFromPhoto(Bundle bundle);

        void canclePopupWindow();
    }

        private static Bundle getBundle(PhotoInfo photo, String reportNo, String pictureType) {
        Bundle bundle = new Bundle();
        bundle.putString(PhotoClaimUtil.REPORT_NO, reportNo);
//        bundle.putString(PhotoClaimUtil.IMAGE_TYPE, photo.getImageType());
//        bundle.putString(PhotoClaimUtil.IMAGE_SUB_TYPE, photo.getImageSubtype());
//        bundle.putString(PhotoClaimUtil.IMAGE_NAME, photo.getImageName());
//        bundle.putString(PhotoClaimUtil.SEED_KEY, photo.getSeedKey());
//        bundle.putString(PhotoClaimUtil.SIGN_ID, signId);
//        bundle.putString(PhotoClaimUtil.SIGN_NAME, signName);
//        bundle.putString(PhotoClaimUtil.TASK_TYPE, photo.getTaskType());
        return bundle;
    }

    public static  void bindPopWindow(final TakePicCallBack takePicCallBack, View popView, final PhotoInfo photo,final String reportNo, final String pictureType ) {
        TextView fromCameraTv = (TextView) popView.findViewById(R.id.photo_pop_txt_camera);
        TextView fromLocalTv = (TextView) popView.findViewById(R.id.photo_pop_txt_local);
        TextView cancleTv = (TextView) popView.findViewById(R.id.dialog_button);
        fromCameraTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getBundle(photo,reportNo, pictureType);
                if(takePicCallBack != null){
                    takePicCallBack.takePicFromCamera(bundle);
                }
            }
        });
        fromLocalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getBundle(photo, reportNo,pictureType);
                if(takePicCallBack != null){
                    takePicCallBack.choicePicFromPhoto(bundle);
                }
            }
        });
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(takePicCallBack != null){
                    takePicCallBack.canclePopupWindow();
                }
            }
        });

    }

    /**
     * 字典转换
     * @param dictInfoList
     * @param imageType
     * @param imageName
     * @return
     */
    public static ArrayList<TypeItem> getTypeItemsByDict(List<DictInfo> dictInfoList, String imageType , String imageName){
        ArrayList<TypeItem> typeItemList = new ArrayList<>();
        if(dictInfoList == null){
            return typeItemList;
        }
        TypeItem typeItem = null;
        DictInfo dictInfo = null;
        for(int i = 0 ,size = dictInfoList.size() ; i < size ; i++){
            dictInfo = dictInfoList.get(i);
            typeItem = new TypeItem(i+"",imageType ,imageName , dictInfo.getTypeCode(),dictInfo.getTypeName());
            typeItemList.add(typeItem);
        }
        return typeItemList;
    }

}
