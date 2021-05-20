package com.example.shoppingmall1809.main.type.sort.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.SortBean;
import com.example.shoppingmall1809.R;

import java.util.List;
import java.util.Random;

public class SortAdapter extends BaseRVAdapter<SortBean.ResultBean> {
    public SortAdapter(List<SortBean.ResultBean> datalist) {
        super(datalist);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.type_item_sort;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, SortBean.ResultBean itemData) {
        TextView name = holder.itemView.findViewById(R.id.type_item_name);
        name.setText(itemData.getName());
        int r = new Random().nextInt(256);
        int g = new Random().nextInt(256);
        int b = new Random().nextInt(256);
        name.setTextColor(Color.rgb(r,g,b));
    }

    @Override
    protected int getRootViewType(int position) {
        return 0;
    }
}
