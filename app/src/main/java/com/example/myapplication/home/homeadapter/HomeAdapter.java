package com.example.myapplication.home.homeadapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.common.type.ToLoginType;
import com.example.common.type.TypeString;
import com.example.framework.BaseRecyclerViewAdapter;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.ShopmallGlide;
import com.example.myapplication.R;
import com.example.myapplication.particulars.ParticularsActivity;
import com.example.net.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseRecyclerViewAdapter<Object> {
    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;
    private final int END_TYPE = 3;
    private final int NEW_TYPE = 4;
    private final int GOOD_TYPE = 5;

    @Override
    public int getLayoutId(int viewType) {
        int layoutId = -1;
        switch (viewType) {
            case BANNER_TYPE:
                layoutId = R.layout.home_item_banner;
                break;
            case CHANNEL_TYPE:
                layoutId = R.layout.home_item_recommend;
                break;
            case ACT_TYPE:
                layoutId = R.layout.home_item_viewpagerimage;
                break;
            case END_TYPE:
                layoutId = R.layout.home_item_end;
                break;
            case NEW_TYPE:
                layoutId = R.layout.home_item_new;
                break;
            case GOOD_TYPE:
                layoutId = R.layout.home_item_good;
                break;
//            case Skirt_TYPE:
//                layoutId=R.layout.item_skirt;
//                break;

        }
        return layoutId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position) {
            case 0:
                //获取Banner数据
                List<HomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<HomeBean.ResultBean.BannerInfoBean>) itemData;
                Banner banner = holder.getView(R.id.homeBanner);
                banner.setImages(bannerInfoBeans);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.ResultBean.BannerInfoBean a = (HomeBean.ResultBean.BannerInfoBean) path;
                        ShopmallGlide.with(context).load("http://49.233.0.68:8080" + "/atguigu/img" + a.getImage()).into(imageView);
                    }
                }).start();
                break;
            case 1:
                List<HomeBean.ResultBean.ChannelInfoBean> list = (List<HomeBean.ResultBean.ChannelInfoBean>) itemData;
                RecyclerView rec = holder.getView(R.id.homeRecommendRec);
                rec.setLayoutManager(new StaggeredGridLayoutManager(5, LinearLayoutManager.VERTICAL));
                //创建嵌套的recycler
                RecommentRecAdapter recommentRecAdapter = new RecommentRecAdapter();
                rec.setAdapter(recommentRecAdapter);
                recommentRecAdapter.updataData(list);
                //点击
                recommentRecAdapter.setRecyclerItemClickListener(new BaseRecyclerViewAdapter.IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }

                    @Override
                    public void onItemLongClick(int position) {

                    }
                });
                break;
            case 2:
                List<HomeBean.ResultBean.ActInfoBean> listAct = (List<HomeBean.ResultBean.ActInfoBean>) itemData;
                ViewPager vp = holder.getView(R.id.homeViewPagerImage);

                View view1 = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.home_item_view1, null);
                ImageView ima1 = view1.findViewById(R.id.ima1);
                ShopmallGlide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080" + "/atguigu/img" + listAct.get(0).getIcon_url()).into(ima1);

                View view2 = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.home_item_view2, null);
                ImageView ima2 = view2.findViewById(R.id.ima2);
                ShopmallGlide.with(holder.itemView.getContext()).load("http://49.233.0.68:8080" + "/atguigu/img" + listAct.get(1).getIcon_url()).into(ima2);
                ArrayList<View> views = new ArrayList<>();
                views.add(view1);
                views.add(view2);
                PagerAdapter pagerAdapter = new PagerAdapter() {
                    @Override
                    public int getCount() {
                        return views.size();
                    }

                    @Override
                    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                        return view == object;
                    }

                    @Override
                    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                        container.addView(views.get(position));
                    }

                    @NonNull
                    @Override
                    public Object instantiateItem(@NonNull ViewGroup container, int position) {
                        container.addView(views.get(position));
                        return views.get(position);
                    }
                };
                vp.setAdapter(pagerAdapter);
                break;
            case 3:
                List<HomeBean.ResultBean.SeckillInfoBean.ListBean> list1 = (List<HomeBean.ResultBean.SeckillInfoBean.ListBean>) itemData;
                RecyclerView endRec = holder.getView(R.id.homeEndItemRec);
                EndRecAdapter endRecAdapter = new EndRecAdapter();
                endRecAdapter.updataData(list1);
                endRec.setAdapter(endRecAdapter);
                endRec.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
                //点击
                endRecAdapter.setRecyclerItemClickListener(new BaseRecyclerViewAdapter.IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //从哪里跳转登录页面
                        ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                        Bundle bundle = new Bundle();
                        bundle.putString("pic", list1.get(position).getFigure());
                        bundle.putString("name", list1.get(position).getName());
                        bundle.putString("price", list1.get(position).getCover_price());
                        bundle.putString("id", list1.get(position).getProduct_id());
                        Intent intent = new Intent(holder.itemView.getContext(), ParticularsActivity.class);
                        intent.putExtras(bundle);
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {
//                        Toast.makeText(holder.itemView.getContext(), "长按了Item", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 4:
                List<HomeBean.ResultBean.RecommendInfoBean> list2 = (List<HomeBean.ResultBean.RecommendInfoBean>) itemData;
                RecyclerView newRec = holder.getView(R.id.homeNewItemRec);
                newRec.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                NewRecAdapter newRecAdapter = new NewRecAdapter();
                newRecAdapter.updataData(list2);
                newRec.setAdapter(newRecAdapter);
                newRecAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //从哪里跳转登录页面
                        ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                        Bundle bundle = new Bundle();
                        bundle.putString("pic", list2.get(position).getFigure());
                        bundle.putString("name", list2.get(position).getName());
                        bundle.putString("id", list2.get(position).getProduct_id());
                        bundle.putString("price", list2.get(position).getCover_price());
                        Intent intent = new Intent(holder.itemView.getContext(), ParticularsActivity.class);
                        intent.putExtras(bundle);
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {
//                        Toast.makeText(holder.itemView.getContext(), "长按1", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case 5:
                List<HomeBean.ResultBean.HotInfoBean> list3 = (List<HomeBean.ResultBean.HotInfoBean>) itemData;
                RecyclerView goodRec = holder.getView(R.id.homeGoodItemRec);
                GoodRecAdapter goodRecAdapter = new GoodRecAdapter();
                goodRecAdapter.updataData(list3);
                goodRec.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                goodRec.setAdapter(goodRecAdapter);

                goodRecAdapter.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //从哪里跳转登录页面
                        ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                        Bundle bundle = new Bundle();
                        bundle.putString("pic", list3.get(position).getFigure());
                        bundle.putString("name", list3.get(position).getName());
                        bundle.putString("id", list3.get(position).getProduct_id());
                        bundle.putString("price", list3.get(position).getCover_price());
                        Intent intent = new Intent(holder.itemView.getContext(), ParticularsActivity.class);
                        intent.putExtras(bundle);
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {
//                        Toast.makeText(holder.itemView.getContext(), "长按1", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
//            case 6:
//                List<SkirtBean.ResultBean.ChildBean> list4= (List<SkirtBean.ResultBean.ChildBean>) itemData;
//                RecyclerView view = holder.getView(R.id.img);
//                SkirAdapter skirAdapter = new SkirAdapter();
//                skirAdapter.updataData(list4);
//                view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
//                view.setAdapter(skirAdapter);
//
//                break;
        }
    }

    @Override
    public int getRootViewType(int position) {
        int type = -1;
        switch (position) {
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
                type = END_TYPE;
                break;
            case 4:
                type = NEW_TYPE;
                break;
            case 5:
                type = GOOD_TYPE;
                break;
//            case 6:
//                type=Skirt_TYPE;
        }
        return type;
    }
}
