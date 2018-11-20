package com.hl.core.lib.adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * @Describe:
 * @Package: com.hl.core.lib.adapter
 * @Author: liyu
 * @Date: 2018/1/9 10:12
 * @Copyright: hl
 */
public class ViewBindingAdter {

    @BindingAdapter("layoutManager")
    public static void setLayoutManager(RecyclerView recyclerView, LayoutManagers.LayoutManagerFactory layoutManagerFactory) {
        recyclerView.setLayoutManager(layoutManagerFactory.create(recyclerView));
    }
}
