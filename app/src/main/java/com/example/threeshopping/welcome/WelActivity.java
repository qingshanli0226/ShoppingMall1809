package com.example.threeshopping.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheHomeManager;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;
import com.example.user.service.AutoService;

public class WelActivity extends BaseActivity<HomePresenter> implements IHomeView {


    @Override
    public int getLayoutId() {
        return R.layout.activity_wel;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void initData() {
        if (CacheConnectManager.getInstance().isConnect()) {
            mPresenter.getHome();
            Intent intent = new Intent(this, AutoService.class);
            startService(intent);
        } else {
            Toast.makeText(this, "网络走丢了", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onHome(HomeBean homeBean) {

        CacheHomeManager.getInstance().setHomeBean(homeBean);
        Bundle bundle = new Bundle();
        bundle.putInt("page",0);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
        finish();
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onDisConnect() {
        super.onDisConnect();
    }

    @Override
    public void onConect() {
        super.onConect();
        mPresenter.getHome();
        Intent intent = new Intent(this, AutoService.class);
        startService(intent);
        Toast.makeText(this, "正在缓冲...", Toast.LENGTH_SHORT).show();
    }
}