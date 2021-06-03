package com.example.myapplication.findforsend;

import android.widget.TextView;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.FindForSendBean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FindSendAdapter extends BaseRecyclerViewAdapter<FindForSendBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {
        return com.example.user.R.layout.send_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, FindForSendBean.ResultBean itemData) {
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
