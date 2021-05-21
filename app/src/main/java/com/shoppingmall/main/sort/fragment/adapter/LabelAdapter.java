package com.shoppingmall.main.sort.fragment.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.LabelBean;

import java.util.Random;

public class LabelAdapter extends BaseRvAdapter<LabelBean.ResultBean> {
    private TextView lebeltext;
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.labeltext_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, LabelBean.ResultBean itemData) {
         lebeltext =holder.getView(R.id.labeltext);
         lebeltext.setText(itemData.getName());

        Random random = new Random();
        int r=30+random.nextInt(200);
        int g=30+random.nextInt(120);
        int b=30+random.nextInt(140);
        lebeltext.setTextColor(Color.rgb(r,g,b));


    }
    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
