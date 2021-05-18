package com.example.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framework.view.LoadingPage;
import com.example.framework.view.ToolBar;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements ToolBar.IToolbarListener {

    protected T httpPresenter;
    protected View mBaseView;
    protected ToolBar toolBar;
    protected LoadingPage loadingPage;
    protected boolean isUseLoading = true;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        toolBar = mBaseView.findViewById(R.id.toolbar);
        toolBar.setToolbarListener(this);
        initPresenter();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseView = loadingPage = new LoadingPage(getActivity()) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };

        return mBaseView;
    }


    protected abstract void initPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public void destroy() {
        if (httpPresenter != null) {
            httpPresenter.detachView();
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightImgClick() {

    }

    @Override
    public void onRightTvClick() {

    }
}
