package com.hl.photo.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.hl.photo.R;
import com.hl.photo.business.entity.PictureTypePo;
import com.hl.photo.business.listener.DeleteListener;

import java.util.List;

/**
 *  Create data： 2017/2/9
 *  Author: ChenXuefei
 *  Function:
 */

public class PictureTypeListAdapter extends BaseRecycleAdapter {
    private static final String TAG = "PictureTypeListAdapter";
    private DeleteListener deleteListener;

    public PictureTypeListAdapter(Context context, List<? extends Object> data, DeleteListener deleteListener) {
        super(context, R.layout.photo_picture_type_list_item_layout, data);
        this.deleteListener = deleteListener;
    }

    @Override
    protected <T> void convert(BaseViewHolder holder, T bean, int positon) {
        TextView typeName = (TextView) holder.findViewById(R.id.picture_type_name);
        TextView textContent = (TextView) holder.findViewById(R.id.picture_text_name);
        TextView numContent = (TextView) holder.findViewById(R.id.picture_total_num);

        final PictureTypePo item = (PictureTypePo) bean;
        String pictureTypeName = item.getPictureTypeName();

        typeName.setText(pictureTypeName);
        String signName = item.getSignName();
        if ("本车定损".equals(pictureTypeName)) {
            if (signName == null || "null".equals(signName)) {
                textContent.setText("");
            } else {
                textContent.setText(signName);
            }
        } else if ("三者定损".equals(pictureTypeName)) {
            if (signName == null || "null".equals(signName)) {
                textContent.setText("");
            } else {
                textContent.setText(signName);
            }
        } else if ("人伤查勘".equals(pictureTypeName)) {
            if (signName == null || "null".equals(signName)) {
                textContent.setText("");
            } else {
                textContent.setText(signName);
            }
        } else {
            textContent.setText("");
        }
        String takeDonePictureNum = item.getTakeDonePictureNum();
        if ("现场查勘".equals(pictureTypeName)) {
            numContent.setText("(" + takeDonePictureNum + ")");
        } else if ("本车定损".equals(pictureTypeName)) {
            numContent.setText("(" + takeDonePictureNum + ")");
        } else if ("三者定损".equals(pictureTypeName)) {
            numContent.setText("(" + takeDonePictureNum + ")");
        } else if ("人伤查勘".equals(pictureTypeName)) {
            numContent.setText("(" + takeDonePictureNum + ")");
        } else if ("物损定损".equals(pictureTypeName)) {
            numContent.setText("(" + takeDonePictureNum + ")");
        } else {
            String totalPictureNum = item.getTotalPictureNum();
            numContent.setText("(" + takeDonePictureNum + "/" + totalPictureNum + ")");
        }
    }
}
