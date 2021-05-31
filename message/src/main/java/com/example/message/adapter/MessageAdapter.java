package com.example.message.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.message.R;
import com.fiannce.sql.bean.MessageBean;

import java.text.SimpleDateFormat;

public class MessageAdapter extends BaseRvAdapter<MessageBean> {
    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_message_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, MessageBean itemView) {
        TextView itemMessageTitle = holder.getView(R.id.itemMessageTitle);
        TextView itemMessageContent = holder.getView(R.id.itemMessageContent);
        TextView itemMessageTime = holder.getView(R.id.itemMessageTime);
        TextView itemMessageFlag = holder.getView(R.id.itemMessageFlag);
        int type = itemView.getType();
        if(type == 0){
            itemMessageTitle.setText("支付信息");
        } else{
            itemMessageTitle.setText("其他");
        }

        itemMessageContent.setText(itemView.getMessage());
//        String format = new SimpleDateFormat("yyyy-MM-DD HH-mm-ss").format();

        itemMessageTime.setText(itemView.getMessageTime());
        if(itemView.getIsRead()){
            itemMessageFlag.setVisibility(View.VISIBLE);
        } else{
            itemMessageFlag.setVisibility(View.GONE);
        }


    }
}
