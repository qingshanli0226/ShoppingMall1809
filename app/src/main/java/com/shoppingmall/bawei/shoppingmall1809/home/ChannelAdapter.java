package com.shoppingmall.bawei.shoppingmall1809.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fiannce.bawei.framework.BaseRvAdapter;
import com.fiannce.bawei.net.mode.ShopmallHomeBean;
import com.shoppingmall.bawei.shoppingmall1809.R;


public class ChannelAdapter extends BaseRvAdapter<ShopmallHomeBean.ResultBean.ChannelInfoBean> {
    @Override
    public int getLayoutId(int ViewType) {
        return R.layout.home_channel_item_view;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, ShopmallHomeBean.ResultBean.ChannelInfoBean itemData) {
        Glide.with(holder.getView(R.id.channelImage).getContext()).load("http://49.233.0.68:8080"+"/atguigu/img"+itemData.getImage())
                .into((ImageView) holder.getView(R.id.channelImage));
        TextView nameTv = holder.getView(R.id.nameTv);
        nameTv.setText(itemData.getChannel_name());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }


}
