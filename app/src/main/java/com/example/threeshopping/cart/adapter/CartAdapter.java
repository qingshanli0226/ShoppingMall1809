package com.example.threeshopping.cart.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.net.bean.CartBean;
import com.example.threeshopping.R;

public class CartAdapter extends BaseRvAdapter<CartBean.ResultBean> {

    private ICartItemListener cartItemListener;

    public void setCartItemListener(ICartItemListener cartItemListener) {
        this.cartItemListener = cartItemListener;
    }

    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shopcart_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, CartBean.ResultBean itemView) {
        ImageView shopCartCheck = holder.getView(R.id.shopCartCheck);
        ImageView shopCartImg = holder.getView(R.id.shopCartImg);
        TextView shopCartTitle = holder.getView(R.id.shopCartTitle);
        TextView shopCartPrice = holder.getView(R.id.shopCartPrice);
        ImageView shopCartSub = holder.getView(R.id.shopCartSub);
        TextView shopCartNum = holder.getView(R.id.shopCartNum);
        ImageView shopCartAdd = holder.getView(R.id.shopCartAdd);


        ShopmallGlide.getInstance().with(holder.itemView.getContext()).load(itemView.getUrl()).into(shopCartImg);
        shopCartTitle.setText(itemView.getProductName());
        shopCartPrice.setText(itemView.getProductPrice()+"");
        shopCartNum.setText(itemView.getProductNum());

        shopCartCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItemListener != null) {
                    cartItemListener.onItemChildClick(position,v);
                }
            }
        });
        shopCartSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItemListener != null) {
                    cartItemListener.onItemChildClick(position,v);
                }
            }
        });
        shopCartAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartItemListener != null) {
                    cartItemListener.onItemChildClick(position,v);
                }
            }
        });

        if (itemView.isProductSelected()) {
            shopCartCheck.setImageResource(R.drawable.checkbox_selected);

        } else{
            shopCartCheck.setImageResource(R.drawable.checkbox_unselected);

        }

    }

    public interface ICartItemListener{
        void onItemChildClick(int position,View view);
    }
}
