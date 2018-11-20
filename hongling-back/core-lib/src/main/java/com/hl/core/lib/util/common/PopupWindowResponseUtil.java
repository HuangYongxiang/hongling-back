package com.hl.core.lib.util.common;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hl.core.lib.adapter.DividerItemDecoration;
import com.hl.core.lib.adapter.PopWindowItemAdapter;
import com.hl.core.lib.bean.TypeItem;

import java.util.List;

/**
 * Created by liyu on 2017/4/7.
 */

public class PopupWindowResponseUtil {

    public interface PopupWindowResponseCallBack {
        void popupWindowCancle();
        void popupWindowResponse(TypeItem typeItem, final String flag, List<? extends Object> data);
    }

    /**
     *
     * @param context
     * @param popView
     * @param title
     * @param popList  数据源
     * @param flag
     * @param data
     * @param popupWindowResponseCallBack
     */
    public static void bindPopWindow(Context context ,
                                     View popView,
                                     String title,
                                     int titleIds,
                                     int recycleViewIds,
                                     int cancelIds,
                                     final List<TypeItem> popList,
                                     final String flag ,
                                     final List<? extends Object> data,
                                     final PopupWindowResponseCallBack popupWindowResponseCallBack) {
        TextView titleName_tv = (TextView) popView.findViewById(titleIds);
        RecyclerView recyclerView = (RecyclerView) popView.findViewById(recycleViewIds);
        TextView cancle_tv = (TextView) popView.findViewById(cancelIds);
        titleName_tv.setText(title);
        cancle_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindowResponseCallBack != null){
                    popupWindowResponseCallBack.popupWindowCancle();
                }
            }
        });

        PopWindowItemAdapter itemAdapter = new PopWindowItemAdapter();
        itemAdapter.addAll(popList);
        recyclerView.setAdapter(itemAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST, 0));
        itemAdapter.setOnItemClickListener((parent, position) -> {
            TypeItem typeItem = popList.get(position);
            if(popupWindowResponseCallBack != null){
                popupWindowResponseCallBack.popupWindowResponse(typeItem,flag,data);
            }
        });

    }
}
