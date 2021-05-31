package com.shoppingmall.pay.awitpayment;

import android.widget.TextView;

import com.fiance.commom.time.TimeUtil;
import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.FindForPayBean;

public class AwaitPaymentAdapter extends BaseRvAdapter<FindForPayBean.ResultBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_find_order;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, FindForPayBean.ResultBean itemData) {
        TextView time = holder.getView(R.id.time_find_order);
        TextView totalprice = holder.getView(R.id.totalprice_find_order);
        TextView tradeno = holder.getView(R.id.tradeno_find_order);
        totalprice.setText("¥"+itemData.getTotalPrice());
        tradeno.setText(itemData.getTradeNo()+"");
        time.setText(TimeUtil.stampToDate(Long.parseLong(itemData.getTime())));
    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
