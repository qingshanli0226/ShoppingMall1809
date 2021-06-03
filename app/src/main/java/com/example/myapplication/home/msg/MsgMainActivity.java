package com.example.myapplication.home.msg;

import com.example.framework.BaseActivity;
import com.example.myapplication.R;

public class MsgMainActivity extends BaseActivity {

    private androidx.recyclerview.widget.RecyclerView rv;

    @Override
    protected int bandLayout() {
        return R.layout.activity_msg_main;
    }

    @Override
    public void initView() {

        rv = findViewById(R.id.rv);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }

}