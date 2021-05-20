package com.example.shoppingmallsix.fragment.classifyfragment.frgment.lab.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.TabBean;
import com.example.shoppingmallsix.R;

import java.util.List;

public class LabAdapter extends BaseRvAdapter<TabBean.ResultBean> {

    public LabAdapter(List<TabBean.ResultBean> list){
        setDataList(list);
    }


    @Override
    public int getLayoutId(int viewType) {
        return R.layout.lab_item;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, TabBean.ResultBean itemData) {
        TextView textView = holder.getView(R.id.lab_tv_item);
        textView.setText(itemData.getName());
        textView.setTextColor(Color.parseColor(itemData.getTextcolor()));
    }

    @Override
    public int getRootViewType(int position) {
        return position;
    }
}
