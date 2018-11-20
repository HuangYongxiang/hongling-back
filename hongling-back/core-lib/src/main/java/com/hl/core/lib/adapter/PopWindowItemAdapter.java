package com.hl.core.lib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hl.core.lib.R;
import com.hl.core.lib.bean.TypeItem;

/**
 * Created by liyu on 2017/1/13.
 */

public class PopWindowItemAdapter extends BaseRecyclerViewAdapter<TypeItem,RecyclerView.ViewHolder> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.core_popwindow_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((MyViewHolder) viewHolder).bind(getItem(position));
        super.onBindViewHolder(viewHolder, position);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            name_tv = (TextView) itemView.findViewById(R.id.item_name_tv);
        }

        void bind(TypeItem typeItem){
            name_tv.setText(typeItem.getValue());
        }

    }


}
