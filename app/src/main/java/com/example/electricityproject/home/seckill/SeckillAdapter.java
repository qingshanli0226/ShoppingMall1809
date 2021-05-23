package com.example.electricityproject.home.seckill;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.adapter.BaseAdapter;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;

public class SeckillAdapter extends BaseAdapter<HomeBean.ResultBean.SeckillInfoBean.ListBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_seckill_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, HomeBean.ResultBean.SeckillInfoBean.ListBean itemData) {
        ImageView img = baseViewHolder.getView(R.id.seckill_img);
        TextView cost = baseViewHolder.getView(R.id.cost_price);
        TextView now = baseViewHolder.getView(R.id.now_price);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getFigure()).into(img);
        cost.setText("￥"+itemData.getCover_price()+"");
        now.setText("￥"+itemData.getCover_price()+"");
    }


    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
