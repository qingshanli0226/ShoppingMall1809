package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fiannce.bawei.framework.manager.FiannceConnectManager;
import com.fiannce.bawei.framework.view.LoadingPage;

public abstract   class BaseFragment<T extends BasePresenter> extends Fragment implements Ifragment, FiannceConnectManager.IConnectListener {

    protected T httpPresenter;
    protected LoadingPage loadingPage;
    private  View inflate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       inflate =  loadingPage = new LoadingPage(getContext()) {
           @Override
           protected int getSuccessLayoutId() {
               return getLoutId();
           }
       };

        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FiannceConnectManager.getInstance().registerConnectListener(this);
        initView();




        initPresenter();
        initData();


    }

    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (httpPresenter != null){
            httpPresenter.detachView();
        }
        FiannceConnectManager.getInstance().unRegisterConnectListener(this);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return inflate.findViewById(id);
    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }
}
