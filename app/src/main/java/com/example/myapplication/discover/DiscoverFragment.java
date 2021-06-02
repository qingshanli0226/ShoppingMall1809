package com.example.myapplication.discover;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.framework.BaseFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.UmengActivity;
import com.tencent.bugly.crashreport.CrashReport;


public class DiscoverFragment extends BaseFragment {

    private Button errorBtn;
    private Button btn;

    @Override
    public int bandLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView() {
        errorBtn = (Button) findViewById(R.id.errorBtn);
        btn = (Button) findViewById(R.id.btn);
    }

    @Override
    public void initPresenter() {
        errorBtn.setOnClickListener(v -> CrashReport.testJavaCrash());

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UmengActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void initData() {

    }
}