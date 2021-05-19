package com.shoppingmall.main.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.shoppingmall.R;
import com.shoppingmall.framework.Variable;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.net.bean.HomeBean;

public class ChannelAdapter extends BaseRvAdapter<HomeBean.ResultBean.ChannelInfoBean> {
    private ImageView menuRvImg;
    private TextView menuRvText;

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_channel_rv_layout;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.ChannelInfoBean itemData) {
        menuRvImg = holder.getView(R.id.channelRvImg);
        menuRvText = holder.getView(R.id.channelRvText);
        Glide.with(menuRvImg.getContext()).load(Variable.IMG_HTTPS+itemData.getImage()).transform(new CircleCrop(),new CenterCrop()).into(menuRvImg);
        menuRvText.setText(""+itemData.getChannel_name());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
