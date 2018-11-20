package com.hl.photo.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.hl.core.lib.activity.TitleActivity;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.dialog.DialogUtil;
import com.hl.photo.R;
import com.hl.photo.business.dto.PhotoUploadDTO;
import com.hl.photo.business.dto.Response;
import com.hl.photo.business.upload.ContinueUploadInputStreamEntity;
import com.hl.photo.business.upload.HttpUploadPicture;
import com.hl.photo.databinding.PhotoActivityUploadPictureBinding;
import com.hl.photo.support.util.ImageUtil;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.support.util.PreferenceManager;
import com.hl.photo.table.manager.PhotoManager;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.adapter.PictureListAdapter;
import com.hl.photo.ui.widget.HorizontalProgressBarWithNumber;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wxl on 2017/9/6.
 *
 *   上传照片照片页面
 */
public class UploadPictureActivity extends TitleActivity<TitleBar> {

//    @BindView(R2.id.gridvew_uploadpicture)
    GridView pictureList;    //上传图片列表
//    @BindView(R2.id.ds_btnPicDel)
    TextView deletePicture;   //照片删除
//    @BindView(R2.id.ds_btn_upload_pic)
    TextView uploadPicture;   //照片上传

    private Context mContext;
    private String reportNo;
    private List<PhotoInfo> photoInfoList;
    private PictureListAdapter pictureListAdapter;
    //获取选中待上传的照片
    private List<PhotoInfo> upLoadList ;
    private int upCount = 0 ;  //待上传的照片数量
    private PhotoActivityUploadPictureBinding binding;

    @Override
    protected Object entryInterceptor(Intent intent) {

        reportNo = intent.getStringExtra(PhotoClaimUtil.REPORT_NO);

        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = getString(R.string.photo_title);
        titleBar.showRightBtn = true;
        titleBar.rightBtnText = "全选";
    }

    @Override
    protected Object initLayout() {
        bindView = getLayoutInflater().inflate(R.layout.photo_activity_upload_picture, null, false);
        binding = DataBindingUtil.bind(bindView);
        //binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.photo_activity_upload_picture, null, false);
        return bindView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =  this;
    }

