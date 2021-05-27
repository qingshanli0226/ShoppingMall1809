package com.example.shoppingmallsix.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.R;

public class ChanneAdapter extends BaseRvAdapter<HomeBean.ResultBean.ChannelInfoBean> {
    @Override
    public int getLayoutId(int viewType) {
        return R.layout.home_channe;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, HomeBean.ResultBean.ChannelInfoBean itemData) {
        Glide.with(holder.itemView.getContext())
                .load(Constants.BASE_URl_IMAGE + itemData.getImage())
                .into((ImageView) holder.getView(R.id.channelImage));

        TextView view = holder.getView(R.id.nameTv);
        view.setText(itemData.getChannel_name());

    }

    @Override
    public int getRootViewType(int position) {
        return 0;
    }
}
