package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.framework.manager.FiannceConnectManager;
import com.example.framework.view.LoadingPage;

public abstract class BaseActivity<T extends  BasePresenter> extends AppCompatActivity implements FiannceConnectManager.IConnectListener {

    protected T httpPresenter;
    protected boolean isUseLoading = true;
    protected LoadingPage loadingPage;
    protected Toolbar toolbar ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLaoutId());

        FiannceConnectManager.getInstance().registerConnectListener(this);

        loadingPage=  new LoadingPage(this){

            @Override
            protected int getSuccessLayoutId() {
                return getLaoutId();
            }
        };

        setContentView(loadingPage);
        initView();

       // toolbar = findViewById(R.id.toolbar);

        initPresenter();
        initData();



    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract int getLaoutId();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpPresenter != null){
            httpPresenter.detachView();
        }

        FiannceConnectManager.getInstance().unRegisterConnectListener(this);
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }
}
