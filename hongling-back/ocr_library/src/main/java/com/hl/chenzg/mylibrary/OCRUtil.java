package com.hl.chenzg.mylibrary;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.hl.photo.support.util.ImageUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static java.net.Proxy.Type.HTTP;

/**
 * Created by liyu on 2017/1/9.
 */

public class OCRUtil {

    public final static int TAKE_XINGSHI_NO = 1;//行驶证
    public final static int TAKE_PLATE_NO = 2;//车牌号
    public final static int TAKE_ID_CARD_NO = 3;//身份证
    public final static int TAKE_DRIVE_NO = 4;//驾驶证
    public final static int TAKE_BANK_NO = 5;//银行卡

    public final static int TAKE_XINGSHI_NO_ALL = 6;//行驶证全部信息
    public final static int TAKE_JIASHI_NO_ALL = 7;//驾驶证全部信息
    public final static int TAKE_SENFEN_NO_ALL = 8;//身份证全部信息
    public final static int TAKE_BANK_NO_ALL = 9;//银行卡全部信息


    public static final String PLATENO_SENDTYPE = "36";//车牌识别代码
    public static final String XINGSHIZHENG_SENDTYPE = "35";//行驶证识别代码
    public static final String CARDNO_SENDTYPE = "37";     //身份证识别代码
    public static final String DRIVER_SENDTYPE = "38";     //驾驶证识别代码
    public static final String BANKCARD_SENDTYPE = "40";     //银行卡识别代码

    public static final int UP_PICTURE = 10;     //上传照片

    private static ProgressDialog progressDialog;
    private static String imgDir;
    private static String imgFile;

