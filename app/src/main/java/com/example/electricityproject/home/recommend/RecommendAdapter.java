package com.example.electricityproject.home.recommend;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.common.Constants;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;
import com.example.glide.ShopGlide;

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
        ShopGlide.getInstance().with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getFigure()).init(img);
        name.setText(itemData.getName()+"");
        price.setText("￥"+itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
