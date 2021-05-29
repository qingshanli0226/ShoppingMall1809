package com.example.message;


import android.os.Bundle;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;

public class MessageActivity extends BaseActivity {

    private com.example.framework.view.ToolBar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        toolbar.setToolbarOnClickLisenter(this);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {
        super.onClickLeft();
        CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
    }

    @Override
    public void onClickRight() {

    }
}
