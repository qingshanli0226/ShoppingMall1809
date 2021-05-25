package com.example.framework;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.CacheUserManager;

import mvp.presenter.BasePresenter;
import mvp.view.IActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IActivity,CacheUserManager.IloginChange{
   protected P mPresenter;
   protected LoadingPage loadingPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         loadingPage = new LoadingPage(this) {

            @Override
            protected int getSuccessLayoutId() {
                return bandLayout();
            }
        };
        setContentView(loadingPage);
        initView();
        initPresenter();
        initData();
        CacheUserManager.getInstance().registerLogin(this);


    }

    protected abstract int bandLayout();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        CacheUserManager.getInstance().unregisterLogin(this);
    }

    @Override
    public void onLoginChange(boolean loginBean) {

    }

    public void destroy(){
        if (mPresenter !=null){
            mPresenter.destroy();
        }
    }
}
