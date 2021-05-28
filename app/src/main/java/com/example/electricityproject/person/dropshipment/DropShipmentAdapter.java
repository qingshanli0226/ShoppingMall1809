package com.example.electricityproject.person.dropshipment;

import android.util.Log;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.common.bean.FindForSendBean;
import com.example.electricityproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DropShipmentAdapter extends BaseAdapter<FindForSendBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_dropshipment;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, FindForSendBean.ResultBean itemData) {
        Log.i("zx", "displayViewHolder: "+itemData);
        TextView year = baseViewHolder.getView(R.id.drop_year);
        TextView order = baseViewHolder.getView(R.id.drop_order);
        TextView price = baseViewHolder.getView(R.id.drop_price);

        Long time = Long.parseLong(itemData.getTime());
        year.setText(contDown(time)+"");
        order.setText(itemData.getTradeNo()+"");
        price.setText("￥"+itemData.getTotalPrice());


    }




    @Override
    public int getRootViewType(int position) {

        return 0;

    }

    public String contDown(long time){
        String str="yyyy-MM--dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        String format = simpleDateFormat.format(new Date(time));
        return format;
    }
}
