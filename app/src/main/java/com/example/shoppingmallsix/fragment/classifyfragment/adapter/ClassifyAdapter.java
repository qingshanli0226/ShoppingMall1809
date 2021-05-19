package com.example.shoppingmallsix.fragment.classifyfragment.adapter;

import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.shoppingmallsix.R;

import java.util.List;

public class ClassifyAdapter extends BaseRvAdapter<String> {

    public ClassifyAdapter(List<String> strings){
        setDataList(strings);
    }
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_left_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, String itemData) {
        TextView textView = holder.getView(R.id.classify_left_item_tv);
        textView.setText(itemData);
    }

    @Override
    public int getRootViewType(int position) {

        return position;
    }
}
