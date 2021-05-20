package com.example.electricityproject.home;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;

public class HotAdapter extends BaseAdapter<HomeBean.ResultBean.HotInfoBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_hot_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, HomeBean.ResultBean.HotInfoBean itemData) {
        ImageView img = baseViewHolder.getView(R.id.hot_img);
        TextView name = baseViewHolder.getView(R.id.hot_name);
        TextView price = baseViewHolder.getView(R.id.hot_price);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getFigure()).into(img);
        name.setText(itemData.getName());
        price.setText("￥"+itemData.getCover_price()+"");
    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
