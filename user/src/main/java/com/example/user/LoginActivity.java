package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commom.ShopConstants;
import com.example.framework.BaseActivity;
import com.example.user.login.LoginFragment;
import com.example.user.register.RegisterFragment;

@Route(path = "/user/LoginActivity")
public class LoginActivity extends BaseActivity implements LoginFragment.IShowRegisterInterface {

    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(ShopConstants.REGISTER_PATH);

        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.user_ll, registerFragment);
        fragmentTransaction.add(R.id.user_ll, loginFragment);
        if (stringExtra==null){
            fragmentTransaction.hide(registerFragment);
            fragmentTransaction.show(loginFragment);
        }else {
            if (stringExtra.equals(ShopConstants.REGISTER_PATH)) {
                fragmentTransaction.hide(loginFragment);
                fragmentTransaction.show(registerFragment);
            } else {
                fragmentTransaction.hide(registerFragment);
                fragmentTransaction.show(loginFragment);
            }
        }

        fragmentTransaction.commit();


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onShowRegister() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(loginFragment);
        fragmentTransaction.show(registerFragment);
        fragmentTransaction.commit();
    }
}