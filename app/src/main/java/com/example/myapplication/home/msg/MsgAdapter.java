package com.example.myapplication.home.msg;

import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.db.DbTable;
import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgAdapter extends BaseRecyclerViewAdapter<DbTable> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.msg_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, DbTable itemData) {
        TextView name = holder.getView(R.id.name);
        name.setText(itemData.getTitle());
        TextView isnew = holder.getView(R.id.isnew);
        if (itemData.getIsNew()) {
            isnew.setVisibility(View.VISIBLE);
        } else {
            isnew.setVisibility(View.INVISIBLE);
        }
        TextView success = holder.getView(R.id.succes);
        success.setText(itemData.getMsg());
        TextView time = holder.getView(R.id.time);
        time.setText(itemData.getTime()+"");
    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
