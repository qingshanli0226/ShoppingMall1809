package com.shoppingmall.order.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.glide.ShopMallGlide;
import com.shoppingmall.net.Constants;
import com.shoppingmall.net.bean.ShopCarBean;

public class OrderAdapter extends BaseRvAdapter<ShopCarBean.ResultBean> {
    private TextView orderProductName;
    private TextView orderProductPrice;
    private TextView orderProductNum;
    private ImageView orderProductImg;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_order_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ShopCarBean.ResultBean itemData) {
        orderProductName = (TextView) holder.getView(R.id.orderProductName);
        orderProductPrice = (TextView) holder.getView(R.id.orderProductPrice);
        orderProductNum = (TextView) holder.getView(R.id.orderProductNum);
        orderProductImg = (ImageView) holder.getView(R.id.orderProductImg);

        orderProductName.setText("" + itemData.getProductName());
        orderProductPrice.setText("$" + itemData.getProductPrice());
        orderProductNum.setText("" + itemData.getProductNum());
        ShopMallGlide.with(orderProductImg.getContext()).load(Constants.IMG_HTTPS+itemData.getUrl()).into(orderProductImg);
    }


    @Override
    public int getRootViewType(int position) {
        return 0;
    }


}
