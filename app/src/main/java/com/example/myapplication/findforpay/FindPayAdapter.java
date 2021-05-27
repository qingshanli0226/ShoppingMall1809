package com.example.myapplication.findforpay;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.FindForPayBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindPayAdapter extends BaseRecyclerViewAdapter<FindForPayBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_findpay;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, FindForPayBean.ResultBean itemData) {
        TextView textView = holder.getView(R.id.text);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        String format = simpleDateFormat.format(date);
        textView.setText(format+"");
        TextView name = holder.getView(R.id.name);
        name.setText(itemData.getTradeNo()+"");
        TextView money = holder.getView(R.id.money);
        money.setText("$"+itemData.getTotalPrice());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
