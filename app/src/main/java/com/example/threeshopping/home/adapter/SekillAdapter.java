package com.example.threeshopping.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;

public class SekillAdapter extends BaseRvAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean> {
    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_seckill_child_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.SeckillInfoBean.ListBean itemView) {
        ImageView reImg = holder.getView(R.id.sekillImg);
        TextView reTitle = holder.getView(R.id.sekillOld);
        TextView rePrice = holder.getView(R.id.sekillNew);
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemView.getFigure()).into(reImg);
        reTitle.setText(itemView.getOrigin_price());
        rePrice.setText(itemView.getCover_price());
    }


}
