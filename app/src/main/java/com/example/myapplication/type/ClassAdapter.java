package com.example.myapplication.type;

import android.widget.TextView;

import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;

public class ClassAdapter extends BaseRecyclerViewAdapter<String> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_tit;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, String itemData) {
        TextView view = holder.getView(R.id.text);
       view.setText(itemData);
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
