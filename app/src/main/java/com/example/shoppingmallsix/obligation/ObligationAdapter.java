package com.example.shoppingmallsix.obligation;

import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.find.FindForSendbean;
import com.example.shoppingmallsix.R;

import java.util.List;

public class ObligationAdapter extends BaseRvAdapter<FindForSendbean.ResultBean> {

    public ObligationAdapter(List<FindForSendbean.ResultBean> resultBeans){
        setDataList(resultBeans);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.ob;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, FindForSendbean.ResultBean itemData) {
        TextView time = holder.getView(R.id.time);
        TextView money = holder.getView(R.id.money);
        TextView ordernum = holder.getView(R.id.ordernum);

        time.setText(itemData.getTime());
        money.setText("$ "+itemData.getTotalPrice().toString());
        ordernum.setText(itemData.getTradeNo());


    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
