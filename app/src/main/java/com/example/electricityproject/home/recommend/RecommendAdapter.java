package com.example.electricityproject.home.recommend;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.adapter.BaseAdapter;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;

public class RecommendAdapter extends BaseAdapter<HomeBean.ResultBean.RecommendInfoBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_recommend_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, HomeBean.ResultBean.RecommendInfoBean itemData) {
        ImageView img = baseViewHolder.getView(R.id.recommend_img);
        TextView name = baseViewHolder.getView(R.id.recommend_name);
        TextView price = baseViewHolder.getView(R.id.recommend_price);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getFigure()).into(img);
        name.setText(itemData.getName()+"");
        price.setText("￥"+itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
