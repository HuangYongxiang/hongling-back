package com.hl.photo.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.hl.core.lib.activity.TitleActivity;
import com.hl.core.lib.bean.Response;
import com.hl.core.lib.network.ApiManager;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.util.common.LogUtil;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.core.lib.util.common.dialog.DialogUtil;
import com.hl.photo.R;
import com.hl.photo.business.photo.PhotoUtil;
import com.hl.photo.databinding.PhotoActivityImageBrowseBinding;
import com.hl.photo.support.util.Base64Util;
import com.hl.photo.support.util.Constants;
import com.hl.photo.support.util.CoreFileProvide;
import com.hl.photo.support.util.FileUtil;
import com.hl.photo.support.util.ImageUtil;
import com.hl.photo.support.util.OCRUtil;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.table.manager.PhotoManager;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.adapter.ViewPageAdapter;

import org.json.JSONArray;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * @Describe:
 * @Package: com.hl.photo.ui.view
 * @Author: liyu
 * @Date: 2018/4/19/ 13:34
 * @Copyright: hl
 */


public class ImageBrowseActivity extends TitleActivity<TitleBar> implements PhotoUtil.TakePicCallBack, ViewPager.OnPageChangeListener, OCRUtil.OcrCallback {

    private PhotoActivityImageBrowseBinding binding;
    //        private List<PhotoInfo> imageList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();
    private PhotoInfo imagePo;
    private String reportNo;
    private String imageType;
    private String imageSubtype;
//    private String imageName;
//    private String signId;
//    private String signName;
//    private String imagePath;
//    private String taskType;

    private ViewPageAdapter adapter;
    private Context mContext;
    private int indexImg;
    private PopupWindow popupWindow;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    private static String imgDir;
    private static String imgFildDir;
    private static String imgName;
    private static ProgressDialog progressDialog;
    private static String imgFile;

    @Override
    protected Object entryInterceptor(Intent intent) {
        // 对数据进行初始化
        reportNo = intent.getStringExtra(PhotoClaimUtil.REPORT_NO);
        imageType = intent.getStringExtra(PhotoClaimUtil.IMAGE_TYPE);
        imageSubtype = intent.getStringExtra(PhotoClaimUtil.IMAGE_SUB_TYPE);
//        imageName = intent.getStringExtra(PhotoClaimUtil.IMAGE_NAME);
//        signId = intent.getStringExtra(PhotoClaimUtil.SIGN_ID);
//        signName = intent.getStringExtra(PhotoClaimUtil.SIGN_NAME);
//        taskType = intent.getStringExtra(PhotoClaimUtil.TASK_TYPE);
        imagePo = new PhotoInfo();
//        imagePo.setReportCode(reportNo);
//        imagePo.setImageName(imageName);
        imagePo.setImageType(imageType);
        imagePo.setImageSubtype(imageSubtype);
//        imagePo.setSignId(signId);
//        imagePo.setSignName(signName);
//        imagePo.setSignName(imagePath);

        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = "拍照上传";
        titleBar.showRightBtn = true;
        titleBar.rightBtnText = "完成";
    }

