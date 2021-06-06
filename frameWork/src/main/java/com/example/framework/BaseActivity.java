package com.example.framework;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.manager.ActManager;

import com.example.framework.manager.CacheAwaitPaymentManager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheShopManager;
import com.example.framework.manager.CacheUserManager;
import com.example.framework.view.LoadPage;
import com.example.framework.view.ToolBar;
import com.example.net.bean.LoginBean;


import java.util.List;

import retrofit2.http.HEAD;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarOnClickLisenter, CacheUserManager.IUserChange,CacheConnectManager.IConnect {

    protected P mPresenter;
    protected ToolBar toolBar;
    protected LoadPage loadPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadPage = new LoadPage(this) {
            @Override
            protected int getSuccessLayout() {
                return getLayoutId();
            }
        };
        setContentView(loadPage);
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarOnClickLisenter(this);

        CacheUserManager.getInstance().registerLogin(this);

        ActManager.getActManager().register(this);



        CacheConnectManager.getInstance().registerConnectListener(this);

        initView();
        initPresenter();
        initData();
    }


    @LayoutRes
    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initPresenter();


    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        CacheUserManager.getInstance().unregisterLogin(this);
        ActManager.getActManager().unRegister(this);
        CacheConnectManager.getInstance().unregisterConnectListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            int layoutId = getLayoutId();
//            if (layoutId == 2131427360){
               finish();
//           }else {
               Bundle bundle = new Bundle();
               bundle.putInt("page", 0);
               CommonArouter.getInstance().build(Constants.PATH_MAIN).with(bundle).navigation();
//           }
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    public void onUserChange(LoginBean loginBean) {

    }


    @Override
    public void onClickCenter() {
        finish();
    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickRight() {
        finish();
    }

    @Override
    public void onConect() {

    }

    @Override
    public void onDisConnect() {
        Toast.makeText(this, "网络开了会儿小差", Toast.LENGTH_SHORT).show();
    }
}
