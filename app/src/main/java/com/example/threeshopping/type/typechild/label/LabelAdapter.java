package com.example.threeshopping.type.typechild.label;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.LabelBean;
import com.example.threeshopping.R;

public class LabelAdapter extends BaseRvAdapter<LabelBean.ResultBean> {
    @Override
    public int getRootItemViewType(int position) {
        return position;
    }

    @Override
    protected int getLayoutId(int viewType) {
        LogUtils.json("zzy");

        return R.layout.item_label_child;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, LabelBean.ResultBean itemView) {
        LogUtils.json(itemView+"zzy");
        TextView textView = holder.getView(R.id.label_tv);
        textView.setText(itemView.getName());
    }
}
