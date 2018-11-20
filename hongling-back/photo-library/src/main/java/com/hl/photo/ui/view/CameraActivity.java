package com.hl.photo.ui.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.SeekBar;

import com.hl.core.lib.activity.TitleActivity;
import com.hl.core.lib.bean.TypeItem;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.photo.PhotoApp;
import com.hl.photo.R;
import com.hl.photo.business.photo.PhotoUtil;
import com.hl.photo.databinding.PhotoActivityCameraBinding;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.support.util.CameraConstant;
import com.hl.photo.support.util.CameraConstant.Oriention;
import com.hl.photo.support.util.CameraImageTypeItem;
import com.hl.photo.support.util.CameraUtil;
import com.hl.photo.support.util.ImageUtil;
import com.hl.photo.support.util.NetUtil;
import com.hl.photo.support.util.TimestampTool;
import com.hl.photo.table.manager.DictManager;
import com.hl.photo.table.manager.PhotoManager;
import com.hl.photo.table.model.DictInfo;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.widget.HorizontalScrollWidget;
import com.hl.photo.ui.widget.HorizontalScrollWidgetItem;
import com.hl.photo.ui.widget.HorizontalScrollWidgetItemLayoutParams;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 自定义保存照片
 *
 * @author liyu 根据案件任务进行拍照 进入拍报页面,拍摄-->确定,重拍,连拍,取消,设置 确定保存照片并返回
 *         重拍覆盖原先所拍照片 连拍保存照片并再次拍摄 ,确定返回 取消不保存照片返回 设置照片大小及质量
 */
public class CameraActivity extends TitleActivity<TitleBar> {


    // 声明界面控制组件
    public SurfaceHolder surfaceHolder;
    // 声明照相机
    public Camera mCamera;

    //焦点动画
    private AnimationSet as = null;

    private int maxZoom;
    private Context context;
    public String flashMode;
    Camera.Parameters parameters;
    private String reportNo;
    //    private String flowId;
//    private String taskType;
    private String imageType;
    private String imageSubType;
    private String imageName;
    private String signId;
    private String signName;
    private String seedKey;
    private String taskType;

    private Boolean isChoice = true;
    private boolean isDialog = true;

    private String picName;
    private String imagePath;
    private String imageDescribe = "";
    private String imageLatitude;
    private String imageLongitude;
    private String imageAddress;
    private String imageTime;
    private List<PhotoInfo> imageList;
    private CameraImageTypeItem filterItemCur;
    //旋转
    private OrientationEventListener mOrientationEventListener;
    private Oriention mOriention = Oriention.ORIENTION_0;
    //弹出条
    ObjectAnimator mAnimatorCameraFilterTranslationDecrease;
    ObjectAnimator mAnimatorCameraFilterTranslationIncrease;
    AnimatorSet mAnimatorCameraFilterScaleIncrease;
    AnimatorSet mAnimatorCameraFilterScaleDecrease;

    public ArrayList<TypeItem> imageTypeList;
//    public List<Filter> imageTypeFilterList = null;

    private static String PATH;
    int turnFlag = 0;// 横屏

    private boolean ispreview = false;

//    private PhotoInfo imagePo;
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:

                    break;
            }
        }
    };
    private DisplayMetrics dm;
    private static final long BTN_FILTER_VALID_GAP_TIME = 300;
    private static final long ANIM_FILTER_BAR_DURING = 300;
    private static final long RUNNABLE_HIDE_FILTER_BAR_TIME = 3000;
    private static final long ANIM_FILTER_ITEM_DURING = 300;

    private String titleText ="";
    private String contentText ="";
    private String afterText ="";
    private boolean isDelete = false;
    Bitmap bmDialog = null;
    BitmapDrawable bdDialog = null;
    Bitmap bm = null;
    //图片缓存用来保存GridView中每个Item的图片，以便释放
    public static Map<String,Bitmap> gridviewBitmapCaches = new HashMap<String,Bitmap>();
    private PhotoActivityCameraBinding binding;


    @Override
    protected Object initLayout() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.photo_activity_camera, null, false);
        return binding.getRoot();
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.hasTitleBar = false;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置界面全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 设置防休眠状态
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        // 横屏
        context = this;
        PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + context.getString(R.string.photo_dir) + context.getString(R.string.photo_img_dir) + "/";
        checkStage();// 检查设备
        super.onCreate(savedInstanceState);
        initData();
        initViewProperty();
        initClickEvent();
        initLocation();
    }

    private void initClickEvent() {
        binding.mSurfaceView.setOnClickListener(v -> clickmSurfaceVie());
        binding.mBtnExit.setOnClickListener(v->clickmBtnExit());
        binding.mBtnFlash.setOnClickListener(v->clickmBtnFlash());
        binding.mBtnFilter.setOnClickListener(v->clickmBtnFilter());
        binding.mBtnCamera.setOnClickListener(v->clickmBtnCamera());
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        registerOrientationSensor();
        new Handler().postDelayed(new Runnable(){
            public void run() {
                if (mCamera!=null){
                    mCamera.cancelAutoFocus();
                    mCamera.startPreview();
                }
            }
        }, 1500);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        unregisterOrientationSensor();

    }
    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
//        locationService.unregisterListener(mListener); //注销掉监听
//        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        //释放bitmap造成的缓存
        if (bm!=null){
            if (!bm.isRecycled()){
                bm.recycle();
                bm = null;
            }
        }
        for(int del=0;del<gridviewBitmapCaches.size();del++){
            Bitmap delBitmap = gridviewBitmapCaches.get(del);
            if(delBitmap != null){
                //如果非空则表示有缓存的bitmap，需要清理
                Log.d("", "release position:" + del);
                //从缓存中移除该del->bitmap的映射
                gridviewBitmapCaches.remove(del);
                delBitmap.recycle();
                delBitmap = null;
            }
        }
