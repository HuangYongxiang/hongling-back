package com.hl.photo.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import com.hl.photo.R;
import com.hl.photo.table.model.PhotoInfo;
import com.hl.photo.ui.widget.HorizontalProgressBarWithNumber;


import java.util.List;

/**
 * Created by wxl on 2017/9/6.
 *
 *      图片上传列表适配器
 */
public class PictureListAdapter extends BaseAdapter {

    List<PhotoInfo> photoInfoList;
    Context context ;
    LayoutInflater  layoutInflater;

    SparseBooleanArray sparseBooleanArray ;  //记录待上传的照片是否被选中
    ArrayMap<String,HorizontalProgressBarWithNumber> progressBarWithNumberArrayMap;  //上传照片对应的进度条

    boolean isSelectedAll = false;    //是否全部选中

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public PictureListAdapter(Context context, List<PhotoInfo> photoInfoList){

        this.context = context.getApplicationContext();
        this.photoInfoList = photoInfoList;
        layoutInflater =  LayoutInflater.from(context);
        sparseBooleanArray = new SparseBooleanArray();
        progressBarWithNumberArrayMap = new ArrayMap();

    }


    @Override
    public int getCount() {
        return photoInfoList == null?0:photoInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return photoInfoList == null? null :photoInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHold viewHold = null;
//        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.photo_upload_picture_list_item,null);
            viewHold = new ViewHold(convertView);
//            convertView.setTag(viewHold);
//        }else{
//            viewHold = (ViewHold) convertView.getTag();
//        }

        //绑定数据
        PhotoInfo photoInfo = photoInfoList.get(position);
        if(!TextUtils.isEmpty(photoInfo.getImagePath())){
            Uri uri = Uri.parse("file://"+photoInfo.getImagePath()); //加载的本地图片地址
            ImageRequest request =
                    ImageRequestBuilder.newBuilderWithSource(uri)
                            .setResizeOptions(new ResizeOptions(64,64))//缩放,在解码前修改内存中的图片大小, 配合Downsampling可以处理所有图片
                            .setProgressiveRenderingEnabled(true)//支持图片渐进式加载
                            .setAutoRotateEnabled(true) //如果图片是侧着,可以自动旋转
                            .build();
            PipelineDraweeController controller =
                    (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                            .setImageRequest(request)
                            .setOldController(viewHold.img.getController())
                            .setAutoPlayAnimations(true)
                            .build();
            viewHold.img.setController(controller);

        }

        //是否被选中
        if(sparseBooleanArray.get(position,false)){
            viewHold.checkImg.setChecked(true);

        }else{
            viewHold.checkImg.setChecked(false);

        }


        viewHold.checkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectedAll = false;  //重置照片全选状态
               boolean isChecked =  ((CheckBox)v).isChecked();
                if(isChecked){
                    setCheckedImg(position);
                }else{
                    deleteCheckedImg(position);
                }
            }
        });

        // TODO: 2017/9/12   暂时将所有的进度条收入，后期优化
        progressBarWithNumberArrayMap.put(photoInfo.getImagePath(),viewHold.mProgressBar);

        return convertView;
    }


    class ViewHold{

      CheckBox checkImg;  //是否选中上传
      SimpleDraweeView img;
      HorizontalProgressBarWithNumber mProgressBar;  //显示的进度条
        private   ViewHold(View view){
            checkImg =  view.findViewById(R.id.ds_imgId);
            img =  view.findViewById(R.id.ds_imgPic);
            mProgressBar = view.findViewById(R.id.id_progressbar01);
        }

    }


     public SparseBooleanArray getCheckedImgs(){
         return  sparseBooleanArray;
     }


    public void deleteCheckedImg(int position){
        sparseBooleanArray.delete(position);
    }


    public void setCheckedImg(int position){
        sparseBooleanArray.put(position, true);
    }


    public void clearCheckedImgs(){
        sparseBooleanArray.clear();
    }

    public void checkedAllImgs(){

        if(!isSelectedAll){
            if(photoInfoList!=null&&photoInfoList.size()>0){
                sparseBooleanArray.clear();
                for (int i = 0; i <photoInfoList.size() ; i++) {
                    sparseBooleanArray.put(i,true);
                }
             }
            isSelectedAll = true;
        }else{
            sparseBooleanArray.clear();
            isSelectedAll = false;
         }

        notifyDataSetChanged();
    }

    public ArrayMap<String,HorizontalProgressBarWithNumber> getAllProgressBar(){
        return  progressBarWithNumberArrayMap;
    }


}
