package com.hl.core.lib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Describe: RecyclerView通用适配器
 * @Package: com.hl.core.lib.adapter
 * @Author: liyu
 * @Date: 2018/1/8 17:49
 * @Copyright: hl
 */
public abstract class BaseRecyclerViewAdapter<E,T extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<T> {

    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
    public View mHeaderView;
    public View mFooterView;

    protected List<E> mList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public BaseRecyclerViewAdapter() {
        if(null == mList)
            mList = new ArrayList<E>();
    }

    public abstract  T onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(T viewHolder, final int position) {
        if (null != mOnItemClickListener) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClickRecycler(v, position);
                }
            });
        }
        if (null != mOnItemLongClickListener) {
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onItemLongClickRecycler(v, position);
                }
            });
        }
    }

    public List<E> getList() {
        return mList;
    }

    public E getItem(int position){
        if(null != mList && mList.size() > 0){
            return mList.get(position);
        }else {
            return null;
        }
    }

    //HeaderView和FooterView的get和set函数
    public View getHeaderView() {
        return mHeaderView;
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }
    public View getFooterView() {
        return mFooterView;
    }
    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount()-1);
    }

    public boolean isNotEmpty(){
        return null != mList && mList.size() > 0;
    }

    public E getFirstItem(){
        return getItem(0);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null){
            return TYPE_NORMAL;
        }
        if (mHeaderView != null && position == 0){
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (mFooterView != null && position == getItemCount()-1){
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if(mHeaderView == null && mFooterView == null){
            return mList.size();
        }else if(mHeaderView == null && mFooterView != null){
            return mList.size() + 1;
        }else if (mHeaderView != null && mFooterView == null){
            return mList.size() + 1;
        }else {
            return mList.size() + 2;
        }
    }

    public void insert(E data, int position){
        mList.add(position, data);
        notifyItemInserted(position);
    }

    public void remove(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void replaceAll(Collection<E> collection){
        mList.clear();
        if(collection != null){
            mList.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public void addAll(Collection<E> collection){
        if(collection != null){
            mList.addAll(collection);
        }
        notifyDataSetChanged();
    }

    public void addItem(E e){
        mList.add(e);
        notifyItemInserted(getPosition(e));
    }

    public int getPosition(E e){
        return mList.indexOf(e);
    }

    public void addAllItem(List<E> list){
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void removeItem(E e){
        mList.remove(e);
        notifyItemRemoved(getPosition(e));
    }

    public void removeAllItem(List<E> list){
        mList.removeAll(list);
        notifyDataSetChanged();
    }

    public List<E> getAllItem(){
        return mList;
    }

    public void clear(){
        mList.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClickRecycler(View parent, int position);
    }

    public interface OnItemLongClickListener {
        public boolean onItemLongClickRecycler(View parent, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }



}
