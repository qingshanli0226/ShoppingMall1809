package com.example.shoppingmall1809.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;

import java.util.List;

public class SeckilAdapter extends BaseRVAdapter<HoemBean.ResultBean.SeckillInfoBean.ListBean> {
    public SeckilAdapter(List<HoemBean.ResultBean.SeckillInfoBean.ListBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.home_item_seckil_item;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, HoemBean.ResultBean.SeckillInfoBean.ListBean itemData) {
        ImageView figure = holder.getView(R.id.figure);
        TextView coverPrice = holder.getView(R.id.cover_price);
        TextView originPrice = holder.getView(R.id.origin_price);
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getFigure()).placeholder(R.drawable.new_img_loading_1).into(figure);
        coverPrice.setText("￥"+itemData.getCover_price());
        originPrice.setText("￥"+itemData.getOrigin_price());
        originPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
