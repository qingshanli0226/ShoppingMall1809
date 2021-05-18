package com.example.framework;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.call.BusinessUserManager;
import com.example.view.LoadingPage;
import com.example.view.ToolBar;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BusinessUserManager.IUserLoginChanged{

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

        BusinessUserManager.getInstance().Register(this);

    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
        BusinessUserManager.getInstance().UnRegister(this);
    }
    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }
    }


}
