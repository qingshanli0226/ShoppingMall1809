package com.shoppingmall.main.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.HomeBean;

public class RecommendAdapter extends BaseRvAdapter<HomeBean.ResultBean.RecommendInfoBean> {
    private ImageView recommendRvImg;
    private TextView recommendRvName;
    private TextView recommendRvCoverPrice;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_recommend_rv_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.RecommendInfoBean itemData) {
        recommendRvImg = (ImageView) holder.getView(R.id.recommendRvImg);
        Glide.with(recommendRvImg.getContext()).load(Constants.IMG_HTTPS+itemData.getFigure()).into(recommendRvImg);
        recommendRvName = (TextView) holder.getView(R.id.recommendRvName);
        recommendRvCoverPrice = (TextView) holder.getView(R.id.recommendRvCoverPrice);
        recommendRvName.setText("" + itemData.getName());
        recommendRvCoverPrice.setText("￥"+itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
