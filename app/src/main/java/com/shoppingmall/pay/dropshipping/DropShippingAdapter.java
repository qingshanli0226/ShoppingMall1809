package com.shoppingmall.pay.dropshipping;

import android.widget.TextView;

import com.fiance.commom.time.TimeUtil;
import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.FindForSendBean;

public class DropShippingAdapter extends BaseRvAdapter<FindForSendBean.ResultBean> {
    private TextView timeDrop;
    private TextView tradenoDrop;
    private TextView totalpriceDrop;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_dropshipping_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, FindForSendBean.ResultBean itemData) {
        timeDrop = (TextView) holder.getView(R.id.time_drop);
        tradenoDrop = (TextView) holder.getView(R.id.tradeno_drop);
        totalpriceDrop = (TextView) holder.getView(R.id.totalprice_drop);

        totalpriceDrop.setText("¥"+itemData.getTotalPrice());
        tradenoDrop.setText(itemData.getTradeNo()+"");
        timeDrop.setText(TimeUtil.stampToDate(Long.parseLong(itemData.getTime())));
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }


}
