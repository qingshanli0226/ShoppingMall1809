package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.NetworkConnectionsManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener, NetworkConnectionsManager.IConnectListener {

    protected T httpPresenter;
    protected boolean isUseLoading = true;
    protected LoadingPage loadingPage;
    protected ToolBar toolBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        NetworkConnectionsManager.getInstance().registerConnectListener(this);
        initView();
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
        initListener();
    }

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    protected  void initListener(){
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter != null) {
            httpPresenter.detachView();
        }
        NetworkConnectionsManager.getInstance().unRegisterConnectListener(this);
    }

    @Override
    public void onLeftClick() {
        finish();
    }
    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }
}
