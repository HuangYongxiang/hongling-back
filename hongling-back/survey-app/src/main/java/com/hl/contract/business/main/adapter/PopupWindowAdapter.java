package com.hl.contract.business.main.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.hl.core.lib.adapter.BaseVMAdapter;
import com.hl.core.lib.adapter.BaseViewHolder;
import com.hl.contract.R;
import com.hl.contract.BR;
import com.hl.contract.business.main.viewmodel.TypeItemModelView;

/**
 * @Describe:popupWindow 中的公用adapter
 * @Author: liyu
 * @Date: 2018/1/17 17:23
 * @Copyright: hl
 */
public class PopupWindowAdapter extends BaseVMAdapter<TypeItemModelView,BaseViewHolder> {
    public PopupWindowAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.survey_pop_window_adapter_layout, parent, false);
        return new BaseViewHolder(viewDataBinding);

    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.typeItemMV, mList.get(position));
        binding.setVariable(BR.position, position);
        binding.executePendingBindings(); //防止闪烁

    }
}
