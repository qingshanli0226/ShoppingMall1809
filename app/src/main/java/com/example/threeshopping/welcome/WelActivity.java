package com.example.threeshopping.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.common.Constants;
import com.example.common.module.CommonArouter;
import com.example.framework.BaseActivity;
import com.example.threeshopping.R;

public class WelActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_wel;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {
        CountDownTimer countDownTimer = new CountDownTimer(1000 * 3, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                CommonArouter.getInstance().build(Constants.PATH_MAIN).navigation();
            }
        };
        countDownTimer.start();

    }

    @Override
    public void onClickCenter() {

    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickRight() {

    }
}