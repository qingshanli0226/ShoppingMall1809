package com.example.electricityproject.person.findforpay;

import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.common.bean.FindForPayBean;
import com.example.electricityproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindForPayAdapter extends BaseAdapter<FindForPayBean.ResultBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_unpaid;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, FindForPayBean.ResultBean itemData) {
        TextView year = baseViewHolder.getView(R.id.unpaid_year);
        TextView order = baseViewHolder.getView(R.id.unpaid_order);
        TextView price = baseViewHolder.getView(R.id.unpaid_price);
        String Unpaidtime = itemData.getTime();
        int time = Integer.parseInt(Unpaidtime);

        year.setText(contDown(time));
        order.setText(itemData.getOrderInfo()+"");
        price.setText("￥"+itemData.getTotalPrice()+"");
    }


    @Override
    public int getRootViewType(int position) {
        return 0;
    }
    public String contDown(int time){
        String str="yyyy-MM--dd HH:mm:ss";
        String format = new SimpleDateFormat(str).format(new Date(time));
        return format;
    }
}
