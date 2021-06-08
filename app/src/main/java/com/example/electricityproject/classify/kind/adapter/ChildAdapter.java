package com.example.electricityproject.classify.kind.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.BaseAdapter;
import com.example.common.Constants;
import com.example.common.bean.GoodsBean;
import com.example.electricityproject.R;

public
class ChildAdapter extends BaseAdapter<GoodsBean.ResultBean.ChildBean> {

    private Context context;
    public ChildAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_kind_child;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, GoodsBean.ResultBean.ChildBean itemData) {
        ImageView iv = baseViewHolder.getView(R.id.kind_child_image);
        TextView tv = baseViewHolder.getView(R.id.kind_name);
        Glide.with(context).load(Constants.BASE_URl_IMAGE+itemData.getPic()).placeholder(R.drawable.new_img_loading_1).into(iv);
        tv.setText(itemData.getName()+"");
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
