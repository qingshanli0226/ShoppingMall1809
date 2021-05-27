package com.example.shoppingmallsix.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.manager.SoppingCartMemoryDataManager;
import com.example.net.bean.business.GetShortcartProductsBean;
import com.example.net.bean.user.LoginBean;
import com.example.net.bean.MainBean;
import com.example.shoppingmallsix.R;
import com.example.shoppingmallsix.fragment.classify.ClassifyFragment;
import com.example.shoppingmallsix.fragment.discover.DiscoverFragment;
import com.example.shoppingmallsix.fragment.home.HomeFragment;
import com.example.shoppingmallsix.fragment.mine.MineFragment;
import com.example.shoppingmallsix.fragment.shoppingcar.ShoppingCarFragment;
import com.example.user.login.LoginActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity implements CacheUserManager.ILoginChange, SoppingCartMemoryDataManager.ISoppingDateChange {

    private FrameLayout mainFram;
    private CommonTabLayout mainCommon;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Fragment> list = new ArrayList<>();
    private android.widget.TextView sign;
    private List<GetShortcartProductsBean.ResultBean> resultBeans = new ArrayList<>();

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        CacheUserManager.getInstance().registerLogin(this);
        LoginBean loginBean = CacheUserManager.getInstance().getLoginBean();
        if (loginBean == null) {
//            Toast.makeText(MainActivity.this, getString(R.string.loginNo), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, getString(R.string.login), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initView() {
        mainFram = findViewById(R.id.mainFram);
        mainCommon = findViewById(R.id.mainCommon);
        sign = (TextView) findViewById(R.id.mainSign);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mTabEntities.add(new MainBean(getString(R.string.homepage), R.drawable.main_home_press, R.drawable.main_home));
        mTabEntities.add(new MainBean(getString(R.string.classify), R.drawable.main_type_press, R.drawable.main_type));
        mTabEntities.add(new MainBean(getString(R.string.discover), R.drawable.main_community_press, R.drawable.main_community));
        mTabEntities.add(new MainBean(getString(R.string.shopping), R.drawable.main_cart_press, R.drawable.main_cart));
        mTabEntities.add(new MainBean(getString(R.string.personal), R.drawable.main_user_press, R.drawable.main_user));
        mainCommon.setTabData(mTabEntities);
        //handler实时监听子线程更新数据
        handler.sendEmptyMessageDelayed(1, 1000);
        //注册接口
        SoppingCartMemoryDataManager.getInstance().registerHoppingCartMemory(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new HomeFragment()).commitAllowingStateLoss();

        mainCommon.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new HomeFragment()).commitAllowingStateLoss();
                        break;
                    case 1:
                        LoginBean loginBean1 = CacheUserManager.getInstance().getLoginBean();
                        if (loginBean1 != null) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new ClassifyFragment()).commitAllowingStateLoss();
                        } else {
                            mainCommon.setCurrentTab(0);
                            Toast.makeText(MainActivity.this, getString(R.string.Pleaselogintoyouraccountfirst), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        LoginBean loginBean2 = CacheUserManager.getInstance().getLoginBean();
                        if (loginBean2 != null) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new DiscoverFragment()).commitAllowingStateLoss();
                        } else {
                            mainCommon.setCurrentTab(0);
                            Toast.makeText(MainActivity.this, getString(R.string.Pleaselogintoyouraccountfirst), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 3:
                        LoginBean loginBean3 = CacheUserManager.getInstance().getLoginBean();
                        if (loginBean3 != null) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new ShoppingCarFragment()).commitAllowingStateLoss();
                        } else {
                            mainCommon.setCurrentTab(0);
                            Toast.makeText(MainActivity.this, getString(R.string.Pleaselogintoyouraccountfirst), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 4:
                        LoginBean loginBean4 = CacheUserManager.getInstance().getLoginBean();
                        if (loginBean4 != null) {
                            getSupportFragmentManager().beginTransaction().replace(R.id.mainFram, new MineFragment()).commitAllowingStateLoss();
                        } else {
                            mainCommon.setCurrentTab(0);
                            Toast.makeText(MainActivity.this, getString(R.string.Pleaselogintoyouraccountfirst), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
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

    @Override
    public void onLoginChange(LoginBean loginBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheUserManager.getInstance().unRegisterLogin(this);
        SoppingCartMemoryDataManager.getInstance().unHoppingCartMemory(this);
    }

    //子线程获取数据 实时刷新
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            GetShortcartProductsBean resultBean = SoppingCartMemoryDataManager.getResultBean();
            if (resultBean != null) {
                resultBeans.clear();
                List<GetShortcartProductsBean.ResultBean> result = resultBean.getResult();
                if (result != null && result.size() != 0) {
                    resultBeans.addAll(result);
                    sign.setVisibility(View.VISIBLE);
                    sign.setText(result.size() + "");
                }
            } else {
                sign.setVisibility(View.GONE);
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    public void onSoppingDataChange(List<GetShortcartProductsBean.ResultBean> resultBeanList) {
        if (resultBeanList.size() != 0) {
            sign.setVisibility(View.VISIBLE);
            sign.setText(resultBeanList.size() + "");
        } else {
            sign.setVisibility(View.GONE);
        }
    }

}
