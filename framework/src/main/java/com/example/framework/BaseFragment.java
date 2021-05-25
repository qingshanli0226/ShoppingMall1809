package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.manager.CacheUserManager;

import mvp.presenter.BasePresenter;
import mvp.view.IFragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IFragment,CacheUserManager.IloginChange {
    protected P mPresenter;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return rootView = inflater.inflate(bandLayout(), container, false);
    }

    protected abstract int bandLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
        CacheUserManager.getInstance().unregisterLogin(this);
    }

    @Override
    public void onLoginChange(boolean loginBean) {

    }

    public void destroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
        }
    }
}
