package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.manager.CaCheMannager;
import com.example.framework.manager.CacheUserManager;
import com.example.net.bean.ShoppingCartBean;

import java.util.List;

import mvp.presenter.BasePresenter;
import mvp.view.IFragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IFragment, CacheUserManager.IloginChange,CaCheMannager.IShoppingCartInterface {
    protected P mPresenter;
    protected View rootView;
    protected LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = loadingPage = new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLayoutId() {
                return bandLayout();
            }
        };
        return rootView;
    }

    protected abstract int bandLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CaCheMannager.getInstance().registerIShoppingCart(this);//购物车注册接口
        initView();
        initPresenter();
        initData();
        CacheUserManager.getInstance().registerLogin(this);
    }

    @Override
    public <T extends View> T findViewById(int setId) {
        return rootView.findViewById(setId);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    //加载界面
    @Override
    public void showLoading() {
        loadingPage.showLoadingView();
    }

    //隐藏加载页面
    @Override
    public void hideLoading() {
        loadingPage.showSuccessView();
    }

    //监听登陆状态
    @Override
    public void onLoginChange(boolean loginBean) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
        CacheUserManager.getInstance().unregisterLogin(this);
        CaCheMannager.getInstance().unregisterIShoppingCart(this);
    }

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }


    @Override
    public void onShoppinCartgData(List<ShoppingCartBean.ResultBean> shoppingCartBean) {

    }

    @Override
    public void onShoppingCartAdd() {

    }

    @Override
    public void onShoppingCartSub() {

    }

    @Override
    public void onShoppingCartUpdata() {

    }
}
