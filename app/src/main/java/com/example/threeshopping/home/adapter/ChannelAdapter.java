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

public class ChannelAdapter extends BaseRvAdapter<HomeBean.ResultBean.ChannelInfoBean> {
    @Override
    public int getRootItemViewType(int position) {
        return 0;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_channel_type_layout;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.ChannelInfoBean itemView) {
        ImageView typeImag = holder.getView(R.id.itemChannelTypeImg);
        TextView typeTitle = holder.getView(R.id.itemChannelTypeTitle);
        Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+itemView.getImage()).into(typeImag);
        typeTitle.setText(itemView.getChannel_name());
    }
}
