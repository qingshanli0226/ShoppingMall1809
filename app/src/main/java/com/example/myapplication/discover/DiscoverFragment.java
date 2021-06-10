package com.example.myapplication.discover;

import android.content.Intent;
import android.widget.Button;

import com.example.framework.BaseFragment;
import com.example.myapplication.R;
import com.example.myapplication.UmengActivity;
import com.example.myapplication.live.LiveonActivity;
import com.example.myapplication.live.LiveunActivity;
import com.tencent.bugly.crashreport.CrashReport;


public class DiscoverFragment extends BaseFragment {

    private Button errorBtn;
    private Button btn;
    private Button tui;
    private Button la;

    @Override
    public int bandLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView() {
        errorBtn = (Button) findViewById(R.id.errorBtn);
        btn = (Button) findViewById(R.id.btn);
        tui = (Button) findViewById(R.id.tui);
        la = (Button) findViewById(R.id.la);
    }

    @Override
    public void initPresenter() {
        errorBtn.setOnClickListener(v -> CrashReport.testJavaCrash());

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UmengActivity.class);
            startActivity(intent);
        });
        tui.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LiveonActivity.class));
        });
        la.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LiveunActivity.class));
        });
    }

    @Override
    public void initData() {

    }
}