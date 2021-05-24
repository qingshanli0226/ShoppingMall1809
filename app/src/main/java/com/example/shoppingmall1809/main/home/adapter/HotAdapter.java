package com.example.shoppingmall1809.main.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.framework.view.BaseRVAdapter;
import com.example.framework.view.ShopmallGlide;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;

import java.util.List;

public class HotAdapter extends BaseRVAdapter<HoemBean.ResultBean.HotInfoBean> {
    public HotAdapter(List<HoemBean.ResultBean.HotInfoBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.home_item_hot_item;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, HoemBean.ResultBean.HotInfoBean itemData) {
        ImageView figure = holder.getView(R.id.figure);
        TextView name = holder.getView(R.id.name);
        TextView coverPrice = holder.getView(R.id.cover_price);

        ShopmallGlide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getFigure()).into(figure);
        coverPrice.setText("￥"+itemData.getCover_price());
        name.setText(itemData.getName());
    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
