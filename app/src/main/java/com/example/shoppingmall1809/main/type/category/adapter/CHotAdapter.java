package com.example.shoppingmall1809.main.type.category.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.framework.view.BaseRVAdapter;
import com.example.framework.view.ShopmallGlide;
import com.example.net.model.CategoryBean;
import com.example.shoppingmall1809.R;

import java.util.List;

public class CHotAdapter extends BaseRVAdapter<CategoryBean.ResultBean.HotProductListBean> {
    public CHotAdapter(List<CategoryBean.ResultBean.HotProductListBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.type_item_hot_item;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, CategoryBean.ResultBean.HotProductListBean itemData) {
        ImageView figure = holder.getView(R.id.figure);
        TextView coverPrice = holder.getView(R.id.name);
        ShopmallGlide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getFigure()).into(figure);
        coverPrice.setText("￥"+itemData.getCover_price());
    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
