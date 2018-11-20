package com.hl.photo.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.hl.photo.R;
import com.hl.photo.business.entity.BasePo;
import com.hl.photo.business.entity.ImageFloder;
import com.hl.photo.support.util.ImageUtil;


/**
 * Created by liyu on 2017/1/22.
 */

public class PhotoLocalChooseHView extends BaseHolderView {

    ImageView imageView;
    ImageView selectImageView;
    String dir;

    public PhotoLocalChooseHView(Context context) {
        super(context, R.layout.photo_local_grid_view_item);
        imageView = findViewById(R.id.photo_local_gridView_item_image);
        selectImageView =  findViewById(R.id.photo_local_gridView_item_select);
    }

    @Override
    protected void bindData(BasePo po, int position) {
        final ImageFloder image = (ImageFloder)po;
        //设置no_pic
        imageView.setImageResource(R.mipmap.photo_no_photo);
        //设置no_selected
        selectImageView.setImageResource(R.mipmap.photo_local_unselected);
        //设置图片
        ImageUtil.getInstance(3, ImageUtil.Type.LIFO).loadImage(image.getDir() + "/" + image.getFirstImagePath(),imageView);

        imageView.setColorFilter(null);
        //设置ImageView的点击事件
        imageView.setOnClickListener(new OnClickListener()
        {
            //选择，则将图片变暗，反之则反之
            @Override
            public void onClick(View v)
            {
                if(image.isChecked()){ // 未选择该图片
                    image.setChecked(false);
                    imageView.setColorFilter(null);
                    selectImageView.setImageResource(R.mipmap.photo_local_unselected);
                }else{// 已经选择过该图片
                    image.setChecked(true);
                    imageView.setColorFilter(Color.parseColor("#30000000"));
                    selectImageView.setImageResource(R.mipmap.photo_local_selected);
                }
//
//                // 已经选择过该图片
//                if (mSelectedImage.contains(mDirPath + "/" + item))
//                {
//                    mSelectedImage.remove(mDirPath + "/" + item);
//                    mSelect.setImageResource(R.drawable.picture_unselected);
//                    mImageView.setColorFilter(null);
//                } else
//                // 未选择该图片
//                {
//                    mSelectedImage.add(mDirPath + "/" + item);
//                    mSelect.setImageResource(R.drawable.pictures_selected);
//                    mImageView.setColorFilter(Color.parseColor("#77000000"));
//                }

            }
        });
//
//        /**
//         * 已经选择过的图片，显示出选择过的效果
//         */
//        if (mSelectedImage.contains(mDirPath + "/" + item))
//        {
//            mSelect.setImageResource(R.drawable.pictures_selected);
//            mImageView.setColorFilter(Color.parseColor("#77000000"));
//        }
    }
}