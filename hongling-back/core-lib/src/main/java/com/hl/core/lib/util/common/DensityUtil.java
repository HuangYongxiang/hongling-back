package com.hl.core.lib.util.common;

import android.content.Context;

/**
 * @Describe: 像素、密度计算与转换
 * @Package: com.hl.core.lib.util
 * @Author: liyu
 * @Date: 2018/1/3 11:50
 * @Copyright: hl
 */
public class DensityUtil {

	/**
	 * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
	 */
	public int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public int dip2px(Context context, int dpValue){
		return dip2px(context,(float) dpValue);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	public int px2dip(Context context, int pxValue) {
		return px2dip(context,(float) pxValue);
	}

	/**
	 * 将px值转换为sp值
	 */
	public int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值
	 */
	public int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

}
