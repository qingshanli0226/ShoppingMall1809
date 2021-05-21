package com.example.threeshopping.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                layoutId = R.layout.item_act_layout;
                break;
            case 3:
                layoutId = R.layout.item_seckill_layout;
                break;
            case 4:
                layoutId = R.layout.item_recommend_layout;
                break;
            case 5:
                layoutId = R.layout.item_hot_layout;
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
            case 1:
                RecyclerView itemRv = holder.getView(R.id.itemChannelRv);
                List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>) itemView;
                itemRv.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL));
                ChannelAdapter channelAdapter = new ChannelAdapter();
                channelAdapter.getData().addAll(channelInfoBeans);
                itemRv.setAdapter(channelAdapter);

                break;
            case 2:
                ViewPager itemViewpage = holder.getView(R.id.itemViewpage);
                List<View> views=new ArrayList<>();
                List<HomeBean.ResultBean.ActInfoBean> actInfoBeans = (List<HomeBean.ResultBean.ActInfoBean>) itemView;

                for (HomeBean.ResultBean.ActInfoBean actInfoBean : actInfoBeans) {
                    ImageView imageView = new ImageView(holder.itemView.getContext());
                    Log.i("TAG", "displayViewHolder: "+Constants.BASE_URl_IMAGE+actInfoBean.getIcon_url());
                    Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + actInfoBean.getIcon_url()).into(imageView);
                    views.add(imageView);
                }
                itemViewpage.setAdapter(new ItemPageAdapter(views));
                break;
            case 3:
                ImageView sekLeft = holder.getView(R.id.itemSeckillLeft);
                sekLeft.setImageBitmap(BitmapFactory.decodeResource(holder.itemView.getResources(), R.drawable.home_arrow_left_flash));

                ImageView sekright = holder.getView(R.id.itemSeckilRight);
                sekright.setImageBitmap(BitmapFactory.decodeResource(holder.itemView.getResources(), R.drawable.home_arrow_right));
                //倒计时
                TextView countDown = holder.getView(R.id.itemseckilCountDown);
                HomeBean.ResultBean.SeckillInfoBean seckillInfoBeans = (HomeBean.ResultBean.SeckillInfoBean) itemView;
                String format = new SimpleDateFormat("HH:mm:ss").format(Long.parseLong(seckillInfoBeans.getStart_time()));
                countDown.setText(format);


                RecyclerView sekllRv = holder.getView(R.id.itemSeckil);

                sekllRv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),RecyclerView.HORIZONTAL,false));
                SekillAdapter sekillAdapter = new SekillAdapter();
                sekillAdapter.getData().addAll(seckillInfoBeans.getList());
                sekllRv.setAdapter(sekillAdapter);
                //点击跳转
                sekillAdapter.setRvItemOnClickListener(new IRvItemOnClickListener() {
                    @Override
                    public void onItemClick(int position,View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("pic",Constants.BASE_URl_IMAGE +seckillInfoBeans.getList().get(position).getFigure());
                        bundle.putString("title",seckillInfoBeans.getList().get(position).getName());
                        bundle.putString("price",seckillInfoBeans.getList().get(position).getCover_price());
                        CommonArouter.getInstance().build(Constants.PATH_PARTICULARS).with(bundle).navigation();
                    }

                    @Override
                    public boolean onLongItemClick(int position,View view) {
                        Toast.makeText(holder.itemView.getContext(), "长", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });

                break;
            case 4:
                ImageView itemLeft = holder.getView(R.id.itemRecommendLeft);

                itemLeft.setImageBitmap(BitmapFactory.decodeResource(holder.itemView.getResources(), R.drawable.home_arrow_left_new));
                ImageView itemRight = holder.getView(R.id.itemRecommendRight);
                itemRight.setImageBitmap(BitmapFactory.decodeResource(holder.itemView.getResources(), R.drawable.home_arrow_right));
                RecyclerView itemReRv = holder.getView(R.id.itemRecommend);
                List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.ResultBean.RecommendInfoBean>) itemView;

                itemReRv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                ReCommendAdapter reCommendAdapter = new ReCommendAdapter();
                reCommendAdapter.getData().addAll(recommendInfoBeans);
                itemReRv.setAdapter(reCommendAdapter);
                //点击跳转
                reCommendAdapter.setRvItemOnClickListener(new IRvItemOnClickListener() {
                    @Override
                    public void onItemClick(int position,View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("pic",Constants.BASE_URl_IMAGE +recommendInfoBeans.get(position).getFigure());
                        bundle.putString("title",recommendInfoBeans.get(position).getName());
                        bundle.putString("price",recommendInfoBeans.get(position).getCover_price());
                        CommonArouter.getInstance().build(Constants.PATH_PARTICULARS).with(bundle).navigation();
                    }

                    @Override
                    public boolean onLongItemClick(int position,View view) {
                        Toast.makeText(holder.itemView.getContext(), "长", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });


                break;
            case 5:

                ImageView hotimg = holder.getView(R.id.itemHotLeft);

                hotimg.setImageBitmap(BitmapFactory.decodeResource(holder.itemView.getResources(), R.drawable.home_arrow_left_hot));
                ImageView hotitemRight = holder.getView(R.id.itemHotRight);
                hotitemRight.setImageBitmap(BitmapFactory.decodeResource(holder.itemView.getResources(), R.drawable.home_arrow_right));
                RecyclerView itemhotRv = holder.getView(R.id.itemHot);
                List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeBean.ResultBean.HotInfoBean>) itemView;
                itemhotRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                HotAdapter hotAdapter = new HotAdapter();
                hotAdapter.getData().addAll(hotInfoBeans);
                itemhotRv.setAdapter(hotAdapter);

                //点击跳转
                hotAdapter.setRvItemOnClickListener(new IRvItemOnClickListener() {
                    @Override
                    public void onItemClick(int position,View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("pic",Constants.BASE_URl_IMAGE +hotInfoBeans.get(position).getFigure());
                        bundle.putString("title",hotInfoBeans.get(position).getName());
                        bundle.putString("price",hotInfoBeans.get(position).getCover_price());
                        CommonArouter.getInstance().build(Constants.PATH_PARTICULARS).with(bundle).navigation();
                    }

                    @Override
                    public boolean onLongItemClick(int position,View view) {
                        Toast.makeText(holder.itemView.getContext(), "长", Toast.LENGTH_SHORT).show();

                        return true;
                    }
                });
                break;
        }

    }
}
