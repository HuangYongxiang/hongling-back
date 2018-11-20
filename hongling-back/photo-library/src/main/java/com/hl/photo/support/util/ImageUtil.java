package com.hl.photo.support.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.hl.photo.support.util.CameraConstant.Filter;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by liyu on 2017/1/23.
 */

public class ImageUtil {



    public static final float PICTURE_WIDTH = 1280f;
    public static final float PICTURE_HEIGHT = 720f;


    public static int PIC_HEIGHT =960;
    /**
     * 图片缓存的核心类
     */
    private LruCache<String, Bitmap> mLruCache;

    /**
     * 线程池
     */
    private ExecutorService mThreadPool;
    /**
     * 线程池的线程数量，默认为1
     */
    private int mThreadCount = 1;
    /**
     * 队列的调度方式
     */
    private Type mType = Type.LIFO;
    /**
     * 任务队列
     */
    private LinkedList<Runnable> mTasks;
    /**
     * 轮询的线程
     */
    private Thread mPoolThread;
    private Handler mPoolThreadHander;

    /**
     * 运行在UI线程的handler，用于给ImageView设置图片
     */
    private Handler mHandler;

    /**
     * 引入一个值为1的信号量，防止mPoolThreadHander未初始化完成
     */
    private volatile Semaphore mSemaphore = new Semaphore(0);

    /**
     * 引入一个值为1的信号量，由于线程池内部也有一个阻塞线程，防止加入任务的速度过快，使LIFO效果不明显
     */
    private volatile Semaphore mPoolSemaphore;

    private static ImageUtil mInstance;

    /**
     * 队列的调度方式
     *
     * @author zhy
     *
     */
    public enum Type
    {
        FIFO, LIFO
    }


    /**
     * 单例获得该实例对象
     *
     * @return
     */
    public static ImageUtil getInstance()
    {

        if (mInstance == null)
        {
            synchronized (ImageUtil.class)
            {
                if (mInstance == null)
                {
                    mInstance = new ImageUtil(1, Type.LIFO);
                }
            }
        }
        return mInstance;
    }

    private ImageUtil(int threadCount, Type type)
    {
        init(threadCount, type);
    }

    private void init(int threadCount, Type type)
    {
        // loop thread
        mPoolThread = new Thread()
        {
            @Override
            public void run()
            {
                Looper.prepare();

                mPoolThreadHander = new Handler()
                {
                    @Override
                    public void handleMessage(Message msg)
                    {
                        mThreadPool.execute(getTask());
                        try
                        {
                            mPoolSemaphore.acquire();
                        } catch (InterruptedException e)
                        {
                        }
                    }
                };
                // 释放一个信号量
                mSemaphore.release();
                Looper.loop();
            }
        };
        mPoolThread.start();

        // 获取应用程序最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<String, Bitmap>(cacheSize)
        {
            @Override
            protected int sizeOf(String key, Bitmap value)
            {
                return value.getRowBytes() * value.getHeight();
            };
        };

        mThreadPool = Executors.newFixedThreadPool(threadCount);
        mPoolSemaphore = new Semaphore(threadCount);
        mTasks = new LinkedList<Runnable>();
        mType = type == null ? Type.LIFO : type;

    }

