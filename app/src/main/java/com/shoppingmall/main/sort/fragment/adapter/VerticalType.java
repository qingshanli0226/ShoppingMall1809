package com.shoppingmall.main.sort.fragment.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.GoodsBean;

public class VerticalType extends BaseRvAdapter<GoodsBean.ResultBean.ChildBean> {
    private ImageView verticalRvImg;
    private TextView verticalRvText;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_vertical_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, GoodsBean.ResultBean.ChildBean itemData) {
        verticalRvImg = (ImageView) holder.getView(R.id.verticalRvImg);
        verticalRvText = (TextView) holder.getView(R.id.verticalRvText);
        Glide.with(verticalRvImg.getContext()).load(Constants.IMG_HTTPS+itemData.getPic()).into(verticalRvImg);
        verticalRvText.setText(""+itemData.getName());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
