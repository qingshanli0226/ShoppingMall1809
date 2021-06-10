package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.common.LogUtil;

import com.example.framework.manager.CacheConnectManager;

import com.example.framework.view.LoadPage;
import com.example.framework.view.ToolBar;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements ToolBar.IToolbarOnClickLisenter, CacheConnectManager.IConnect {
    protected P mPresenter;
    protected View rootView;
    protected ToolBar toolBar;
    protected LoadPage loadPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = loadPage = new LoadPage(getActivity()) {
            @Override
            protected int getSuccessLayout() {
                return getLayoutId();
            }
        };
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarOnClickLisenter(this);

        CacheConnectManager.getInstance().registerConnectListener(this);

        initView();
        initPrensenter();
        initData();
        return rootView;
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initPrensenter();

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }


    //清除
    public void destroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        CacheConnectManager.getInstance().unregisterConnectListener(this);

    }
    @Override
    public void onClickCenter() {
        getActivity().finish();

    }

    @Override
    public void onClickLeft() {
        LogUtil.d("zybonclickleft");
        getActivity().finish();

    }

    @Override
    public void onClickRight() {
        getActivity().finish();
    }

    @Override
    public void onConect() {

    }

    @Override
    public void onDisConnect() {

    }
}
