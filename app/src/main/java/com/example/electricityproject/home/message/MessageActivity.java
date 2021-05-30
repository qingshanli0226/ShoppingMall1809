package com.example.electricityproject.home.message;

import com.example.common.db.DaoMaster;
import com.example.electricityproject.R;
import com.example.electricityproject.db.MessageManger;
import com.example.framework.BaseActivity;

public class MessageActivity extends BaseActivity {

    private MessageAdapter adapter;

    private DaoMaster daoMaster;

    @Override
    protected void initData() {

        daoMaster = MessageManger.getInstance().getDaoMaster();


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_messafe;
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