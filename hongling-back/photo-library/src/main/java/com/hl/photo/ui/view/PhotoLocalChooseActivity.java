package com.hl.photo.ui.view;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.hl.core.lib.activity.TitleActivity;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.photo.R;
import com.hl.photo.business.entity.ImageFloder;
import com.hl.photo.databinding.PhotoLocalFragmentLayoutBinding;
import com.hl.photo.support.util.Base64Util;
import com.hl.photo.support.util.Constants;
import com.hl.photo.support.util.ImageUtil;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.table.manager.PhotoManager;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.widget.HolderViewAdapter;
import com.hl.photo.ui.widget.ListImageDirPopupWindow;
import com.hl.photo.ui.widget.PhotoLocalChooseHView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Describe:
 * @Package: com.hl.photo.view
 * @Author: liyu
 * @Date: 2018/4/18/ 11:42
 * @Copyright: hl
 */


public class PhotoLocalChooseActivity extends TitleActivity<TitleBar> implements ListImageDirPopupWindow.OnImageDirSelected {

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();
    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private static File mImgDir;
    /**
     * 所有的图片
     */
    private List<ImageFloder> mImgs;
    /**
     * 所有的图片
     */
    private List<ImageFloder> mSelectedImages = new ArrayList<ImageFloder>();
    /**
     * 选中的图片
     */
    private List<PhotoInfo> selectedImages = new ArrayList<PhotoInfo>();

    private String reportNo;
    private String imageType;
    private String imageSubType;
//    private String imageName;
//    private String signId;
//    private String signName;
//    private String seedKey;
//    private String taskType;
    private PhotoInfo imagePo;
    private Context mContext;

    private static ProgressDialog mProgressDialog;
    private ListImageDirPopupWindow mListImageDirPopupWindow;
    private int mScreenHeight;
    private String PATH = "";
    int totalCount = 0;
    private static String imgDir;
    private static String imgFildDir;
    private static String imgName;
    private static ProgressDialog progressDialog;
    private static int count = 0;
    private static ImageFloder floder = new ImageFloder();


    private Message msgLoadFromCursor;
    private Message msgLoadPicture;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        if (msg == msgLoadFromCursor){
            // 初始化展示文件夹的popupWindw
            initListDirPopupWindw();
            // 为View绑定数据
            bindView();
        }else if (msg == msgLoadPicture){
            dismissDialog();
            HolderViewAdapter adapter = new HolderViewAdapter(mContext, mImgs,PhotoLocalChooseHView.class);
            binding.photoLocalGridView.setAdapter(adapter);
            binding.photoLocalTotalCount.setText(totalCount + "张");
        }

        }
    };

