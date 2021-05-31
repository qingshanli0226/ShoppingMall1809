package com.example.shoppingmall1809.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.framework.db.MessageTable;
import com.example.framework.view.BaseRVAdapter;
import com.example.shoppingmall1809.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageAdapter extends BaseRVAdapter<MessageTable> {
    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, MessageTable itemData) {
        TextView message = holder.getView(R.id.message);
        TextView newText = holder.getView(R.id.new_text);
        TextView messageTime = holder.getView(R.id.message_time);

        message.setText(itemData.getMessage());
        String format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(Long.parseLong(itemData.getTime())));
        messageTime.setText(format);

        if (itemData.getIsRead()){
            newText.setVisibility(View.VISIBLE);
        }else {
            newText.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
