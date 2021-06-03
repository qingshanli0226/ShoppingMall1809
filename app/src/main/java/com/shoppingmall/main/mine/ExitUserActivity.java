package com.shoppingmall.main.mine;

import android.content.Intent;
import android.widget.TextView;

import com.shoppingmall.R;
import com.shoppingmall.framework.manager.CacheShopManager;
import com.shoppingmall.framework.manager.ShopMallUserManager;
import com.shoppingmall.framework.mvp.BaseActivity;
import com.shoppingmall.main.MainActivity;
import com.shoppingmall.net.sp.SpUtil;

import org.greenrobot.eventbus.EventBus;

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
            CacheShopManager.getInstance().destroy();
            Intent intent = new Intent(ExitUserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}