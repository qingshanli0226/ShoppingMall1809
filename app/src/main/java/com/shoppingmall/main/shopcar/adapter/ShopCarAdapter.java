package com.shoppingmall.main.shopcar.adapter;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.glide.ShopMallGlide;
import com.shoppingmall.net.bean.ShopCarBean;

public class ShopCarAdapter extends BaseRvAdapter<ShopCarBean.ResultBean> {
    private CheckBox isSelect;
    private ImageView shopCarImg;
    private TextView shopCarTxt;
    private TextView shopCarPrice;
    private TextView shopCarRemoveNum;
    private TextView shopCarNum;
    private TextView shopCarAddNum;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shop_car_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ShopCarBean.ResultBean itemData) {
        isSelect = (CheckBox) holder.getView(R.id.is_select);
        shopCarImg = (ImageView) holder.getView(R.id.shopCar_img);
        shopCarTxt = (TextView) holder.getView(R.id.shopCar_txt);
        shopCarPrice = (TextView) holder.getView(R.id.shopCar_price);
        shopCarRemoveNum = (TextView) holder.getView(R.id.shopCar_removeNum);
        shopCarNum = (TextView) holder.getView(R.id.shopCar_num);
        shopCarAddNum = (TextView) holder.getView(R.id.shopCar_addNum);

        Glide.with(shopCarImg.getContext()).load("https://ss.netnr.com/wallpaper").into(shopCarImg);
        shopCarTxt.setText(""+itemData.getProductName());
        shopCarPrice.setText(""+itemData.getProductPrice());
        shopCarNum.setText(""+itemData.getProductNum());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