    /**
     * 加载图片
     *
     * @param path
     * @param imageView
     */
    public void loadImage(final String path, final ImageView imageView)
    {
        // set tag
        imageView.setTag(path);
        // UI线程
        if (mHandler == null)
        {
            mHandler = new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    ImgBeanHolder holder = (ImgBeanHolder) msg.obj;
                    ImageView imageView = holder.imageView;
                    Bitmap bm = holder.bitmap;
                    String path = holder.path;
                    if (imageView.getTag().toString().equals(path))
                    {
                        imageView.setImageBitmap(bm);
                    }
                }
            };
        }

        Bitmap bm = getBitmapFromLruCache(path);
        if (bm != null)
        {
            ImgBeanHolder holder = new ImgBeanHolder();
            holder.bitmap = bm;
            holder.imageView = imageView;
            holder.path = path;
            Message message = Message.obtain();
            message.obj = holder;
            mHandler.sendMessage(message);
        } else
        {
            addTask(new Runnable()
            {
                @Override
                public void run()
                {

                    ImageSize imageSize = getImageViewWidth(imageView);

                    int reqWidth = imageSize.width;
                    int reqHeight = imageSize.height;

                    Bitmap bm = decodeSampledBitmapFromResource(path, reqWidth,
                            reqHeight);
                    addBitmapToLruCache(path, bm);
                    ImgBeanHolder holder = new ImgBeanHolder();
                    holder.bitmap = getBitmapFromLruCache(path);
                    holder.imageView = imageView;
                    holder.path = path;
                    Message message = Message.obtain();
                    message.obj = holder;
                    // Log.e("TAG", "mHandler.sendMessage(message);");
                    mHandler.sendMessage(message);
                    mPoolSemaphore.release();
                }
            });
        }

    }

    /**
     * 添加一个任务
     *
     * @param runnable
     */
    private synchronized void addTask(Runnable runnable)
    {
        try
        {
            // 请求信号量，防止mPoolThreadHander为null
            if (mPoolThreadHander == null)
                mSemaphore.acquire();
        } catch (InterruptedException e)
        {
        }
        mTasks.add(runnable);

        mPoolThreadHander.sendEmptyMessage(0x110);
    }

    /**
     * 取出一个任务
     *
     * @return
     */
    private synchronized Runnable getTask()
    {
        if (mType == Type.FIFO)
        {
            return mTasks.removeFirst();
        } else if (mType == Type.LIFO)
        {
            return mTasks.removeLast();
        }
        return null;
    }

    /**
     * 单例获得该实例对象
     *
     * @return
     */
    public static ImageUtil getInstance(int threadCount, Type type)
    {

        if (mInstance == null)
        {
            synchronized (ImageUtil.class)
            {
                if (mInstance == null)
                {
                    mInstance = new ImageUtil(threadCount, type);
                }
            }
        }
        return mInstance;
    }


    /**
     * 根据ImageView获得适当的压缩的宽和高
     *
     * @param imageView
     * @return
     */
    private ImageSize getImageViewWidth(ImageView imageView)
    {
        ImageSize imageSize = new ImageSize();
        final DisplayMetrics displayMetrics = imageView.getContext()
                .getResources().getDisplayMetrics();
        final LayoutParams params = imageView.getLayoutParams();

        int width = params.width == LayoutParams.WRAP_CONTENT ? 0 : imageView
                .getWidth(); // Get actual image width
        if (width <= 0)
            width = params.width; // Get layout width parameter
        if (width <= 0)
            width = getImageViewFieldValue(imageView, "mMaxWidth"); // Check
        // maxWidth
        // parameter
        if (width <= 0)
            width = displayMetrics.widthPixels;
        int height = params.height == LayoutParams.WRAP_CONTENT ? 0 : imageView
                .getHeight(); // Get actual image height
        if (height <= 0)
            height = params.height; // Get layout height parameter
        if (height <= 0)
            height = getImageViewFieldValue(imageView, "mMaxHeight"); // Check
        // maxHeight
        // parameter
        if (height <= 0)
            height = displayMetrics.heightPixels;
        imageSize.width = width;
        imageSize.height = height;
        return imageSize;

    }

    /**
     * 从LruCache中获取一张图片，如果不存在就返回null。
     */
    private Bitmap getBitmapFromLruCache(String key)
    {
        return mLruCache.get(key);
    }

    /**
     * 往LruCache中添加一张图片
     *
     * @param key
     * @param bitmap
     */
    private void addBitmapToLruCache(String key, Bitmap bitmap)
    {
        if (getBitmapFromLruCache(key) == null)
        {
            if (bitmap != null)
                mLruCache.put(key, bitmap);
        }
    }

    /**
     * 计算inSampleSize，用于压缩图片
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight)
    {
        // 源图片的宽度
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;

        if (width > reqWidth && height > reqHeight)
        {
            // 计算出实际宽度和目标宽度的比率
            int widthRatio = Math.round((float) width / (float) reqWidth);
            int heightRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = Math.max(widthRatio, heightRatio);
        }
        return inSampleSize;
    }

    /**
     * 根据计算的inSampleSize，得到压缩后图片
     *
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private Bitmap decodeSampledBitmapFromResource(String pathName,
                                                   int reqWidth, int reqHeight)
    {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(pathName, options);

        return bitmap;
    }

    private class ImgBeanHolder
    {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }

    private class ImageSize
    {
        int width;
        int height;
    }

    /**
     * 反射获得ImageView设置的最大宽度和高度
     *
     * @param object
     * @param fieldName
     * @return
     */
    private static int getImageViewFieldValue(Object object, String fieldName)
    {
        int value = 0;
        try
        {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = (Integer) field.get(object);
            if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE)
            {
                value = fieldValue;

                Log.e("TAG", value + "");
            }
        } catch (Exception e)
        {
        }
        return value;
    }

    /**
     *
     * @param bytes
     * @param addr
     * @param picPath
     * @param imageType
     * @param imageTime
     * @param reportNo
     * @param turnFlag
     */
    public static void compressBitmap(Bitmap waterBitmap,byte[] bytes,String addr, String picPath,String imageType,String imageTime,String reportNo,int turnFlag) {

        Bitmap b = null;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        b = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, newOpts);
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(picPath));
            Bitmap makeTextBitMap = null;
            makeTextBitMap = makeTextBitMap(waterBitmap,b.getWidth(),b.getHeight(),addr,imageTime, b, imageType, reportNo);
            makeTextBitMap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            makeTextBitMap.recycle();
            System.gc();
        } catch (Exception e) {
            Log.e("a", "a", e);
        }

    }


    /**
     *  本地图片压缩
     * @param inPath
     * @param outImgPath
     * @param addr
     * @param imageType
     * @param imageTime
     * @param reportNo
     */
    public static void compressBitmap(Bitmap waterBitmap,String inPath, String outImgPath,String addr,String imageType,String imageTime,String reportNo) {
//        Bitmap b=ScalingUtilities.getimage(inPath,640f,480f);
        Bitmap b = null;
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bs = BitmapFactory.decodeFile(inPath,newOpts);
        //此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是640*480分辨率，所以高和宽我们设置为
        float hh = PICTURE_WIDTH;//这里设置高度为640f
        float ww = PICTURE_HEIGHT;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {
            //如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            //如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        b = BitmapFactory.decodeFile(inPath, newOpts);
        if(b == null){
         try {
             File file = new File(inPath);
             InputStream inputStream = new FileInputStream(file) ;
             b = BitmapFactory.decodeStream(inputStream,null,newOpts);

         }catch (Exception e){
             e.printStackTrace();
         }
        }
        try {
            OutputStream out = new BufferedOutputStream(new FileOutputStream(outImgPath));
            Bitmap makeTextBitMap = null;
            makeTextBitMap = makeTextBitMap(waterBitmap,b.getWidth(),b.getHeight(),addr,imageTime, b, imageType, reportNo);
            makeTextBitMap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            makeTextBitMap.recycle();
        } catch (Exception e) {
            Log.e("a", "a", e);
        }
    }

    /* 旋转图片
    * @param angle
    * @param bitmap
    * @return Bitmap
    */
    public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片
        Matrix matrix = new Matrix();;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
// TODO:
//        CameraActivity.gridviewBitmapCaches.put("", bitmap);
        return resizedBitmap;
    }
    public static Bitmap makeTextBitMap(Bitmap waterBitmap,int w, int h, String addr, String date,Bitmap b,String imageType,String reportNo) {
        int j = 22;

        Bitmap icon = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // 建立一个空的BItMap
        // TODO:
//        CameraActivity.gridviewBitmapCaches.put("", icon);
        Canvas canvas = new Canvas(icon);// 初始化画布 绘制的图像到icon上

        Paint photoPaint = new Paint(); // 建立画笔
        photoPaint.setDither(true); // 获取跟清晰的图像采样
        photoPaint.setFilterBitmap(true);// 过滤一些

        Rect src = new Rect(0, 0, b.getWidth(), b.getHeight());// 创建一个指定的新矩形的坐标
        Rect dst = new Rect(0, 0, w, h);// 创建一个指定的新矩形的坐标
        canvas.drawBitmap(b, src, dst, photoPaint);

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);//设置画笔
        textPaint.setTextSize(18.0f);// 字体大小
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);//采用默认的宽度
        textPaint.setColor(Color.parseColor("#FFA51F"));// 采用的颜色

        j = b.getHeight()-80;

        String title ="";
        Filter filter = Filter.mapFilterTitleToFilter(imageType);
        if(filter!=null){
            title = filter.getFilterTitleResId();
        }
        title = reportNo;
        canvas.drawText(title, 0, j, textPaint);
