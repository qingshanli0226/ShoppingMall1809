package com.example.shoppingcar.user.findforpay.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.FindForBean;
import com.example.shoppingcar.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FindForPayAdapter extends BaseRVAdapter<FindForBean.ResultBean> {
    public FindForPayAdapter(List<FindForBean.ResultBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.shop_item_findforpay;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, FindForBean.ResultBean itemData) {
        TextView time = holder.itemView.findViewById(R.id.shop_time);
        TextView pirce = holder.itemView.findViewById(R.id.shop_totalPrice);
        TextView tradeNo = holder.itemView.findViewById(R.id.shop_tradeNo);
        String timeData = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(Long.parseLong(itemData.getTime())));
        time.setText(timeData);
        pirce.setText("￥"+itemData.getTotalPrice());
        tradeNo.setText(itemData.getTradeNo());
    }


    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
