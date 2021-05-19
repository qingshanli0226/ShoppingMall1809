package com.shoppingmall.framework.mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shoppingmall.framework.R;
import com.shoppingmall.framework.custom.LoadingPage;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected T httpPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(loadingPage);
        initView();
        initPresenter();
        initData();

    }

    public LoadingPage loadingPage;
    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void initPresenter();
    public abstract void initData();


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