package com.example.electricityproject.home;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.bean.HomeBean;
import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenter> implements CallHomeData{
    private Banner banner;
    private List<String> list=new ArrayList<>();
    @Override
    protected void initData() {
        httpPresenter=new HomePresenter(this);
        httpPresenter.getHomeBannerData();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        banner = mView.findViewById(R.id.banner);
        list.clear();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {
        loadingPage.showError(error);
    }

    @Override
    public void onHomeBanner(HomeBean homeBean) {
        List<HomeBean.ResultBean.BannerInfoBean> banner_info = homeBean.getResult().getBanner_info();
        for (HomeBean.ResultBean.BannerInfoBean bannerInfoBean : banner_info) {
            list.add("http://49.233.0.68:8080/atguigu/img"+bannerInfoBean.getImage());
            Log.i("zx", "onHomeBanner: http://49.233.0.68:8080/atguigu/img"+bannerInfoBean.getImage()+"");
        }
        banner.setImages(list);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                String s= (String) path;
                Glide.with(getActivity()).load(s).into(imageView);
            }
        });
        banner.start();
    }
}