package com.example.electricityproject.home.message;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adapter.BaseAdapter;
import com.example.electricityproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageAdapter extends BaseAdapter {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, Object itemData) {

    }

//    @Override
//    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, MessageTable itemData) {
//
//        TextView pay_status = baseViewHolder.getView(R.id.pay_status);
//        pay_status.setText(itemData.getPayMessage());
//        TextView pay_time = baseViewHolder.getView(R.id.pay_time);
//        pay_time.setText(""+getTime(itemData.getTime()));
//        TextView message_status = baseViewHolder.getView(R.id.message_status);
//
//        boolean isShow = itemData.getIsShow();
//        if (isShow){
//            message_status.setVisibility(View.GONE);
//        }else {
//            message_status.setVisibility(View.VISIBLE);
//        }
//
//    }

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

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
