package com.example.electricityproject.home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.adapter.BaseAdapter;
import com.example.common.bean.HomeBean;
import com.example.electricityproject.R;
import com.example.electricityproject.details.DetailsActivity;
import com.example.electricityproject.home.act.ActAdapter;
import com.example.electricityproject.home.channel.channelAdapter;
import com.example.electricityproject.home.hot.HotAdapter;
import com.example.electricityproject.home.recommend.RecommendAdapter;
import com.example.electricityproject.home.seckill.SeckillAdapter;
import com.example.glide.ShopGlide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeAdapter extends BaseAdapter<Object> {

    private final int BANNER_TYPE=0;
    private final int CHANNEL_TYPE=1;
    private final int ACT_TYPE=2;
    private final int SECKILL_TYPE=3;
    private final int RECOMMEND_TYPE=4;
    private final int HOT_TYPE=5;
    private List<String> list=new ArrayList<>();
    private Handler handler=new Handler();
    private Long start_time;
    private Long end_time;
    private long l;

    /**
     * 多布局 判断它的各种布局
     * @param viewType
     * @return
     */
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
                layoutId=R.layout.item_home_seckill;
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
        LinearLayoutManager ActLayoutManager = new LinearLayoutManager(baseViewHolder.itemView.getContext());
        ActLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        switch (position){
            case 0:
                List<HomeBean.ResultBean.BannerInfoBean> banner_info = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
                Banner banner = baseViewHolder.getView(R.id.banner);
                for (HomeBean.ResultBean.BannerInfoBean bannerInfoBean : banner_info) {
                    list.add("http://49.233.0.68:8080//atguigu/img"+bannerInfoBean.getImage());
                }
                banner.setImages(list);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        String s= (String) path;
                        ShopGlide.getInstance().with(context).load(s).init(imageView);
                    }
                });
                banner.start();

                break;
            case 1:
                List<HomeBean.ResultBean.ChannelInfoBean> channelInfoBeans= (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
                RecyclerView channnel_re = baseViewHolder.getView(R.id.home_re);
                channelAdapter channelAdapter = new channelAdapter();
                channelAdapter.updateData(channelInfoBeans);
                channnel_re.setAdapter(channelAdapter);
                channnel_re.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
                channelAdapter.setRecyclerItemClickListener(new iRecyclerItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Toast.makeText(baseViewHolder.itemView.getContext(), ""+channelInfoBeans.get(position).getChannel_name(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }

                });
                break;
            case 2:
                List<HomeBean.ResultBean.ActInfoBean> actInfoBeans= (List<HomeBean.ResultBean.ActInfoBean>) itemData;
                RecyclerView act_re = baseViewHolder.getView(R.id.act_re);
                ActAdapter actAdapter = new ActAdapter();
                act_re.setLayoutManager(ActLayoutManager);
                actAdapter.updateData(actInfoBeans);
                act_re.setAdapter(actAdapter);
                actAdapter.setRecyclerItemClickListener(new iRecyclerItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Toast.makeText(baseViewHolder.itemView.getContext(), ""+actInfoBeans.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }

                });
                break;
            case 3:
                HomeBean.ResultBean.SeckillInfoBean listBeans= (HomeBean.ResultBean.SeckillInfoBean) itemData;
                RecyclerView seckill_re = baseViewHolder.getView(R.id.seckill_re);
                LinearLayoutManager SeckillLayoutManager = new LinearLayoutManager(baseViewHolder.itemView.getContext());
                SeckillLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                SeckillAdapter seckillAdapter = new SeckillAdapter();
                seckill_re.setLayoutManager(SeckillLayoutManager);
                List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list = listBeans.getList();
                seckillAdapter.updateData(list);
                seckill_re.setAdapter(seckillAdapter);
                seckillAdapter.setRecyclerItemClickListener(new iRecyclerItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                        intent.putExtra("img",list.get(position).getFigure());
                        intent.putExtra("name",list.get(position).getName()+"");
                        intent.putExtra("price",list.get(position).getCover_price());
                        intent.putExtra("url",list.get(position).getFigure());
                        intent.putExtra("productId",list.get(position).getProduct_id());
                        intent.putExtra("productPrice",list.get(position).getCover_price());
                        baseViewHolder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }

                });
                TextView time = baseViewHolder.getView(R.id.time);
                start_time = Long.parseLong(listBeans.getStart_time());
                end_time = Long.parseLong(listBeans.getEnd_time());

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (start_time<=end_time) {
                                    time.setText(Current(start_time) + "");
                                }
                            }
                        });
                        start_time-=1000;
                        if (start_time>=end_time){
                            start_time=Long.parseLong(listBeans.getStart_time());
                        }
                    }
                },0,1000);
                break;
            case 4:
                List<HomeBean.ResultBean.RecommendInfoBean> recommendInfoBeans= (List<HomeBean.ResultBean.RecommendInfoBean>) itemData;
                RecyclerView recommend_re=baseViewHolder.getView(R.id.recommend_re);
                RecommendAdapter recommendAdapter = new RecommendAdapter();
                recommendAdapter.updateData(recommendInfoBeans);
                recommend_re.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                recommend_re.setAdapter(recommendAdapter);
                recommendAdapter.setRecyclerItemClickListener(new iRecyclerItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        /**
                         * 传值 各种传值
                         */
                        Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                        intent.putExtra("img",recommendInfoBeans.get(position).getFigure());
                        intent.putExtra("name",recommendInfoBeans.get(position).getName()+"");
                        intent.putExtra("price",recommendInfoBeans.get(position).getCover_price());
                        intent.putExtra("url",recommendInfoBeans.get(position).getFigure());
                        intent.putExtra("productId",recommendInfoBeans.get(position).getProduct_id());
                        intent.putExtra("productPrice",recommendInfoBeans.get(position).getCover_price());
                        baseViewHolder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }

                });
                break;
            case 5:
                List<HomeBean.ResultBean.HotInfoBean> hotInfoBeans= (List<HomeBean.ResultBean.HotInfoBean>) itemData;
                RecyclerView hot_re = baseViewHolder.getView(R.id.hot_re);
                HotAdapter hotAdapter = new HotAdapter();
                hotAdapter.updateData(hotInfoBeans);
                hot_re.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                hot_re.setAdapter(hotAdapter);
                hotAdapter.setRecyclerItemClickListener(new iRecyclerItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        /**
                         * 
                         */
                        Intent intent = new Intent(baseViewHolder.itemView.getContext(), DetailsActivity.class);
                        intent.putExtra("img",hotInfoBeans.get(position).getFigure());
                        intent.putExtra("name",hotInfoBeans.get(position).getName()+"");
                        intent.putExtra("price",hotInfoBeans.get(position).getCover_price());
                        intent.putExtra("url",hotInfoBeans.get(position).getFigure());
                        intent.putExtra("productId",hotInfoBeans.get(position).getProduct_id());
                        intent.putExtra("productPrice",hotInfoBeans.get(position).getCover_price());
                        intent.putExtra("position", position);
                        intent.putExtra("more","more");
                        baseViewHolder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void OnItemLongClick(int position) {

                    }

                });
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

    public String Current(Long i){
        String str="HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        String format = simpleDateFormat.format(new Date(i));
        return format;
    }
}
