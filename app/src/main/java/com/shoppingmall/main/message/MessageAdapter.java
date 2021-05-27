package com.shoppingmall.main.message;

import android.view.View;
import android.widget.TextView;

import com.fiance.commom.time.TimeUtil;
import com.shoppingmall.R;
import com.shoppingmall.detail.messagedao.MessageBean;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.main.bean.CustomBean;

public class MessageAdapter extends BaseRvAdapter<MessageBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.message_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, MessageBean itemData) {
        TextView titleItemMessage = holder.getView(R.id.title_item_message);
        TextView isnewItemMessage = holder.getView(R.id.isnew_item_message);
        TextView msgItemMessage = holder.getView(R.id.msg_item_message);
        TextView timeItemMessage = holder.getView(R.id.time_item_message);
        titleItemMessage.setText(itemData.getTitle());
        if (itemData.getIsNew()){
            isnewItemMessage.setVisibility(View.VISIBLE);
        }else{
            isnewItemMessage.setVisibility(View.GONE);
        }
        msgItemMessage.setText(itemData.getMsg());
        timeItemMessage.setText(TimeUtil.stampToDate(itemData.getTime()));
    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
