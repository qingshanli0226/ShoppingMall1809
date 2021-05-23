package com.shoppingmall.framework.mvp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoppingmall.framework.R;
import com.shoppingmall.framework.custom.LoadingPage;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView{
    protected T httpPresenter;
    public View mView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mView = inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingPage = new LoadingPage(getContext()){
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
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
}