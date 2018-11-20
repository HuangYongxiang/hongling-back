package com.hl.photo.ui.widget;


public abstract class HorizontalScrollWidgetItem implements IHorizontalScrollWidgetItem {

	private int mLayoutResId;
	private String key;
	private HorizontalScrollWidgetItemLayoutParams itemLayoutParams;
	
	public HorizontalScrollWidgetItem(int mLayoutResId){
		this.mLayoutResId = mLayoutResId;
		this.key = generateKey();
	}
	
	public HorizontalScrollWidgetItem(int mLayoutResId, HorizontalScrollWidgetItemLayoutParams itemLayoutParams){
		this.mLayoutResId = mLayoutResId;
		this.itemLayoutParams = itemLayoutParams;
		this.key = generateKey();
	}
	
	public void setItemLayoutParams(HorizontalScrollWidgetItemLayoutParams itemLayoutParams){
		this.itemLayoutParams = itemLayoutParams;
	}
	
	private String generateKey() {
		return hashCode() + "";
	}
	
	public int getLayoutResId() {
		return mLayoutResId;
	}
	
	@Override
	public final String getKey() {
		return key;
	}
	
	@Override
	public HorizontalScrollWidgetItemLayoutParams getParams() {
		return itemLayoutParams;
	}

}
