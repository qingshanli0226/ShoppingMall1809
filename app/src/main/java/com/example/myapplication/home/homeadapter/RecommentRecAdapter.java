package com.example.myapplication.home.homeadapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.myapplication.R;
import com.example.net.bean.HomeBean;

public class RecommentRecAdapter extends BaseRecyclerViewAdapter<HomeBean.ResultBean.ChannelInfoBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_channel_item_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.ChannelInfoBean itemData) {
        Glide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getImage())
                .into((ImageView) holder.getView(R.id.recommendItemImage));
        TextView nameTv = holder.getView(R.id.recommendItemTv);
        nameTv.setText(itemData.getChannel_name());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
