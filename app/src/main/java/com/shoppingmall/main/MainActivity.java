package com.shoppingmall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ScrollView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shoppingmall.bean.CustomBean;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.HomeBean;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private android.widget.ScrollView mainScroll;
    private androidx.viewpager.widget.ViewPager mainVp;
    private com.flyco.tablayout.CommonTabLayout mainCommon;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mainScroll = (ScrollView) findViewById(R.id.mainScroll);
        mainVp = (ViewPager) findViewById(R.id.mainVp);
        mainCommon = (CommonTabLayout) findViewById(R.id.mainCommon);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        //拿到数据
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_home),R.drawable.ic_launcher_home_table,R.drawable.ic_launcher_home02_table));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_sort),R.drawable.ic_launcher_home_table,R.drawable.ic_launcher_home02_table);
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_find),R.drawable.ic_launcher_home_table,R.drawable.ic_launcher_home02_table);
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_shopCar),R.drawable.ic_launcher_home_table,R.drawable.ic_launcher_home02_table);
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_mine),R.drawable.ic_launcher_home_table,R.drawable.ic_launcher_home02_table);
        mainCommon.setTabData(customTabEntities);
        mainCommon.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainVp.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mainVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainCommon.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}