    @Override
    protected Object initLayout() {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.photo_activity_image_browse, null, false);
        return binding.getRoot();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mContext = this;
        Log.e("image","onresume");
        getData();
//        getNewData();
        setImageBrowse(0);
        setClickEvent();
    }

    private void setClickEvent() {
//        binding.bottomLayout.photoDeleteTv.setOnClickListener(v->deletePhoto());
//        binding.bottomLayout.photoRemarkTv.setOnClickListener(v->remarkPhoto());
        binding.photoRetakeTv.setOnClickListener(v -> retakePhoto());

        binding.getRoot().getRootView().findViewById(R.id.title_right_btn).setOnClickListener(v -> finish());
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                getNewData();
            }
        }, 2000);
    }

    public void getNewData() {
        imageList.clear();


        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();
                progressDialog = ProgressDialog.show(ImageBrowseActivity.this, "请稍候。。。",
                        "正在下载照片中。。");
            }

            @Override
            protected String doInBackground(Void... params) {
                // TODO Auto-generated method stub
                String result = null;
                String testUrl = Constants.PICTURES;
                URL url = null;
                HttpURLConnection httpURLConnection = null;
                OutputStream outputStream = null;
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("contractNo", reportNo);
                    String data = jsonObject.toString();
                    url = new URL(testUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(20000);        //设置连接超时时间
                    httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
                    httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
                    httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
                    httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
                    //设置请求体的类型是文本类型
                    httpURLConnection.setRequestProperty("Content-Type", "application/json");

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
                    Log.e("imagebrowse", "resultcode:" + resultCode);
                    if (HttpURLConnection.HTTP_OK == resultCode) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                        byte[] b = new byte[1024];
                        int read = 0;
                        while ((read = inputStream.read(b)) != -1) {
                            byteOut.write(b, 0, read);
                        }
                        result = new String(byteOut.toByteArray(), "UTF-8");
                        Log.e("imagebrowse", "resul:" + result);
                        byteOut.close();
                    }
                } catch (Exception e) {
                    result = "";
                    e.printStackTrace();
                } finally {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e) {
                            result = "";
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                progressDialog.dismiss();
                if (result != null && !result.equals("")) {
                    System.out.println("++++++++++++++++++++++ download" + result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String responseCode = jsonObject.getString("code");
                        String responseMessage = jsonObject.getString("message");
                        if (responseCode.equals("1")) {
                            String data = jsonObject.getString("data");
                            if (!TextUtils.isEmpty(data)) {
                                JSONArray dataAry = new JSONArray(data);
                                for (int i = 0; i < dataAry.length(); i++) {
                                    JSONObject obj = dataAry.getJSONObject(i);
                                    String id = obj.getString("id");
                                    imageList.add(id);
                                }
                            }
                          //  Toast.makeText(ImageBrowseActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
                            setImageBrowse(0);
                        } else
                            Toast.makeText(ImageBrowseActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(ImageBrowseActivity.this, "返回数据为空", Toast.LENGTH_SHORT).show();
            }
        }.execute();


    }

    //显示图片是否上传
    private void setImgUploadStatus(int position) {
        if (imageList != null && imageList.size() > 0) {
//            PhotoInfo photoInfo = imageList.get(position);
//            String imageUpload = photoInfo.getImageUpload();
//            if("1".equals(imageUpload)){
//                titleBar.showRightBtn = true;
//                titleBar.rightBtnText = "拍照上传";
//                titleBar.updateTitle();
//            }else{
//                titleBar.showRightBtn = false;
//                titleBar.updateTitle();
//            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.title_right_btn) {
            List<PhotoInfo> photoList = PhotoManager.getInstance().getPhotoListByReportCode(reportNo);
            if (photoList != null && photoList.size() > 0) {
                Bundle bundle = new Bundle();
                bundle.putString("reportCode", reportNo);
                startActivity(PhotoManagerEditActivity.class, bundle);
            } else
                UtilManager.Toast.show(mContext, getString(R.string.photo_need_add_photo));
        }
    }

    /**
     * 删除图片
     */
//    @OnClick(R2.id.photo_delete_tv)
    public void deletePhoto() {
//        if (indexImg >= 0) {
//            if (imageList != null && imageList.size() > 0) {
//                PhotoInfo photoInfo = imageList.get(indexImg);
//                String imagePath = photoInfo.getImagePath();
//                FileUtil.delPicFile(imagePath);
//                PhotoManager.getInstance().deletePhotoInfo(photoInfo);
//                imageList.remove(photoInfo);
//                adapter.notifyDataSetChanged();
//                if (indexImg > 0 && indexImg != imageList.size() - 1) {
//                    indexImg--;
//                }
//                if (indexImg == 0) {
//                    imagePo = photoInfo;
//                }
//                showRemarkAndNum(indexImg);
//            } else {
//                UtilManager.Toast.show(mContext, "暂无图片");
//            }
//        }

    }

    /**
     * 备注图片
     */
//    @OnClick(R2.id.photo_remark_tv)
    public void remarkPhoto() {
        showEditRemarkDialog();
    }

    /**
     * 补拍图片
     */
//    @OnClick(R2.id.photo_retake_tv)
    public void retakePhoto() {
        if (imageList != null && imageList.size() > 0 && indexImg >= 0) {
            showPopWindow(null);
        } else {
            showPopWindow(imagePo);
        }
    }


    public void setImageBrowse(int position) {
//        setTitle(imageName);//显示标题
        if (adapter == null && imageList.size() > 0) {
            adapter = new ViewPageAdapter(mContext, imageList);
            binding.viewPager.setAdapter(adapter);
            binding.viewPager.setCurrentItem(position);
            binding.viewPager.addOnPageChangeListener(this);
        } else if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        showRemarkAndNum(position);
//        setImgUploadStatus(position);
    }

    private void showEditRemarkDialog() {
        View inflateView = LayoutInflater.from(mContext).inflate(R.layout.photo_edit_remark_layout, null);
        final EditText editText = (EditText) inflateView.findViewById(R.id.photo_remark_et);
        editText.setSelection(editText.getText().length());
        DialogUtil.dialogSureEdit(mContext, inflateView, "请输入备注", new DialogUtil.DialogOnClickListener() {
            @Override
            public void onClickListener(Dialog dialog) {
                //收回软键盘
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                dialog.dismiss();

                String s = editText.getText().toString().trim();
                String result = s.toString();
                if (!TextUtils.isEmpty(result)) {
//                    PhotoInfo photoInfo = imageList.get(indexImg);
//                    photoInfo.setImageDescribe(result);
//                    PhotoManager.getInstance().updatePhotoInfo(photoInfo);
                    binding.photoRemarkText.setVisibility(View.VISIBLE);
                    binding.photoRemarkText.setText(result);
                }
            }
        });
    }

    /**
     * 设置备注 和 索引
     *
     * @param position
     */
    private void showRemarkAndNum(int position) {
        if (imageList != null && imageList.size() > 0) {
            binding.photoNumberHintText.setText(position + 1 + "/" + imageList.size());
//            PhotoInfo photoInfo = imageList.get(position);
//            String imageDescribe = photoInfo.getImageDescribe();
//            if (!TextUtils.isEmpty(imageDescribe)) {
//                binding.photoRemarkText.setVisibility(View.VISIBLE);
//                binding.photoRemarkText.setText("备注: " + imageDescribe);
//            } else {
//                binding.photoRemarkText.setVisibility(View.GONE);
//            }
        } else {
            binding.photoRemarkText.setVisibility(View.GONE);
            binding.photoNumberHintText.setText("0/0");
        }
    }

    private void cancle() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    private void showPopWindow(final PhotoInfo photo) {
        View popwindowLayout = LayoutInflater.from(mContext).inflate(R.layout.photo_popwindow_layout, null, false);
        PhotoUtil.bindPopWindow(this, popwindowLayout, photo, reportNo, imageType);
        popupWindow = PopupWindowUtil.getInitince(getWindow()).initPopuptWindowNoCancelBtn(bindView.getRootView(), popwindowLayout);
    }

    @Override
    public void takePicFromCamera(Bundle bundle) {
        try {
            checkStage(OCRUtil.UP_PICTURE);
            // 三星部分手机拍照和保存是在两个Activity完成的，返回时其实会调用两次onCreate方法
            // 故将图片名称imgFile保存在全局变量中，防止其发生变化
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri mImageCaptureUri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
                Uri uriForFile = CoreFileProvide.getUriForFile(ImageBrowseActivity.this, new File(imgFile));
                mImageCaptureUri = uriForFile;
                imgFile = mImageCaptureUri.getPath();
            } else {
                mImageCaptureUri = Uri.fromFile(new File(imgFile));
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            startActivityForResult(intent, OCRUtil.UP_PICTURE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
//        OCRUtil.takePhoto(ImageBrowseActivity.this, OCRUtil.UP_PICTURE);
//        Intent intent = new Intent(mContext, CameraActivity.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
        cancle();
    }

    @Override
    public void choicePicFromPhoto(Bundle bundle) {

        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, IMAGE);

//        startActivity(PhotoLocalChooseActivity.class,bundle);
        cancle();
    }

    @Override
    public void canclePopupWindow() {
        cancle();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        indexImg = position;
        showRemarkAndNum(position);
//        setImgUploadStatus(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case OCRUtil.UP_PICTURE:
                    uploadPic(mContext, this, reportNo);
                    break;

            }
        }
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            c.close();
            new AsyncTask<Void, Void, String>() {

                @Override
                protected void onPreExecute() {
                    // TODO Auto-generated method stub
                    super.onPreExecute();
                    progressDialog = ProgressDialog.show(ImageBrowseActivity.this, "请稍候。。。",
                            "正在上传照片中。。");
                }

                @Override
                protected String doInBackground(Void... params) {
                    // TODO Auto-generated method stub
                    InputStream stream = bitmapToIS(getSmallBitmap(imagePath));
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
                    progressDialog.dismiss();
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
                                imagePo.setImagePath(imagePath);
                                imagePo.setImageType("1");
                                imagePo.setImageSubtype("2");
                                imagePo.setImageUpload("1");//表示没上传
                                PhotoManager.getInstance().savePhotoInfoData(imagePo);//插入数据库
                                Toast.makeText(ImageBrowseActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
//                                getData();
//                                setImageBrowse(0);
                            } else
                                Toast.makeText(ImageBrowseActivity.this, responseMessage, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else
                        Toast.makeText(ImageBrowseActivity.this, "返回数据为空", Toast.LENGTH_SHORT).show();
                }
            }.execute();


        }
    }

    @Override
    public void ocrRespone(int requestCode, String responseString) {
        switch (requestCode) {
            default:
                break;
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
        try {
            url = new URL(testUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(20000);        //设置连接超时时间
            httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hongling" + File.separator + "img" + File.separator + flag);
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
            String data = "contractNo=" + flag + "&base64=" + URLEncoder.encode(Base64Util.encode(imgData), "utf-8");

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
            filePath = filePath.replace(rootStart, "");
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
     *
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

    public void checkStage(int flag) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            imgDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hongling" + File.separator + "ocr";
            File dir = new File(imgDir);// 返回 /sdcard
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String dPicName = getStrDateTime() + ".jpg";
            imgFile = imgDir + File.separator + dPicName;
        } else {
            Toast.makeText(ImageBrowseActivity.this, "检查到没有存储卡,请插入手机存储卡再开启本应用", Toast.LENGTH_LONG).show();
        }
    }

    // 当前时间
    public Timestamp crunttime() {
        return new Timestamp(System.currentTimeMillis());
    }

    //	获取当前时间的字符串  2006-07-07 22:10:10 2006-07-07_221010
    public String getStrDateTime() {
        Timestamp d = crunttime();
        return d.toString().replace(":", "").replace(" ", "_").replace(".", "");
    }

    public void uploadPic(final Context context, final OCRUtil.OcrCallback ocrCallback, final String flag) {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "请稍候。。。",
                        "正在上传照片中。。");
            }

            @Override
            protected String doInBackground(Void... params) {
                // TODO Auto-generated method stub
                InputStream stream = bitmapToIS(getSmallBitmap(imgFile));
                String result = null;
                try {
                    result = uploadDataPic64(stream, flag);
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
                progressDialog.dismiss();
                if (result != null && !result.equals("")) {
                    System.out.println("++++++++++++++++++++++" + result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String responseCode = jsonObject.getString("code");
                        String responseMessage = jsonObject.getString("message");
                        if (responseCode.equals("1")) {
                            PhotoInfo imagePo = new PhotoInfo();
                            imagePo.setReportCode(flag);
                            imagePo.setImageName(imgName);
                            imagePo.setImagePath(imgFildDir);
                            imagePo.setImageType("1");
                            imagePo.setImageSubtype("2");
                            imagePo.setImageUpload("1");//表示没上传
                            PhotoManager.getInstance().savePhotoInfoData(imagePo);//插入数据库
                            delFile(imgFile);
                            Toast.makeText(context, responseMessage, Toast.LENGTH_SHORT).show();
//                            getData();
//                            setImageBrowse(0);
                        } else
                            Toast.makeText(context, responseMessage, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(context, "返回数据为空", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

}
