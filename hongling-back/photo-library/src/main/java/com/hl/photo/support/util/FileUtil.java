package com.hl.photo.support.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;


import com.hl.photo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * 文件相关的工具类
 * @author liyu
 *
 */
public class FileUtil {

	private static final String TAG = FileUtil.class.getSimpleName();
	private final static String DIR_CACHE = "app_data";//接口数据缓存
	private static final String JPEG_FILE_PREFIX = "IMG";
	private static final String JPEG_FILE_SUFFIX = ".jpg";
	/**
     * 得到pic路径
     * 1、有sdcard返回Android/data/com..../app_data目录
     * 2、无sdcard返回data/data/com.../app_data目录
     * 
     **/
    public static File getDirCache(Context pContext){
        if(isSDCardWritable()){
            return pContext.getExternalFilesDir(DIR_CACHE);
        }else{
            return getDirInner(pContext, DIR_CACHE);
        }
    }
    //每次退出都清空缓存
    public static void clearCache(Context pContext){
        //删除内部cache
        deleteDirInner(pContext, DIR_CACHE);
        //删除sdcard上的cache
        deleteDirCache(pContext);
    }
	
	
	
	
	
	/**
	 * sdcard是否被移除
	 * 
	 * @Title isExternalStorageRemovable
	 * @return boolean
	 * @date 2013-12-6 下午4:33:00
	 */
	@TargetApi(9)
    private static boolean isExternalStorageRemovable() {
        if (hasGingerbread()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }
	
	private static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }
	
