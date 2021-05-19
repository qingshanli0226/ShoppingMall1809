package com.shoppingmall.main.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Variable;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.HomeBean;

public class HotAdapter extends BaseRvAdapter<HomeBean.ResultBean.HotInfoBean> {
    private ImageView hotRvImg;
    private TextView hotRvName;
    private TextView hotRvCoverPrice;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_hot_rv_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.HotInfoBean itemData) {
        hotRvImg = (ImageView) holder.getView(R.id.hotRvImg);
        hotRvName = (TextView) holder.getView(R.id.hotRvName);
        hotRvCoverPrice = (TextView) holder.getView(R.id.hotRvCoverPrice);
        Glide.with(hotRvImg.getContext()).load(Variable.IMG_HTTPS+itemData.getFigure()).into(hotRvImg);
        hotRvName.setText("" + itemData.getName());
        hotRvCoverPrice.setText("￥"+itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
