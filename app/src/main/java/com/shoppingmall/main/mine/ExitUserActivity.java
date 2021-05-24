package com.shoppingmall.main.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.net.sp.SpUtil;

public class ExitUserActivity extends BaseActivity {

    private android.widget.TextView exitUser;

    @Override
    public int getLayoutId() {
        return R.layout.activity_exit_user;
    }

    @Override
    public void initView() {
        exitUser = (TextView) findViewById(R.id.exitUser);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        exitUser.setOnClickListener(v->{
            SpUtil.putString(this,"token","");
            ShopMallUserManager.getInstance().setLoginBean(null);
            finish();
        });
    }
}