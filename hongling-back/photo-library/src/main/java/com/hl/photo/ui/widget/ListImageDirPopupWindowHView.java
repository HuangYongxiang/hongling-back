package com.hl.photo.ui.widget;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.hl.photo.R;
import com.hl.photo.business.entity.BasePo;
import com.hl.photo.business.entity.ImageFloder;


/**
 * Created by liyu on 2017/1/22.
 */

public class ListImageDirPopupWindowHView extends BaseHolderView {

    ImageView imageView;
    TextView textViewName;
    TextView textViewCount;


    public ListImageDirPopupWindowHView(Context context) {
        super(context, R.layout.photo_local_list_dir_item);
        imageView = findViewById(R.id.photo_local_dir_item_image);
        textViewName = findViewById(R.id.photo_local_dir_item_name);
        textViewCount = findViewById(R.id.photo_local_dir_item_count);

    }

    @Override
    protected void bindData(BasePo po, int position) {
        final ImageFloder image = (ImageFloder) po;
        textViewName.setText(image.getName());
        //使用ImageLoader
        Glide.with(imageView.getContext()).load(image.getFirstImagePath()).into(imageView);
        textViewCount.setText(image.getCount() + "张");
    }
}