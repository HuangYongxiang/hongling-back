package com.hl.photo.ui.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


import com.hl.photo.R;
import com.hl.photo.business.entity.ImageFloder;

import java.util.List;


/**
 * Created by Liyu on 2017/1/22.
 */

public class ListImageDirPopupWindow extends BasePopupWindowForListView<ImageFloder>
{
    ListView mListDir;

    public ListImageDirPopupWindow(int width, int height, List<ImageFloder> datas, View convertView)
    {
        super(convertView, width, height, true, datas);
    }

    @Override
    public void initViews()
    {
        mListDir = (ListView) findViewById(R.id.photo_local_list_dir);
        HolderViewAdapter adapter = new HolderViewAdapter(context, mDatas,ListImageDirPopupWindowHView.class);
        mListDir.setAdapter(adapter);
    }

    public interface OnImageDirSelected
    {
        void selected(ImageFloder floder);
    }

    private OnImageDirSelected mImageDirSelected;

    public void setOnImageDirSelected(OnImageDirSelected mImageDirSelected)
    {
        this.mImageDirSelected = mImageDirSelected;
    }

    @Override
    public void initEvents()
    {
        mListDir.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                if (mImageDirSelected != null)
                {
                    mImageDirSelected.selected(mDatas.get(position));
                }
            }
        });
    }

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    protected void beforeInitWeNeedSomeParams(Object... params)
    {
        // TODO Auto-generated method stub
    }

}
