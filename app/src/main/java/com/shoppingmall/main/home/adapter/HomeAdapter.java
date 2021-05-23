package com.shoppingmall.main.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.shoppingmall.R;
import com.shoppingmall.framework.Constants;
import com.shoppingmall.framework.adapter.BaseRvAdapter;
import com.shoppingmall.framework.manager.ShopMallArouter;
import com.shoppingmall.net.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

public class HomeAdapter extends BaseRvAdapter<Object> {
    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int SECKILL_TYPE = 3;
    private final int RECOMMEND_TYPE = 4;
    private final int HOT_TYPE = 5;
    @Override
    public int getLayoutId(int viewType) {
        //绑定对应布局
        int layoutId = -1;
        switch (viewType){
            case BANNER_TYPE:
                layoutId = R.layout.item_banner_layout;
                break;
            case CHANNEL_TYPE:
                layoutId = R.layout.item_channel_layout;
                break;
            case ACT_TYPE:
                layoutId = R.layout.item_act_layout;
                break;
            case SECKILL_TYPE:
                layoutId = R.layout.item_sec_kill_layout;
                break;
            case RECOMMEND_TYPE:
                layoutId = R.layout.item_recommend_layout;
                break;
            case HOT_TYPE:
                layoutId = R.layout.item_hot_layout;
                break;
        }
        return layoutId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position){
            case 0:
                //第一个轮播图
                List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
                Banner banner = holder.getView(R.id.banner);
                banner.setImages(bannerInfoBeans);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.ResultBean.BannerInfoBean bannerInfoBean = (HomeBean.ResultBean.BannerInfoBean) path;
                        Glide.with(holder.itemView).load(Constants.IMG_HTTPS + bannerInfoBean.getImage()).into(imageView);
                    }
                });
                banner.start();
                break;
            case 1:
                List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
                RecyclerView channelRv = holder.getView(R.id.channelRv);
                ChannelAdapter channelAdapter = new ChannelAdapter();
                channelRv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
                channelRv.setAdapter(channelAdapter);
                channelAdapter.updateData(channelInfoBeans);
                channelAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(holder.itemView.getContext(), "点击了Item", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(int position) {
                        Toast.makeText(holder.itemView.getContext(), "长按了Item", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:
                List<HomeBean.ResultBean.ActInfoBean> actInfoBeans = (List<HomeBean.ResultBean.ActInfoBean>) itemData;
                Banner actBanner = holder.getView(R.id.ActBanner);
                actBanner.setImages(actInfoBeans);
                actBanner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.ResultBean.ActInfoBean actInfoBean = (HomeBean.ResultBean.ActInfoBean) path;
                        Glide.with(holder.itemView).load(Constants.IMG_HTTPS + actInfoBean.getIcon_url()).into(imageView);
                    }
                });
                actBanner.start();
                break;
            case 3:
                HomeBean.ResultBean.SeckillInfoBean seckillInfoBeans = (HomeBean.ResultBean.SeckillInfoBean) itemData;
                RecyclerView secKillRv = holder.getView(R.id.secKillRv);
                TextView view = holder.getView(R.id.secKillTime);
                int endTime = Integer.parseInt(seckillInfoBeans.getEnd_time());
                view.setText(""+secToTime(endTime));
                SecKillAdapter secKillAdapter = new SecKillAdapter();
                secKillRv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
                secKillRv.setAdapter(secKillAdapter);
                secKillAdapter.updateData(seckillInfoBeans.getList());
                secKillAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ARouter.getInstance().build(Constants.TO_DETAIL_ACTIVITY).withSerializable("goods",seckillInfoBeans.getList().get(position)).withString("type","seckill").navigation();
                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
                break;
            case 4:
                List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.ResultBean.RecommendInfoBean>) itemData;
                RecyclerView recommendRv = holder.getView(R.id.recommendRv);
                RecommendAdapter recommendAdapter = new RecommendAdapter();
                recommendRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                recommendRv.setAdapter(recommendAdapter);
                recommendAdapter.updateData(recommendInfoBeans);
                recommendAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = new HomeBean.ResultBean.RecommendInfoBean();
                        ARouter.getInstance().build(Constants.TO_DETAIL_ACTIVITY).withSerializable("goods",recommendInfoBeans.get(position)).withString("type","recommend").navigation();
                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
                break;
            case 5:
                List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeBean.ResultBean.HotInfoBean>) itemData;
                RecyclerView hotRv = holder.getView(R.id.hotRv);
                HotAdapter hotAdapter = new HotAdapter();
                hotRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                hotRv.setAdapter(hotAdapter);
                hotAdapter.updateData(hotInfoBeans);
                hotAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ARouter.getInstance().build(Constants.TO_DETAIL_ACTIVITY).withSerializable("goods",hotInfoBeans.get(position)).withString("type","hot").navigation();
                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
                break;
        }
    }

    @Override
    public int getRootViewType(int position) {
        //绑定对应数据类型
        int type = -1;
        switch (position){
            case 0:
                type = BANNER_TYPE;
                break;
            case 1:
                type = CHANNEL_TYPE;
                break;
            case 2:
                type = ACT_TYPE;
                break;
            case 3:
                type = SECKILL_TYPE;
                break;
            case 4:
                type = RECOMMEND_TYPE;
                break;
            case 5:
                type = HOT_TYPE;
                break;
        }
        return type;
    }
    //毫秒转化为时分秒的格式  00:00:00
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00:00";
        else {
            second = time /1000;
            minute = second / 60;
            if (second < 60) {

                timeStr = "00:00:" + unitFormat(second);
            }else if (minute < 60) {
                second = second % 60;
                timeStr = "00:" + unitFormat(minute) + ":" + unitFormat(second);
            }else{//数字>=3600 000的时候
                hour = minute /60;
                minute = minute % 60;
                second = second - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }
    public static String unitFormat(int i) {//时分秒的格式转换
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

}
