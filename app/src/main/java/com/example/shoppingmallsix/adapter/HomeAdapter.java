package com.example.shoppingmallsix.adapter;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.BuildConfig;
import com.example.shoppingmallsix.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseRvAdapter<Object> {

    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int HOT_TYPE = 3;
    private final int RECOMMEND_TYPE = 4;

    public HomeAdapter(List<Object> list){
        setDataList(list);
    }

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
                layoutId = R.layout.home_viewpage;
                break;
        }


        return layoutId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position){
            case 0:
                List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
                if (BuildConfig.DEBUG) Log.d("HomeAdapter", "bannerInfoBeans:" + bannerInfoBeans.toString());
                Banner banner = holder.getView(R.id.banner);
                banner.setImages(bannerInfoBeans);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {

                        HomeBean.ResultBean.BannerInfoBean bean = (HomeBean.ResultBean.BannerInfoBean) path;
                        String image = bean.getImage();

                        Glide.with(holder.itemView)
                                .load(Constants.BASE_URl_IMAGE+image)
                                .into(imageView);

                    }
                });
                banner.start();
                break;

            case 1:

                List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans = (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
                RecyclerView recyclerView = holder.getView(R.id.recommendRv);

                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
                ChanneAdapter channeAdapter = new ChanneAdapter();
                recyclerView.setAdapter(channeAdapter);
                channeAdapter.dataList.addAll(channelInfoBeans);
                channeAdapter.setiRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(holder.itemView.getContext(), "短点击", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItwmLongClick(int position) {
                        Toast.makeText(holder.itemView.getContext(), "长点击item", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case 2:

                List<HomeBean.ResultBean.ActInfoBean> actInfoBeans = (List<HomeBean.ResultBean.ActInfoBean>) itemData;
                ViewPager vp = holder.getView(R.id.vp);

                ArrayList<ImageView> imageViews = new ArrayList<>();

                for (HomeBean.ResultBean.ActInfoBean actInfoBean : actInfoBeans) {
                    ImageView imageView = new ImageView(holder.itemView.getContext());
                    Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE+actInfoBean.getIcon_url())
                            .error(R.drawable.animation_loading)
                            .into(imageView);
                    imageViews.add(imageView);
                }

                ActAdapter actAdapter = new ActAdapter(imageViews);
                vp.setAdapter(actAdapter);


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
