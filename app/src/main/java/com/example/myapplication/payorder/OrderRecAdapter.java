package com.example.myapplication.payorder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.ShopmallGlide;
import com.example.myapplication.R;
import com.example.net.bean.ShoppingCartBean;

public class OrderRecAdapter extends BaseRecyclerViewAdapter<ShoppingCartBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.order_rec_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ShoppingCartBean.ResultBean itemData) {
        ImageView image = holder.getView(R.id.orderItemImage);
        ShopmallGlide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getUrl()).into(image);
        TextView title = holder.getView(R.id.orderItemTitle);
        title.setText(itemData.getProductName());
        TextView num = holder.getView(R.id.orderItemNum);
        num.setText(itemData.getProductNum());
        TextView price = holder.getView(R.id.orderItemPrice);
        price.setText(itemData.getProductPrice()+"");
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
