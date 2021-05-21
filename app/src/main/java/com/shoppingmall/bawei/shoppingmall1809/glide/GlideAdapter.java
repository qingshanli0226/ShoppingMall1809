package com.shoppingmall.bawei.shoppingmall1809.glide;

import android.widget.ImageView;

import com.fiannce.bawei.framework.BaseRvAdapter;
import com.fiannce.bawei.framework.manager.ShopmallGlide;
import com.fiannce.bawei.net.mode.FocusBean;
import com.shoppingmall.bawei.shoppingmall1809.R;

public class GlideAdapter extends BaseRvAdapter<FocusBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.glide_item_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, FocusBean.ResultBean itemData) {
        ShopmallGlide.with(holder.getView(R.id.imageView).getContext()).load(itemData.getCoverImg())
                .into((ImageView) holder.getView(R.id.imageView));
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
