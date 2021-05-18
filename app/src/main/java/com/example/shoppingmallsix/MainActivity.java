package com.example.shoppingmallsix;

import android.widget.FrameLayout;

import com.example.framework.BaseActivity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private FrameLayout mainFram;
    private CommonTabLayout mainCommon;
    private ArrayList<CustomTabEntity> arrayList = new ArrayList<>();

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mainFram = findViewById(R.id.mainFram);
        mainCommon = findViewById(R.id.mainCommon);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
