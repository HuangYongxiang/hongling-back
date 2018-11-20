package com.hl.photo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wangliang on 2016/12/30.
 */

public abstract class BaseRecycleAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private int layoutId;
    private List<? extends Object> data;
    public Context context;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean clickFlag = true;//单击事件和长单击事件的屏蔽标识

    /**
     *
     * @param context //上下文
     * @param layoutId  //布局id
     * @param data  //数据源
     */
    public BaseRecycleAdapter(Context context, int layoutId, List<? extends Object> data) {
        this.layoutId = layoutId;
        this.data = data;
        this.context = context;
    }

    public List getDataResources(){
        return data;
    }

    public void clearAllListData(){
        if(data != null){
            data.clear();
            notifyDataSetChanged();
        }
    }

    public void bindListData(List newData){
        if(data != null){
            data.clear();
            data.addAll(newData);
            notifyDataSetChanged();
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layoutId, parent, false);
        final BaseViewHolder holder = new BaseViewHolder(v, context);
        //单击事件回调
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickFlag) {
                    if(onItemClickListner != null){
                        onItemClickListner.onItemClickListner(v,holder.getLayoutPosition());
                    }
                }
                clickFlag = true;
            }
        });
        //单击长按事件回调
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(onItemLongClickListner != null){
                    onItemLongClickListner.onItemLongClickListner(v,holder.getLayoutPosition());
                }
                clickFlag = false;
                return false;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, data!=null?data.get(position):null,position);
    }

//    protected abstract <T extends BaseBean> void convert(BaseViewHolder holder, T bean);
    protected abstract <T extends Object> void convert(BaseViewHolder holder, T bean,int positon);

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public interface OnItemClickListner {
        void onItemClickListner(View v, int position);
    }

    public interface OnItemLongClickListner {
        void onItemLongClickListner(View v, int position);
    }
}
