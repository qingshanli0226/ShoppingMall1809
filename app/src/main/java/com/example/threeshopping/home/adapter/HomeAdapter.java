package com.example.threeshopping.home.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;


import com.blankj.utilcode.util.LogUtils;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;


import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HEAD;

public class HomeAdapter extends BaseRvAdapter<Object> {
    @Override
    public int getRootItemViewType(int position) {
        int type = -1;
        switch (position) {
            case 0:
                type = Constants.HOME_BANNER;
                break;
            case 1:
                type = Constants.HOME_CHANNEL;
                break;
            case 2:
                type = Constants.HOME_ACT;
                break;
            case 3:
                type = Constants.HOME_SEKILL;
                break;
            case 4:
                type = Constants.HOME_RECOMMEND;
                break;
            case 5:
                type = Constants.HOME_HOT;
                break;
        }
        Log.i("TAG", "getRootItemViewType: " + type);
        return type;
    }

    @Override
    protected int getLayoutId(int viewType) {
        int layoutId = -1;
        switch (viewType) {
            case 0:
                layoutId = R.layout.item_banner_layout;
                break;
            case 1:

                layoutId = R.layout.item_channel_layout;

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
        }
        return layoutId;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, Object itemView) {

        switch (position) {
            case 0:
                Banner itemBanner = holder.getView(R.id.itemBanner);
                List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) itemView;
                itemBanner.setImages(bannerInfoBeans)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                HomeBean.ResultBean.BannerInfoBean bean = (HomeBean.ResultBean.BannerInfoBean) path;
                                Glide.with(context).load(Constants.BASE_URl_IMAGE + bean.getImage()).into(imageView);
                            }
                        }).start();
                break;

            case 5:
                break;

            case 1:
                RecyclerView itemRv = holder.getView(R.id.itemChannelRv);
                List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>) itemView;
                itemRv.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
                ChannelAdapter channelAdapter = new ChannelAdapter();
                channelAdapter.getData().addAll(channelInfoBeans);
                itemRv.setAdapter(channelAdapter);

                break;
        }

    }
}
