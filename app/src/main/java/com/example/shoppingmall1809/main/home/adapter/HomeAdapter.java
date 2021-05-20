package com.example.shoppingmall1809.main.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.commom.Constants;
import com.example.framework.view.BaseRVAdapter;
import com.example.net.model.HoemBean;
import com.example.shoppingmall1809.R;
import com.example.shoppingmall1809.particulars.ParticularsActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeAdapter extends BaseRVAdapter<Object> {

    public HomeAdapter(List<Object> datalist) {
        super(datalist);
    }

    private final int BANNER_INFO = 0;
    private final int CHANNEL_INFO = 1;
    private final int ACT_INFO = 2;
    private final int SECKIL_INFO = 3;
    private final int RECOMMEND_INFO = 4;
    private final int HOT_INFO = 5;

    @Override
    protected int getLayoutId(int viewType) {
        int type = -1;
        switch (viewType) {
            case BANNER_INFO:
                type = R.layout.home_item_banner;
                break;
            case CHANNEL_INFO:
                type = R.layout.home_item_hand;
                break;
            case ACT_INFO:
                type = R.layout.home_item_act;
                break;
            case SECKIL_INFO:
                type = R.layout.home_item_seckil;
                break;
            case RECOMMEND_INFO:
                type = R.layout.home_item_recommend;
                break;
            case HOT_INFO:
                type = R.layout.home_item_hot;
                break;
        }
        return type;
    }

    @Override
    protected void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position) {
            case BANNER_INFO:
                List<HoemBean.ResultBean.BannerInfoBean> banner_info = (List<HoemBean.ResultBean.BannerInfoBean>) itemData;

                Banner banner = holder.getView(R.id.frag_home_rv_banner);
                banner.setImages(banner_info);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HoemBean.ResultBean.BannerInfoBean bannerInfoBean = (HoemBean.ResultBean.BannerInfoBean) path;
                        Glide.with(context)
                                .load(Constants.BASE_URl_IMAGE + bannerInfoBean.getImage())
                                .placeholder(R.drawable.new_img_loading_1)
                                .into(imageView);
                    }
                });
                banner.start();
                break;
            case CHANNEL_INFO:
                List<HoemBean.ResultBean.ChannelInfoBean> channel_info = (List<HoemBean.ResultBean.ChannelInfoBean>) itemData;
                HoemHandAdapter hoemHandAdapter = new HoemHandAdapter(holder.itemView.getContext(), channel_info);
                GridView gridView = holder.itemView.findViewById(R.id.frag_home_rv_gv);
                gridView.setAdapter(hoemHandAdapter);

                gridView.setOnItemClickListener((adapterView, view, i, l) -> {
                    Toast.makeText(holder.itemView.getContext(), "点击"+i, Toast.LENGTH_SHORT).show();
                });

                break;
            case ACT_INFO:
                List<HoemBean.ResultBean.ActInfoBean> act_info = (List<HoemBean.ResultBean.ActInfoBean>) itemData;
                ViewPager vp = holder.itemView.findViewById(R.id.frag_home_vp);
                ArrayList<ImageView> imageViews = new ArrayList<>();
                for (HoemBean.ResultBean.ActInfoBean actInfoBean : act_info) {
                    ImageView imageView = new ImageView(holder.itemView.getContext());
                    Glide.with(holder.itemView.getContext()).load(Constants.BASE_URl_IMAGE + actInfoBean.getIcon_url()).placeholder(R.drawable.new_img_loading_1).into(imageView);
                    imageViews.add(imageView);
                }
                ActAdapter actAdapter = new ActAdapter(imageViews);
                vp.setAdapter(actAdapter);
                break;
            case SECKIL_INFO:
                HoemBean.ResultBean.SeckillInfoBean seckill_info = (HoemBean.ResultBean.SeckillInfoBean) itemData;
                List<HoemBean.ResultBean.SeckillInfoBean.ListBean> list = seckill_info.getList();
                RecyclerView rv = holder.itemView.findViewById(R.id.frag_home_seckil_rv);
                rv.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.HORIZONTAL, false));
                TextView textView = holder.itemView.findViewById(R.id.frag_home_seckil_time);
                SeckilAdapter seckilAdapter = new SeckilAdapter(list);
                rv.setAdapter(seckilAdapter);



                String end = seckill_info.getEnd_time();
                String start = seckill_info.getStart_time();
                long time = Integer.parseInt(end) - Integer.parseInt(start);
                String format = new SimpleDateFormat("HH:mm:ss").format(new Date(time));
                textView.setText(format);

                break;
            case RECOMMEND_INFO:
                List<HoemBean.ResultBean.RecommendInfoBean> recommend_info = (List<HoemBean.ResultBean.RecommendInfoBean>) itemData;
                RecyclerView recomrv = holder.itemView.findViewById(R.id.frag_home_recom_rv);
                recomrv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),3));
                RecommendAdapter recommendAdapter = new RecommendAdapter(recommend_info);
                recomrv.setAdapter(recommendAdapter);

                recommendAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(holder.itemView.getContext(), ParticularsActivity.class);
                        intent.putExtra("code",1);
                        intent.putExtra("recommend",recommend_info.get(position));
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });

                break;
            case HOT_INFO:
                List<HoemBean.ResultBean.HotInfoBean> hot_info = (List<HoemBean.ResultBean.HotInfoBean>) itemData;
                RecyclerView hotrv = holder.itemView.findViewById(R.id.frag_home_hot_rv);
                hotrv.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(),2));
                HotAdapter hotAdapter = new HotAdapter(hot_info);
                hotrv.setAdapter(hotAdapter);

                hotAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(holder.itemView.getContext(), ParticularsActivity.class);
                        intent.putExtra("code",2);
                        intent.putExtra("hotInfo",hot_info.get(position));
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });

                break;
        }
    }

    @Override
    protected int getRootViewType(int position) {
        return position;
    }
}

