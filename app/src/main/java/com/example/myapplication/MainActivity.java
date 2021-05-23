package com.example.myapplication;


import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CacheUserManager;
import com.example.myapplication.type.ClassifyFragment;
import com.example.myapplication.discover.DiscoverFragment;
import com.example.myapplication.home.HomeFragment;
import com.example.myapplication.personalCenter.PersonalCenterFragment;
import com.example.myapplication.shoppingtrolley.ShoppingTrolleyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;


import java.util.ArrayList;
import java.util.List;

import com.example.framework.BaseActivity;
import com.flyco.tablayout.listener.OnTabSelectListener;

public class MainActivity extends BaseActivity {

    private com.flyco.tablayout.CommonTabLayout mainComm;
    private ArrayList<CustomTabEntity> cusList=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private List<String> strings=new ArrayList<>();
    private boolean isLogin;//判断是否登陆

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainComm = (CommonTabLayout) findViewById(R.id.mainComm);


    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        fragments.add(new HomeFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new ShoppingTrolleyFragment());
        fragments.add(new PersonalCenterFragment());

        strings.add(getString(R.string.mainFragmentTitle1));
        strings.add(getString(R.string.mainFragmentTitle2));
        strings.add(getString(R.string.mainFragmentTitle3));
        strings.add(getString(R.string.mainFragmentTitle4));
        strings.add(getString(R.string.mainFragmentTitle5));

        cusList.add(new MainCustomTabEntity(strings.get(0),R.mipmap.main_home_press,R.mipmap.main_home));
        cusList.add(new MainCustomTabEntity(strings.get(1),R.mipmap.main_type_press,R.mipmap.main_type));
        cusList.add(new MainCustomTabEntity(strings.get(2),R.mipmap.main_community_press,R.mipmap.main_community));
        cusList.add(new MainCustomTabEntity(strings.get(3),R.mipmap.main_cart_press,R.mipmap.main_cart));
        cusList.add(new MainCustomTabEntity(strings.get(4),R.mipmap.main_user_press,R.mipmap.main_user));

        mainComm.setTabData(cusList,this,R.id.mainFra,fragments);
        mainComm.setTextSelectColor(Color.RED);
        mainComm.setTextUnselectColor(Color.BLACK);

        //点击切换fragment的时候  判断是否登陆
        mainComm.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position!=0){
                    //获取登陆状态
                    isLogin= CacheUserManager.getInstance().getIsLogin();
                    if (!isLogin){
                        //没有登陆
                        Bundle bundle = new Bundle();
                        bundle.putString("falg","2");
                        CaCheArote.getInstance().getUserInterface().openLoginActivity(MainActivity.this,bundle);
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}