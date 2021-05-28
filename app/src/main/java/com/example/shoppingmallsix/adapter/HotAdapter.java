package com.example.shoppingmallsix.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.net.bean.HomeBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;

public class HotAdapter extends BaseRvAdapter<HomeBean.ResultBean.HotInfoBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_hot_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.HotInfoBean itemData) {
        ShopmallGlide.with(holder.itemView.getContext())
                .load(Constants.BASE_URl_IMAGE + itemData.getFigure())
                .into((ImageView) holder.getView(R.id.hotImage));

        TextView view = holder.getView(R.id.hotTitle);
        view.setText(itemData.getName());
        TextView view1 = holder.getView(R.id.hotPrice);
        view1.setText(itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}

