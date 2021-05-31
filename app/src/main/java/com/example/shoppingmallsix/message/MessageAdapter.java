package com.example.shoppingmallsix.message;

import android.util.TimeUtils;
import android.view.View;
import android.widget.TextView;

import com.example.commened.TimeUtil;
import com.example.framework.BaseRvAdapter;
import com.example.framework.greendao.CacheMessage;
import com.example.shoppingmallsix.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends BaseRvAdapter<CacheMessage> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_message;
    }

    public void updateData(List<CacheMessage> messageBeans) {
        setDataList(messageBeans);
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, CacheMessage itemData) {
        TextView titleItemMessage = holder.getView(R.id.title_item_message);
        TextView isnewItemMessage = holder.getView(R.id.isnew_item_message);
        TextView timeItemMessage = holder.getView(R.id.time_item_message);
        titleItemMessage.setText(itemData.getMessage());
        if (itemData.getIsRead()) {
            isnewItemMessage.setVisibility(View.VISIBLE);
        } else {
            isnewItemMessage.setVisibility(View.GONE);
        }
        timeItemMessage.setText(itemData.getTime());
        String format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date(Long.parseLong(itemData.getTime())));
        timeItemMessage.setText(format);
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
