package com.example.shoppingmallsix.mainactivity;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.net.bean.MainBean;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classifyfragment.ClassifyFragment;
import com.example.shoppingmallsix.fragment.discoverfragment.DiscoverFragment;
import com.example.shoppingmallsix.fragment.homefragment.HomeFragment;
import com.example.shoppingmallsix.fragment.minefragment.MineFragment;
import com.example.shoppingmallsix.fragment.shoppingcarfragment.ShoppingCarFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {

    private FrameLayout mainFram;
    private CommonTabLayout mainCommon;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mainFram = findViewById(R.id.mainFram);
        mainCommon = findViewById(R.id.mainCommon);

        mTabEntities.add(new MainBean("首页", R.drawable.main_home_press, R.drawable.main_home));
        mTabEntities.add(new MainBean("分类", R.drawable.main_type_press, R.drawable.main_type));
        mTabEntities.add(new MainBean("发现", R.drawable.main_community_press, R.drawable.main_community));
        mTabEntities.add(new MainBean("购物车", R.drawable.main_cart_press, R.drawable.main_cart));
        mTabEntities.add(new MainBean("个人中心", R.drawable.main_user_press, R.drawable.main_user));
        mainCommon.setTabData(mTabEntities);


        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new HomeFragment()).commitAllowingStateLoss();

        mainCommon.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new HomeFragment()).commitAllowingStateLoss();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new ClassifyFragment()).commitAllowingStateLoss();
                    break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new DiscoverFragment()).commitAllowingStateLoss();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new ShoppingCarFragment()).commitAllowingStateLoss();
                        break;
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new MineFragment()).commitAllowingStateLoss();
                        break;
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
