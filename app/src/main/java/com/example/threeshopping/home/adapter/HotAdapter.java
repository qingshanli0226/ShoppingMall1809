package com.example.threeshopping.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;

import org.w3c.dom.Text;

public class HotAdapter extends BaseRvAdapter<HomeBean.ResultBean.HotInfoBean> {
    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_hot_child_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.HotInfoBean itemView) {
        ImageView img = holder.getView(R.id.hotImg);
        TextView title = holder.getView(R.id.hotTitle);
        TextView price = holder.getView(R.id.hotPrice);
        title.setText(itemView.getName());
        price.setText(itemView.getCover_price());
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemView.getFigure()).into(img);
    }
}
