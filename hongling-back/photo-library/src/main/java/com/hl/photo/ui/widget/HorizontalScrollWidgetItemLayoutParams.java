package com.hl.photo.ui.widget;

import android.view.ViewGroup.LayoutParams;

public class HorizontalScrollWidgetItemLayoutParams {

	public int leftMargin = 0;
	public int rightMargin = 0;
	public int topMargin = 0;
	public int bottomMargin = 0;
	public int width = LayoutParams.WRAP_CONTENT;
	public int height = LayoutParams.WRAP_CONTENT;
	
	public HorizontalScrollWidgetItemLayoutParams() {
	}

	public HorizontalScrollWidgetItemLayoutParams(int leftMargin, int rightMargin,
                                                  int topMargin, int bottomMargin, int width, int height) {
		this.leftMargin = leftMargin;
		this.rightMargin = rightMargin;
		this.topMargin = topMargin;
		this.bottomMargin = bottomMargin;
		this.width = width;
		this.height = height;
	}


}
