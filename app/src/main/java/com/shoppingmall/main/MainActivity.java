package com.shoppingmall.main;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.widget.ScrollView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shoppingmall.R;
import com.shoppingmall.bean.CustomBean;
import com.shoppingmall.framework.manager.CacheManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.framework.mvp.IBaseView;
import com.shoppingmall.main.adapter.ComAdapter;
import com.shoppingmall.main.find.FindFragment;
import com.shoppingmall.main.home.HomeFragment;
import com.shoppingmall.main.mine.MineFragment;
import com.shoppingmall.main.shopcar.ShopCarFragment;
import com.shoppingmall.main.sort.SortFragment;
import com.shoppingmall.net.bean.HomeBean;


import java.util.ArrayList;

public class MainActivity extends BaseActivity  {


    private ComAdapter commonAdapter;
    private ViewPager mainVp;
    private CommonTabLayout mainCommon;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainVp = (ViewPager) findViewById(R.id.mainVp);
        mainCommon = (CommonTabLayout) findViewById(R.id.mainCommon);

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        //拿到数据
        HomeBean homeBean = CacheManager.getInstance().getHomeBean();

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new SortFragment());
        list.add(new FindFragment());
        list.add(new ShopCarFragment());
        list.add(new MineFragment());
        commonAdapter = new ComAdapter(getSupportFragmentManager(),list);
        mainVp.setAdapter(commonAdapter);

        ArrayList<CustomTabEntity> customTabEntities = new ArrayList<>();
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_home),R.drawable.main_home_press,R.drawable.main_home));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_sort),R.drawable.main_type_press,R.drawable.main_type));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_find),R.drawable.main_community_press,R.drawable.main_community));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_shopCar),R.drawable.main_cart_press,R.drawable.main_cart));
        customTabEntities.add(new CustomBean(getString(R.string.mainActivity_fragment_title_mine),R.drawable.main_user_press,R.drawable.main_user));
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