//    @Override
//    public void handleMessage(Message msg) {
//        super.handleMessage(msg);
//        if (msg == msgLoadFromCursor){
//            // 初始化展示文件夹的popupWindw
//            initListDirPopupWindw();
//            // 为View绑定数据
//            bindView();
//        }else if (msg == msgLoadPicture){
//            dismissDialog();
//            HolderViewAdapter adapter = new HolderViewAdapter(this, mImgs,PhotoLocalChooseHView.class);
//            binding.photoLocalGridView.setAdapter(adapter);
//            binding.photoLocalTotalCount.setText(totalCount + "张");
//        }
//    }

    private PhotoLocalFragmentLayoutBinding binding;


    @Override
    protected Object entryInterceptor(Intent intent) {

        reportNo = intent.getStringExtra(PhotoClaimUtil.REPORT_NO);
        imageType = intent.getStringExtra(PhotoClaimUtil.IMAGE_TYPE);
        imageSubType = intent.getStringExtra(PhotoClaimUtil.IMAGE_SUB_TYPE);
//        imageName = intent.getStringExtra(PhotoClaimUtil.IMAGE_NAME);
//        signId = intent.getStringExtra(PhotoClaimUtil.SIGN_ID);
//        signName = intent.getStringExtra(PhotoClaimUtil.SIGN_NAME);
//        seedKey = intent.getStringExtra(PhotoClaimUtil.SEED_KEY);
//        taskType = intent.getStringExtra(PhotoClaimUtil.TASK_TYPE);
        imagePo = new PhotoInfo();
        imagePo.setReportCode(reportNo);
//        imagePo.setImageName(imageName);
        imagePo.setImageType(imageType);
        imagePo.setImageSubtype(imageSubType);
//        imagePo.setSignId(signId);
//        imagePo.setSignName(signName);
//        imagePo.setSeedKey(seedKey);
//        imagePo.setTaskType(taskType);
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = getString(R.string.photo_local_title);
    }

    @Override
    protected Object initLayout() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.photo_local_fragment_layout, null, false);
        return binding.getRoot();
    }

    /**
     * 为View绑定数据
     */
    private void  bindView(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mImgDir == null)
                {
                    UtilManager.Toast.show(mContext,mContext.getResources().getString(R.string.photo_local_no_photo));
                    return;
                }
                ImageFloder f = null;
                mImgs = new ArrayList<ImageFloder>();
                for (int i = 0; i< Arrays.asList(mImgDir.list()).size(); i++){
                    f = new ImageFloder();
                    f.setDir(mImgDir.getAbsolutePath());
                    f.setFirstImagePath(Arrays.asList(mImgDir.list()).get(i));
                    f.setChecked(false);
                    mImgs.add(f);
                }
                msgLoadPicture = new Message();
                msgLoadPicture.what = 200;
                mHandler.sendMessage(msgLoadPicture);
            }
        }).start();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            UtilManager.Toast.show(this,getResources().getString(R.string.photo_local_no_sd));
        }
        binding.photoLocalChooseBottom.setOnClickListener(this::clickPhotoLocalButton);
        binding.buttonConfirm.setOnClickListener(this::saveLocalChoicePhoto);
        // 显示进度条
        showLoadingDialog();
        PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ getString(R.string.photo_dir)+ getString(R.string.photo_img_dir) + "/";
        LoadLocalPickTask loadLocalPickTask = new LoadLocalPickTask();
        new Thread(loadLocalPickTask).start();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 为底部的确定按钮设置点击事件，保存选中照片
     */
    public void saveLocalChoicePhoto(View view) {
        PhotoInfo photo = null;
        progressDialog = ProgressDialog.show(PhotoLocalChooseActivity.this, "请稍候。。。",
                "正在上传照片中。。");
        for (int i=0;i<mImgs.size();i++){
            floder = mImgs.get(i);
            if(floder.isChecked()){
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected void onPreExecute() {
                        // TODO Auto-generated method stub
                        super.onPreExecute();
                    }

                    @Override
                    protected String doInBackground(Void... params) {
                        // TODO Auto-generated method stub
                        InputStream stream = bitmapToIS(getSmallBitmap(floder.getDir()+ File.separator +floder.getFirstImagePath()));
                        String result = null;
                        try {
                            result = uploadDataPic64(stream, reportNo);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        return result;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        // TODO Auto-generated method stub
                        super.onPostExecute(result);
                        if (result != null && !result.equals("")) {
                            System.out.println("++++++++++++++++++++++" + result);
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                String responseCode = jsonObject.getString("code");
                                String responseMessage = jsonObject.getString("message");
                                if (responseCode.equals("1")) {
                                    //得到结果后回调
                                    PhotoInfo imagePo = new PhotoInfo();
                                    imagePo.setReportCode(reportNo);
                                    imagePo.setImageName(imgName);
                                    imagePo.setImagePath(imgFildDir);
                                    imagePo.setImageType("1");
                                    imagePo.setImageSubtype("2");
                                    imagePo.setImageUpload("1");//表示没上传
                                    PhotoManager.getInstance().savePhotoInfoData(imagePo);//插入数据库
                                    delFile(floder.getDir());
                                    count++;
                                    if(count == mImgs.size()){
                                        progressDialog.dismiss();
                                    }
                                    Toast.makeText(PhotoLocalChooseActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
                                } else
                                    Toast.makeText(PhotoLocalChooseActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        } else
                            Toast.makeText(PhotoLocalChooseActivity.this, "返回数据为空", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
//                if(checkIsSave(taskType,mImgs.get(i).getFirstImagePath())){
//                    count++;
//                }else{

//                imagePo = new PhotoInfo();
//                imagePo.setReportCode(reportNo);
//                imagePo.setImageType(imageType);
//                imagePo.setImageSubtype(imageSubType);
////                imagePo.setSignId(signId);
////                imagePo.setSignName(signName);
//
//                String imageTime = TimestampTool.getCurrentDateTime();
//                String fileName = System.currentTimeMillis() + ".jpg";
//                String picName = "";
////                if(imageSubType!=null&&!"".equals(imageSubType)){
////                    picName = picNameTime.concat("_").concat(imageSubType).concat(".jpg");
////                }else{
////                    picName = picNameTime.concat(".jpg");
////                }
//                imagePo.setImageTime(imageTime);
//                imagePo.setImageName(fileName);
//
//
////                File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hongling" + File.separator + "img");
////                if (!appDir.exists()) {
////                    appDir.mkdir();
////                }
////                File file = new File(appDir, fileName);
////
////
////                String outImgPath = PATH.concat(imagePo.getReportCode()).concat("/").concat(imagePo.getImageType()).concat("/").concat(imagePo.getImageName());
////
////                Bitmap waterBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.photo_company_logo);
////                //  ImageUtil.compressBitmap(waterBitmap,mImgs.get(i).getFirstImagePath(),outImgPath,"手机本地照片无地址",imagePo.getImageSubtype(),imagePo.getImageTime(),imagePo.getReportCode());
////                PhotoInfo imagePo = new PhotoInfo();
////                imagePo.setReportCode(flag);
////                imagePo.setImageName(imgName);
////                imagePo.setImagePath(imgFildDir);
////                imagePo.setImageType("1");
////                imagePo.setImageSubtype("2");
////                imagePo.setImageUpload("1");//表示没上传
////                PhotoManager.getInstance().savePhotoInfoData(imagePo);//插入数据库
//
//
//                ImageUtil.compressBitmap(waterBitmap,mImgs.get(i).getDir() + "/" + mImgs.get(i).getFirstImagePath(),outImgPath,"手机本地照片无地址",imagePo.getImageSubtype(),imagePo.getImageTime(),imagePo.getReportCode());
//                imagePo.setImageUpload("0");//表示没上传
//                imagePo.setImagePath(outImgPath);
////                imagePo.setTaskType(taskType);
////                imagePo.setSeedKey(seedKey);
//                PhotoManager.getInstance().savePhotoInfoData(imagePo);
//                selectedImages.add(photo);
//                }
            }
        }
//        if(count>0){
//            ToastUtil.showToast(getActivity(),"您重复添加了"+String.valueOf(count)+"张照片");
//        }
        onBackPressed();
//        PhotoLocalChooseFragment.this.replaceFragment(PhotoManageFragment.class,null);

    }

//    private boolean checkIsSave(String taskType,String imageName) {
//        PhotoInfo info ;
//        info = new PhotoInfo();
//        info.setId(123254l);
//        info.setReportCode("20170203");
//        info.setFlowId("123456");
//        info.setTaskType(taskType);
//        List<PhotoInfo>  photoListFromDB = PhotoManager.getInstance().getPhotoListByReportCodeAndTaskType(info);
//        for (PhotoInfo photoInfo:photoListFromDB) {
//            if(imageName!=null&&imageName.equals(photoInfo.getImageName())){
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 为底部的布局设置点击事件，弹出popupWindow
     */
    public void clickPhotoLocalButton(View view) {
        mListImageDirPopupWindow.setAnimationStyle(R.style.photo_anim_popup_dir);
        mListImageDirPopupWindow.showAsDropDown(binding.photoLocalChooseBottom, 0, 0);

        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = .3f;
        getWindow().setAttributes(lp);
    }




    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListDirPopupWindw(){
        mListImageDirPopupWindow = new ListImageDirPopupWindow(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
                mImageFloders, LayoutInflater.from(this)
                .inflate(R.layout.photo_local_list_dir, null));

        mListImageDirPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {

            @Override
            public void onDismiss()
            {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
        mListImageDirPopupWindow.setOnImageDirSelected(this);
    }



    @Override
    public void selected(ImageFloder floder) {
        mImgDir = new File(floder.getDir());
        for (int i=0;i<Arrays.asList(mImgDir.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String filename)
            {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        })).size();i++){
            ImageFloder f = new ImageFloder();
            f.setDir(mImgDir.getAbsolutePath());
            f.setFirstImagePath(Arrays.asList(mImgDir.list()).get(i));
            mImgs.add(f);
        }

        HolderViewAdapter adapter = new HolderViewAdapter(this, mImgs,PhotoLocalChooseHView.class);

        binding.photoLocalGridView.setAdapter(adapter);
        // mAdapter.notifyDataSetChanged();
        binding.photoLocalTotalCount.setText(floder.getCount() + "张");
        binding.photoLocalChooseDir.setText(floder.getName());
        mListImageDirPopupWindow.dismiss();
    }

    class LoadLocalPickTask implements Runnable{
        @Override
        public void run(){
            String firstImage = null;
            Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            ContentResolver mContentResolver = getContentResolver();
            // 只查询jpg和png的图片
            Cursor mCursor = mContentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
            while (mCursor.moveToNext()) {
                // 获取图片的路径
                String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));
                // 拿到第一张图片的路径
                if (firstImage == null)
                    firstImage = path;
                // 获取该图片的父路径名
                File parentFile = new File(path).getParentFile();
                if (parentFile == null)
                    continue;
                String dirPath = parentFile.getAbsolutePath();
                ImageFloder imageFloder = null;
                // 利用一个HashSet防止多次扫描同一个文件夹
                if (mDirPaths.contains(dirPath)) {
                    continue;
                } else {
                    mDirPaths.add(dirPath);
                    // 初始化imageFloder
                    imageFloder = new ImageFloder();
                    imageFloder.setDir(dirPath);
                    imageFloder.setFirstImagePath(path);
                    int lastIndexOf = path.lastIndexOf("/");
                    imageFloder.setName(path.substring(lastIndexOf, path.length() - 1));
                }
                String[] imgArray = parentFile.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String filename) {
                        if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jpeg"))
                            return true;
                        return false;
                    }
                });

//                    int picSize = parentFile.list(new FilenameFilter(){
//                        @Override
//                        public boolean accept(File dir, String filename){
//                            if (filename.endsWith(".jpg")|| filename.endsWith(".png")|| filename.endsWith(".jpeg"))
//                                return true;
//                            return false;
//                        }
//                    }).length;

                if (imgArray == null) {
                    continue;
                }

                int picSize = imgArray.length;
                totalCount += picSize;
                imageFloder.setCount(picSize);
                mImageFloders.add(imageFloder);
                if (picSize > mPicsSize) {
                    mPicsSize = picSize;
                    mImgDir = parentFile;
                }
            }
            mCursor.close();
            // 扫描完成，辅助的HashSet也就可以释放内存了
            mDirPaths = null;
            // 通知Handler扫描图片完成
            msgLoadFromCursor = new Message();
            msgLoadFromCursor.what = 100;
            mHandler.sendMessage(msgLoadFromCursor);
        }
    }
    /**
     * 发送图片 识别
     *
     * @param stream
     * @param
     * @return
     * @throws Exception
     */
    public static String uploadDataPic64(InputStream stream, String flag) throws Exception {


        String testUrl = Constants.UPLOADPICTURE;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        OutputStream outputStream = null;
        String resultJson = "";
        StringBuffer sb = new StringBuffer();

        imgDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hongling" + File.separator + "img";
        File dir = new File(imgDir);// 返回 /sdcard
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try{
            url = new URL(testUrl);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setConnectTimeout(20000);        //设置连接超时时间
            httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hongling" + File.separator + "img"+File.separator +flag);
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
            imgName = fileName;
            imgFildDir = file.getPath();

            try {
                FileOutputStream fos = new FileOutputStream(file);
                ImageUtil.InputStream2Bitmap(stream).compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] imgData = readFileByBytes(file.getPath());
            //数据准备
            String data = "contractNo="+flag+"&base64="+ URLEncoder.encode(Base64Util.encode(imgData), "utf-8");

            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length()));
            //获得输出流，向服务器写入数据
            outputStream = httpURLConnection.getOutputStream();
            DataOutputStream out = new DataOutputStream(outputStream);

            out.write(data.getBytes());
            out.flush();
            out.close();
            //获得响应状态
            int resultCode = httpURLConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int read = 0;
                while ((read = inputStream.read(b)) != -1) {
                    byteOut.write(b, 0, read);
                }
                resultJson = new String(byteOut.toByteArray(), "UTF-8");
                byteOut.close();
            }
        } catch (Exception e) {
            resultJson = "";
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    resultJson = "";
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return resultJson;

    }
    //压缩图片
    private static Bitmap getSmallBitmap(String filePath) {
        String rootStart = "/root";
        if (filePath.startsWith(rootStart)) { // 7.0 手机获取路径时候会, 在地址前加 /root
            filePath = filePath.replace(rootStart,"");
        }
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int sampliSize = computeSampleSize(options, -1, 1280 * 960);
        options.inSampleSize = sampliSize;
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    private static InputStream bitmapToIS(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
        return sbs;
    }
    /**
     * 根据文件路径读取byte[] 数组
     */
    public static byte[] readFileByBytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
            BufferedInputStream in = null;

            try {
                in = new BufferedInputStream(new FileInputStream(file));
                short bufSize = 1024;
                byte[] buffer = new byte[bufSize];
                int len1;
                while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                    bos.write(buffer, 0, len1);
                }

                byte[] var7 = bos.toByteArray();
                return var7;
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var14) {
                    var14.printStackTrace();
                }

                bos.close();
            }
        }
    }
    //计算压缩的尺寸
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }
    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == UNCONSTRAINED) ? 1 : (int) Math
                .ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == UNCONSTRAINED) ? 128 : (int) Math
                .min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == UNCONSTRAINED)
                && (minSideLength == UNCONSTRAINED)) {
            return 1;
        } else if (minSideLength == UNCONSTRAINED) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
    private static final int UNCONSTRAINED = -1;
    /**
     * 删除文件
     * @param filePathAndName
     * @return
     */
    public static boolean delFile(String filePathAndName) {
        boolean bea = false;
        try {
            String filePath = filePathAndName;
            File myDelFile = new File(filePath);
            if (myDelFile.exists()) {
                myDelFile.delete();
                bea = true;
            } else {
                bea = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bea;
    }
}
