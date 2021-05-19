package com.example.shoppingmallsix.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.shoppingmallsix.R;
import com.youth.banner.Banner;

import java.util.List;

public class HomeAdapter extends BaseRvAdapter<Object> {

    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;

    @Override
    public int getLayoutId(int viewType) {

        int layoutId = -1;
        switch (viewType){
            case BANNER_TYPE:
                layoutId = R.layout.home_banner;
                break;

            case CHANNEL_TYPE:
                layoutId = R.layout.home_recommend;
                break;

            case ACT_TYPE:
                layoutId = R.layout.home_act;
                break;
        }


        return layoutId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position){
            case 0:
                List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
                Banner banner = holder.getView(R.id.banner);
                
                break;

            case 1:

                break;

            case 2:

                break;
        }
    }

    @Override
    public int getRootViewType(int position) {

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
        }

        return type;
    }
}
