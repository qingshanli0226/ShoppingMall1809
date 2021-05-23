package com.example.user.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.framework.BaseActivity;
import com.example.framework.view.ToolBar;
import com.example.user.R;

public class UserActivity extends BaseActivity implements ToolBar.OnClickListener {


    private android.widget.LinearLayout userLL;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    public void initView() {
        userLL = (LinearLayout) findViewById(R.id.userLL);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initData() {

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

    @Override
    public void onClick(View v) {

    }
}