//        LocationUtil.getInstance().destroy();
        System.gc();
        super.onDestroy();
    }
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        initLocation();
    }


    public void initData() {
        Intent intent = getIntent();
        reportNo = intent.getStringExtra(PhotoClaimUtil.REPORT_NO);
        imageType = intent.getStringExtra(PhotoClaimUtil.IMAGE_TYPE);
        imageSubType = intent.getStringExtra(PhotoClaimUtil.IMAGE_SUB_TYPE);
        imageName = intent.getStringExtra(PhotoClaimUtil.IMAGE_NAME);
        signId = intent.getStringExtra(PhotoClaimUtil.SIGN_ID);
        signName = intent.getStringExtra(PhotoClaimUtil.SIGN_NAME);
        seedKey = intent.getStringExtra(PhotoClaimUtil.SEED_KEY);
        taskType = intent.getStringExtra(PhotoClaimUtil.TASK_TYPE);
//        imagePo = new PhotoInfo();
//        imagePo.setReportCode(reportNo);
//        imagePo.setImageName(imageName);
//        imagePo.setImageType(imageType);
//        imagePo.setImageSubtype(imageSubType);
//        imagePo.setSignName(signName);
    }

    public void initViewProperty() {

        // 获取DisplayMetrics
        dm = this.getResources().getDisplayMetrics();
        //初始化类型选择条动画
        initCameraFilterScaleAnimator();
        initCameraFilterTranslationAnimator();
        as = new AnimationSet(true);
        Animation scaleAnimation = new ScaleAnimation(1.4f, 1.0f, 1.4f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(CameraConstant.FOCUS_MODE_CONTINUOUS_TIME);//设置动画持续时间为1秒
        scaleAnimation.setFillAfter(true);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {

            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub
            }

            public void onAnimationEnd(Animation animation) {
                binding.viewFocuse.setVisibility(View.GONE);
            }

            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub
            }
        });
        as.addAnimation(scaleAnimation);

        binding.tvCameraImageTypeCenter.setTextColor(Color.argb(125, 174, 174, 174));   //文字透明度

        surfaceHolder = binding.mSurfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.setKeepScreenOn(true);
        binding.mSurfaceView.setFocusable(true);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ispreview == false && mCamera == null) {
                        mCamera = Camera.open();
                    }
                    mCamera.setPreviewDisplay(surfaceHolder);
                    initCamera();
//                    mCamera.startPreview();
                } catch (IOException e) {
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                    UtilManager.Toast.show(context, "打开摄像头失败");
                    finish();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                //实现自动对焦
                mCamera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (camera != null && !PhotoApp.instance().isTakingPicture) {
                            if (View.INVISIBLE == binding.viewFocuse.getVisibility()) {
                                binding.viewFocuse.setVisibility(View.VISIBLE);
                                binding.viewFocuse.getParent().requestTransparentRegion(binding.mSurfaceView);
                            }
                        } else if (camera == null) {
                            PhotoApp.instance().isTakingPicture = false;
                        } else if (camera != null) {
                            if (View.INVISIBLE ==  binding.viewFocuse.getVisibility()) {
                                binding.viewFocuse.setVisibility(View.VISIBLE);
                                binding.viewFocuse.getParent().requestTransparentRegion(binding.mSurfaceView);
                            }
                        }
                        if (success) {
                            if (mCamera != null) {
                                mCamera.cancelAutoFocus();//只有加上了这一句，才会自动对焦。
                            }
                            initCamera();//实现相机的参数初始化
                            binding.viewFocuse.startAnimation(as);
                        }
                    }

                });
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mCamera != null) {
                    mCamera.setPreviewCallback(null);
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                    ispreview = false;
                }
            }
        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //手动拖拽实现相机的缩放
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                parameters = mCamera.getParameters();
                parameters.setZoom((int) (((progress * (1.0f / (maxZoom * 100))) * maxZoom)));
                mCamera.setParameters(parameters);

            }
        });
        binding.mHswFilter.clearItems();

        List<DictInfo> seedPhptoTypeList = DictManager.getInstance().getDictInfosByKey(seedKey);
        imageTypeList = PhotoUtil.getTypeItemsByDict(seedPhptoTypeList,imageType,imageName);
