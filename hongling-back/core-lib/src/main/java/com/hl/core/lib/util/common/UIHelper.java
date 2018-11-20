package com.hl.core.lib.util.common;

import java.util.Random;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.media.AudioManager;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 *
 * @author liyu
 * @version 1.0, 2012-8-29 下午01:55:17
 *
 * @(#)ActivityDialog.java
 *
 * @Copyright 2012 AngelShine
 */
@SuppressWarnings("all")
public class UIHelper {

	public static String TAG = "ActivityDialog----------UI工具";

	private static int seq = 500000000;

	public static NotificationManager notificationManager;

	static double DEF_PI = 3.14159265359; // PI
	static double DEF_2PI = 6.28318530712; // 2*PI
	static double DEF_PI180 = 0.01745329252; // PI/180.0
	static double DEF_R = 6370693.5; // radius of earth

	/**
	 * @param context
	 *            载体
	 * @param msg
	 *            消息
	 */
	public static void showMsg(Context context, String msg) {
		showMsg(context, msg, Toast.LENGTH_LONG);
	}

	/**
	 * @param context
	 *            载体
	 * @param msg
	 *            消息资源ID
	 */
	public static void showMsg(Context context, int msgID) {
		Resources res = context.getResources();
		String msg = res.getString(msgID);
		showMsg(context, msg);
	}

	/**
	 * @param context
	 *            载体
	 * @param msg
	 *            消息
	 * @param times
	 *            时间
	 */
	public static void showMsg(Context context, String msg, int times) {
		if (times == 0) {
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, msg, times).show();
		}
	}

//	/**
//	 * 显示主页面
//	 *
//	 * @param activity
//	 */
//	public static void showMain(Activity activity) {
//		Intent intent = new Intent(activity, MainActivity.class);
//		activity.startActivity(intent);
//		activity.finish();
//	}

	/**
	 * 页面跳转
	 *
	 * @param activity
	 */
	public static void showActivity(Activity activity, Class cls) {
		showActivity(activity, cls, true);
	}

	/**
	 * 页面跳转
	 *
	 * @param activity
	 */
	public static void showActivity(Activity activity, Class cls,
									boolean isFinish) {
		Intent intent = new Intent(activity, cls);
		activity.startActivity(intent);
		if (isFinish) {
			activity.finish();
		}
	}

	public static void toActity(Activity activity, String clzName) {
		Class cls = null;
		try {
			cls = Class.forName("clzName");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Intent intent = new Intent(activity, cls);
		activity.startActivity(intent);
		activity.finish();
	}

	/**
	 * 生成随机码
	 *
	 * @return
	 */
	public static int getSeq() {
		Random ran = new Random();
		// int seq = (int) (ran.nextDouble() * 99999 + 10000);
		return seq++;
	}

	/**
	 * 声音提示
	 *
	 * @return
	 */
//	public static void notice(Context mContext, int voiceResID) {
//		Notification notification = null;
//		if (notificationManager == null || notification == null) {
//			notificationManager = (NotificationManager) mContext
//					.getSystemService(Context.NOTIFICATION_SERVICE);
//			notification = new Notification();
//		}
//		notification.sound = Uri.parse("android.resource://"
//				+ mContext.getPackageName() + "/" + voiceResID);
//		notificationManager.notify(R.layout.main, notification);
//	}

	public static void voiceManage(Context context, TextView voiceBtn,
								   boolean onFlag, int voiceOn) {
		AudioManager am = (AudioManager) context
					.getSystemService(Context.AUDIO_SERVICE);
		// 得到AudioManager对象
		// am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		// 参数为 设置情景模式的类型
		// AudioManager.RINGER_MODE_NORMAL 正常模式,有声,是否震动取决于原来系统声音设置中振动的设置
		// AudioManager.RINGER_MODE_SILENT 静音模式,无声不震
		// AudioManager.RINGER_MODE_VIBRATE 震动模式,无声,震动
		if (onFlag) {
			am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		} else {
			am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		}
		voiceBtn.setBackgroundDrawable(context.getResources().getDrawable(
					voiceOn));

	}

	/**
	 * 自定义提示框
	 *
	 * @param mContext
	 * @param msg
	 */
	public static void showMyMsg(Context mContext, String msg, int layoutID,
								 TextView textView) {
		Toast toast = new Toast(mContext);
		View v = View.inflate(mContext, layoutID, null);
		LinearLayout linearLayout = new LinearLayout(mContext);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		linearLayout.setGravity(Gravity.CENTER);
		textView.setText(msg);
		linearLayout.addView(v);
		toast.setView(linearLayout);
		toast.setDuration(5000);
		toast.show();
	}

	/**
	 * 取得了当前版本号
	 *
	 * @return
	 */
	public static String getVersionName(Context context) {
		String ver = "";
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo;
			packInfo = packageManager.getPackageInfo(context.getPackageName(),
						0);
			ver = packInfo.versionName;

		} catch (NameNotFoundException e) {
			Log.e("nationz", "获取软件的版本号异常：", e);
			return ver;
		}
		return ver;
	}

	public static void setBtnBG(Context context, Button btn, int drawable) {
		btn.setBackgroundDrawable(context.getResources().getDrawable(drawable));
	}



	/**
	 * 根据key清除保存的记录
	 *
	 * @param editor
	 * @param key
	 */
	public static void clearRecord(Editor editor, String key) {
		editor.remove(key);
		editor.commit();
	}

	public static String getRecord(Context mContext, String name) {
		SharedPreferences sPreferences = mContext.getSharedPreferences(name, 0);
		return sPreferences.getString(name, "");
	}

	/**
	 * @param mContext
	 * @param name
	 * @return
	 */
	public static Editor getEditor(Context mContext, String name) {
		SharedPreferences sPreferences = mContext.getSharedPreferences(name, 0);
		return sPreferences.edit();
	}

	public static Editor addSetting(Context mContext, String name, String val) {
		SharedPreferences sPreferences = mContext.getSharedPreferences(name, 0);
		Editor editor = getEditor(mContext, name);
		editor.putString(name, val);
		editor.commit();
		return sPreferences.edit();
	}

	public static boolean getBooleanRecord(Context mContext, String name) {
		SharedPreferences sPreferences = mContext.getSharedPreferences(name, 0);
		return sPreferences.getBoolean(name, true);
	}

	public static Editor addBoolean(Context mContext, String name, Boolean val) {
		SharedPreferences sPreferences = mContext.getSharedPreferences(name, 0);
		Editor editor = getEditor(mContext, name);
		editor.putBoolean(name, val);
		editor.commit();
		return sPreferences.edit();
	}

	public static void Log(String type, String tag, Object tagMsg, Object... O) {
		Object[] obj = O;
		if (tag == null || tag.equals(""))
			tag = "upload";
		String msg = tagMsg + "";
		boolean isOpen = true;
		type = type.toLowerCase();
		if (obj.length == 1) {
			isOpen = (Boolean) obj[0];
		}
		if (isOpen) {
			if (type.equals("i")) {
				Log.i(tag, msg);
			} else if (type.equals("d")) {
				Log.d(tag, msg);
			} else if (type.equals("e")) {
				Log.e(tag, msg);
			} else if (type.equals("v")) {
				Log.v(tag, msg);
			} else if (type.equals("w")) {
				Log.w(tag, msg);
			} else {
				Log.i(tag, msg);
			}
		}

	}

	// 修改屏幕Density
	public static void changeMetrics(Context context, boolean DebugFlag) {
		DisplayMetrics curMetrics = context.getResources().getDisplayMetrics();
		if (!DebugFlag) {
			if (curMetrics.densityDpi == DisplayMetrics.DENSITY_HIGH) {
				DisplayMetrics metrics = new DisplayMetrics();
				metrics.scaledDensity = 1.0f;
				metrics.density = 1.0f;
				metrics.densityDpi = DisplayMetrics.DENSITY_MEDIUM;
				metrics.xdpi = DisplayMetrics.DENSITY_MEDIUM;
				metrics.ydpi = DisplayMetrics.DENSITY_MEDIUM;
				metrics.heightPixels = curMetrics.heightPixels;
				metrics.widthPixels = curMetrics.widthPixels;
				context.getResources().getDisplayMetrics().setTo(metrics);
			}
		} else {
			DisplayMetrics metrics = new DisplayMetrics();
			metrics.scaledDensity = (float) (130 / 160.0);
			metrics.density = (float) (130 / 160.0);
			metrics.densityDpi = 130;
			metrics.xdpi = 130;
			metrics.ydpi = 130;
			metrics.heightPixels = curMetrics.heightPixels;
			metrics.widthPixels = curMetrics.widthPixels;
			context.getResources().getDisplayMetrics().setTo(metrics);
		}
	}

	public static int getScreenBrightness(Activity activity) {
		int value = 0;
		ContentResolver cr = activity.getContentResolver();
		try {
			value = Settings.System.getInt(cr,
						Settings.System.SCREEN_BRIGHTNESS);
		} catch (SettingNotFoundException e) {

		}
		return value;
	}

	public static void setScreenBrightness(Activity mContext, int value) {
		WindowManager.LayoutParams params = mContext.getWindow()
					.getAttributes();
		params.screenBrightness = value / 255f;
		mContext.getWindow().setAttributes(params);
		/*
		 * Settings.System.putInt(mContext.getContentResolver(),
		 * Settings.System.SCREEN_BRIGHTNESS,value);
		 */
	}

	/**
	 * 判断是否开启了自动亮度调节
	 *
	 * @param aContext
	 * @return
	 */
	public static boolean isAutoBrightness(Activity activity) {
		boolean automicBrightness = false;
		try {
			automicBrightness = Settings.System.getInt(
						activity.getContentResolver(),
						Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
		} catch (SettingNotFoundException e) {
			e.printStackTrace();
		}
		return automicBrightness;
	}

	/**
	 * 停止自动亮度调节
	 *
	 * @param activity
	 */
	public static void stopAutoBrightness(Activity activity) {
		Settings.System.putInt(activity.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE,
					Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
	}

	/**
	 * 开启亮度自动调节
	 *
	 * @param activity
	 */
	public static void startAutoBrightness(Activity activity) {
		Settings.System.putInt(activity.getContentResolver(),
					Settings.System.SCREEN_BRIGHTNESS_MODE,
					Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
	}

}
