package com.example.myapplication.personalCenter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.framework.BaseFragment;
import com.example.myapplication.R;
import com.example.myapplication.findforpay.FindPayMainActivity;
import com.example.myapplication.findforsend.FindsendMainActivity;


public class PersonalCenterFragment extends BaseFragment {

    private ImageView forpay;
    private ImageView forsend;

    @Override
    protected int bandLayout() {
        return R.layout.fragment_personalcenter;
    }

    @Override
    public void initView() {

        forpay = (ImageView) findViewById(R.id.forpay);
        forsend = (ImageView) findViewById(R.id.forsend);
        forpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FindPayMainActivity.class);
                startActivity(intent);
            }
        });
        forsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), FindsendMainActivity.class);
                startActivity(intent1);
            }
        });
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

    }
}