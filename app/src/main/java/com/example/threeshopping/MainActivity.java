package com.example.threeshopping;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.Constants;
import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.UserManager;
import com.example.framework.view.CircleView;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.threeshopping.cart.CartFragment;
import com.example.threeshopping.communit.CommunitFragment;
import com.example.threeshopping.home.HomeFragment;
import com.example.threeshopping.particulars.ParticularsActivity;
import com.example.threeshopping.personal.PersonalFragment;
import com.example.threeshopping.type.TypeFragment;
import com.example.threeshopping.welcome.WelActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{

    private LoginBean loginBean;
    private com.example.framework.view.ToolBar toolbar;
    private com.google.android.material.bottomnavigation.BottomNavigationView btv;
    private CircleView mainCircle;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        fragments = new ArrayList<>();
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        btv = (BottomNavigationView) findViewById(R.id.btv);
        mainCircle = (CircleView) findViewById(R.id.mainCircle);
    }

    @Override
    public void initPresenter() {

    }

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    public void initData() {

        //注册

        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        //添加fragment
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunitFragment());
        fragments.add(new CartFragment());
        fragments.add(new PersonalFragment());


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mainLinear, fragments.get(0));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(1));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(2));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(3));
        fragmentTransaction.add(R.id.mainLinear, fragments.get(4));
        fragmentTransaction.commit();


        //第一个显示
        btv.setSelectedItemId(R.id.mainOne);
        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mainOne:
                        showFragment(0);
                        break;
                    case R.id.mainTwo:
                        showFragment(1);
                        skip(1);
                        break;
                    case R.id.mainThree:
                        showFragment(2);
                        skip(2);
                        break;
                    case R.id.mainFour:
                        showFragment(3);
                        skip(3);
                        break;
                    case R.id.mainFive:
                        showFragment(4);
                        skip(4);
                        break;
                }
                return true;
            }
        });

        loginBean = UserManager.getInstance().getLoginBean();
        if(loginBean != null){
            if(CacheShopManager.getInstance().getCarts().size() > 0){
                mainCircle.setText(CacheShopManager.getInstance().getCarts().size()+"");
                mainCircle.setVisibility(View.VISIBLE);
            }
        }

    }

    private void skip(int page) {
        if (loginBean == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("page", page);
            CommonArouter.getInstance().build(Constants.PATH_USER).with(bundle).navigation();
        }
    }

    //显示那个fargment
    private void showFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position) {
                fragmentTransaction.show(fragments.get(i));
            } else {
                fragmentTransaction.hide(fragments.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = CommonArouter.getInstance().getBundle();
        int page = bundle.getInt("page");


        showFragment(page);

        switch (page) {
            case 0:
                btv.setSelectedItemId(R.id.mainOne);
                break;
        }
    }

    @Subscribe
    public void updateCircle(String string){
        if(CacheShopManager.getInstance().getCarts().size() > 0){
            mainCircle.setText(CacheShopManager.getInstance().getCarts().size()+"");
            mainCircle.setVisibility(View.VISIBLE);
        }
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

    @Override
    public void onUserChange(LoginBean loginBean) {
        super.onUserChange(loginBean);
        this.loginBean = loginBean;
        if(CacheShopManager.getInstance().getCarts().size() > 0){
            mainCircle.setText(CacheShopManager.getInstance().getCarts().size()+"");
            mainCircle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}