package com.shoppingmall.bawei.shoppingmall1809.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.fiannce.bawei.framework.BaseRvAdapter;
import com.fiannce.bawei.framework.view.ToolBar;
import com.fiannce.bawei.net.mode.ShopmallHomeBean;
import com.shoppingmall.bawei.shoppingmall1809.R;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class HomeAdapter extends BaseRvAdapter<Object> {

    private final int BANNER_TYPE = 0;
    private final int CHANNEL_TYPE = 1;
    private final int ACT_TYPE = 2;


    @Override
    public int getLayoutId(int viewType) {
        int layoutId = -1;
        switch (viewType) {
            case BANNER_TYPE:
                layoutId = R.layout.home_item_view_banner;
                break;
            case CHANNEL_TYPE:
                layoutId = R.layout.home_item_view_recommend;
                break;
            case ACT_TYPE:
                layoutId = R.layout.home_item_view_act;
                break;
        }
        return layoutId;
    }

    @Override
    public void displayViewHolder(BaseViewHolder holder, int position, Object itemData) {
        switch (position) {
            case 0:
                //获取第0个位置的数据,也就是Banner的数据
                List<ShopmallHomeBean.ResultBean.BannerInfoBean> bannerInfoBeans = (List<ShopmallHomeBean.ResultBean.BannerInfoBean>) itemData;
                Banner banner = holder.getView(R.id.banner);
                banner.setAdapter(new BannerImageAdapter<ShopmallHomeBean.ResultBean.BannerInfoBean>(bannerInfoBeans) {

                    @Override
                    public void onBindView(BannerImageHolder holder, ShopmallHomeBean.ResultBean.BannerInfoBean data, int position, int size) {
                        Glide.with(holder.itemView)
                                .load("http://49.233.0.68:8080"+"/atguigu/img"+data.getImage())
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver((AppCompatActivity)holder.itemView.getContext())//添加生命周期观察者
                        .setIndicator(new CircleIndicator(holder.itemView.getContext()));
                break;
            case 1:
                List<ShopmallHomeBean.ResultBean.ChannelInfoBean> channelInfoBeanList = (List<ShopmallHomeBean.ResultBean.ChannelInfoBean>) itemData;
                RecyclerView recommedRv = holder.getView(R.id.recommendRv);
                recommedRv.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL));
                ChannelAdapter channelAdapter = new ChannelAdapter();
                recommedRv.setAdapter(channelAdapter);
                channelAdapter.updateData(channelInfoBeanList);
                channelAdapter.setRecyclerItemClickListener(new BaseRvAdapter.IRecyclerItemClickListener() {
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
                //ShopmallHomeBean.ResultBean.ActInfoBean actInfoBean = (ShopmallHomeBean.ResultBean.ActInfoBean) dataList.get(position);
                break;
        }
    }

    @Override
    public int getRootViewType(int position) {
        //通过position来手动指定该位置的UI类型
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
            default:
                break;
        }
        return type;
    }

}
