package com.example.shoppingmallsix.message;

import android.util.TimeUtils;
import android.view.View;
import android.widget.TextView;

import com.example.commened.TimeUtil;
import com.example.framework.BaseRvAdapter;
import com.example.framework.greendao.CacheMessage;
import com.example.shoppingmallsix.R;

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
        TextView msgItemMessage = holder.getView(R.id.msg_item_message);
        TextView timeItemMessage = holder.getView(R.id.time_item_message);
        titleItemMessage.setText(itemData.getTitle());
        if (itemData.getIsNew()) {
            isnewItemMessage.setVisibility(View.VISIBLE);
        } else {
            isnewItemMessage.setVisibility(View.GONE);
        }
        msgItemMessage.setText(itemData.getMsg());
        timeItemMessage.setText(TimeUtil.stampToDate(itemData.getTime()));
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
