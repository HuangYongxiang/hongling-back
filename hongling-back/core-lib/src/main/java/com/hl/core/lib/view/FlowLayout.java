package com.hl.core.lib.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Describe:流式布局
 * @Package: com.hl.core.lib.view
 * @Author: liyu
 * @Date: 2018/1/22 15:26
 * @Copyright: hl
 */
public class FlowLayout extends ViewGroup {

	private final static int PAD_H = 10, PAD_V = 5; // Space between child views.
	private int mHeight;

	public FlowLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		assert (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);
		final int width = MeasureSpec.getSize(widthMeasureSpec)
				- getPaddingLeft() - getPaddingRight();
		int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop()
				- getPaddingBottom();
		final int count = getChildCount();
		int xpos = getPaddingLeft();
		int ypos = getPaddingTop();
		int childHeightMeasureSpec;
		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST)
			childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
					MeasureSpec.AT_MOST);
		else
			childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		mHeight = 0;
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() != GONE) {
				child.measure(
						MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
						childHeightMeasureSpec);
				final int childw = child.getMeasuredWidth();
				mHeight = Math.max(mHeight, child.getMeasuredHeight() + PAD_V);
				if (xpos + childw > width) {
					xpos = getPaddingLeft();
					ypos += mHeight;
				}
				xpos += childw + PAD_H;
			}
		}
		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
			height = ypos + mHeight;
		} else if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
			if (ypos + mHeight < height) {
				height = ypos + mHeight;
			}
		}
		height += 5; // Fudge to avoid clipping bottom of last row.
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		final int width = r - l;
		int xpos = getPaddingLeft();
		int ypos = getPaddingTop();
		for (int i = 0; i < getChildCount(); i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() != GONE) {
				final int childw = child.getMeasuredWidth();
				final int childh = child.getMeasuredHeight();
				if (xpos + childw > width) {
					xpos = getPaddingLeft();
					ypos += mHeight;
				}
				child.layout(xpos, ypos, xpos + childw, ypos + childh);
				xpos += childw + PAD_H;
			}
		}
	} // end onLayout()

}
