package com.hl.photo.ui.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hl.core.lib.activity.TitleActivity;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.photo.R;
import com.hl.photo.business.photo.PhotoUtil;
import com.hl.photo.databinding.PhotoActivityShowGridviewBinding;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.table.manager.DictManager;
import com.hl.photo.table.manager.PhotoManager;
import com.hl.photo.table.model.DictInfo;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.adapter.PhotoImageAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: 拍照子分类页面
 * @Package: com.hl.photo.view
 * @Author: liyu
 * @Date: 2018/4/18/ 10:15
 * @Copyright: hl
 */


public class ShowPictureAndTextActivity extends TitleActivity<TitleBar> implements PhotoUtil.TakePicCallBack {
    private static String seedKey;
    private static String reportNo;
    private String taskNo;
    private static String pictureType;
    private static String pictureTypeName;
    private static String signId;
    private static String signName;
    private static String taskType;
    private String PATH;
    private Context mContext;
    private PhotoActivityShowGridviewBinding binding;
    private PhotoImageAdapter adapter;
    private PopupWindow popupWindow;


    @Override
    protected Object entryInterceptor(Intent intent) {
        mContext = this;
        seedKey = intent.getStringExtra(PhotoClaimUtil.SEED_KEY);
        reportNo = intent.getStringExtra("reportNo");
        taskNo = intent.getStringExtra("taskNo");
        pictureType = intent.getStringExtra("pictureType");
        pictureTypeName = intent.getStringExtra("pictureTypeName");
        signId = intent.getStringExtra("signId");
        signName = intent.getStringExtra("signName");
        taskType = intent.getStringExtra(PhotoClaimUtil.TASK_TYPE);
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = getString(R.string.photo_title);
        titleBar.showBack = true;
    }

    @Override
    protected Object initLayout() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.photo_activity_show_gridview, null, false);
        return binding.getRoot();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + getString(R.string.photo_dir)+
            getString(R.string.photo_img_dir) + "/" + reportNo+"/" + pictureType;
        checkStage();
        setHeaderView();
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadPicture();
        binding.gridviewPhotoSubtypeShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickPhotoType(position);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
