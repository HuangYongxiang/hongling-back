package com.hl.photo.support.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.hl.core.lib.bean.TypeItem;
import com.hl.photo.R;
import com.hl.photo.ui.widget.HorizontalScrollWidgetItem;
import com.hl.photo.ui.widget.HorizontalScrollWidgetItemLayoutParams;
import com.hl.photo.support.util.CameraConstant.Filter;


public class CameraImageTypeItem extends HorizontalScrollWidgetItem {

    private Context context;
    private Filter filter;

    private ImageView mImageViewFilterIcon;
    private TextView mTextViewFilterTitle;
    private TypeItem typeItem;

    public CameraImageTypeItem(int mLayoutResId, Filter filter, Context context) {
        super(mLayoutResId);
        this.filter = filter;
        this.context = context;
    }
    public CameraImageTypeItem(int mLayoutResId, TypeItem typeItem, Context context) {
        super(mLayoutResId);
        this.typeItem = typeItem;
        this.context = context;
    }

    public CameraImageTypeItem(int mLayoutResId, HorizontalScrollWidgetItemLayoutParams itemLayoutParams, Filter filter, Context context) {
        super(mLayoutResId, itemLayoutParams);
        this.filter = filter;
        this.context = context;
    }

    @Override
    public View convertView() {

        if(context == null || getLayoutResId() < 0)
            return null;

//        if(filter == null)
//            return null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layoutResId = getLayoutResId();

        View view = inflater.inflate(layoutResId, null);
        mImageViewFilterIcon = (ImageView) view.findViewById(R.id.mImageViewFilterIcon);
        mTextViewFilterTitle = (TextView) view.findViewById(R.id.mTextViewFilterTitle);

//        mImageViewFilterIcon.setBackgroundResource(filter.getFilterIconResId());
//        mTextViewFilterTitle.setText(filter.getFilterTitleResId());
        mImageViewFilterIcon.setBackgroundResource(R.mipmap.photo_camera_filter_none_normal);
        mTextViewFilterTitle.setText(typeItem.getValue());

        return view;
    }

    public Filter getFilter(){
        return this.filter;
    }

    public TypeItem getTypeItem(){
        return this.typeItem;
    }

    public ImageView getImageViewFilterIcon() {
        return mImageViewFilterIcon;
    }

    public TextView getTextViewFilterTitle() {
        return mTextViewFilterTitle;
    }

}
