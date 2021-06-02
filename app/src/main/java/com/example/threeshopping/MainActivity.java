package com.example.threeshopping;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.common.Constants;

import com.example.common.LogUtil;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.ActManager;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.CircleView;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;
import com.example.threeshopping.cart.CartFragment;
import com.example.threeshopping.communit.CommunitFragment;
import com.example.threeshopping.home.HomeFragment;
import com.example.threeshopping.personal.PersonalFragment;
import com.example.threeshopping.type.TypeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tencent.bugly.crashreport.CrashReport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

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
        if(Build.VERSION.SDK_INT>=23){
            String[] mPermissionList =new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,mPermissionList,123);
        }
        //注册

        if (!EventBus.getDefault().isRegistered(this)) {
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
                switch (item.getItemId()) {
                    case R.id.mainOne:
                        showFragment(0);
                        break;
                    case R.id.mainTwo:
//                        CrashReport.testJavaCrash();
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


        loginBean = CacheUserManager.getInstance().getLoginBean();
        if (loginBean != null) {
            if (CacheShopManager.getInstance().getCarts().size() > 0) {
                mainCircle.setText(CacheShopManager.getInstance().getCarts().size() + "");

                loginBean = CacheUserManager.getInstance().getLoginBean();
                if (loginBean != null) {
                    if (CacheShopManager.getInstance().getCarts().size() > 0) {
                        mainCircle.setText(CacheShopManager.getInstance().getCarts().size() + "");

                        mainCircle.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    private void skip(int page) {
        if (loginBean == null) {
            Bundle bundle = new Bundle();
            bundle.putInt("page", page);
            CommonArouter.getInstance().build(Constants.PATH_USER).with(bundle).navigation();
            finish();
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

        LogUtil.d("page" + page);

        showFragment(page);
        switch (page) {
            case 0:
                showFragment(0);
                btv.setSelectedItemId(R.id.mainOne);
                break;
            case 1:
                showFragment(1);
                btv.setSelectedItemId(R.id.mainTwo);
                break;
            case 2:
                showFragment(2);
                btv.setSelectedItemId(R.id.mainThree);
                break;
            case 3:
                showFragment(3);
                btv.setSelectedItemId(R.id.mainFour);
                break;
            case 4:
                showFragment(4);
                btv.setSelectedItemId(R.id.mainFive);
                break;
        }
    }

    @Subscribe
    public void updateCircle(String string) {
        if (CacheShopManager.getInstance().getCarts().size() > 0) {
            mainCircle.setText(CacheShopManager.getInstance().getCarts().size() + "");
            mainCircle.setVisibility(View.VISIBLE);
        } else {
            mainCircle.setVisibility(View.GONE);

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
        if (CacheShopManager.getInstance().getCarts().size() > 0) {
            mainCircle.setText(CacheShopManager.getInstance().getCarts().size() + "");
            mainCircle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
//            List<Activity> activities = ActManager.getActManager().getActivities();
//            for (Activity activity : activities) {
//                activity.finish();
//            }
//            System.exit(0);
        }
        return true;

    }

    @Override
    public void destroy() {
        super.destroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}