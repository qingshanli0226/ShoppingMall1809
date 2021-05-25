package com.example.threeshopping.welcome;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.manager.CacheHomeManager;
import com.example.net.bean.HomeBean;
import com.example.threeshopping.R;
import com.example.user.service.AutoService;

public class WelActivity extends BaseActivity<HomePresenter>  implements IHomeView {


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
        Intent intent = new Intent(this, AutoService.class);
        startService(intent);
    }

    @Override
    public void initData() {
        mPresenter.getHome();
    }

    @Override
    public void onHome(HomeBean homeBean) {
        CacheHomeManager.getInstance().setHomeBean(homeBean);
        CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
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
}