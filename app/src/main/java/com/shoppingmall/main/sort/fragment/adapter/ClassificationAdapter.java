package com.shoppingmall.main.sort.fragment.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;


public class ClassificationAdapter extends BaseRvAdapter<String> {

    private TextView classificationTitle;
    private int itemPosition;

    public void setPosition(int position){
        this.itemPosition = position;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_classification_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, String itemData) {
        classificationTitle = (TextView) holder.getView(R.id.classificationTitle);
        classificationTitle.setText(""+itemData);
        classificationTitle.setTextColor(Color.BLACK);
        classificationTitle.setBackgroundColor(Color.parseColor("#EEEEEE"));

        if (itemPosition == position){
            classificationTitle.setTextColor(Color.RED);
            classificationTitle.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
