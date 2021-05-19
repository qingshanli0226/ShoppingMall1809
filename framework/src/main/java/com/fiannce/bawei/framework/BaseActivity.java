package com.fiannce.bawei.framework;

import android.accessibilityservice.FingerprintGestureController;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.fiannce.bawei.framework.manager.FiannceConnectManager;
import com.fiannce.bawei.framework.view.LoadingPage;
import com.fiannce.bawei.framework.view.ToolBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements ToolBar.IToolbarListener,FiannceConnectManager.IConnectListener {

    protected T httpPresenter;
    protected ToolBar toolBar;
    protected boolean isUseLoading = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();

        //页面启动时，注册网络回调接口
        FiannceConnectManager.getInstance().registerConnectListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    protected LoadingPage loadingPage;

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        //页面销毁时，将回调接口注销，避免内存泄漏问题
        FiannceConnectManager.getInstance().unRegisterConnectListener(this);
    }

    public void destroy() {
        if (httpPresenter!=null) {
            //httpPresenter.detachView();
        }
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

    //在基类里注册回调接口，方法是空实现，子类需要通知的话，就重写一下即可
    @Override
    public void onConnected() {
    }

    @Override
    public void onDisconnected() {
    }
}
