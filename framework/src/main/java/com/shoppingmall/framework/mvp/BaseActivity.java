package com.shoppingmall.framework.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shoppingmall.framework.R;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected T httpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initPresenter();
        initData();
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initPresenter();
    protected abstract void initData();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }
}