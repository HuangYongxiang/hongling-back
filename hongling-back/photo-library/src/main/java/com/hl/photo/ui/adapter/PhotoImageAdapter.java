package com.hl.photo.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hl.photo.R;
import com.hl.photo.support.util.ImageUtil;
import com.hl.photo.support.util.PhotoClaimUtil;
import com.hl.photo.table.model.PhotoInfo;

import java.io.File;
import java.util.List;

/**
 * Created by liyu on 2017/6/12.
 */

public class PhotoImageAdapter extends BaseAdapter {
    private Context context;
    private Bitmap bitmap;
    List<PhotoInfo> data;
    LayoutInflater inflater;
    public PhotoImageAdapter(Context c, List<PhotoInfo> imgList) {
        context = c;
        data = imgList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //		public void setChecked(boolean ischecked){
//			Set<Integer> keySet = map.keySet();
////			for(map)
//			Iterator<Integer> iterator = keySet.iterator();
//			while(iterator.hasNext()){
//				Integer next = iterator.next();
//				CheckBox checkBox = map.get(next);
//				if(ischecked){
//					checkBox.setChecked(true);
//				}else{
//					checkBox.setChecked(false);
//				}
//				checkBox.invalidate();
//			}
//
//		}
    @Override
    public int getCount() {
        return data.size();
    }
    public void setData(List<PhotoInfo> data) {
        this.data = data;
    }

    public void clearData(){
        data.clear();
    }

    public void refrush() {
        this.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PhotoInfo image = data.get(position);

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.photo_pic_show_grid, parent, false);
        ImageView img = (ImageView) layout.findViewById(R.id.pic_show_imageView);
        ImageView uploadImage = (ImageView) layout.findViewById(R.id.pic_show_scan_img_upload_ok);
        TextView picDescText = (TextView) layout.findViewById(R.id.pic_desc);

        String fileName = image.getImagePath();
        if (fileName!=null){
            File f = new File(fileName);
            if (f.exists()) {
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                // TODO
                BitmapFactory.decodeFile(fileName, opts);
                opts.inSampleSize = ImageUtil.computeSampleSize(opts, -1, 128 * 128);
                opts.inJustDecodeBounds = false;
                try {
                    bitmap = BitmapFactory.decodeFile(fileName, opts);
                } catch (OutOfMemoryError err) {
                }

//                img.setImageBitmap(bitmap);
                Drawable drawable = new BitmapDrawable(context.getResources(),bitmap);
                img.setBackground(drawable);
                img.setImageDrawable(null);
//				img.setContentDescription(String.valueOf(epart._id));
//				picDescText.setText(epart.imgDesc==null||"".equals(epart.imgDesc)?"?????????":epart.imgDesc);


            } else {
                img.setBackgroundResource(R.drawable.photo_dash_bg);
                img.setImageResource(R.mipmap.photo_add);
            }
        }else {
            img.setBackgroundResource(R.drawable.photo_dash_bg);
            img.setImageResource(R.mipmap.photo_add);
        }
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                ArrayList<PhotoInfo> list = new ArrayList<PhotoInfo>();
//                final PhotoInfo image = data.get(position);
//                if("13702".equals(pictureType)){//人伤要根据人名
//                    list = claimImageAction.getImageListByReportNoAndSubTypeAndSignName(reportNo, image.getImageSubtype(),injuryName);
//                }else if("1370631".equals(pictureType)){//三者要根据车牌号
//                    list = claimImageAction.getImageListByReportNoAndSubTypeAndSignName(reportNo, image.getImageSubtype(),plateNo);
//                    if(list!=null&&list.size()>0){
//
//                    }else{//只有一只三者
//                        list = claimImageAction.getImageListByReportNoAndSubType(reportNo, image.getImageSubtype());
//                    }
//                }else{
//                    list = claimImageAction.getImageListByReportNoAndSubType(reportNo, image.getImageSubtype());
//                }
//
//                if(list!=null&&list.size()>0){
//                    Intent it = new Intent(ShowPictureAndTextActivity.this,ImageViewActivity.class);
//                    it.putExtra("ReportNo",reportNo);
//                    it.putExtra("ImageName", image.getImageName());
//                    it.putExtra("ImagePath", image.getImagePath());
//                    it.putExtra("ImageType", image.getImageType());
//                    it.putExtra("ImageSubtype", image.getImageSubtype());
//                    //sign存人名或车牌
//                    if("13702".equals(pictureType)){//人伤要根据人名
//                        it.putExtra("SignName",injuryName==null?"":injuryName);
//                    }else if("1370621".equals(pictureType)){//标的要根据车牌号
//                        it.putExtra("SignName",plateNo==null?"":plateNo);
//                    }else if("1370631".equals(pictureType)){//三者要根据车牌号
//                        it.putExtra("SignName",plateNo==null?"":plateNo);
//                    }else{
//                        it.putExtra("SignName",image.getSignName()==null?"":image.getSignName());
//                    }
//                    startActivityForResult(it, 10);
////                        Bitmap bmDialog = BitmapFactory.decodeFile(image.getImagePath());
////                        BitmapDrawable bdDialog = new BitmapDrawable(bmDialog);
////                        new SweetAlertDialog(ShowPictureAndTextActivity.this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
////                                .setTitleText(image.getImageDescribe())
////                                .setContentText("")
////                                .setCustomImage(bdDialog)
////                                .setCancelText(getString(R.string.dialog_delete))
////                                .setConfirmText(getString(R.string.dialog_ok))
////                                .showCancelButton(true)
////                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                                    @Override
////                                    public void onClick(final SweetAlertDialog sDialog) {
////                                        if(FileOperate.delPicFile(image.getImagePath())){
////                                            if("13702".equals(image.getImageType())){
////                                                claimImageAction.delImageListByReportNoAndSubTypeAndInjuryName(reportNo, image.getImageSubtype(),image.getSignName());
////                                            }else {
////                                                claimImageAction.delImageListByReportNoAndSubType(reportNo, image.getImageSubtype());
////                                            }
////                                            loadPicture();
////                                        }
////                                        sDialog.dismiss();
////
////                                    }
////                                })
////                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                                    @Override
////                                    public void onClick(final SweetAlertDialog sDialog) {
////                                        sDialog.dismiss();
////                                    }
////                                })
////                                .show();
//                }else{
//                    showPopWindow(image, 0.25f);
////                        Intent it = new Intent(ShowPictureAndTextActivity.this,TakePicActivity.class);
////                        it.putExtra("ReportNo",reportNo);
////                        it.putExtra("ImageName", image.getImageName());
////                        it.putExtra("ImageType", image.getImageType());
////                        it.putExtra("ImageSubtype", image.getImageSubtype());
////                        //sign存人名或车牌
////                        if("13702".equals(pictureType)){//人伤要根据人名
////                            it.putExtra("SignName",injuryName==null?"":injuryName);
////                        }else if("1370631".equals(pictureType)){//三者要根据车牌号
////                            it.putExtra("SignName",plateNo==null?"":plateNo);
////                        }else{
////                            it.putExtra("SignName",image.getSignName()==null?"":image.getSignName());
////                        }
////                        startActivityForResult(it, 10);
//                }
//            }
//        });
        if(PhotoClaimUtil.YES.equals(image.getImageUpload())){
            uploadImage.setBackgroundResource(R.mipmap.photo_check_box_selected);
        }else{
            uploadImage.setBackgroundResource(R.mipmap.photo_check_box_unchecked);
        }
        picDescText.setText(image.getImageDescribe());
        String pictureType = image.getImageType();
        if("1370611".equals(pictureType)||"1370621".equals(pictureType)||"1370641".equals(pictureType)){
            //查勘、标的、物损   不用显示图片说明
            picDescText.setVisibility(View.GONE);
        }

        layout.setId(position);
        return layout;
    }
}