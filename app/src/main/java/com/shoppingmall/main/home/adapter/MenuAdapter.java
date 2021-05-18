package com.shoppingmall.main.home.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shoppingmall.R;
import com.shoppingmall.net.bean.HomeBean;

import java.util.List;

public class MenuAdapter extends BaseQuickAdapter<HomeBean.ResultBean.ChannelInfoBean, BaseViewHolder> {
    private ImageView menuRvImg;
    private TextView menuRvText;

    public MenuAdapter(@Nullable List<HomeBean.ResultBean.ChannelInfoBean> data) {
        super(R.layout.item_menu_rv_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ResultBean.ChannelInfoBean item) {
        menuRvImg = helper.getView(R.id.menuRvImg);
        menuRvText = helper.getView(R.id.menuRvText);
        Glide.with(mContext).load(item.getImage()).transform(new CircleCrop(),new CenterCrop()).into(menuRvImg);
        menuRvText.setText(""+item.getChannel_name());
    }


}