//        cancle();
    }

    /**
     * 检查是否可以存储
     */
    public void checkStage() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            if (reportNo!=null){
                File dir = new File(PATH );
                if (!dir.isDirectory()) {
                    dir.mkdirs();
                }
            }else{
                UtilManager.Toast.show(mContext,"请检查手机存储");
                finish();
            }
        } else {
            UtilManager.Toast.show(mContext,"请检查手机存储");
            finish();
        }
    }

    /**
     * 设置头View
     */
    private void setHeaderView(){
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.photo_header_view_layout, null);
        TextView type_tv =  headerView.findViewById(R.id.img_type_tv);
        type_tv.setText(pictureTypeName);
        binding.gridviewPhotoSubtypeShow.addHeaderView(headerView);
    }

    /**
     * 加载图片
     */
    private void loadPicture() {
        if (adapter == null){
            showDialog(getResources().getString(R.string.photo_loading_photo));
        }
        LoadPictureTask task = new LoadPictureTask(new LoadCompleteListener() {
            @Override
            public void onLoadComplete(List<PhotoInfo> result) {
                dismissDialog();
                adapter = new PhotoImageAdapter(mContext,result);
                binding.gridviewPhotoSubtypeShow.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        task.execute(null,null,null);

    }

    @Override
    public void takePicFromCamera(Bundle bundle) {
        startActivity(CameraActivity.class,bundle);
        cancle();
    }

    @Override
    public void choicePicFromPhoto(Bundle bundle) {
        startActivity(PhotoLocalChooseActivity.class,bundle);
        cancle();
    }

    @Override
    public void canclePopupWindow() {
        cancle();
    }

    private static class LoadPictureTask extends AsyncTask<Void, Void, List<PhotoInfo>>{
        LoadCompleteListener listener;
        private LoadPictureTask(LoadCompleteListener listener) {
            this.listener = listener;
        }
        @Override
        protected List<PhotoInfo> doInBackground(Void... voids) {
            List<PhotoInfo> photoInfosList = new ArrayList<PhotoInfo>();
            //所有的图片集合
//                ArrayList<TypeItem> allPhotoTypeList = PictureTypeStaticCode.getAllPhotoTypeList();
//                ArrayList<TypeItem> phptoTypeList = PictureTypeStaticCode.getPhptoTypeList(pictureType, allPhotoTypeList);
            List<DictInfo> seedPhptoTypeList = DictManager.getInstance().getDictInfosByKey(seedKey);
            //查勘 标的 三者 物损 其他 ，添加一个空的图片
            if (pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_SURVEY_CODE) || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_EVAL_BD_CODE)
                    || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE) || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_POPLOSS_CODE)
                    || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_OTHER_CODE)) {
                //先加一个空的
                DictInfo typeItem = seedPhptoTypeList.get(0);
                PhotoInfo firstPhoto = new PhotoInfo();
                firstPhoto.setImageDescribe(typeItem.getTypeName());
                firstPhoto.setImageName(pictureTypeName);
                firstPhoto.setImageType(pictureType);
                firstPhoto.setImageSubtype(typeItem.getTypeCode());
                firstPhoto.setSeedKey(seedKey);
                photoInfosList.add(firstPhoto);
            }

            PhotoInfo po = null;
            for (DictInfo item : seedPhptoTypeList) {
                po = new PhotoInfo();
                if (pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_SURVEY_CODE) || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_EVAL_BD_CODE)
                        || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_EVAL_SZ_CODE) || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_POPLOSS_CODE)
                        || pictureType.equals(PhotoClaimUtil.PHOTO_TYPE_OTHER_CODE)) {
                    List<PhotoInfo> list = PhotoManager.getInstance().getImageListByReportNoAndSubType(reportNo, item.getTypeCode());
                    if (list != null && list.size() > 0) {
                        photoInfosList.addAll(list);
                    }
                }else{
                    List<PhotoInfo> list = null;
                    //标的　三者　人伤　物损
                    if(PhotoClaimUtil.TASK_TYPE_EVAL_BD.equals(taskType) || PhotoClaimUtil.TASK_TYPE_EVAL_SZ.equals(taskType)
                            || PhotoClaimUtil.TASK_TYPE_POP.equals(taskType) || PhotoClaimUtil.TASK_TYPE_INJURY.equals(taskType)){
                        list = PhotoManager.getInstance().getImageListByReportNoAndSubTypeAndSignName(reportNo, item.getTypeCode(),signName);
                    }else{
                        list = PhotoManager.getInstance().getImageListByReportNoAndSubType(reportNo, item.getTypeCode());
                    }
                    if (list != null && list.size() > 0) {
                        PhotoInfo photoInfo = list.get(list.size() - 1);
                        po.setImagePath(photoInfo.getImagePath());
                        po.setImageUpload(photoInfo.getImageUpload());
                    }
                    po.setImageDescribe(item.getTypeName());
                    po.setImageName(pictureTypeName);
                    po.setImageType(pictureType);
                    po.setImageSubtype(item.getTypeCode());
                    po.setSeedKey(seedKey);
                    po.setTaskType(taskType);
                    po.setSignId(signId);
                    po.setSignName(signName);
                    photoInfosList.add(po);
                }
            }
            return photoInfosList;
        }

        @Override
        public void onPostExecute(List<PhotoInfo> result) {
            listener.onLoadComplete(result);
        }
    }
    interface LoadCompleteListener{
        void onLoadComplete(List<PhotoInfo> result);
    }

    protected void clickPhotoType(int position){
        final PhotoInfo image = (PhotoInfo)adapter.getItem(position);
        List<PhotoInfo> list = new ArrayList<PhotoInfo>();

        //标的　三者　人伤　物损
        if(PhotoClaimUtil.TASK_TYPE_EVAL_BD.equals(taskType) || PhotoClaimUtil.TASK_TYPE_EVAL_SZ.equals(taskType)
                || PhotoClaimUtil.TASK_TYPE_POP.equals(taskType) || PhotoClaimUtil.TASK_TYPE_INJURY.equals(taskType)){
            list = PhotoManager.getInstance().getImageListByReportNoAndSubTypeAndSignName(reportNo, image.getImageSubtype(),signName);
        }else{
            list = PhotoManager.getInstance().getImageListByReportNoAndSubType(reportNo, image.getImageSubtype());
        }

        if(list!=null&&list.size()>0) {
            Bundle bundle = new Bundle();
            bundle.putString(PhotoClaimUtil.REPORT_NO, reportNo);
            bundle.putString(PhotoClaimUtil.IMAGE_TYPE, image.getImageType());
            bundle.putString(PhotoClaimUtil.IMAGE_SUB_TYPE, image.getImageSubtype());
            bundle.putString(PhotoClaimUtil.IMAGE_NAME, image.getImageName());
            bundle.putString(PhotoClaimUtil.TASK_TYPE, image.getTaskType());
            //sign存人名或车牌
            bundle.putString(PhotoClaimUtil.SIGN_NAME, image.getSignName() == null ? "" : image.getSignName());
            startActivity(ImageBrowseActivity.class,bundle);
        }else {
            showPopWindow(image);
        }
    }

    /**
     * 点击某个照片小类
     * @param photo
     */
    private void showPopWindow(final PhotoInfo photo) {
        View popwindowLayout = LayoutInflater.from(mContext).inflate(R.layout.photo_popwindow_layout, null, false);
//            bindPopWindow(popwindowLayout, photo);
        PhotoUtil.bindPopWindow(this,popwindowLayout,photo,reportNo,pictureType);
        popupWindow = PopupWindowUtil.getInitince(getWindow()).initPopuptWindowNoCancelBtn(binding.getRoot(), popwindowLayout);
    }

    private void cancle() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
