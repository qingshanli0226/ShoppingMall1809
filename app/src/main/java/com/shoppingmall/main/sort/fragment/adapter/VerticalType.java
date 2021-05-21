package com.shoppingmall.main.sort.fragment.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Variable;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.HomeBean;

public class VerticalType extends BaseRvAdapter<HomeBean.ResultBean.HotInfoBean> {
    private ImageView verticalRvImg;
    private TextView verticalRvText;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_vertical_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.HotInfoBean itemData) {
        verticalRvImg = (ImageView) holder.getView(R.id.verticalRvImg);
        verticalRvText = (TextView) holder.getView(R.id.verticalRvText);
        Glide.with(verticalRvImg.getContext()).load(Variable.IMG_HTTPS+itemData.getFigure()).into(verticalRvImg);
        verticalRvText.setText(""+itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
