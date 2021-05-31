package com.example.framework;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.manager.BusinessNetManager;
import com.example.view.LoadingPage;
import com.example.view.ToolBar;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView, ToolBar.IToolbarListener, BusinessNetManager.NetConnectListener{

    protected T httpPresenter;
    protected ToolBar toolBar;
    protected LoadingPage loadingPage;

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
        initPresenter();
        initData();
        toolBar.setToolbarListener(this);
        BusinessNetManager.getInstance().RegisterConnect(this);
    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        BusinessNetManager.getInstance().UnRegisterConnect(this);
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }


    public void onLeftClick() {
        finish();
        destroy();
    }


    public void onRightImgClick() {

    }


    public void onRightTvClick() {

    }

    @Override
    public void OnConnect() {

    }

    @Override
    public void DisConnect() {

    }

}
