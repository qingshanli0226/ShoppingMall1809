package com.shoppingmall.main.home;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shoppingmall.R;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.manager.FiannceArouter;
import com.shoppingmall.framework.mvp.BaseFragment;
import com.shoppingmall.main.home.adapter.MenuAdapter;
import com.shoppingmall.net.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;


public class HomeFragment extends BaseFragment {

    private EditText homeFragmentSearch;
    private Banner banner;
    private RecyclerView menuRv;
    private Banner bannerTwo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {

        homeFragmentSearch = (EditText) mView.findViewById(R.id.homeFragmentSearch);
        banner = (Banner) mView.findViewById(R.id.banner);
        menuRv = (RecyclerView) mView.findViewById(R.id.menuRv);
        bannerTwo = (Banner) mView.findViewById(R.id.bannerTwo);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //得到数据
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();
        setBanner(homeBean);
        setMenuRv(homeBean);
        setTwoBanner(homeBean);

    }

    //第二个轮播图
    private void setTwoBanner(HomeBean homeBean) {
        bannerTwo.setImages(homeBean.getResult().getAct_info());
        bannerTwo.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.ActInfoBean actInfoBean = (HomeBean.ResultBean.ActInfoBean) path;
                Glide.with(getContext()).load(actInfoBean.getIcon_url()).into(imageView);
            }
        });
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.start();
    }

    //小菜单
    private void setMenuRv(HomeBean homeBean) {
        MenuAdapter menuAdapter = new MenuAdapter(homeBean.getResult().getChannel_info());
        menuRv.setLayoutManager(new GridLayoutManager(getContext(), 5));
        menuRv.setAdapter(menuAdapter);
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FiannceArouter.getInstance().getIUserInterface().openLoginActivity(getContext(),null);
            }
        });
    }

    //设置轮播图
    private void setBanner(HomeBean homeBean) {
        banner.setImages(homeBean.getResult().getBanner_info());
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                HomeBean.ResultBean.BannerInfoBean bannerInfoBean = (HomeBean.ResultBean.BannerInfoBean) path;
                Glide.with(getContext()).load(bannerInfoBean.getImage()).into(imageView);
            }
        });
        banner.start();
    }

}