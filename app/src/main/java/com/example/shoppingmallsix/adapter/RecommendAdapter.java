package com.example.shoppingmallsix.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;

public class RecommendAdapter extends BaseRvAdapter<HomeBean.ResultBean.RecommendInfoBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_recommend_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.RecommendInfoBean itemData) {
        ShopmallGlide.with(holder.itemView.getContext())
                .load(Constants.BASE_URl_IMAGE + itemData.getFigure())
                .into((ImageView) holder.getView(R.id.recommendImage));

        TextView view = holder.getView(R.id.recommendTitle);
        view.setText(itemData.getName());
        TextView view1 = holder.getView(R.id.recommendPrice);
        view1.setText(itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