	/**
	 * 判断sd卡读写能力
	 * @return
	 */
	private static boolean isSDCardWritable(){
		if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !isExternalStorageRemovable()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据dir名字 得到文件
	 * 存在 返回
	 * 不存在 创建
	 * 
	 * 
	 * @Title getDir
	 * @param pDir
	 * @return File
	 * @date 2013-12-6 下午4:52:02
	 */
	private static File getDirInner(Context pContext, String pDir){
		File tFile = null;
		String tRoot = pContext.getFilesDir().getParent();
		if(tRoot!=null && !"".equals(tRoot)){
		    String tDir = tRoot + File.separator + pDir;
			if(createDir(tDir)){
				tFile = new File(tDir);
			}else{
			}
		}else{
		}
		return tFile;
	}
	/**
	 * 根据文件夹名字 创建文件夹
	 * @param pDir
	 * @return
	 */
	private static boolean createDir(String pDir) {
		boolean result = false;
		if(pDir!=null && !"".equals(pDir)){
			File folder = new File(pDir);
			if (!folder.exists()) {
				if (folder.mkdirs()) {
					result = true;
				} else {
					result = false;
				}
			}else{
				result = true;
			}
		}
		return result;
	}
	
	private static void deleteDirInner(Context pContext, String pDir){
        String tRoot = pContext.getFilesDir().getParent() + File.separator + pDir;
        deleteDir(new File(tRoot));
    }
    private static void deleteDirCache(Context pContext){
        if(isSDCardWritable()){
            File tDir = pContext.getExternalFilesDir(DIR_CACHE);
            deleteDir(tDir);
        }
    }
    private static void deleteDir(File pDir){
        if(pDir.exists()){
            String[] files = pDir.list();
            if(files!=null && files.length>0){
                File file;
                for(int i=0; i<files.length; i++){
                    file = new File(pDir, files[i]);
                    if(file.exists()){
                        file.delete();
                    }
                }
            }
        }
    }


	public static File createTmpFile(Context context) throws IOException {
		File dir;
		if(TextUtils.equals(Environment.getExternalStorageState(), Environment.MEDIA_MOUNTED)) {
			dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
			if (!dir.exists()) {
				dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera");
				if (!dir.exists()) {
					dir = getCacheDirectory(context, true);
				}
			}
		}else{
			dir = getCacheDirectory(context, true);
		}
		return File.createTempFile(JPEG_FILE_PREFIX, JPEG_FILE_SUFFIX, dir);
	}


	private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

	/**
	 * Returns application cache directory. Cache directory will be created on SD card
	 * <i>("/Android/data/[app_package_name]/cache")</i> if card is mounted and app has appropriate permission. Else -
	 * Android defines cache directory on device's file system.
	 *
	 * @param context Application context
	 * @return Cache {@link File directory}.<br />
	 * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
	 * {@link Context#getCacheDir() Context.getCacheDir()} returns null).
	 */
	public static File getCacheDirectory(Context context) {
		return getCacheDirectory(context, true);
	}

	/**
	 * Returns application cache directory. Cache directory will be created on SD card
	 * <i>("/Android/data/[app_package_name]/cache")</i> (if card is mounted and app has appropriate permission) or
	 * on device's file system depending incoming parameters.
	 *
	 * @param context        Application context
	 * @param preferExternal Whether prefer external location for cache
	 * @return Cache {@link File directory}.<br />
	 * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
	 * {@link Context#getCacheDir() Context.getCacheDir()} returns null).
	 */
	public static File getCacheDirectory(Context context, boolean preferExternal) {
		File appCacheDir = null;
		String externalStorageState;
		try {
			externalStorageState = Environment.getExternalStorageState();
		} catch (NullPointerException e) { // (sh)it happens (Issue #660)
			externalStorageState = "";
		} catch (IncompatibleClassChangeError e) { // (sh)it happens too (Issue #989)
			externalStorageState = "";
		}
		if (preferExternal && MEDIA_MOUNTED.equals(externalStorageState) && hasExternalStoragePermission(context)) {
			appCacheDir = getExternalCacheDir(context);
		}
		if (appCacheDir == null) {
			appCacheDir = context.getCacheDir();
		}
		if (appCacheDir == null) {
			String cacheDirPath = context.getFilesDir().getPath() + context.getPackageName() + "/cache/";
			appCacheDir = new File(cacheDirPath);
		}
		return appCacheDir;
	}

	/**
	 * Returns individual application cache directory (for only image caching from ImageLoader). Cache directory will be
	 * created on SD card <i>("/Android/data/[app_package_name]/cache/uil-images")</i> if card is mounted and app has
	 * appropriate permission. Else - Android defines cache directory on device's file system.
	 *
	 * @param context Application context
	 * @param cacheDir Cache directory path (e.g.: "AppCacheDir", "AppDir/cache/images")
	 * @return Cache {@link File directory}
	 */
	public static File getIndividualCacheDirectory(Context context, String cacheDir) {
		File appCacheDir = getCacheDirectory(context);
		File individualCacheDir = new File(appCacheDir, cacheDir);
		if (!individualCacheDir.exists()) {
			if (!individualCacheDir.mkdir()) {
				individualCacheDir = appCacheDir;
			}
		}
		return individualCacheDir;
	}

	private static File getExternalCacheDir(Context context) {
		File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
		File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
		if (!appCacheDir.exists()) {
			if (!appCacheDir.mkdirs()) {
				return null;
			}
			try {
				new File(appCacheDir, ".nomedia").createNewFile();
			} catch (IOException e) {
				Log.d(TAG, "getExternalCacheDir: " + Log.getStackTraceString(e));
			}
		}
		return appCacheDir;
	}

	private static boolean hasExternalStoragePermission(Context context) {
		int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
		return perm == PackageManager.PERMISSION_GRANTED;
	}

	public static Uri getUriByResId(int resId) {
		// 增加对资源id类型的图片类型判断
		return new Uri.Builder().scheme("res").path(String.valueOf(resId)).build();
	}

	/**
	 * 目录下建立文件夹
	 * @param folderPath
	 * @param fileName
	 * @return
	 */
	public static File createFile(String folderPath, String fileName) {
		File destDir = new File(folderPath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return new File(folderPath, fileName + fileName);
	}

	/**
	 * 新建目录
	 * @param directoryName
	 * @return
	 */
	public static boolean createDirectory(String directoryName) {
		boolean status;
		if (!directoryName.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + directoryName);
			status = newPath.mkdir();
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 复制文件
	 * @param oldPathFile 原文件路径 如：c:/fqf.txt
	 * @param newPathFile 复制后路径 如：f:/fqf.txt
	 */
	public static void copyFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在
				InputStream inStream = new FileInputStream(oldPathFile); // 读入源文件
				File n = new File(newPathFile);
				if (!n.exists()) {
					n.createNewFile();
				}
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节 文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 移动网文件
	 * @param oldPath
	 * @param newPath
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
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
	 * 压缩文件
	 *
	 * @param zipFilePath
	 * @param imageList
	 * @throws IOException
	 */
	public static void compressFiles(String upLoadJson,String zipFilePath, List<File> imageList)
			throws IOException {


		File zipFile = new File(zipFilePath);

		if (zipFile.exists()) {
			zipFile.delete();
		}
		// 1.创建文件
		zipFile.createNewFile();
		ZipOutputStream outZip = null;

		try {
			outZip = new ZipOutputStream(new FileOutputStream(zipFile));
			// 2.压缩任务信息
			// 上传的文件
			ZipEntry zipEntry = null;

			zipEntry = new ZipEntry("busi.xml");
			outZip.putNextEntry(zipEntry);
			outZip.write(upLoadJson.getBytes());
			outZip.closeEntry();

			// 3.打包图片信息
			for (int i = 0; i < imageList.size() ; i++) {
				File imageFile = imageList.get(i);

				// 判断是不是文件
				if (imageFile.isFile() && imageFile.exists()) {
					zipEntry = new ZipEntry(imageFile.getName());
					FileInputStream inputStream = new FileInputStream(
							imageFile.getAbsolutePath());
					outZip.putNextEntry(zipEntry);

					int len = -1;
					byte[] buffer = new byte[4096];

					while ((len = inputStream.read(buffer)) != -1) {
						int end = len;

						outZip.write(buffer, 0, len);
					}
					outZip.closeEntry();
				}
			}

		} catch (Exception e) {
		} finally {
			if (outZip != null) {
				try {
					outZip.close();
				} catch (IOException ex) {

				}
			}
		}

	}

	/**
	 * 获取SD卡路径
	 * @return
	 */
	public static String getSDPath(Context context) {
		File f = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // determine whether sd card is exist
		if (sdCardExist) {
			f = new File(Environment.getExternalStorageDirectory() + File.separator + context.getString(R.string.photo_app_dir));
			if (!f.exists()) {
				f.mkdir();
			}
		}
		return f.toString();
	}

	/** *//**文件重命名
	 * @param path 文件目录
	 * @param oldname  原来的文件名
	 * @param newname 新文件名
	 */
	public static void renameFile(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {//新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(path + "/" + oldname);
			File newfile = new File(path + "/" + newname);
			if (!oldfile.exists()) {
				return;//重命名文件不存在
			}
			if (newfile.exists()){//若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				System.out.println(newname + "已经存在！");
			    delFile(path + "/" + newname);
		    }
			oldfile.renameTo(newfile);
		} else {
			System.out.println("新文件名和旧文件名相同...");
		}
	}

	/**
	 * 复制文件
	 * @param oldPathFile 原文件路径 如：c:/fqf.txt
	 * @param newPathFile 复制后路径 如：f:/fqf.txt
	 */
	public static void copyFile(String oldPathFile, File newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在
				InputStream inStream = new FileInputStream(oldPathFile); // 读入源文件
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节 文件大小
					fs.write(buffer, 0, byteread);
				}
				fs.flush();
				fs.close();
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}



	/**
	 * 删除文件
	 * @param filePathAndName
	 * @return
	 */
	public static boolean delPicFile(String filePathAndName) {
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
	
