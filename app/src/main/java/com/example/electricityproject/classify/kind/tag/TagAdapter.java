package com.example.electricityproject.classify.kind.tag;

import android.graphics.Color;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.common.bean.ClassifyBean;
import com.example.electricityproject.R;

import java.util.Random;

public
class TagAdapter extends BaseAdapter<ClassifyBean.ResultBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_tag;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, ClassifyBean.ResultBean itemData) {
        //随机颜色
        Random random = new Random();
        String []ranColor ={"#FFB6C1","#DB7093","#000000"};
        int randomcolor =random.nextInt(ranColor.length);
        TextView view = baseViewHolder.getView(R.id.tag_text);
        view.setText(itemData.getName()+"");
        view.setTextColor(Color.parseColor(ranColor[randomcolor]));
    }

    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
