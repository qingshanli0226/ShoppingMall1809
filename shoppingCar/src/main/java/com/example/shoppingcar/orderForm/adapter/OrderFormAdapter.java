package com.example.shoppingcar.orderForm.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.view.BaseRVAdapter;
import com.example.framework.view.ShopmallGlide;
import com.example.net.model.ShoppingTrolleyBean;
import com.example.shoppingcar.R;

import java.util.List;

public class OrderFormAdapter extends BaseRVAdapter<ShoppingTrolleyBean.ResultBean> {
    public OrderFormAdapter(List<ShoppingTrolleyBean.ResultBean> datalist) {
        super(datalist);
    }

    public OrderFormAdapter() {
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_orderform;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, ShoppingTrolleyBean.ResultBean itemData) {
        ImageView imageView = holder.getView(R.id.shoppingTrolley_img);
        TextView textView = holder.getView(R.id.shoppingTrolley_text);
        TextView price = holder.getView(R.id.shoppingTrolley_price);
        TextView num = holder.getView(R.id.shoppingTrolley_num);

        ShopmallGlide.with(holder.itemView.getContext())
                .load(itemData.getUrl())
                .into(imageView);
        textView.setText(itemData.getProductName());
        float priceNum=Float.parseFloat(itemData.getProductPrice()+"")*Integer.parseInt(itemData.getProductNum());
        price.setText("￥"+priceNum);
        num.setText("数量"+itemData.getProductNum());

    }

    @Override
    protected int getRootViewType(int position) {
        return position;
    }


}
