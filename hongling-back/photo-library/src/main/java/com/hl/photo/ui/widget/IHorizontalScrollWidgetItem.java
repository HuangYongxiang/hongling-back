package com.hl.photo.ui.widget;

import android.view.View;

public interface IHorizontalScrollWidgetItem {
	
	View convertView();
	
	String getKey();
	
	HorizontalScrollWidgetItemLayoutParams getParams();

}
