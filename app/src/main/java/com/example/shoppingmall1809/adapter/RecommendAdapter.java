package com.example.shoppingmall1809.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;

import java.util.List;

public class RecommendAdapter extends BaseRVAdapter<HoemBean.ResultBean.RecommendInfoBean> {

    public RecommendAdapter(List<HoemBean.ResultBean.RecommendInfoBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.home_item_recommend_item;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, HoemBean.ResultBean.RecommendInfoBean itemData) {
        ImageView figure = holder.getView(R.id.figure);
        TextView name = holder.getView(R.id.name);
        TextView coverPrice = holder.getView(R.id.cover_price);

        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getFigure()).placeholder(R.drawable.new_img_loading_1).into(figure);
        coverPrice.setText("￥"+itemData.getCover_price());
        name.setText(itemData.getName());

    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
