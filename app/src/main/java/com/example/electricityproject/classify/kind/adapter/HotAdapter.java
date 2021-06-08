package com.example.electricityproject.classify.kind.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.BaseAdapter;
import com.example.common.Constants;
import com.example.common.bean.GoodsBean;
import com.example.electricityproject.R;

public class HotAdapter extends BaseAdapter<GoodsBean.ResultBean.HotProductListBean> {

    private Context context;
    public HotAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_kind_hot;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, GoodsBean.ResultBean.HotProductListBean itemData) {
        ImageView iv = baseViewHolder.getView(R.id.kind_image);
        TextView tv = baseViewHolder.getView(R.id.kind_money);
        Glide.with(context).load(Constants.BASE_URl_IMAGE+itemData.getFigure()).placeholder(R.drawable.new_img_loading_1).into(iv);
        tv.setText("¥"+itemData.getCover_price());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }

}
