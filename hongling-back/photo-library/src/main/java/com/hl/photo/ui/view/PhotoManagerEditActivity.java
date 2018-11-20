package com.hl.photo.ui.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.core.lib.activity.TitleActivity;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.photo.R;
import com.hl.photo.databinding.PhotoActivityManageEditBinding;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.table.manager.PhotoManager;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.widget.HolderViewAdapter;
import com.hl.photo.ui.widget.PhotoManageEditHView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Describe:
 * @Package: com.hl.photo.ui.view
 * @Author: liyu
 * @Date: 2018/4/19/ 14:53
 * @Copyright: hl
 */


public class PhotoManagerEditActivity extends TitleActivity<TitleBar> {


    public String reportNo="20170203";
    public String taskNo="123456";
    private Context mContext;

    private List<PhotoInfo> surveyPhotoInfoList = new ArrayList<PhotoInfo>();
    private List<PhotoInfo> surveyPhotoListFromDB = new ArrayList<PhotoInfo>();
    private List<PhotoInfo> evalPhotoInfoList= new ArrayList<PhotoInfo>();
    private List<PhotoInfo> evalPhotoListFromDB = new ArrayList<PhotoInfo>();
    private List<PhotoInfo> thirdEvalPhotoInfoList= new ArrayList<PhotoInfo>();
    private List<PhotoInfo> thirdEvalPhotoListFromDB = new ArrayList<PhotoInfo>();

    //图片缓存用来保存GridView中每个Item的图片，以便释放
    public static Map<String,Bitmap> gridviewBitmapCaches = new HashMap<String,Bitmap>();
    //选择获取照片方式
    private PopupWindow popupWindow;
    private PhotoActivityManageEditBinding binding;

    @Override
    protected Object entryInterceptor(Intent intent) {
        reportNo = intent.getStringExtra(PhotoClaimUtil.REPORT_NO);
        taskNo = intent.getStringExtra(PhotoClaimUtil.TASK_NO);
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = getString(R.string.photo_title);
        titleBar.rightBtnText = getString(R.string.photo_edit_over);
        titleBar.showRightBtn = true;
    }

    @Override
    protected Object initLayout() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.photo_activity_manage_edit, null, false);
        return binding.getRoot();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setClickEvent();
    }



    @Override
    protected void onResume() {
        super.onResume();
        showAllPhoto();
    }

    @Override
    protected void onDestroy() {
        //释放bitmap造成的缓存
        if (surveyPhotoListFromDB!=null&&surveyPhotoListFromDB.size()>0){
            for(int del=0;del<surveyPhotoListFromDB.size();del++){
                Bitmap delBitmap = gridviewBitmapCaches.get(surveyPhotoListFromDB.get(del).getImageName());
                if(delBitmap != null){
                    //如果非空则表示有缓存的bitmap，需要清理
                    Log.d("", "release position:"+ del);
                    //从缓存中移除该del->bitmap的映射
                    gridviewBitmapCaches.remove(surveyPhotoListFromDB.get(del).getImageName());
                    delBitmap.recycle();
                    delBitmap = null;
                }
            }
        }
        System.gc();
        super.onDestroy();

    }

    /**
     * 点击事件
     */
    private void setClickEvent() {
        binding.dsPicshowSurvey.setOnItemClickListener(this::clickSurveyPhoto);
        binding.dsPicShowEval.setOnItemClickListener(this::clickEvalPhoto);
        binding.dsPicShowThirdEval.setOnItemClickListener(this::clickThirdPhoto);
        binding.bottomLayout.deleteTv.setOnClickListener(v->clickDeletePhoto());
        binding.bottomLayout.checkBox.setOnClickListener(v->clickCheckAllPhoto());
    }

    private void showAllPhoto() {
        HolderViewAdapter surveyAdapter = new HolderViewAdapter(mContext, surveyPhotoInfoList,PhotoManageEditHView.class);
        binding.dsPicshowSurvey.setAdapter(surveyAdapter);

        HolderViewAdapter evalAdapter = new HolderViewAdapter(mContext, evalPhotoInfoList,PhotoManageEditHView.class);
        binding.dsPicShowEval.setAdapter(evalAdapter);

        HolderViewAdapter thirdEvalAdapter = new HolderViewAdapter(mContext, thirdEvalPhotoInfoList,PhotoManageEditHView.class);
        binding.dsPicShowThirdEval.setAdapter(thirdEvalAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.title_right_btn){
            Bundle bundle = new Bundle();
            bundle.putString(PhotoClaimUtil.REPORT_NO,reportNo);
            bundle.putString(PhotoClaimUtil.TASK_NO,taskNo);
            startActivity(PhotoManagerActivity.class,bundle);
        }

    }


