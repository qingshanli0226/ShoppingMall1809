package com.example.myapplication.discover;

import android.view.View;
import android.widget.Button;

import com.example.framework.BaseFragment;
import com.example.myapplication.R;
import com.tencent.bugly.crashreport.CrashReport;


public class DiscoverFragment extends BaseFragment {

    private Button errorBtn;

    @Override
    public int bandLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView() {
        errorBtn = (Button) findViewById(R.id.errorBtn);
    }

    @Override
    public void initPresenter() {

        errorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrashReport.testJavaCrash();
            }
        });
    }

    @Override
    public void initData() {

    }
}