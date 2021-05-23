package com.shoppingmall.main.home.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.glide.ShopMallGlide;
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
        ShopMallGlide.with(menuRvImg.getContext()).load(Constants.IMG_HTTPS+itemData.getImage()).into(menuRvImg);
        menuRvText.setText(""+itemData.getChannel_name());
    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