//        imageTypeList = PictureTypeStaticCode.getPhptoTypeList(imageType,PictureTypeStaticCode.getAllPhotoTypeList());

        if (imageTypeList != null && !imageTypeList.isEmpty()) {
            Boolean isSelected = false;
            for (int index = 0; index < imageTypeList.size(); index++) {
//                HorizontalScrollWidgetItem item = new CameraImageTypeItem(R.layout.camera_image_type_item, imageTypeFilterList.get(index), CameraActivity.this);
                HorizontalScrollWidgetItem item = new CameraImageTypeItem(R.layout.photo_camera_image_type_item, imageTypeList.get(index), CameraActivity.this);
                HorizontalScrollWidgetItemLayoutParams ilp = new HorizontalScrollWidgetItemLayoutParams();
                ilp.leftMargin = (int) (dm.density * 10);
                ilp.rightMargin = (int) (dm.density * 10);
                ilp.topMargin = (int) (dm.density * 10);
                ilp.bottomMargin = (int) (dm.density * 10);

                if (index == 0) ilp.leftMargin = ilp.leftMargin * 2;
                if (index == imageTypeList.size() - 1) ilp.rightMargin = ilp.rightMargin * 2;

                item.setItemLayoutParams(ilp);
                binding.mHswFilter.addItem(item);
                //标的　三者　人伤　物损
                if(PhotoClaimUtil.TASK_TYPE_EVAL_BD.equals(taskType) || PhotoClaimUtil.TASK_TYPE_EVAL_SZ.equals(taskType)
                        ||PhotoClaimUtil.TASK_TYPE_POP.equals(taskType) || PhotoClaimUtil.TASK_TYPE_INJURY.equals(taskType)){
                    imageList = PhotoManager.getInstance().getImageListByReportNoAndSubTypeAndSignName(reportNo, imageTypeList.get(index).getCode(),signName);
                }else{
                    imageList = PhotoManager.getInstance().getImageListByReportNoAndSubType(reportNo, imageTypeList.get(index).getCode());
                }

                if (isChoice) {
                    Boolean isHavePicture = false;
                    //先把照片显示出来
                    if (imageList != null && imageList.size() > 0) {
                        PhotoInfo image = imageList.get(imageList.size() - 1);
                        CameraImageTypeItem filterItem = (CameraImageTypeItem) item;
//                                //设置图片
                        ImageUtil.getInstance(3, ImageUtil.Type.LIFO).loadImage(image.getImagePath(), filterItem.getImageViewFilterIcon());
                        ViewGroup.LayoutParams lp = filterItem.getImageViewFilterIcon().getLayoutParams();
                        bm = ImageUtil.getBitmapThumbnail(image.getImagePath(), lp.width, lp.height);
                        filterItem.getImageViewFilterIcon().setImageBitmap(bm);
                        gridviewBitmapCaches.put("", bm);
                        if (image.getImageSubtype().equals(imageSubType)) {//照片列表中包含选择类型图片
                            isHavePicture = true;
                        }
                    }
                    //显示选择的类型
                    if ( !TextUtils.isEmpty(imageSubType) && imageTypeList.get(index).getCode().equals(imageSubType)) {

                        isSelected = setSelectedImageItem(item);
                    }
                } else {
                    if (imageList != null && imageList.size() > 0) {
                        Boolean isHavePicture = false;
                        for (PhotoInfo image : imageList) {
                            if (image.getTaskType() != null && image.getTaskType().equals(imageTypeList.get(index).getID())) {
                                CameraImageTypeItem filterItem = (CameraImageTypeItem) item;
                                CameraConstant.Filter filterCur = filterItem.getFilter();
                                ViewGroup.LayoutParams lp = filterItem.getImageViewFilterIcon().getLayoutParams();
                                bm = ImageUtil.getBitmapThumbnail(image.getImagePath(), lp.width, lp.height);
                                filterItem.getImageViewFilterIcon().setImageBitmap(bm);
                                gridviewBitmapCaches.put("", bm);
                                isHavePicture = true;
//									break;//已拍照跳出
                            } else if (!isSelected && !isHavePicture) {

                                isSelected = setSelectedImageItem(item);

                                break;//未拍照的选上
                            } else if (isHavePicture) {
                                break;
                            }
                        }
                    } else if (!isSelected) {
                        //都没选默认第一个

                        isSelected = setSelectedImageItem(item);

                    }

                }
            }
            //都选默认第一个
            if (!isSelected) {
                HorizontalScrollWidgetItem item = binding.mHswFilter.getHswItems().get(0);

                isSelected = setSelectedImageItem(item);

            }
            binding.mHswFilter.invalidate();

        }
