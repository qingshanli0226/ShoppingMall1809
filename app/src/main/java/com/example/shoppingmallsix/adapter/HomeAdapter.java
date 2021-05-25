package com.example.shoppingmallsix.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.framework.BaseRvAdapter;
import com.example.net.bean.HomeBean;
import com.example.net.constants.Constants;
import com.example.shoppingmallsix.BuildConfig;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.goodsactivity.GoodsActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeAdapter extends BaseRvAdapter<Object> {

    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int HOT_TYPE = 3;
    private final int RECOMMEND_TYPE = 4;
    private final int HOTT_TYPE = 5;
    private Context context;

    public HomeAdapter(List<Object> list,Context context){
        setDataList(list);
        this.context = context;
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
            case HOT_TYPE:
                layoutId = R.layout.home_seckill;
                break;
            case RECOMMEND_TYPE:
                layoutId = R.layout.home_recommend1;
                break;
            case HOTT_TYPE:
                layoutId = R.layout.home_hot;
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

                    }

                    @Override
                    public void onItwmLongClick(int position) {

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
            case 3:
                List<HomeBean.ResultBean.SeckillInfoBean.ListBean> seckillInfoBeans = (List<HomeBean.ResultBean.SeckillInfoBean.ListBean>) itemData;
                RecyclerView seckillRecyclerView = holder.getView(R.id.fourRv);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                seckillRecyclerView.setLayoutManager(linearLayoutManager);
                SeckillAdapter seckillAdapter = new SeckillAdapter();
                seckillRecyclerView.setAdapter(seckillAdapter);
                seckillAdapter.dataList.addAll(seckillInfoBeans);
                hours = holder.getView(R.id.hours_tv);
                minutes = holder.getView(R.id.minutes_tv);
                seconds = holder.getView(R.id.seconds_tv);
                startRun();
                seckillAdapter.setiRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(holder.itemView.getContext(), GoodsActivity.class);
                        intent.putExtra("id",seckillInfoBeans.get(position).getProduct_id());
                        intent.putExtra("name",seckillInfoBeans.get(position).getName());
                        intent.putExtra("figure",seckillInfoBeans.get(position).getFigure());
                        intent.putExtra("price",seckillInfoBeans.get(position).getCover_price());
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItwmLongClick(int position) {

                    }
                });
                break;
            case 4:
                List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans = (List<HomeBean.ResultBean.RecommendInfoBean>) itemData;
                RecyclerView recommendRecyclerView = holder.getView(R.id.recommendRv);
                recommendRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
                RecommendAdapter recommendAdapter = new RecommendAdapter();
                recommendRecyclerView.setAdapter(recommendAdapter);
                recommendAdapter.dataList.addAll(recommendInfoBeans);
                recommendAdapter.setiRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(holder.itemView.getContext(), GoodsActivity.class);
                        intent.putExtra("id",recommendInfoBeans.get(position).getProduct_id());
                        intent.putExtra("name",recommendInfoBeans.get(position).getName());
                        intent.putExtra("figure",recommendInfoBeans.get(position).getFigure());
                        intent.putExtra("price",recommendInfoBeans.get(position).getCover_price());
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItwmLongClick(int position) {

                    }
                });
                break;
            case 5:
                List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans = (List<HomeBean.ResultBean.HotInfoBean>) itemData;
                RecyclerView hotRecyclerView = holder.getView(R.id.hotRv);
                hotRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                HotAdapter hotAdapter = new HotAdapter();
                hotRecyclerView.setAdapter(hotAdapter);
                hotAdapter.dataList.addAll(hotInfoBeans);
                hotAdapter.setiRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(holder.itemView.getContext(), GoodsActivity.class);
                        intent.putExtra("id",hotInfoBeans.get(position).getProduct_id());
                        intent.putExtra("name",hotInfoBeans.get(position).getName());
                        intent.putExtra("figure",hotInfoBeans.get(position).getFigure());
                        intent.putExtra("price",hotInfoBeans.get(position).getCover_price());
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItwmLongClick(int position) {

                    }
                });
                break;
        }
    }

    private long mHour = 23;//小时,
    private long mMin = 59;//分钟,
    private long mSecond = 59;//秒
    private TextView hours;
    private TextView minutes;
    private TextView seconds;
    private Timer timer = new Timer();

    Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                computeTime();
                hours.setText(getTv(mHour));
                minutes.setText(getTv(mMin));
                seconds.setText(getTv(mSecond));
                if (mSecond == 0 && mHour == 0 && mMin == 0 ) {
                    timer.cancel();
                }
            }
        }
    };


    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
                if (mHour < 0) {
                    // 倒计时结束
                    mHour = 0;
                    mMin = 0;
                    mSecond = 0;
                }
            }
        }
    }

    private void startRun() {
        TimerTask mTimerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = Message.obtain();
                message.what = 1;
                timeHandler.sendMessage(message);
            }
        };
        timer.schedule(mTimerTask,0,1000);
    }

    private String getTv(long l){
        if(l>=10){
            return l+"";
        }else{
            return "0"+l;//小于10,,前面补位一个"0"
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
            case 3:
                type = HOT_TYPE;
                break;
            case 4:
                type = RECOMMEND_TYPE;
                break;
            case 5:
                type = HOTT_TYPE;
                break;
        }

        return type;
    }
}
