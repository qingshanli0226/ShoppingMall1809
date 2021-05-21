package com.shoppingmall.main.sort.fragment.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Variable;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.HomeBean;

public class HorizontalTypeAdapter extends BaseRvAdapter<HomeBean.ResultBean.RecommendInfoBean> {
    private ImageView horizontalRvImg;
    private TextView horizontalRvName;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_horizontal_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.RecommendInfoBean itemData) {
        horizontalRvImg = (ImageView) holder.getView(R.id.horizontalRvImg);
        horizontalRvName = (TextView) holder.getView(R.id.horizontalRvName);
        Glide.with(horizontalRvImg.getContext()).load(Variable.IMG_HTTPS+itemData.getFigure()).into(horizontalRvImg);
        horizontalRvName.setText(""+itemData.getName());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
