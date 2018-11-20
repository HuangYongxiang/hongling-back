package com.hl.photo.support.util;

import android.content.pm.PackageManager;
import android.util.Log;

import com.hl.core.lib.bean.TypeItem;
import com.hl.photo.PhotoApp;
import com.hl.photo.support.util.CameraConstant.Filter;

import java.util.List;

public class CameraUtil {

	public static boolean checkCameraHardware() {
		return checkCameraHardware(false);
	}
	
	public static boolean checkCameraHardware(boolean frontCamera) {

		if(frontCamera)
			return PhotoApp.instance().get().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
		else
			return PhotoApp.instance().get().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);

	}

	public static boolean checkFlashLightHardware() {
		if (PhotoApp.instance().get().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_FLASH))
			return true;
		else
			return false;
	}
	
	public static boolean checkAutoFocusHardware() {
		if (PhotoApp.instance().get().getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_AUTOFOCUS))
			return true;
		else
			return false;
	}
	
	public static void L(String msg){
		Log.i("Camera", msg == null ? "" : msg);
	}

	public List<Filter> getSupportedFilters(List<TypeItem> iamgeTypeList){
		List<Filter> filters = null;

		if(iamgeTypeList != null&&iamgeTypeList.size()>0){
//			List<String> supportedColorEffects = iamgeTypeList.getSupportedColorEffects();
//			if(supportedColorEffects != null && !supportedColorEffects.isEmpty()){
//				filters = new ArrayList<Filter>();
				for(TypeItem item : iamgeTypeList){
					Filter filter = Filter.mapFilterNameToFilter(item.getValue());
					if(filter != null) filters.add(filter);
				}
//			}
		}

		return filters;
	}

}
