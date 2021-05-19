package com.example.threeshopping.home;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.common.Constants;
import com.example.framework.BaseFragment;
import com.example.framework.manager.CacheHomeManager;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;


public class HomeFragment extends BaseFragment {


    private ImageView imgSearch;
    private EditText edSearch;
    private ImageView imgCom;
    private Banner homeBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        imgSearch = (ImageView) findViewById(R.id.imgSearch);
        edSearch = (EditText) findViewById(R.id.edSearch);
        imgCom = (ImageView) findViewById(R.id.imgCom);
        homeBanner = (Banner) findViewById(R.id.homeBanner);
    }

    @Override
    protected void initPrensenter() {

    }

    @Override
    protected void initData() {
        //轮播图
        HomeBean homeBean = CacheHomeManager.getInstance().getHomeBean();
        List<HomeBean.ResultBean.BannerInfoBean> banner_info = homeBean.getResult().getBanner_info();
        homeBanner.setImages(banner_info)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HomeBean.ResultBean.BannerInfoBean bean = (HomeBean.ResultBean.BannerInfoBean) path;
                        Glide.with(context).load(Constants.BASE_URl_IMAGE+bean.getImage()).into(imageView);
                    }
                }).start();
    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }
}