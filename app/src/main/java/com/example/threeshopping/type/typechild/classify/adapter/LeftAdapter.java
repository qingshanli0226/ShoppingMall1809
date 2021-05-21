package com.example.threeshopping.type.typechild.classify.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.threeshopping.R;

public class LeftAdapter extends BaseRvAdapter<String> {
    private int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.left_string_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, String itemView) {
        TextView view = holder.getView(R.id.itemStringTv);
        view.setTextColor(Color.BLACK);
        view.setText(itemView);
        if(this.position == position){
            view.setTextColor(Color.RED);
        }
    }
}
