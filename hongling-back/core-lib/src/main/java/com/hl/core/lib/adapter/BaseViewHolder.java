package com.hl.core.lib.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * @Describe: recycleView viewHolder 基类
 * @Package: com.hl.core.lib.adapter
 * @Author: liyu
 * @Date: 2018/1/5 15:47
 * @Copyright: hl
 */
public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    /**
     * ViewDataBinding
     */
    private B mBinding;


    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    /**
     * @return viewDataBinding
     */
    public B getBinding() {
        return mBinding;
    }

}
