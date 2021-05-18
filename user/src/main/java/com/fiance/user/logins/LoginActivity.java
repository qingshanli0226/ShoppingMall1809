package com.fiance.user.logins;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fiance.user.R;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.bean.LoginBean;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {


    @Override
    public void onLoginData(LoginBean loginBean) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}