//        if (imageTypeFilterList == null || imageTypeFilterList.size() == 0 || (imageTypeFilterList.size() == 1 && imageTypeFilterList.get(0) == Filter.imageGeneralDocumentsTypeList_1)) {
        if (imageTypeList == null || imageTypeList.size() == 0 || imageTypeList.size() == 1 ) {
            binding.mBtnFilter.setVisibility(View.GONE);
            binding.mHswFilter.setVisibility(View.GONE);
        } else {
            // 更新图片类型状态
            binding.mHswFilter.setVisibility(View.VISIBLE);
        }
            //初始化图片类型条
            binding.mHswFilter.setHorizontalScrollWidgetScrollListener(new HorizontalScrollWidget.HorizontalScrollWidgetScrollListener() {

                @Override
                public void onScroll(int l, int t, int oldl, int oldt) {

//				// 当滤镜条发生滚动时,重置隐藏滤镜条任务
//				if (Math.abs(l - oldl) > 0)
//					hideHswFilter(RUNNABLE_HIDE_FILTER_BAR_TIME);

                }
            });
        binding.mHswFilter.setHorizontalScrollWidgetVisibilityListener(new HorizontalScrollWidget.HorizontalScrollWidgetVisibilityListener() {

                @Override
                public void onVisibilityState(int before, int after) {

                    if (after == View.VISIBLE) {
                        binding.mBtnFilter.setSelected(true);
                    } else {
                        binding.mBtnFilter.setSelected(false);
                    }

                }
            });
        binding.mHswFilter.setHorizontalScrollWidgetItemListener(new HorizontalScrollWidget.HorizontalScrollWidgetItemListener() {
                @Override
                public void onItemSelected(HorizontalScrollWidgetItem item, View view) {

//				// 当有滤镜选项被操作时,重置隐藏滤镜条任务
//				hideHswFilter(RUNNABLE_HIDE_FILTER_BAR_TIME);

                    // 设置滤镜
                    filterItemCur = (CameraImageTypeItem) item;
//                    Filter filterCur = filterItemCur.getFilter();
                    TypeItem typeItem = filterItemCur.getTypeItem();
//				mCameraEngine.controlCameraFilter(filterCur);
                    imageSubType = typeItem.getCode();
                    if (imageSubType != null) {
                        if ("1370120".equals(imageSubType) || "1370122".equals(imageSubType) || "1370121".equals(imageSubType) || "1370123".equals(imageSubType) || "1370804".equals(imageSubType) || "1370801".equals(imageSubType) || "1370803".equals(imageSubType)) {
                            biankuangView();
                        } else {
                            biankuangGone();
                        }
                    }
                    binding.tvCameraImageTypeCenter.setText(typeItem.getValue());
                    // 当前选中的与之前选中的为同一个
                    if (binding.mHswFilter.getCurrentSelectedView() == view) {
                        return;
                    }
                    // 当前选中的与之前选中的不为同一个(之前可能没选中,也可能选中)
                    else {

                        // 之前选中
                        if (binding.mHswFilter.getCurrentSelectedView() != null) {
                            CameraImageTypeItem filterItemPre = (CameraImageTypeItem) binding.mHswFilter.getCurrentSelectedItem();
                            setTakedPhotoOnImageView(filterItemPre,binding.mHswFilter.getCurrentSelectedView(),"0");
                        }

                        // 当前选中
                        setTakedPhotoOnImageView(filterItemCur,view,"1");
                    }
                    // 刷新视图
                    binding.mHswFilter.invalidate();

                }
            });
    }
    /**
     * 设置选中类型
     * @param item
     */
    private boolean setSelectedImageItem(HorizontalScrollWidgetItem item){
        binding.mHswFilter.setSelectedItem(item);
        mAnimatorCameraFilterScaleIncrease.setTarget(binding.mHswFilter.getCurrentSelectedView());
        mAnimatorCameraFilterScaleIncrease.end();
        mAnimatorCameraFilterScaleIncrease.start();
        CameraImageTypeItem filterItem = (CameraImageTypeItem) item;
        TypeItem typeItem = filterItem.getTypeItem();
        filterItem.getImageViewFilterIcon().setBackgroundResource(R.mipmap.photo_camera_filter_none_down);
        imageSubType = typeItem.getCode();
        if (imageSubType != null) {
            if ("1370120".equals(imageSubType) || "1370122".equals(imageSubType) || "1370121".equals(imageSubType) || "1370123".equals(imageSubType) || "1370804".equals(imageSubType) || "1370801".equals(imageSubType) || "1370803".equals(imageSubType)) {
                biankuangView();
            } else {
                biankuangGone();
            }
        }
//        tvCameraImageTypeCenter.setText(typeItem.getValue());
        filterItemCur = filterItem;
        return true;
    }

    /**
     * 设置已拍的图片到imageView
     * @param view
     * @param selectedType 0 之前选中 ， 1 当前选中
     */
    private void setTakedPhotoOnImageView(CameraImageTypeItem filterItemPre, View view , String selectedType){
        mAnimatorCameraFilterScaleIncrease.setTarget(view);
        mAnimatorCameraFilterScaleIncrease.end();
        mAnimatorCameraFilterScaleIncrease.start();
        //标的　三者　人伤　物损
        if(PhotoClaimUtil.TASK_TYPE_EVAL_BD.equals(taskType) || PhotoClaimUtil.TASK_TYPE_EVAL_SZ.equals(taskType)
                || PhotoClaimUtil.TASK_TYPE_POP.equals(taskType) || PhotoClaimUtil.TASK_TYPE_INJURY.equals(taskType)){
            imageList = PhotoManager.getInstance().getImageListByReportNoAndSubTypeAndSignName(reportNo, imageSubType,signName);
        }else{
            imageList = PhotoManager.getInstance().getImageListByReportNoAndSubType(reportNo, imageSubType);
        }
        if (imageList != null && imageList.size() > 0) {
            if (imageList.get(imageList.size() - 1).getTaskType() != null && imageList.get(imageList.size() - 1).getTaskType().equals(filterItemPre.getFilter().getFilterName())) {
                ViewGroup.LayoutParams lp = filterItemPre.getImageViewFilterIcon().getLayoutParams();
                bm = ImageUtil.getBitmapThumbnail(imageList.get(imageList.size() - 1).getImagePath(), lp.width, lp.height);
                filterItemPre.getImageViewFilterIcon().setImageBitmap(bm);
                gridviewBitmapCaches.put("", bm);
            }
        } else {
            if("0".equals(selectedType)){
                filterItemPre.getImageViewFilterIcon().setBackgroundResource(R.mipmap.photo_camera_filter_none_normal);
            }else if("1".equals(selectedType)){
                filterItemPre.getImageViewFilterIcon().setBackgroundResource(R.mipmap.photo_camera_filter_none_down);
            }

        }
    }
    /**
     * 获取定位坐标
     */
    public void initLocation() {
    }

    //自动对焦
//    @OnClick(R2.id.mSurfaceView)
    protected void clickmSurfaceVie(){
        doAutoFocus();
    }
    //关闭按钮
//    @OnClick(R2.id.mBtnExit)
    protected void clickmBtnExit(){
        finish();
    }
    //闪关灯按钮
//    @OnClick(R2.id.mBtnFlash)
    protected void clickmBtnFlash(){
        flashMode = parameters.getFlashMode();
        if (flashMode != null && flashMode.equals(Camera.Parameters.FLASH_MODE_ON)) {//从ON改OFF
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameters);
            binding.mBtnFlash.setBackgroundResource(R.drawable.photo_camera_flash_off_selector);
            doAutoFocus();
        } else if (flashMode != null && flashMode.equals(Camera.Parameters.FLASH_MODE_OFF)) {//从OFF改FLASH_MODE_TORCH
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            binding.mBtnFlash.setBackgroundResource(R.drawable.photo_camera_flash_on_selector);
            doAutoFocus();
        } else {//从FLASH_MODE_TORCH改ON
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            mCamera.setParameters(parameters);
            binding.mBtnFlash.setBackgroundResource(R.drawable.photo_camera_flash_auto_selector);
            doAutoFocus();
        }
        // 刷新视图
        binding.mBtnFlash.invalidate();
    }
    // 图片类型选择按钮
