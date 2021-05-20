package com.example.electricityproject.shopp;

import android.view.View;
import android.widget.Button;

import com.example.common.bean.LogBean;
import com.example.common.call.BusinessARouter;
import com.example.electricityproject.App;
import com.example.electricityproject.AppModel;
import com.example.electricityproject.R;
import com.example.framework.BaseFragment;
import com.example.framework.FrameModel;
import com.example.view.ToolBar;

public class ShoppingFragment extends BaseFragment {


    private ToolBar toolbar;
    private Button goHome;

    @Override
    protected void initData() {
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessARouter.getInstance().getAppManager().OpenMainActivity(getContext(),null);
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        toolbar = (ToolBar) findViewById(R.id.toolbar);
        goHome = (Button) findViewById(R.id.go_home);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void onLoginChange(LogBean isLog) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}