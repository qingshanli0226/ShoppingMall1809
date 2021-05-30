package com.example.electricityproject.home.message;

import android.view.View;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.electricityproject.R;
import com.example.electricityproject.db.MessageTable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageAdapter extends BaseAdapter<MessageTable> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, MessageTable itemData) {

        TextView pay_status = baseViewHolder.getView(R.id.pay_status);
        pay_status.setText(itemData.getPayMessage());
        TextView pay_time = baseViewHolder.getView(R.id.pay_time);
        pay_time.setText(""+getTime(itemData.getTime()));
        TextView message_status = baseViewHolder.getView(R.id.message_status);

        boolean isShow = itemData.getIsShow();
        if (isShow){
            message_status.setVisibility(View.GONE);
        }else {
            message_status.setVisibility(View.VISIBLE);
        }

    }

    public String getTime(long time){
        String str="yyyy-MM--dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        String format = simpleDateFormat.format(new Date(time));
        return format;

    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
