package com.hl.photo.ui.widget;
import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HorizontalScrollWidget extends HorizontalScrollView {
	
	private Context context;
	private LinearLayout hswContainer;
	private Map<String, HorizontalScrollWidgetItem> hswItems = new LinkedHashMap<String, HorizontalScrollWidgetItem>();
	private Map<String, View> hswViews = new LinkedHashMap<String, View>();
	
	private HorizontalScrollWidgetItemListener hswItemListener;
	private HorizontalScrollWidgetVisibilityListener hswVisibilityListener;
	private HorizontalScrollWidgetScrollListener hswScrollListener;
	
	private HorizontalScrollWidgetItem currentSelectedItem;
	private View currentSelectedView;
	
	public HorizontalScrollWidget(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public HorizontalScrollWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public HorizontalScrollWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		
		if(hswScrollListener != null) hswScrollListener.onScroll(l, t, oldl, oldt);
		
	}
	
	private void init(){
		
		hswContainer = new LinearLayout(context);
		hswContainer.setBackgroundResource(R.color.transparent);// 要设为透明,否则有些机型对会绘制空白格
		hswContainer.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.addView(hswContainer, lp);
		
		this.setHorizontalFadingEdgeEnabled(false);
		this.setHorizontalScrollBarEnabled(false);
		
	}
	
	public boolean addItem(HorizontalScrollWidgetItem item){
		
		if(item == null || TextUtils.isEmpty(item.getKey()))
			return false;
		
		String key = item.getKey();
		
		if(hswItems.containsKey(key))
			return false;
		
		final View view = item.convertView();
		
		if(view == null)
			return false;
		
		view.setTag(item);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(hswItemListener != null) {
					hswItemListener.onItemSelected((HorizontalScrollWidgetItem) view.getTag(), view);
				}
				currentSelectedView = view;
				currentSelectedItem = (HorizontalScrollWidgetItem) view.getTag();
			}
		});
		
		HorizontalScrollWidgetItemLayoutParams ilp = item.getParams();
		if(ilp == null)
			ilp = new HorizontalScrollWidgetItemLayoutParams();
		
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ilp.width, ilp.height);
		lp.leftMargin = ilp.leftMargin;
		lp.rightMargin = ilp.rightMargin;
		lp.topMargin = ilp.topMargin;
		lp.bottomMargin = ilp.bottomMargin;
		hswContainer.addView(view, lp);
		
		hswItems.put(item.getKey(), item);
		hswViews.put(item.getKey(), view);
		
		return true;
		
	}
	
	public boolean delItem(HorizontalScrollWidgetItem item){
		
		if(item == null || TextUtils.isEmpty(item.getKey()))
			return false;
		
		String key = item.getKey();
		
		if(!hswItems.containsKey(key))
			return false;
		
		View view = hswViews.remove(key);
		
		if(view == null)
			return false;
		
		view.setTag(null);
		hswItems.remove(key);
		hswContainer.removeView(view);
		
		return true;
		
	}
	
	public void clearItems(){
		
		currentSelectedItem = null;
		currentSelectedView = null;
		hswItems.clear();
		hswViews.clear();
		hswContainer.removeAllViews();
		
	}
	
	public HorizontalScrollWidgetItem getCurrentSelectedItem(){
		return currentSelectedItem;
	}
	
	public View getCurrentSelectedView(){
		return currentSelectedView;
	}
	
	public void setSelectedItem(HorizontalScrollWidgetItem item){
		if(item != null && !TextUtils.isEmpty(item.getKey())){
			
			String key = item.getKey();
			View view = hswViews.get(key);
			if(view != null) view.performClick();
			
		}
	}
	
	public List<View> getHswViews(){
		List<View> views = new ArrayList<View>(hswViews.values());
		return views;
	}
	
	public List<HorizontalScrollWidgetItem> getHswItems(){
		List<HorizontalScrollWidgetItem> items = new ArrayList<HorizontalScrollWidgetItem>(hswItems.values());
		return items;
	}
	
	
	/**
	 * HSW控件Item Listener
	 */
	
	public interface HorizontalScrollWidgetItemListener {
		
		void onItemSelected(HorizontalScrollWidgetItem item, View view);
		
	}
	
	public void setHorizontalScrollWidgetItemListener(HorizontalScrollWidgetItemListener listener){
		this.hswItemListener = listener;
	}
	
	/**
	 * HSW控件可见性Listener
	 */
	@Override
	public void setVisibility(int visibility) {
		if(hswVisibilityListener != null) hswVisibilityListener.onVisibilityState(this.getVisibility(), visibility);
		super.setVisibility(visibility);
	}
	
	public interface HorizontalScrollWidgetVisibilityListener {
		
		void onVisibilityState(int before, int after);
		
	}
	
	public void setHorizontalScrollWidgetVisibilityListener(HorizontalScrollWidgetVisibilityListener listener){
		this.hswVisibilityListener = listener;
	}
	
	/**
	 * HSW控件滚动Listener
	 */
	public interface HorizontalScrollWidgetScrollListener{
		
		void onScroll(int l, int t, int oldl, int oldt);
		
	}
	
	public void setHorizontalScrollWidgetScrollListener(HorizontalScrollWidgetScrollListener listener){
		this.hswScrollListener = listener;
	}

}
