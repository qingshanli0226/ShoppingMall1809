package com.example.shoppingcar.shoppingtrolley.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commom.Constants;
import com.example.framework.view.BaseRVAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.R;

import java.util.List;

public class ShoppingCarAdapter extends BaseRVAdapter<ShoppingTrolleyBean.ResultBean> {
    public ShoppingCarAdapter(List<ShoppingTrolleyBean.ResultBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_shoppingtrolley;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, ShoppingTrolleyBean.ResultBean itemData) {
        ImageView imageView = holder.getView(R.id.shoppingTrolley_img);
        TextView textView = holder.getView(R.id.shoppingTrolley_text);
        TextView price = holder.getView(R.id.shoppingTrolley_price);
        ImageView sub = holder.getView(R.id.shoppingTrolley_sub);
        ImageView add = holder.getView(R.id.shoppingTrolley_add);
        TextView num = holder.getView(R.id.shoppingTrolley_num);

        ShopmallGlide.with(holder.itemView.getContext())
                .load(itemData.getUrl())
                .into(imageView);
        textView.setText(itemData.getProductName());
        price.setText("￥"+itemData.getProductPrice().toString());
        num.setText(itemData.getProductNum());

        CheckBox checkBox = holder.getView(R.id.shoppingTrolley_check);
        checkBox.setChecked(itemData.isProductSelected());
        if (itemData.isProductSelected()) {
            checkBox.setBackgroundResource(R.drawable.checkbox_selected);
        }else {
            checkBox.setBackgroundResource(R.drawable.checkbox_unselected);
        }


        checkBox.setOnClickListener(view -> {
            if (iShopItemListener != null) {
                iShopItemListener.onItemChildClick(position,view);
            }
        });

        sub.setOnClickListener(view -> {
            if (iShopItemListener != null) {
                iShopItemListener.onItemChildClick(position,view);
            }
        });

        add.setOnClickListener(view -> {
            if (iShopItemListener != null) {
                iShopItemListener.onItemChildClick(position,view);
            }
        });

    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }


    public interface IShopItemListener{
        void onItemChildClick(int position,View view);
    }

    private IShopItemListener iShopItemListener;

    public void setShopItemListener(IShopItemListener iShopItemListener) {
        this.iShopItemListener = iShopItemListener;
    }
}
