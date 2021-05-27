package com.example.shoppingmallsix.fragment.classify.frgment.classitfy.adapter;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.classify.ClassLeftBean;
import com.example.shoppingmallsix.R;

import java.util.List;

public class ClassLeftAdapter extends BaseRvAdapter<ClassLeftBean> {
    private LinearLayout linearLayout;

    public ClassLeftAdapter(List<ClassLeftBean> strings) {
        setDataList(strings);
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.class_left_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ClassLeftBean itemData) {
        linearLayout = holder.getView(R.id.classify_item_linear);

        if (position == 0) {
            linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            linearLayout.setBackgroundColor(Color.parseColor("#E9E9E9"));
        }
        if (itemData.isaBoolean()) {
            linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            linearLayout.setBackgroundColor(Color.parseColor("#E9E9E9"));
        }

        TextView textView = holder.getView(R.id.classify_left_item_tv);
        textView.setText(itemData.getString());
    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }

}
