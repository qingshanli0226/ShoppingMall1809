package com.example.myapplication;


import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.common.type.ToLoginType;
import com.example.common.type.TypeString;
import com.example.framework.manager.CaCheArote;
import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.MessageNumView;
import com.example.myapplication.type.TypefyFragment;
import com.example.myapplication.discover.DiscoverFragment;
import com.example.myapplication.home.HomeFragment;
import com.example.myapplication.personalcenter.PersonalCenterFragment;
import com.example.myapplication.shoppingcart.ShoppingCartFragment;
import com.example.user.AutoService;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;


import java.util.ArrayList;
import java.util.List;

import com.example.framework.BaseActivity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MainActivity extends BaseActivity {

    private com.flyco.tablayout.CommonTabLayout mainComm;
    private ArrayList<CustomTabEntity> cusList = new ArrayList<>();
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();
    private boolean isLogin;//判断是否登陆
    private com.example.framework.view.MessageNumView numView;
    private android.widget.Button btn;
    private android.widget.FrameLayout mainFra;

    @Override
    public int bandLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mainComm = (CommonTabLayout) findViewById(R.id.mainComm);
        numView = (MessageNumView) findViewById(R.id.numView);

        //自动登录
        Intent intent1 = new Intent(this, AutoService.class);
        startService(intent1);

        //注册Eventbus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        btn = findViewById(R.id.btn);
        mainFra = findViewById(R.id.mainFra);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UmengActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initPresenter() {
    }

    @Override
    public void onShoppinCartgData(List shoppingCartBean) {
        super.onShoppinCartgData(shoppingCartBean);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String page = extras.getString("page");
            mainComm.setCurrentTab(Integer.parseInt(page));
        }
    }

    @Override
    public void initData() {
        numView.getNum(CaCheMannager.getInstance().getShoppingCartBeanList().size());
        fragments.add(new HomeFragment());
        fragments.add(new TypefyFragment());
        fragments.add(new DiscoverFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new PersonalCenterFragment());

        strings.add(getString(R.string.mainFragmentTitle1));
        strings.add(getString(R.string.mainFragmentTitle2));
        strings.add(getString(R.string.mainFragmentTitle3));
        strings.add(getString(R.string.mainFragmentTitle4));
        strings.add(getString(R.string.mainFragmentTitle5));

        cusList.add(new MainCustomTabEntity(strings.get(0), R.mipmap.main_home_press, R.mipmap.main_home));
        cusList.add(new MainCustomTabEntity(strings.get(1), R.mipmap.main_type_press, R.mipmap.main_type));
        cusList.add(new MainCustomTabEntity(strings.get(2), R.mipmap.main_community_press, R.mipmap.main_community));
        cusList.add(new MainCustomTabEntity(strings.get(3), R.mipmap.main_cart_press, R.mipmap.main_cart));
        cusList.add(new MainCustomTabEntity(strings.get(4), R.mipmap.main_user_press, R.mipmap.main_user));

        mainComm.setTabData(cusList, this, R.id.mainFra, fragments);
        mainComm.setTextSelectColor(Color.RED);
        mainComm.setTextUnselectColor(Color.BLACK);

        //点击切换fragment的时候  判断是否登陆
        mainComm.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position != 0) {
                    //获取登陆状态
                    isLogin = CacheUserManager.getInstance().getIsLogin();
                    if (!isLogin) {
                        mainComm.setCurrentTab(0);
                        ToLoginType.getInstance().setActivityType(TypeString.MAIN_TYPE);
                        //没有登陆
                        Bundle bundle = new Bundle();
                        bundle.putString("falg", "2");
                        CaCheArote.getInstance().getUserInterface().openLoginActivity(MainActivity.this, bundle);
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    //如果数据删除或者下订单则将红点数据刷新
    @Subscribe
    public void invalidate(String flag) {
        if (flag.equals("1")) {
            numView.getNum(CaCheMannager.getInstance().getShoppingCartBeanList().size());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        destroy();
    }
}