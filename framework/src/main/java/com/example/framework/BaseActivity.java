package com.example.framework;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.CacheUserManager;

import mvp.presenter.BasePresenter;
import mvp.view.IActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IActivity,CacheUserManager.IloginChange{
   protected P rootPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bandLayout());
        initView();
        initPresenter();
        initData();
        CacheUserManager.getInstance().registerLogin(this);


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



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
        if (rootPresenter!=null){
            rootPresenter.destroy();
        }
    }
}
