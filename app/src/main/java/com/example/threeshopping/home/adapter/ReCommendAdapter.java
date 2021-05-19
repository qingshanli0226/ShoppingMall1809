package com.example.threeshopping.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;

import org.w3c.dom.Text;

public class ReCommendAdapter extends BaseRvAdapter<HomeBean.ResultBean.RecommendInfoBean> {
    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_recommend_child_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.RecommendInfoBean itemView) {
        ImageView reImg = holder.getView(R.id.reImg);
        TextView reTitle = holder.getView(R.id.reTitle);
        TextView rePrice = holder.getView(R.id.rePrice);
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemView.getFigure()).into(reImg);
        reTitle.setText(itemView.getName());
        rePrice.setText(itemView.getCover_price());
    }
}
