package com.hl.photo.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.hl.photo.R;
import com.hl.photo.business.entity.BasePo;
import com.hl.photo.support.util.ImageUtil;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.view.PhotoManagerActivity;


/**
 * @author liyu on 2017/1/21
 */
public class PhotoManageEditHView extends BaseHolderView {

//    @BindView(R2.id.pic_show_imageView)
    ImageView imageView;
//    @BindView(R2.id.pic_show_hint)
    RelativeLayout imageViewTint;
//    @BindView(R2.id.photo_local_gridView_item_select)
    ImageView selectImageView;
    private Bitmap bm = null;
    public PhotoManageEditHView(Context context) {
        super(context, R.layout.photo_edit_show_grid_item);
        imageView = findViewById(R.id.pic_show_imageView);
        imageViewTint = findViewById(R.id.pic_show_hint);
        selectImageView = findViewById(R.id.photo_local_gridView_item_select);
    }

    @Override
    protected void bindData(BasePo po, int position) {
        final PhotoInfo image = (PhotoInfo) po;
        if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_NO.equals(image.getImageType())){
            imageView.setBackgroundResource(R.mipmap.photo_no_photo);
            imageViewTint.setVisibility(View.GONE);
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_ADD.equals(image.getImageType())){
            imageView.setBackgroundResource(R.mipmap.photo_add_photo);
            imageViewTint.setVisibility(View.GONE);
        }else if(PhotoClaimUtil.PHOTO_IMAGE_TYPE_CAMERA.equals(image.getImageType())){
            imageView.setBackgroundResource(R.mipmap.photo_camera);
            imageViewTint.setVisibility(View.GONE);
        }else{
            //显示图片
            ImageUtil.getInstance().loadImage(image.getImagePath(),imageView);
            if("0".equals(image.getImageUpload())){
                imageViewTint.setVisibility(View.VISIBLE);
            }else{
                imageViewTint.setVisibility(View.GONE);
            }
            if(image.isChecked()){
                selectImageView.setImageResource(R.mipmap.photo_local_selected);
            }else {
                selectImageView.setImageResource(R.mipmap.photo_local_unselected);
            }
            //添加到缓存中

            PhotoManagerActivity.gridviewBitmapCaches.put(image.getImageName(), bm);
            imageView.setOnClickListener(new OnClickListener()
            {
                //选择，则将图片变暗，反之则反之
                @Override
                public void onClick(View v)
                {
                    if(image.isChecked()){ // 未选择该图片
                        image.setChecked(false);
                        selectImageView.setImageResource(R.mipmap.photo_local_unselected);
                    }else{// 已经选择过该图片
                        image.setChecked(true);
                        selectImageView.setImageResource(R.mipmap.photo_local_selected);
                    }
                }
            });
        }

    }
}