//        j = j+20;
//        String title ="";
//        Filter filter = Filter.mapFilterTitleToFilter(imageType);
//        if(filter!=null){
//            title = filter.getFilterTitleResId();
//        }
//
//        canvas.drawText(title, 0, j, textPaint);
        j = j+24;
        if(addr==null||"".equals(addr)){
            addr="";
        }else if(addr.contains("中国")){
            addr = addr.replace("中国", "");

        }
        String[] stringFormat = StringFormat(addr, w, 24);
        for (int i = 0; i < stringFormat.length; i++) {
            canvas.drawText(stringFormat[i], 0, j, textPaint);
            j = j + 24;
        }

        canvas.drawText(date, 0, j, textPaint);

        //在画布上绘制水印图片
        if(waterBitmap!=null){
            //在画布上绘制水印图片
            int www = b.getWidth();
            if(www<=720){
                canvas.drawBitmap(waterBitmap,  b.getWidth()-150, 10, null);
            }else if(www>720&&www<1920){
                canvas.drawBitmap(waterBitmap,  b.getWidth()-220, 10, null);
            }else if(www>=1920){
                canvas.drawBitmap(waterBitmap,  b.getWidth()-250, 10, null);
            }
        }


        return icon;
    }

    public static String[] StringFormat(String text, int maxWidth, int fontSize) {
        String[] result = null;
        Vector<String> tempR = new Vector<String>();
        int lines = 0;
        int len = text.length();
        int index0 = 0;
        int index1 = 0;
        boolean wrap;
        while (true) {
            int widthes = 0;
            wrap = false;
            for (index0 = index1; index1 < len; index1++) {
                if (text.charAt(index1) == '\n') {
                    index1++;
                    wrap = true;
                    break;
                }
                widthes = fontSize + widthes;
                if (widthes > maxWidth) {
                    break;
                }
            }
            lines++;
            if (wrap) {
                tempR.addElement(text.substring(index0, index1 - 1));
            } else {
                tempR.addElement(text.substring(index0, index1));
            }
            if (index1 >= len) {
                break;
            }
        }
        result = new String[lines];
        tempR.copyInto(result);
        return result;
    }




    /**
     * inSampleSize
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options,
                                         int minSideLength, int maxNumOfPixels) {
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

    public static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }


    public static Bitmap getBitmapThumbnail(String iamgeUrl,int reqWidth,int reqHeight){

        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(iamgeUrl, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(iamgeUrl, options);

    }


    /**
     * 图片的base64编码
     */
    public static  String encodeBase64File(String path){
        String data ="";
        try {

            File file = new File(path);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length() + 100];
            int length = inputFile.read(buffer);
            data = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
    //将Bitmap转换成Base64
           public static String getImgStr(Bitmap bit){
                      ByteArrayOutputStream bos=new ByteArrayOutputStream();
                      bit.compress(Bitmap.CompressFormat.JPEG,  100, bos);//参数100表示不压缩
                      byte[] bytes=bos.toByteArray();
                      return Base64.encodeToString(bytes, Base64.DEFAULT);
                   }
// 将InputStream转换成Bitmap
            public static Bitmap InputStream2Bitmap(InputStream is) {
                       return BitmapFactory.decodeStream(is);
                  }
}
