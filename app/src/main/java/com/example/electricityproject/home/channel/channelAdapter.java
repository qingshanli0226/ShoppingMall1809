package com.example.electricityproject.home.channel;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.BaseAdapter;
import com.example.common.Constants;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;
import com.example.glide.ShopGlide;

public class channelAdapter extends BaseAdapter<HomeBean.ResultBean.ChannelInfoBean> {

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_channel_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, HomeBean.ResultBean.ChannelInfoBean itemData) {
        ImageView img = baseViewHolder.getView(R.id.channel_img);
        ShopGlide.getInstance().with(baseViewHolder.itemView.getContext()).load(Constants.BASE_URl_IMAGE +itemData.getImage()).init(img);
        TextView name = baseViewHolder.getView(R.id.channel_name);
        name.setText(itemData.getChannel_name()+"");
    }


    @Override
    public int getRootViewType(int position) {
        return 1;
    }
}
