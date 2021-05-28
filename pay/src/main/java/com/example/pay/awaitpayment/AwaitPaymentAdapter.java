package com.example.pay.awaitpayment;

import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.AwaitPaymentBean;
import com.example.pay.R;

import java.text.SimpleDateFormat;

public class AwaitPaymentAdapter extends BaseRvAdapter<AwaitPaymentBean.ResultBean> {

    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_awaitpayment_child;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, AwaitPaymentBean.ResultBean itemView) {
        TextView date = holder.getView(R.id.payment_date);
        TextView price = holder.getView(R.id.payment_price);
        TextView tradeNo = holder.getView(R.id.payment_tradeNo);
        String format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(Long.parseLong(itemView.getTime()));
        date.setText(format);
        price.setText("￥"+itemView.getTotalPrice());
        tradeNo.setText(itemView.getTradeNo());
    }


}
