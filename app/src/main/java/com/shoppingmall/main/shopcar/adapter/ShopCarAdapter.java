package com.shoppingmall.main.shopcar.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.ShopCarBean;

public class ShopCarAdapter extends BaseRvAdapter<ShopCarBean.ResultBean> {
    private ImageView shopCarCheck;
    private ImageView shopCarImg;
    private TextView shopCarTxt;
    private TextView shopCarPrice;
    private TextView shopCarRemoveNum;
    private TextView shopCarNum;
    private TextView shopCarAddNum;

    private IRecyclerChildItemClickListener iRecyclerChildItemClickListener;


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_shop_car_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ShopCarBean.ResultBean itemData) {
        shopCarCheck = (ImageView) holder.getView(R.id.shopCarCheck);
        shopCarImg = (ImageView) holder.getView(R.id.shopCar_img);
        shopCarTxt = (TextView) holder.getView(R.id.shopCar_txt);
        shopCarPrice = (TextView) holder.getView(R.id.shopCar_price);
        shopCarRemoveNum = (TextView) holder.getView(R.id.shopCar_removeNum);
        shopCarNum = (TextView) holder.getView(R.id.shopCar_num);
        shopCarAddNum = (TextView) holder.getView(R.id.shopCar_addNum);

        Glide.with(shopCarImg.getContext()).load("https://browser9.qhimg.com/bdr/__85/t010448c46c1ecf7cab.jpg").into(shopCarImg);
        shopCarTxt.setText(""+itemData.getProductName());
        shopCarPrice.setText(""+itemData.getProductPrice());
        shopCarNum.setText(""+itemData.getProductNum());

        shopCarAddNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iRecyclerChildItemClickListener!=null){
                    iRecyclerChildItemClickListener.onShopCarAddNumItemClick(position,view);
                }
            }
        });
        shopCarRemoveNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iRecyclerChildItemClickListener!=null){
                    iRecyclerChildItemClickListener.onShopCarRemoveNumItemClick(position,view);
                }
            }
        });
        shopCarCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iRecyclerChildItemClickListener!=null){
                    iRecyclerChildItemClickListener.onIsSelectItemClick(position,view);
                }
            }
        });
        if (itemData.isProductSelected()) {
            shopCarCheck.setImageResource(R.drawable.checkbox_selected);
        } else{
            shopCarCheck.setImageResource(R.drawable.checkbox_unselected);
        }
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

    public void setRecyclerChildItemClickListener(IRecyclerChildItemClickListener listener) {
        this.iRecyclerChildItemClickListener = listener;
    }

    public interface IRecyclerChildItemClickListener{
        void onShopCarAddNumItemClick(int position,View v);
        void onShopCarRemoveNumItemClick(int position,View v);
        void onIsSelectItemClick(int position, View view);
    }
}
