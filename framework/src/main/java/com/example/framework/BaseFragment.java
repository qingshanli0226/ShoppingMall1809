package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.bean.LogBean;
import com.example.manager.BusinessNetManager;
import com.example.manager.BusinessUserManager;
import com.example.view.LoadingPage;
import com.example.view.ToolBar;
import com.umeng.message.PushAgent;


public abstract class BaseFragment <T extends BasePresenter> extends Fragment implements IBaseView, BusinessUserManager.IUserLoginChanged, BusinessNetManager.NetConnectListener{


    protected T httpPresenter;
    protected View mView;
    protected LoadingPage loadingPage;
    protected ToolBar toolBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = loadingPage = new LoadingPage(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        toolBar = mView.findViewById(R.id.toolbar);
        PushAgent.getInstance(getContext()).onAppStart();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
        initData();
        BusinessUserManager.getInstance().Register(this);
        BusinessNetManager.getInstance().RegisterConnect(this);
    }


    public <T extends View> T findViewById(int resId) {
        return mView.findViewById(resId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
        BusinessUserManager.getInstance().UnRegister(this);
        BusinessNetManager.getInstance().UnRegisterConnect(this);
    }

    public void destroy(){
        if (httpPresenter!=null){
            httpPresenter.detachView();
        }

    }

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayoutId();


    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void OnConnect() {

    }

    @Override
    public void DisConnect() {

    }


}