    public static void requestOcr(final Context context, final OcrCallback ocrCallback, final int flag) {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "请稍候。。。",
                        "正在影像识别中。。");
            }

            @Override
            protected String doInBackground(Void... params) {
                // TODO Auto-generated method stub
                InputStream stream = bitmapToIS(getSmallBitmap(imgFile));
                String result = null;
                try {
                    result = sendDataPic64(stream, flag);
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
                        String responseCode = jsonObject.getString("responseCode");
                        String responseMessage = jsonObject.getString("responseMessage");
                        if (responseCode.equals("0000")) {
                            //得到结果后回调
                            String resuluString = null;
                            if (flag == TAKE_XINGSHI_NO) {
                                resuluString = jsonObject.getString("VIN");
                            } else if (flag == TAKE_PLATE_NO) {
                                resuluString = jsonObject.getString("PlateNo");
                            } else if (flag == TAKE_ID_CARD_NO) {
                                resuluString = jsonObject.getString("IdCardNo");
                            } else if (flag == TAKE_DRIVE_NO) {
                                resuluString = jsonObject.getString("CardNo");
                            } else if (flag == TAKE_BANK_NO) {
                                resuluString = jsonObject.getString("CardNum");
                            }else if(flag == TAKE_XINGSHI_NO_ALL||flag == TAKE_JIASHI_NO_ALL||flag == TAKE_SENFEN_NO_ALL||flag == TAKE_BANK_NO_ALL){
                                resuluString=result;
                            }
                            ocrCallback.ocrRespone(flag, resuluString);
                            delFile(imgFile);
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

    public static void uploadPic(final Context context, final OcrCallback ocrCallback, final String flag) {

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
                        if (responseCode.equals("0000")) {
                            //得到结果后回调
//                            String resuluString = null;
//                            if (flag == TAKE_XINGSHI_NO) {
//                                resuluString = jsonObject.getString("VIN");
//                            } else if (flag == TAKE_PLATE_NO) {
//                                resuluString = jsonObject.getString("PlateNo");
//                            } else if (flag == TAKE_ID_CARD_NO) {
//                                resuluString = jsonObject.getString("IdCardNo");
//                            } else if (flag == TAKE_DRIVE_NO) {
//                                resuluString = jsonObject.getString("CardNo");
//                            } else if (flag == TAKE_BANK_NO) {
//                                resuluString = jsonObject.getString("CardNum");
//                            }else if(flag == TAKE_XINGSHI_NO_ALL||flag == TAKE_JIASHI_NO_ALL||flag == TAKE_SENFEN_NO_ALL||flag == TAKE_BANK_NO_ALL){
//                                resuluString=result;
//                            }
//                            ocrCallback.ocrRespone(flag, resuluString);
                            delFile(imgFile);
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

    public interface OcrCallback {
        void ocrRespone(int flag, String responseString);
    }

    /**
     * 发送图片 识别
     *
     * @param stream
     * @param
     * @return
     * @throws Exception
     */
    public static String sendDataPic(InputStream stream, int flag) throws Exception {

        String testUrl = Constants.ZHENGJIANPATH;
        URL url = null;
        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        InputStream in = null;
        String resultJson = "";

        if (TAKE_XINGSHI_NO == flag) {
            testUrl = testUrl + XINGSHIZHENG_SENDTYPE;
        } else if (TAKE_PLATE_NO == flag) {
            testUrl = testUrl + PLATENO_SENDTYPE;
        } else if (TAKE_ID_CARD_NO == flag) {
            testUrl = testUrl + CARDNO_SENDTYPE;
        } else if (TAKE_DRIVE_NO == flag) {
            testUrl = testUrl + DRIVER_SENDTYPE;
        } else if (TAKE_BANK_NO == flag) {
            testUrl = testUrl + BANKCARD_SENDTYPE;
        } else if (TAKE_XINGSHI_NO_ALL == flag) {
            testUrl = testUrl + XINGSHIZHENG_SENDTYPE;
        } else if (TAKE_JIASHI_NO_ALL == flag) {
            testUrl = testUrl + DRIVER_SENDTYPE;
        } else if (TAKE_SENFEN_NO_ALL== flag) {
            testUrl = testUrl + CARDNO_SENDTYPE;
        } else if (TAKE_BANK_NO_ALL== flag) {
            testUrl = testUrl + BANKCARD_SENDTYPE;
        }

        try {
            url = new URL(testUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=Bounday---");
            connection.setRequestProperty("Cache-Control", "no-cache");
            outputStream = connection.getOutputStream();
            in = stream;
            byte[] myByte = new byte[1024];
            int count = -1;
            while ((count = in.read(myByte, 0, myByte.length)) != -1) {
                outputStream.write(myByte, 0, count);
                outputStream.flush();
            }
            stream.close();
            outputStream.close();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(b)) != -1) {
                byteOut.write(b, 0, read);
            }
            resultJson = new String(byteOut.toByteArray(), "GBK");
            in.close();
            byteOut.close();
        } catch (Exception e) {
            resultJson = "";
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    resultJson = "";
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    resultJson = "";
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return resultJson;
    }
    /**
     * 发送图片 识别
     *
     * @param stream
     * @param
     * @return
     * @throws Exception
     */
    public static String sendDataPic64(InputStream stream, int flag) throws Exception {

        String testUrl = Constants.ZHENGJIANPATH;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        OutputStream outputStream = null;
        String sendType = "";
        String resultJson = "";
        if (TAKE_XINGSHI_NO == flag) {
            sendType =  XINGSHIZHENG_SENDTYPE;
        } else if (TAKE_PLATE_NO == flag) {
            sendType = PLATENO_SENDTYPE;
        } else if (TAKE_ID_CARD_NO == flag) {
            sendType =  CARDNO_SENDTYPE;
        } else if (TAKE_DRIVE_NO == flag) {
            sendType =  DRIVER_SENDTYPE;
        } else if (TAKE_BANK_NO == flag) {
            sendType =  BANKCARD_SENDTYPE;
        } else if (TAKE_XINGSHI_NO_ALL == flag) {
            sendType =  XINGSHIZHENG_SENDTYPE;
        } else if (TAKE_JIASHI_NO_ALL == flag) {
            sendType =  DRIVER_SENDTYPE;
        } else if (TAKE_SENFEN_NO_ALL== flag) {
            sendType =  CARDNO_SENDTYPE;
        } else if (TAKE_BANK_NO_ALL== flag) {
            sendType =  BANKCARD_SENDTYPE;
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

            File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
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
            String data = "SendType="+sendType+"&base64="+URLEncoder.encode(Base64Util.encode(imgData), "utf-8");
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
    /**
     * 发送图片 识别
     *
     * @param stream
     * @param
     * @return
     * @throws Exception
     */
    public static String uploadDataPic(InputStream stream, String flag) throws Exception {

        String testUrl = Constants.UPLOADPICTURE+flag;
        URL url = null;
        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        InputStream in = null;
        String resultJson = "";
        testUrl = testUrl+"&base64="+ImageUtil.getImgStr(ImageUtil.InputStream2Bitmap(stream));
        try {
            url = new URL(testUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=Bounday---");
            connection.setRequestProperty("Cache-Control", "no-cache");
            outputStream = connection.getOutputStream();
            in = stream;
            byte[] myByte = new byte[1024];
            int count = -1;
            while ((count = in.read(myByte, 0, myByte.length)) != -1) {
                outputStream.write(myByte, 0, count);
                outputStream.flush();
            }
            stream.close();
            outputStream.close();
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int read = 0;
            while ((read = inputStream.read(b)) != -1) {
                byteOut.write(b, 0, read);
            }
            resultJson = new String(byteOut.toByteArray(), "UTF-8");
            in.close();
            byteOut.close();
        } catch (Exception e) {
            resultJson = "";
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    resultJson = "";
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    resultJson = "";
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return resultJson;
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

            File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
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
            String data = "contractNo="+flag+"&base64="+URLEncoder.encode(Base64Util.encode(imgData), "utf-8");

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

    public static void takePhoto(Context context, int flag) {
        try {
            checkStage(context, flag);
            // 三星部分手机拍照和保存是在两个Activity完成的，返回时其实会调用两次onCreate方法
            // 故将图片名称imgFile保存在全局变量中，防止其发生变化
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri mImageCaptureUri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
                Uri uriForFile = CoreFileProvide.getUriForFile((Activity) context, new File(imgFile));
                mImageCaptureUri = uriForFile;
                imgFile =mImageCaptureUri.getPath();
            }else{
                mImageCaptureUri = Uri.fromFile(new File(imgFile));
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            ((Activity) context).startActivityForResult(intent, flag);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void takePhoto(Fragment context, int flag) {
        try {
            checkStage(context.getContext(), flag);
            // 三星部分手机拍照和保存是在两个Activity完成的，返回时其实会调用两次onCreate方法
            // 故将图片名称imgFile保存在全局变量中，防止其发生变化
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri mImageCaptureUri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
                mImageCaptureUri = CoreFileProvide.getUriForFile( context.getActivity(), new File(imgFile));
            }else{
                mImageCaptureUri = Uri.fromFile(new File(imgFile));
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            context.startActivityForResult(intent, flag);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void checkStage(Context context, int flag) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            imgDir = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "hongling" + File.separator + "ocr";
            File dir = new File(imgDir);// 返回 /sdcard
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String dPicName = getStrDateTime();
            if (flag == TAKE_XINGSHI_NO) {
                dPicName = dPicName + "_drivinglicense.jpg";
            } else if (flag == TAKE_PLATE_NO) {
                dPicName = dPicName + "_plateno.jpg";
            } else if (flag == TAKE_ID_CARD_NO) {
                dPicName = dPicName + "_idcard.jpg";
            } else if (flag == TAKE_DRIVE_NO) {
                dPicName = dPicName + "_drive.jpg";
            } else if (flag == TAKE_BANK_NO) {
                dPicName = dPicName + "_bank.jpg";
            }
            imgFile = imgDir + File.separator + dPicName;
        } else {
            Toast.makeText(context, "检查到没有存储卡,请插入手机存储卡再开启本应用", Toast.LENGTH_LONG).show();
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

    private static final int UNCONSTRAINED = -1;

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

    // 当前时间
    public static Timestamp crunttime() {
        return new Timestamp(System.currentTimeMillis());
    }
    //	获取当前时间的字符串  2006-07-07 22:10:10 2006-07-07_221010
    public static String getStrDateTime() {
        Timestamp d = crunttime();
        return d.toString().replace(":", "").replace(" ", "_").replace(".", "");
    }
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
}
