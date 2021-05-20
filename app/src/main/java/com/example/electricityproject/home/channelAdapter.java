package com.example.electricityproject.home;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;

public class channelAdapter extends BaseAdapter<HomeBean.ResultBean.ChannelInfoBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_channel_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, HomeBean.ResultBean.ChannelInfoBean itemData) {
//        List<HomeBean.ResultBean.ChannelInfoBean> list= (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
        ImageView img = baseViewHolder.getView(R.id.channel_img);
        Glide.with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getImage()).into(img);
        TextView name = baseViewHolder.getView(R.id.channel_name);
        name.setText(itemData.getChannel_name()+"");
    }


    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
