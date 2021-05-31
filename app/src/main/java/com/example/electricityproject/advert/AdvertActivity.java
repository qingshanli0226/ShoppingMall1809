package com.example.electricityproject.advert;

import com.example.electricityproject.R;
import com.example.framework.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AdvertActivity extends BaseActivity {


    @Override
    protected void initData() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int time = 0;
            @Override
            public void run() {
                time++;
                if (time==3){
                    timer.cancel();
                    finish();
                }
            }
        },1000,1000);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advert;
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