package com.hl.core.lib.adapter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describe: recycleView adapter 基类
 * @Package: com.hl.core.lib.adapter
 * @Author: liyu
 * @Date: 2018/1/5 15:55
 * @Copyright: hl
 */
public abstract class BaseVMAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public Context mContext;
    public List<T> mList; // 数据源
    public LayoutInflater inflater;
    protected Object ItemPresenter;
    protected BaseObservable observable;
    public BaseVMAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateVH(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH vh, int position) {
        onBindVH(vh, position);
    }

    public void setObservable(BaseObservable observable){
        this.observable = observable;
    }
    protected BaseObservable getObservable(){
        return observable;
    }

    /**
     * 创建 View Holder
     *
     * @param parent   parent
     * @param viewType item type
     * @return view holder
     */
    public abstract VH onCreateVH(ViewGroup parent, int viewType);

    /**
     * 绑定 View Holder
     *
     * @param vh       view holder
     * @param position position
     */
    public abstract void onBindVH(VH vh, int position);

    /**
     * 清除数据
     */
    public void clearData(){
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 刷新数据
     *
     * @param data 数据源
     */
    public void refreshData(List<T> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getData(){
        return mList;
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     *
     * @param data 加载的新数据
     */
    public void loadMoreData(List<T> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public Object getItemPresenter() {
        return ItemPresenter;
    }

    /**
     * 用于设置Item的事件Presenter
     *
     * @param itemPresenter
     * @return
     */
    public BaseVMAdapter setItemPresenter(Object itemPresenter) {
        ItemPresenter = itemPresenter;
        return this;
    }

}
