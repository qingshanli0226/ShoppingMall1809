package com.example.myapplication.home.msg;

import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.db.MessageBean;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgAdapter extends BaseRecyclerViewAdapter<MessageBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.msg_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, MessageBean itemData) {
        TextView name = holder.getView(R.id.name);
        name.setText(itemData.getTitle());
        TextView isnew = holder.getView(R.id.isnew);
        if (itemData.getIsnew()){
            isnew.setVisibility(View.VISIBLE);
        }else {
            isnew.setVisibility(View.INVISIBLE);
        }
        TextView success = holder.getView(R.id.succes);
        success.setText(itemData.getMsg());
        TextView time = holder.getView(R.id.time);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        String format = simpleDateFormat.format(date);
        time.setText(format+"");
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
