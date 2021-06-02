package com.example.framework;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.CacheManager;
import com.example.framework.manager.ShopManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;
import com.umeng.message.PushAgent;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener , ShopManager.IConnectListener {

    protected P httpPresenter;
    protected ToolBar toolBar;
    protected LoadingPage loadingPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loadingPage = new LoadingPage(this) {
            @Override
            protected int getSucessLayout() {
                return getLayoutId();
            }
        });
        initView();
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();

        ShopManager.getInstance().registerConnectListener(this);
        //注册Activity管理
        CacheManager.getInstance().addActivityList(this);

        PushAgent.getInstance(this).onAppStart();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        CacheManager.getInstance().removeActivityList(this);
        ShopManager.getInstance().unregisterConnectListener(this);
        if (httpPresenter != null) {
            httpPresenter.detachView();
            httpPresenter = null;
        }
    }

    @Override
    public void onLeftImgClick() {
        finish();
    }

    @Override
    public void onCenterTitleClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTitle() {

    }

    @Override
    public void onConnected() {
        Toast.makeText(this, "有网", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(this, "没网", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
