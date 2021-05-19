package com.example.electricityproject.home;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.base.BaseAdapter;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseAdapter<Object> {
    private final int BANNER_TYPE=0;
    private final int CHANNEL_TYPE=1;
    private final int ACT_TYPE=2;
    private final int SECKILL_TYPE=3;
    private final int RECOMMEND_TYPE=4;
    private final int HOT_TYPE=5;
    private List<String> list=new ArrayList<>();
    @Override
    public int getLayoutId(int viewType) {
        int layoutId=-1;
        switch (viewType){
            case BANNER_TYPE:
                layoutId= R.layout.item_home_banner;
                break;
            case CHANNEL_TYPE:
                layoutId=R.layout.item_home_channel;
                break;
            case ACT_TYPE:
                layoutId=R.layout.item_home_act;
                break;
            case SECKILL_TYPE:
                layoutId=R.layout.item_home_act;
                break;
            case RECOMMEND_TYPE:
                layoutId=R.layout.item_home_recommend;
                break;
            case HOT_TYPE:
                layoutId=R.layout.item_home_hot;
                break;
        }
        return layoutId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder baseViewHolder, int position, Object itemData) {
        switch (position){
            case 0:
                List<HomeBean.ResultBean.BannerInfoBean> banner_info = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
                Banner banner = baseViewHolder.getView(R.id.banner);
                for (HomeBean.ResultBean.BannerInfoBean bannerInfoBean : banner_info) {
                    list.add("http://49.233.0.68:8080//atguigu/img"+bannerInfoBean.getImage());
                }
                banner.setImages(list);
                Log.i("zx", "onHomeBanner: "+list.toString());
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        String s= (String) path;
                        Glide.with(context).load(s).into(imageView);
                    }
                });
                banner.start();
                break;
            case 1:

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
    }


    @Override
    public int getRootViewType(int position) {
        int type=-1;
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
}
