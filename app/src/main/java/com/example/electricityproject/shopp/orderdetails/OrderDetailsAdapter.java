package com.example.electricityproject.shopp.orderdetails;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.BaseAdapter;
import com.example.common.Constants;
import com.example.common.bean.ShortcartProductBean;
import com.example.electricityproject.R;

public
class OrderDetailsAdapter extends BaseAdapter<ShortcartProductBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {

        return R.layout.item_order;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, ShortcartProductBean.ResultBean itemData) {
        TextView order_title = baseViewHolder.getView(R.id.order_name);
        order_title.setText(""+itemData.getProductName());
        TextView order_money = baseViewHolder.getView(R.id.order_money);
        order_money.setText("￥"+itemData.getProductPrice());
        ImageView order_image = baseViewHolder.getView(R.id.order_image);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemData.getUrl()).into(order_image);
        TextView order_num = baseViewHolder.getView(R.id.order_num);
        order_num.setText("数量:"+itemData.getProductNum());
    }




    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
