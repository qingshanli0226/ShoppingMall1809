package com.example.electricityproject.welcome;

import android.content.Intent;

import com.example.common.bean.LogBean;
import com.example.electricityproject.R;
import com.example.electricityproject.main.MainActivity;
import com.example.framework.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends BaseActivity {
    private int time;


    @Override
    protected void initData() {
        time=3;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time--;
                if (time<=0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            timer.cancel();
                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                        }
                    });
                }
            }
        },0,1000);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
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