    @Override
    public void initData() {
        super.initData();
        photoInfoList = new ArrayList<>();
        pictureListAdapter = new PictureListAdapter(mContext,photoInfoList);
        binding.gridvewUploadpicture.setAdapter(pictureListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPictures();
        setClickEvent();
    }

    private void setClickEvent() {
        binding.dsBtnPicDel.setOnClickListener(v->deleteCheckedPictures());
        binding.dsBtnUploadPic.setOnClickListener(v->uploadCheckedPictures());
        binding.gridvewUploadpicture.setOnItemClickListener(this::browsImages);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        if (id == R.id.title_right_btn){ // 全选
            //照片全选
            pictureListAdapter.checkedAllImgs();
        }
    }

    /**
     *  加载数据库中的未上传照片
     */
    private void loadPictures(){
        List<PhotoInfo> tempList = PhotoManager.getInstance().getUnUploadPhotoListByReportCode(reportNo);
        if(tempList!=null){
            photoInfoList.clear();
            photoInfoList.addAll(tempList);
        }else{
            photoInfoList.clear();
        }
        pictureListAdapter.notifyDataSetChanged();
    }


    /**
      *  删除选中的照片
     */
//    @OnClick(R2.id.ds_btnPicDel)
    public void deleteCheckedPictures(){

        final SparseBooleanArray sparseBooleanArray = pictureListAdapter.getCheckedImgs();
        if (sparseBooleanArray == null || sparseBooleanArray.size() == 0) {
            UtilManager.Toast.show(mContext,"未选中删除照片");
            return;
        }

        View inflateView = LayoutInflater.from(mContext).inflate(R.layout.photo_dialog_textview_content_layout, null);
        TextView textView = (TextView) inflateView.findViewById(R.id.content_tv);
        textView.setText("\n确定要删除选中的照片吗？");
        DialogUtil.dialogCancleOrSure(mContext, inflateView, "提示", new DialogUtil.DialogOnClickListener() {
            @Override
            public void onClickListener(Dialog dialog) {
                //取消
                dialog.dismiss();
            }
        }, dialog -> {
            //确定删除
            //获得选中的照片
            for (int i = 0; i < sparseBooleanArray.size(); i++) {
                if (sparseBooleanArray.valueAt(i)) {  //如果选中
                    PhotoManager.getInstance().deletePhotoInfo(photoInfoList.get(sparseBooleanArray.keyAt(i)));  //删除对应位置的照片
                    //photoInfoList.remove(sparseBooleanArray.keyAt(i));//列表中移除
                }
            }
            List<PhotoInfo> templist = PhotoManager.getInstance().getUnUploadPhotoListByReportCode(reportNo);
            if (templist != null) {
                photoInfoList.clear();
                photoInfoList.addAll(templist);
            } else {
                photoInfoList.clear();
            }
            pictureListAdapter.clearCheckedImgs();
            pictureListAdapter.notifyDataSetChanged();
            UtilManager.Toast.show(mContext,"删除成功");

            dialog.dismiss();
        });



    }

    /**
     * 上传选中的照片
     */
//    @OnClick(R2.id.ds_btn_upload_pic)
    public void uploadCheckedPictures(){

        final SparseBooleanArray sparseBooleanArray = pictureListAdapter.getCheckedImgs();
        if (sparseBooleanArray == null || sparseBooleanArray.size() == 0) {
            UtilManager.Toast.show(mContext,"未选中照片");
            return;
        }

        View inflateView = LayoutInflater.from(mContext).inflate(R.layout.photo_dialog_textview_content_layout, null);
        TextView textView = (TextView) inflateView.findViewById(R.id.content_tv);
        textView.setText("\n确定要上传选中的照片吗？");
        DialogUtil.dialogCancleOrSure(mContext, inflateView, "提示", dialog -> dialog.dismiss(), dialog -> {
            //确定上传
            dialog.dismiss();
            //获取选中待上传的照片
            upLoadList = new ArrayList<PhotoInfo>();

            for (int i = 0; i < sparseBooleanArray.size(); i++) {
                if (sparseBooleanArray.valueAt(i)) {  //如果选中
                    photoInfoList.get(sparseBooleanArray.keyAt(i)).setTag(sparseBooleanArray.keyAt(i));
                    upLoadList.add(photoInfoList.get(sparseBooleanArray.keyAt(i)));
                }
            }
            //具体上传逻辑...
            if(upLoadList!=null){
                upCount = upLoadList.size();
                showDialog("正在上传，请稍后...");
                for (PhotoInfo photoInfo : upLoadList){
                    new UploadImgTask().execute(photoInfo);
                }

            }

        });

    }

    /**
     * 预览上传照片
     */
//    @OnItemClick(R2.id.gridvew_uploadpicture)
    protected void  browsImages(AdapterView<?> parent, View view, int position, long id){

        PhotoInfo image = photoInfoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString(PhotoClaimUtil.REPORT_NO,reportNo);
        bundle.putString(PhotoClaimUtil.IMAGE_TYPE, image.getImageType());
        bundle.putString(PhotoClaimUtil.IMAGE_SUB_TYPE, image.getImageSubtype());
        bundle.putString(PhotoClaimUtil.IMAGE_NAME, image.getImageName());
        startActivity(ImageBrowseActivity.class,bundle);
    }

    /**
     *  上传图片异步类
     */
    class UploadImgTask extends AsyncTask<PhotoInfo, Integer, Response<String>> {

        HorizontalProgressBarWithNumber mProgressBar = null;   //上传照片所对应的进度条
        private int sentSize;
        private int totalSize;
        private PhotoInfo img;

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            if(mProgressBar == null ){
                mProgressBar = new HorizontalProgressBarWithNumber(mContext,null);
            }

            float type = values[0];

            if (type == 1) {  //初始化进度条
                totalSize = values[1];
                mProgressBar.setMax(100);
                mProgressBar.setProgress(0);

            } else if (type == 2) {  //设置完成进度
                int result = values[1] * 100 / totalSize;
                mProgressBar.setProgress(result);
                System.out.println("++++++++++++"+mProgressBar);
            }

            super.onProgressUpdate(values);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Response doInBackground(PhotoInfo... params) {
            Response response = null;

            img = params[0];
            //获得上传图片所在的进度条
            ArrayMap<String,HorizontalProgressBarWithNumber> arrayMap =  pictureListAdapter.getAllProgressBar();
            mProgressBar = arrayMap.get(img.getImagePath());
            System.out.println("======================="+mProgressBar);
            try {
                int intValue = new Long(new File(img.getImagePath()).length()).intValue();
                publishProgress(1, intValue);
                //封装类 for test ,实际环境中应将图片对应的base64写入
                img.setImageData(ImageUtil.encodeBase64File(img.getImagePath()));

                PhotoUploadDTO photoUploadDTO = new PhotoUploadDTO();
                photoUploadDTO.setAppCode("CXLP");
                photoUploadDTO.setAppName("车险理赔");
                photoUploadDTO.setOrgCode(PreferenceManager.getString(PhotoClaimUtil.COM_CODE, ""));
                photoUploadDTO.setOrgName(PreferenceManager.getString(PhotoClaimUtil.COM_CODE_NAME, ""));
                photoUploadDTO.setUserCode(PreferenceManager.getString(PhotoClaimUtil.LOGIN_NAME, ""));
                photoUploadDTO.setUserName(PreferenceManager.getString(PhotoClaimUtil.HANDLER_NAME, ""));
                photoUploadDTO.setReportNo(reportNo);
                photoUploadDTO.setData(img);

                response = HttpUploadPicture.uploadPicture(photoUploadDTO, new OnProgressListener(0));

            } catch (Exception ex){
                //错误处理
            }


            return response;
        }

        @Override
        protected void onPostExecute(Response response) {
            //上传成功
            if (response != null && ("0".equals(response.getCode()))) {
                SparseBooleanArray sparseBooleanArray = pictureListAdapter.getCheckedImgs();
                sparseBooleanArray.delete(img.getTag());  //删除选中的位置
//                PhotoManager.getInstance().deletePhotoInfo(img);  //删除对应位置的照片
                img.setImageUpload("1");
                PhotoManager.getInstance().updatePhotoInfo(img);//修改图片上传状态
                List<PhotoInfo> templist = PhotoManager.getInstance().getUnUploadPhotoListByReportCode(reportNo);
                if (templist != null) {
                    photoInfoList.clear();
                    photoInfoList.addAll(templist);
                } else {
                    photoInfoList.clear();
                }
                pictureListAdapter.notifyDataSetChanged();
            } else{   //上传失败的处理
                mProgressBar.setProgress(0);
                UtilManager.Toast.show(mContext,response.getMessage());
            }
            if(--upCount == 0){
                dismissDialog();
            }
        }

        /**
         *
         * 上传进度监听器
         */
        private class OnProgressListener implements ContinueUploadInputStreamEntity.ProgressListener {
            int originalLength = 0;

            public OnProgressListener(int originalLength) {
                this.originalLength = originalLength;
            }

            @Override
            public void transferred(long num) {
                sentSize = this.originalLength + (int) num;

                publishProgress(2, sentSize);
            }

            @Override
            public void onUpload(InputStream in, OutputStream out) {
                if (isCancelled()) {
                    try {
                        in.close();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }





}