//    @OnClick(R2.id.mBtnFilter)
    protected void clickmBtnFilter(){
        // 防暴力操作
        binding.mBtnFlash.setClickable(false);
        handler.postDelayed(new Runnable() {
            public void run() {
                binding.mBtnFlash.setClickable(true);
            }
        }, BTN_FILTER_VALID_GAP_TIME);
        if (binding.mBtnFlash.isSelected()) {
            mAnimatorCameraFilterTranslationDecrease.setTarget(binding.mHswFilter);
            mAnimatorCameraFilterTranslationDecrease.end();
            mAnimatorCameraFilterTranslationDecrease.start();
        } else {
            mAnimatorCameraFilterTranslationIncrease.setTarget(binding.mHswFilter);
            mAnimatorCameraFilterTranslationIncrease.end();
            mAnimatorCameraFilterTranslationIncrease.start();
        }
    }
    // 拍照
//    @OnClick(R2.id.mBtnCamera)
    protected void clickmBtnCamera(){
        takePic();
    }



    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        Log.e("---------------1", degrees + "");
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { //
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
        // camera.stopPreview();
        // camera.setDisplayOrientation(result);
        // camera.startPreview();
        // return degrees;
    }

    /**
     * 保存照片
     */
    private void savePhotoPic(){
        // 保存到image表中
        PhotoInfo imagePo = new PhotoInfo();
        imagePo.setReportCode(reportNo);
        imagePo.setImageName(picName);
        imagePo.setImagePath(imagePath);
        imagePo.setImageType(imageType);
        imagePo.setImageSubtype(imageSubType);
        imagePo.setImageLatitude(imageLatitude);
        imagePo.setImageLongitude(imageLongitude);
        imagePo.setImageAddress(imageAddress);
        imagePo.setImageTime(imageTime);
        imagePo.setSignId(signId);
        imagePo.setSignName(signName);
        imagePo.setImageUpload("0");//表示没上传
        imagePo.setSeedKey(seedKey);
        imagePo.setTaskType(taskType);
        PhotoManager.getInstance().savePhotoInfoData(imagePo);//插入数据库
        if("1370611".equals(imageSubType)||"1370621".equals(imageSubType)||"1370641".equals(imageSubType)){//查勘、本车、物损

        }else{
            ViewGroup.LayoutParams lp = filterItemCur.getImageViewFilterIcon().getLayoutParams();
            bm = ImageUtil.getBitmapThumbnail(imagePath, lp.width, lp.height);
            filterItemCur.getImageViewFilterIcon().setImageBitmap(bm);
        }
    }

    // 照片回调 只需实现这个回调函数来就行解码、保存即可，前2个参数可以直接设为null，不过系统一般会自动帮你把这些都写进来的
    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {

        @Override
        public synchronized void onPictureTaken(byte[] data, Camera camera) {
            //标的　三者　人伤　物损
            if(PhotoClaimUtil.TASK_TYPE_EVAL_BD.equals(taskType) || PhotoClaimUtil.TASK_TYPE_EVAL_SZ.equals(taskType)
                    || PhotoClaimUtil.TASK_TYPE_POP.equals(taskType) || PhotoClaimUtil.TASK_TYPE_INJURY.equals(taskType)){
                imageList = PhotoManager.getInstance().getImageListByReportNoAndSubTypeAndSignName(reportNo, imageSubType,signName);
            }else{
                imageList = PhotoManager.getInstance().getImageListByReportNoAndSubType(reportNo, imageSubType);
            }

            if(NetUtil.getAPNType(context)==-1){
                imageTime = "手机时间："+ TimestampTool.getCurrentDateTime();
            }else{
                imageTime = "网络时间："+TimestampTool.getWebsiteDatetime();
            }

            String picNameTime = String.valueOf(Calendar.getInstance().getTimeInMillis());
            if(imageSubType!=null&&!"".equals(imageSubType)){
                picName = picNameTime.concat("_").concat(imageSubType).concat(".jpg");
            }else{
                picName = picNameTime.concat(".jpg");
            }
            imagePath = PATH.concat(reportNo).concat("/").concat(imageType).concat("/").concat(picName);

            Bitmap waterBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.photo_company_logo);
            ImageUtil.compressBitmap(waterBitmap,data, imageAddress, imagePath, imageSubType, imageTime, reportNo, turnFlag);
//            Filter filter = Filter.mapFilterNameToFilter(imageSubType);
//            titleText = filter.getFilterTitleResId();
//            titleText = tvCameraImageTypeCenter.getText().toString();
            bmDialog = BitmapFactory.decodeFile(imagePath);
            bdDialog = new BitmapDrawable(bmDialog);
            if("1370120".equals(imageSubType)||"1370122".equals(imageSubType)||"1370121".equals(imageSubType)||"1370123".equals(imageSubType)||"1370804".equals(imageSubType)||"1370801".equals(imageSubType)||"1370803".equals(imageSubType)){
                contentText="是否保存当前照片？";
                isDelete = false;
                afterText = "已保存成功！";
                isDialog = false;
            }else if (PhotoClaimUtil.PHOTO_TYPE_GENERAL_DOCUMENT_CODE.equals(imageType)||"13702".equals(imageType)||"13703".equals(imageType)||"13704".equals(imageType)||"13705".equals(imageType)||"13707".equals(imageType)||"13708".equals(imageType)||"1370121".equals(imageSubType)||"1370123".equals(imageSubType)){
                if (imageList!=null&&imageList.size()>0){
                    contentText="是否用当前照片覆盖前一张照片？";
                    isDelete = true;
                    afterText = "您上一张照片已删除，并保存当前照片！";
                }else{
                    contentText="是否保存当前照片？";
                    isDelete = false;
                    afterText = "已保存成功！";
                    isDialog = false;
                }
            }else{
                contentText="是否保存当前照片？";
                isDelete = false;
                afterText = "已保存成功！";
                isDialog = false;
            }
            gridviewBitmapCaches.put("",bmDialog);
            if(false) {//全部可多拍
            }else{
                savePhotoPic();
            }

            as=new AnimationSet(true);
            Animation scaleAnimation=new ScaleAnimation(1.4f, 1.0f,1.4f, 1.0f,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(CameraConstant.FOCUS_MODE_CONTINUOUS_TIME);//设置动画持续时间为1秒
            scaleAnimation.setFillAfter(true);

            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {

                public void onAnimationStart(Animation animation) {
                    // TODO Auto-generated method stub
//                autoFocusHandler.removeCallbacks(doAutoFocus);
                    if(mCamera != null)
                        mCamera.cancelAutoFocus();
                }
                public void onAnimationEnd(Animation animation) {
//                    focuseView.setVisibility(View.GONE);
                }
                public void onAnimationRepeat(Animation animation) {
                    // TODO Auto-generated method stub
                }
            });
            as.addAnimation(scaleAnimation);

//            focuseView.startAnimation(as);
            camera.startPreview();
            initCamera();
            // 拍照完成，回复拍照功能可用
            PhotoApp.instance().isTakingPicture = false;
        }
    };

    // 拍照方法
    private void takePic() {
        if (mCamera != null&&!PhotoApp.instance().isTakingPicture) {
            PhotoApp.instance().isTakingPicture = true;//防止暴力操作，快速点击拍照
            mCamera.takePicture(new Camera.ShutterCallback() {
                @Override
                public void onShutter() {
                }
            }, null, pictureCallback);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_CAMERA
                || keyCode == KeyEvent.KEYCODE_SEARCH) {
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) { //
            this.setResult(Activity.RESULT_OK);
//			if(bm != null && !bm.isRecycled()) {
//				bm.recycle();
//			}
            finish();
        }
        return true;
    }

    /**
     * 检查一下手机设备各项硬件的开启状态 判断手机SD卡是否存在
     */
    public void checkStage() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            if ( !TextUtils.isEmpty(reportNo)){
                File dir = new File(PATH  + reportNo+"/" + imageType);
                if (!dir.isDirectory()) {
                    dir.mkdirs();
                }
            }
        } else {
            new AlertDialog.Builder(this)
                    .setMessage("检查到没有存储卡,请插入手机存储卡再开启本应用")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
//									}
                                    finish();
                                }
                            }).show();
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0: {
                ProgressDialog dialog = new ProgressDialog(this);
                dialog.setMessage("处理中，请稍后...");
                dialog.setIndeterminate(true);
                dialog.setCancelable(true);
                return dialog;
            }
        }
        return null;
    }

    private void eshowDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("相机故障").setMessage(msg).setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
        final double ASPECT_TOLERANCE = 0.05;
        double targetRatio = (double) w / h;
        if (sizes == null)
            return null;

        Size optimalSize = null;
        double minDiff = Double.MAX_VALUE;

        int targetHeight = h;

        // Try to find an size match aspect ratio and size
        for (Size size : sizes) {
            double ratio = (double) size.width / size.height;
            if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
                continue;
            if (Math.abs(size.height - targetHeight) < minDiff) {
                optimalSize = size;
                minDiff = Math.abs(size.height - targetHeight);
            }
        }

        // Cannot find the one match the aspect ratio, ignore the
        // requirement
        if (optimalSize == null) {
            minDiff = Double.MAX_VALUE;
            for (Size size : sizes) {
                if (Math.abs(size.height - targetHeight) < minDiff) {
                    optimalSize = size;
                    minDiff = Math.abs(size.height - targetHeight);
                }
            }
        }

        return optimalSize;
    }

    private Size getOptimalPictureSize(List<Size> sizes, double targetRatio) {
        final double ASPECT_TOLERANCE = 0.05;

        if (sizes == null)
            return null;

        Size optimalSize = null;
        int optimalSideLen = 0;
        double optimalDiffRatio = Double.MAX_VALUE;

        for (Size size : sizes) {

            int sideLen = Math.max(size.width, size.height);
            //LogEx.i("size.width: " + size.width + ", size.height: " + size.height);
            boolean select = false;
            if (sideLen < PhotoClaimUtil.PHOTO_MAX_SAVE_SIDELEN) {
                if (0 == optimalSideLen || sideLen > optimalSideLen) {
                    select = true;
                }
            } else {
                if ( PhotoClaimUtil.PHOTO_MAX_SAVE_SIDELEN > optimalSideLen) {
                    select = true;
                } else {
                    double diffRatio = Math.abs((double) size.width / size.height - targetRatio);
                    if (diffRatio + ASPECT_TOLERANCE < optimalDiffRatio) {
                        select = true;
                    } else if (diffRatio < optimalDiffRatio + ASPECT_TOLERANCE && sideLen < optimalSideLen) {
                        select = true;
                    }
                }
            }

            if (select) {
                optimalSize = size;
                optimalSideLen = sideLen;
                optimalDiffRatio = Math.abs((double) size.width / size.height - targetRatio);
            }
        }

        return optimalSize;
    }

    //相机参数的初始化设置
    private void initCamera()
    {
        parameters=mCamera.getParameters();
        parameters.setJpegQuality(70);
        parameters.setPictureFormat(PixelFormat.JPEG);

        //parameters.setPictureSize(surfaceView.getWidth(), surfaceView.getHeight());  // 部分定制手机，无法正常识别该方法。
//        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        List<Size> list = parameters.getSupportedPictureSizes();
        Size temp; // 设置相机参数
        int size = list.size(); // 数组大小
        for (int i = 0; i < size - 1; i++) {//排序
            for (int j = i + 1; j < size; j++) {
                if (list.get(i).height > list.get(j).height) { // 交换两数的位置
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
        int width = 0;
        int height = 0;

        if(height==0){
            for (int i = 0; i < size - 1; i++) {//
                if (list.get(i).height ==720) { //720
                    width = list.get(i).width;
                    height = list.get(i).height;
                    break;
                }
            }
        }

        if(height==0){
            for (int i = 0; i < size - 1; i++) {//
                if (list.get(i).height ==480) { //480
                    width = list.get(i).width;
                    height = list.get(i).height;
                    break;
                }
            }
        }

//        if(height==0) {
//            for (int i = 0; i < size - 1; i++) {//
//                if (list.get(i).height == 1080) { //1080
//                    width = list.get(i).width;
//                    height = list.get(i).height;
//                    break;
//                }
//            }
//        }
        if(height==0){
            width = 480;
            height = 640;
//            if(size>1){
//                width = list.get(1).width;
//                height = list.get(1).height;
//            }else{
//                width = list.get(size-1).width;
//                height = list.get(size-1).height;
//            }
        }
        parameters.setPictureSize(width, height);

        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);//1连续对焦
        setDispaly(parameters,mCamera);

        //初始化拖拽条，
        maxZoom = parameters.getMaxZoom();
//        seekBar.setMax(maxZoom*100);
//        seekBar.setProgress(0);


        mCamera.setDisplayOrientation(90);// 设置PreviewDisplay的方向，效果就是将捕获的画面旋转多少度显示
        mCamera.setParameters(parameters);
        mCamera.startPreview();
        //mCamera.cancelAutoFocus();// 2如果要实现连续的自动对焦，这一句必须加上
    }

    private void registerOrientationSensor(){

        if(mOrientationEventListener == null){
            mOrientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {

                @Override
                public void onOrientationChanged(int orientation) {

                    Oriention lastOriention = mOriention;

                    if (orientation >= 315 || orientation < 45) {
                        mOriention = Oriention.ORIENTION_0;
//						turnFlag = "1";// 竖屏
                        // 竖屏
                    }
                    else if(orientation >= 45 && orientation < 135){
                        mOriention = Oriention.ORIENTION_90;
                        // 横屏
//						turnFlag = "0";// 横屏

                    }
                    else if (orientation >= 135 && orientation < 225) {
                        mOriention = Oriention.ORIENTION_180;
//						turnFlag = "1";// 竖屏
                        // 竖屏
                    }
                    else if (orientation >= 225 && orientation < 315) {
                        mOriention = Oriention.ORIENTION_270;
                        // 横屏
//						turnFlag = "0";// 横屏

                    }
                    turnFlag =mOriention.getIntValue()-270;
//					setCameraDisplayOrientation(CameraActivity.this, 0, camera);
                    if(lastOriention != mOriention){

                        int fromDegrees = orientationToDegrees(lastOriention);
                        int toDegrees = orientationToDegrees(mOriention);


                        // 解决屏幕从 0 - 90度之间的来回转动，导致动画需270度转动问题，现优化为逆方向90度动画旋转
                        if(0 == fromDegrees && 270 == toDegrees){
                            fromDegrees = 0;
                            toDegrees = -90;
                        }else if(270 == fromDegrees && 0 == toDegrees){
                            fromDegrees = -90;
                            toDegrees = 0;
                        }

                        Log.i("CameraActivity========>","屏幕发生旋转变化:fromDegrees,toDegrees = " + fromDegrees + "," + toDegrees);

//						if(!mCameraEngine.isRecording()){
//                        ObjectAnimator.ofFloat(mBtnExit, "rotation", fromDegrees, toDegrees).setDuration(500).start();
//                        ObjectAnimator.ofFloat(mBtnSetting, "rotation", fromDegrees, toDegrees).setDuration(500).start();
//                        ObjectAnimator.ofFloat(mBtnFlash, "rotation", fromDegrees, toDegrees).setDuration(500).start();
//                        ObjectAnimator.ofFloat(mBtnFilter, "rotation", fromDegrees, toDegrees).setDuration(500).start();
//
//                        ObjectAnimator.ofFloat(tvCameraImageTypeCenter, "rotation", fromDegrees, toDegrees).setDuration(500).start();
//
//                        List<View> filterViews = mHswFilter.getHswViews();
//                        if(filterViews != null){
//                            for(View filterView : filterViews){
//                                ObjectAnimator.ofFloat(filterView, "rotation", fromDegrees, toDegrees).setDuration(500).start();
////								startRotateAnim(filterView, fromDegrees, toDegrees, ANIM_ROTATE_DURING);
//                            }
//                        }
                    }
                }
            };
            mOrientationEventListener.enable();
        }
    }

    private void unregisterOrientationSensor(){
        if(mOrientationEventListener != null){
            mOrientationEventListener.disable();
            mOrientationEventListener = null;
        }
    }

    private int orientationToDegrees(Oriention orientation){

        int degrees = 0;
        if(orientation == null)
            return degrees;
        switch (orientation.getIntValue()) {
            case 0:
                degrees = 0;
                break;
            case 270:
                degrees = 90;
                break;
            case 180:
                degrees = 180;
                break;
            case 90:
                degrees = 270;
                break;
        }
        return degrees;
    }

    public boolean isFlashOpened(){

        boolean isFlashOpened = false;

        if(mCamera != null){
            Camera.Parameters parameters = mCamera.getParameters();
            String flashMode = parameters.getFlashMode();
            if(flashMode != null && flashMode.equals(Camera.Parameters.FLASH_MODE_ON)){
                isFlashOpened = true;
            }
        }

        return isFlashOpened;
    }


    //控制图像的正确显示方向
    private void setDispaly(Camera.Parameters parameters,Camera camera)
    {
        if (Integer.parseInt(Build.VERSION.SDK) >= 8){
            setDisplayOrientation(camera,90);
        }
        else{
            parameters.setRotation(90);
        }

    }

    //实现的图像的正确显示
    private void setDisplayOrientation(Camera camera, int i) {
        Method downPolymorphic;
        try{
            downPolymorphic=camera.getClass().getMethod("setDisplayOrientation", new Class[]{int.class});
            if(downPolymorphic!=null) {
                downPolymorphic.invoke(camera, new Object[]{i});
            }
        }
        catch(Exception e){
            Log.e("Came_e", "图像出错");
        }
    }
    public boolean controlCameraFlash(boolean open) {
        try {

            if(!CameraUtil.checkFlashLightHardware()) return false;

            // 前置摄像头设置闪关灯参数是失效的

            if(mCamera != null){

                Camera.Parameters parameters = mCamera.getParameters();
                boolean isOpenNow = false;
                isOpenNow = Camera.Parameters.FLASH_MODE_ON.equals(parameters.getFlashMode());

                if(open == isOpenNow){
                    return false;
                }

                if(open){
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
                    mCamera.setParameters(parameters);
                }else{
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(parameters);
                }

                return true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void initCameraFilterScaleAnimator(){

        ObjectAnimator animatorXIncrease = new ObjectAnimator();
        animatorXIncrease.setPropertyName("scaleX");
        animatorXIncrease.setFloatValues(1.0f, 1.2f);

        ObjectAnimator animatorYIncrease = new ObjectAnimator();
        animatorYIncrease.setPropertyName("scaleY");
        animatorYIncrease.setFloatValues(1.0f, 1.2f);

        mAnimatorCameraFilterScaleIncrease = new AnimatorSet();
        mAnimatorCameraFilterScaleIncrease.setDuration(ANIM_FILTER_ITEM_DURING);
        mAnimatorCameraFilterScaleIncrease.playTogether(animatorXIncrease, animatorYIncrease);
        mAnimatorCameraFilterScaleIncrease.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });

        ObjectAnimator animatorXDecrease = new ObjectAnimator();
        animatorXDecrease.setPropertyName("scaleX");
        animatorXDecrease.setFloatValues(1.2f, 1.0f);

        ObjectAnimator animatorYDecrease = new ObjectAnimator();
        animatorYDecrease.setPropertyName("scaleY");
        animatorYDecrease.setFloatValues(1.2f, 1.0f);

        mAnimatorCameraFilterScaleDecrease = new AnimatorSet();
        mAnimatorCameraFilterScaleDecrease.setDuration(ANIM_FILTER_ITEM_DURING);
        mAnimatorCameraFilterScaleDecrease.playTogether(animatorXDecrease, animatorYDecrease);
        mAnimatorCameraFilterScaleDecrease.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        });

    }

    private void initCameraFilterTranslationAnimator(){
        mAnimatorCameraFilterTranslationIncrease = new ObjectAnimator();
        mAnimatorCameraFilterTranslationIncrease.setDuration(ANIM_FILTER_BAR_DURING);
        mAnimatorCameraFilterTranslationIncrease.setPropertyName("x");
        mAnimatorCameraFilterTranslationIncrease.setFloatValues(-dm.widthPixels,0.0f);
        mAnimatorCameraFilterTranslationIncrease.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                mHswFilter.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
//                mHswFilter.clearAnimation();
//                mHswFilter.invalidate();
            }
            @Override
            public void onAnimationCancel(Animator animation) {
//                mHswFilter.clearAnimation();
//                mHswFilter.invalidate();
            }
        });
        mAnimatorCameraFilterTranslationDecrease = new ObjectAnimator();
        mAnimatorCameraFilterTranslationDecrease.setDuration(ANIM_FILTER_BAR_DURING);
        mAnimatorCameraFilterTranslationDecrease.setPropertyName("x");
        mAnimatorCameraFilterTranslationDecrease.setFloatValues(0.0f, -dm.widthPixels);
        mAnimatorCameraFilterTranslationDecrease.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                mHswFilter.setVisibility(View.VISIBLE);
