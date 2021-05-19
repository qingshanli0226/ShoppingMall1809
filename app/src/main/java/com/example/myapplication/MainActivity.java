package com.example.myapplication;


import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.widget.FrameLayout;

import com.example.myapplication.classify.ClassifyFragment;
import com.example.myapplication.discover.DiscoverFragment;
import com.example.myapplication.home.HomeFragment;
import com.example.myapplication.my.MyFragment;
import com.example.myapplication.shoppingtrolley.ShoppingTrolleyFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.List;

import com.example.framework.BaseActivity;

public class MainActivity extends BaseActivity {

    private com.flyco.tablayout.CommonTabLayout mainComm;
    private android.widget.FrameLayout mainFra;
    private ArrayList<CustomTabEntity> cusList=new ArrayList<>();
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private List<String> strings=new ArrayList<>();

    @Override
    public int bandLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void initView() {
        mainComm = (CommonTabLayout) findViewById(R.id.mainComm);
        mainFra = (FrameLayout) findViewById(R.id.mainFra);
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
        fragments.add(new MyFragment());

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
    }
}