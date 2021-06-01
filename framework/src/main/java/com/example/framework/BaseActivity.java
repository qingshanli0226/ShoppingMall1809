package com.example.framework;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.CacheConnectManager;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.ShoppingCartBean;

import java.util.List;

import mvp.presenter.BasePresenter;
import mvp.view.IActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IActivity, CacheUserManager.IloginChange, CacheConnectManager.IConnect,CaCheMannager.IShoppingCartInterface {
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

    //监听登陆状态
    @Override
    public void onLoginChange(boolean loginBean) {

    }

    //加载界面
    @Override
    public void showLoading() {
    }

    //隐藏加载页面
    @Override
    public void hideLoading() {
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg+"", Toast.LENGTH_SHORT).show();
    }

    //网络连接
    @Override
    public void onConnect() {
        loadingPage.showSuccessView();
    }

    //网络无连接
    @Override
    public void onDisConnect() {
        loadingPage.showError("网络无连接");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        CacheUserManager.getInstance().unregisterLogin(this);
    }

    @Override
    public void onShoppinCartgData(List<ShoppingCartBean.ResultBean> shoppingCartBean) {

    }

    @Override
    public void onShoppingCartRemove(int position) {

    }

    @Override
    public void onShoppingCartRemove(List<ShoppingCartBean.ResultBean> shoppingCartBean) {

    }
}