//                mHswFilter.invalidate();
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
//                mHswFilter.clearAnimation();
//                mHswFilter.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
//                mHswFilter.clearAnimation();
//                mHswFilter.setVisibility(View.GONE);
            }
        });
    }


    private void biankuangGone(){
//        photoTopleft.setVisibility(View.GONE);
//        photoTopright.setVisibility(View.GONE);
//        photoBottomLeft.setVisibility(View.GONE);
//        photoBottomright.setVisibility(View.GONE);
    }
    private void biankuangView(){
//        photoTopleft.setVisibility(View.VISIBLE);
//        photoTopright.setVisibility(View.VISIBLE);
//        photoBottomLeft.setVisibility(View.VISIBLE);
//        photoBottomright.setVisibility(View.VISIBLE);
    }
    // handle button auto focus
    private void doAutoFocus() {
        parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(parameters);
        mCamera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                if (success) {
                    camera.cancelAutoFocus();// 只有加上了这一句，才会自动对焦。
                    if (!Build.MODEL.equals("KORIDY H30")) {
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 1连续对焦
                        camera.setParameters(parameters);
                    }else{
                        parameters = camera.getParameters();
                        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                        camera.setParameters(parameters);
                    }
//                    focuseView.startAnimation(as);
                }
            }
        });
    }
}