//    @OnItemClick(R2.id.ds_picshow_survey)
    protected void clickSurveyPhoto(AdapterView<?> parent, View view, int position, long id){
        final PhotoInfo photo = (PhotoInfo)parent.getAdapter().getItem(position);
        if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_NO.equals(photo.getImageType())){
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_ADD.equals(photo.getImageType())){
            showPopWindow(photo, 0.25f);
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_CAMERA.equals(photo.getImageType())){
            showPopWindow(photo, 0.25f);
        }
    }
//    @OnItemClick(R2.id.ds_pic_show_eval)
    protected void clickEvalPhoto(AdapterView<?> parent, View view, int position, long id){
        PhotoInfo photo = (PhotoInfo)parent.getAdapter().getItem(position);
        if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_NO.equals(photo.getImageType())){
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_ADD.equals(photo.getImageType())){
            showPopWindow(photo, 0.25f);
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_CAMERA.equals(photo.getImageType())){
            showPopWindow(photo, 0.25f);
        }
    }
//    @OnItemClick(R2.id.ds_pic_show_third_eval)
    protected void clickThirdPhoto(AdapterView<?> parent, View view, int position, long id){
        PhotoInfo photo = (PhotoInfo)parent.getAdapter().getItem(position);
        if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_NO.equals(photo.getImageType())){
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_ADD.equals(photo.getImageType())){
            showPopWindow(photo, 0.25f);
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_CAMERA.equals(photo.getImageType())){
            showPopWindow(photo, 0.25f);
        }
    }

//    @OnClick(R2.id.delete_tv)
    protected void clickDeletePhoto(){
        if(surveyPhotoListFromDB!=null&&surveyPhotoListFromDB.size()>0){
            for (PhotoInfo info:surveyPhotoListFromDB) {
                if (info.isChecked()){
                    PhotoManager.getInstance().deletePhotoInfo(info);
                }
            }
//           initSurveyData(false);//取消选中
        }
        if(evalPhotoListFromDB!=null&&evalPhotoListFromDB.size()>0){
            for (PhotoInfo info:evalPhotoListFromDB) {
                if (info.isChecked()){
                    PhotoManager.getInstance().deletePhotoInfo(info);
                }
            }
//            initEvalData(false);//取消选中
        }
        if(thirdEvalPhotoListFromDB!=null&&thirdEvalPhotoListFromDB.size()>0){
            for (PhotoInfo info:thirdEvalPhotoListFromDB) {
                if (info.isChecked()){
                    PhotoManager.getInstance().deletePhotoInfo(info);
                }
            }
//            initThirdEvalData(false);//取消选中
        }
        showAllPhoto();
    }
//    @OnClick(R2.id.check_box)
    protected void clickCheckAllPhoto(){
        showAllPhoto();
    }


    private void showPopWindow(final PhotoInfo photo, final float ratio) {
        View popwindowLayout = LayoutInflater.from(mContext).inflate(R.layout.photo_popwindow_layout, null, false);
        bindPopWindow(popwindowLayout, photo);
        popupWindow = PopupWindowUtil.getInitince(getWindow()).initPopuptWindowNoCancelBtn(binding.getRoot(), popwindowLayout);
    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void setWindowAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    private void bindPopWindow(View popView,final PhotoInfo photo) {
        TextView fromCameraTv = (TextView) popView.findViewById(R.id.photo_pop_txt_camera);
        TextView fromLocalTv = (TextView) popView.findViewById(R.id.photo_pop_txt_local);
        TextView cancleTv = (TextView) popView.findViewById(R.id.dialog_button);
        fromCameraTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CameraActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("reportCode",photo.getReportCode());
                bundle.putString("flowId",photo.getFlowId());
                bundle.putString("taskType",photo.getTaskType());
                intent.putExtras(bundle);
                startActivity(intent);
                cancle();
            }
        });
        fromLocalTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("reportCode",photo.getReportCode());
                bundle.putString("flowId",photo.getFlowId());
                bundle.putString("taskType",photo.getTaskType());
                startActivity(PhotoLocalChooseActivity.class,bundle);
                cancle();
            }
        });
        cancleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });
    }
    private void cancle() {
        if (popupWindow != null && popupWindow.isShowing()) {
            setWindowAlpha(1.0f);
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
