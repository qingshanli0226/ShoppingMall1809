package com.example.pay.orderinfo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.net.bean.CartBean;
import com.example.net.bean.PayBean;
import com.example.pay.R;

public class OrderAdapter extends BaseRvAdapter<PayBean.BodyBean> {


    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_order_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, PayBean.BodyBean itemView) {
        ImageView itemOrderImg = holder.getView(R.id.itemOrderImg);
        TextView itemOrderTitle = holder.getView(R.id.itemOrderTitle);
        TextView itmeOrderPrice = holder.getView(R.id.itmeOrderPrice);
        TextView itemOrderNum = holder.getView(R.id.itemOrderNum);
        ShopmallGlide.getInstance().with(holder.itemView.getContext()).load(itemView.getProductUrl()).into(itemOrderImg);
        itemOrderTitle.setText(itemView.getProductName());
        int num = Integer.parseInt(itemView.getProductNum());
        float price = Float.parseFloat(itemView.getProductPrice());
        itmeOrderPrice.setText(num*price+"");
        itemOrderNum.setText(itemView.getProductNum()+"");

    }
}
