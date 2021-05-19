package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.manager.ShopManager;
import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements ToolBar.IToolbarListener, ShopManager.IConnectListener {

    protected P httpPresenter;
    private View rootview;
    protected ToolBar toolBar;
    protected LoadingPage loadingPage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         rootview = loadingPage = new LoadingPage(getActivity()) {
            @Override
            protected int getSucessLayout() {
                return getLayoutId();
            }
        };
        return rootview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        toolBar = findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initPresenter();

    protected abstract void initView();

    public <T extends View> T findViewById(@IdRes int id) {
        return rootview.findViewById(id);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter !=null){
            httpPresenter.detachView();
            httpPresenter =null;
        }
    }

    @Override
    public void onLeftImgClick() {

    }

    @Override
    public void onCenterTitleClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTitle() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }
}
